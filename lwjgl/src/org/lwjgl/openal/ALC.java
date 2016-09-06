/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.openal;

import org.lwjgl.system.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * This class must be used before any OpenAL function is called. It has the following responsibilities:
 * <ul>
 * <li>Loads the OpenAL native library into the JVM process.</li>
 * <li>Creates instances of {@link ALCCapabilities} classes. An {@code ALCCapabilities} instance contains flags for functionality that is available for an
 * OpenAL device. Internally, it also contains function pointers that are only valid for that specific OpenAL device.</li>
 * </ul>
 *
 * <h3>Library lifecycle</h3>
 * <p>The OpenAL library is loaded automatically when this class is initialized. Set the {@link Configuration#OPENAL_EXPLICIT_INIT} option to override this
 * behavior. Manual loading/unloading can be achieved with the {@link #create} and {@link #destroy} functions. The name of the library loaded can be overridden
 * with the {@link Configuration#OPENAL_LIBRARY_NAME} option.</p>
 *
 * <h3>ALCCapabilities creation</h3>
 * <p>Instances of {@code ALCCapabilities} can be created with the {@link #createCapabilities} method. Calling this method is expensive, so
 * {@code ALCCapabilities} instances should be cached in user code.</p>
 *
 * @see AL
 */
public final class ALC {

	private static FunctionProviderLocal functionProvider;

	private static ALCCapabilities icd;

	static {
		if ( !Configuration.OPENAL_EXPLICIT_INIT.get(false) )
			create();
	}

	private ALC() {}

	/** Loads the OpenAL native library, using the default library name. */
	public static void create() {
		String libName;
		switch ( Platform.get() ) {
			case LINUX:
			case MACOSX:
				libName = "openal";
				break;
			case WINDOWS:
				libName = "OpenAL";
				break;
			default:
				throw new IllegalStateException();
		}

		create(Configuration.OPENAL_LIBRARY_NAME.get(Pointer.BITS64 ? libName : libName + "32"));
	}

	/**
	 * Loads the OpenAL native library, using the specified library name.
	 *
	 * @param libName the native library name
	 */
	public static void create(String libName) {
		SharedLibrary OPENAL = Library.loadNative(libName);

		try {
			FunctionProviderLocal functionProvider = new FunctionProviderLocal() {

				private final long alcGetProcAddress = getFunctionAddress("alcGetProcAddress");

				{
					if ( alcGetProcAddress == NULL ) {
						OPENAL.free();
						throw new RuntimeException("A core ALC function is missing. Make sure that the OpenAL library has been loaded correctly.");
					}
				}

				@Override
				public long getFunctionAddress(ByteBuffer functionName) {
					long address = OPENAL.getFunctionAddress(functionName);
					if ( address == NULL && Checks.DEBUG_FUNCTIONS )
						apiLog("Failed to locate address for ALC core function " + memASCII(functionName));
					return address;
				}

				@Override
				public long getFunctionAddress(long handle, ByteBuffer functionName) {
					long address = invokePPP(alcGetProcAddress, handle, memAddress(functionName));
					if ( address == NULL && Checks.DEBUG_FUNCTIONS )
						apiLog("Failed to locate address for ALC extension function " + memASCII(functionName));
					return address;
				}

				@Override
				public void free() {
					OPENAL.free();
				}
			};
			create(functionProvider);
		} catch (RuntimeException e) {
			OPENAL.free();
			throw e;
		}
	}

	/**
	 * Initializes ALC with the specified {@link FunctionProviderLocal}. This method can be used to implement custom ALC library loading.
	 *
	 * @param functionProvider the provider of ALC function addresses
	 */
	public static void create(FunctionProviderLocal functionProvider) {
		if ( ALC.functionProvider != null )
			throw new IllegalStateException("ALC has already been created.");

		ALC.functionProvider = functionProvider;

		icd = new ALCCapabilities(functionProvider, NULL, Collections.emptySet());

		AL.init();
	}

	/** Unloads the OpenAL native library. */
	public static void destroy() {
		if ( functionProvider == null )
			return;

		AL.destroy();

		icd = null;

		functionProvider.free();
		functionProvider = null;
	}

	/** Returns the {@link FunctionProviderLocal} for the OpenAL native library. */
	public static FunctionProviderLocal getFunctionProvider() {
		return functionProvider;
	}

	/** Returns the {@link ALCCapabilities} of the OpenAL implementation. */
	static ALCCapabilities getICD() {
		return icd;
	}

	/**
	 * Creates a new {@link ALCCapabilities} instance for the specified OpenAL device.
	 *
	 * @return the {@code ALCCapabilities} instance
	 */
	public static ALCCapabilities createCapabilities(long device) {
		// We don't have an ALCCapabilities instance when this method is called
		// so we have to use the native bindings directly.
		long GetIntegerv = functionProvider.getFunctionAddress("alcGetIntegerv");
		long GetString = functionProvider.getFunctionAddress("alcGetString");
		long IsExtensionPresent = functionProvider.getFunctionAddress("alcIsExtensionPresent");

		if ( GetIntegerv == NULL || GetString == NULL || IsExtensionPresent == NULL )
			throw new IllegalStateException("Core ALC functions could not be found. Make sure that OpenAL has been loaded.");

		int majorVersion;
		int minorVersion;

		MemoryStack stack = stackPush();
		try {
			IntBuffer version = stack.mallocInt(1);

			invokePIIPV(GetIntegerv, device, ALC_MAJOR_VERSION, 1, memAddress(version));
			majorVersion = version.get(0);

			invokePIIPV(GetIntegerv, device, ALC_MINOR_VERSION, 1, memAddress(version));
			minorVersion = version.get(0);
		} finally {
			stack.pop();
		}

		int[][] ALC_VERSIONS = {
			{ 0, 1 },  // ALC 1
		};

		Set<String> supportedExtensions = new HashSet<>(16);

		for ( int major = 1; major <= ALC_VERSIONS.length; major++ ) {
			int[] minors = ALC_VERSIONS[major - 1];
			for ( int minor : minors ) {
				if ( major < majorVersion || (major == majorVersion && minor <= minorVersion) )
					supportedExtensions.add("OpenALC" + Integer.toString(major) + Integer.toString(minor));
			}
		}

		// Parse EXTENSIONS string
		String extensionsString = memUTF8(checkPointer(invokePIP(GetString, device, ALC_EXTENSIONS)));

		StringTokenizer tokenizer = new StringTokenizer(extensionsString);
		while ( tokenizer.hasMoreTokens() ) {
			String extName = tokenizer.nextToken();
			stack.push();
			try {
				if ( invokePPZ(IsExtensionPresent, device, memAddress(stack.ASCII(extName, true))) )
					supportedExtensions.add(extName);
			} finally {
				stack.pop();
			}
		}

		return new ALCCapabilities(getFunctionProvider(), device, supportedExtensions);
	}

	static boolean checkExtension(String extension, boolean supported) {
		if ( supported )
			return true;

		apiLog("[ALC] " + extension + " was reported as available but an entry point is missing.");
		return false;
	}

}