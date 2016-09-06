/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.egl;

import org.lwjgl.system.*;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static java.lang.Math.*;
import static org.lwjgl.egl.EGL10.*;
import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * This class must be used before any EGL function is called. It has the following responsibilities:
 * <ul>
 * <li>Loads the EGL native library into the JVM process.</li>
 * <li>Creates instances of {@link EGLCapabilities} classes. An {@code EGLCapabilities} instance contains flags for functionality that is available in an
 * EGLDisplay or the EGL client library. Internally, it also contains function pointers that are only valid in that specific EGLDisplay or client library.</li>
 *
 * <h3>Library lifecycle</h3>
 * <p>The EGL library is loaded automatically when this class is initialized. Set the {@link Configuration#EGL_EXPLICIT_INIT} option to override this behavior.
 * Manual loading/unloading can be achieved with the {@link #create} and {@link #destroy} functions. The name of the library loaded can be overridden with the
 * {@link Configuration#EGL_LIBRARY_NAME} option.</p>
 *
 * <h3>EGLCapabilities creation</h3>
 * <p>Instances of {@code EGLCapabilities} for an EGLDisplay can be created with the {@link #createDisplayCapabilities} method. Calling this method is
 * expensive, so the {@code EGLCapabilities} instance should be associated with the EGLDisplay and reused as necessary.</p>
 *
 * <p>The {@code EGLCapabilities} instance for the client library is created automatically when the EGL native library is loaded.</p>
 */
public final class EGL {

	private static FunctionProvider functionProvider;

	private static EGLCapabilities caps;

	static {
		if ( !Configuration.EGL_EXPLICIT_INIT.get(false) )
			create();
	}

	private EGL() {}

	/** Loads the EGL native library, using the default library name. */
	public static void create() {
		SharedLibrary EGL;
		switch ( Platform.get() ) {
			case LINUX:
				EGL = Library.loadNative(Configuration.EGL_LIBRARY_NAME, "libEGL.so.1");
				break;
			case MACOSX:
				EGL = Library.loadNative(Configuration.EGL_LIBRARY_NAME, "EGL");
				break;
			case WINDOWS:
				EGL = Library.loadNative(Configuration.EGL_LIBRARY_NAME, "libEGL", "EGL");
				break;
			default:
				throw new IllegalStateException();
		}
		create(EGL);
	}

	/**
	 * Loads the EGL native library, using the specified library name.
	 *
	 * @param libName the native library name
	 */
	public static void create(String libName) {
		create(Library.loadNative(libName));
	}

	private static void create(SharedLibrary EGL) {
		try {
			FunctionProvider functionProvider = new FunctionProvider() {
				private final long eglGetProcAddress = EGL.getFunctionAddress("eglGetProcAddress");

				{
					if ( eglGetProcAddress == NULL ) {
						EGL.free();
						throw new EGLException("A core EGL function is missing. Make sure that EGL is available.");
					}
				}

				@Override
				public long getFunctionAddress(ByteBuffer functionName) {
					long address = invokePP(eglGetProcAddress, memAddress(functionName));
					if ( address == NULL ) {
						address = EGL.getFunctionAddress(functionName);
						if ( address == NULL && Checks.DEBUG_FUNCTIONS )
							apiLog("Failed to locate address for EGL function " + memASCII(functionName));
					}

					return address;
				}

				@Override
				public void free() {
					EGL.free();
				}
			};
			create(functionProvider);
		} catch (RuntimeException e) {
			EGL.free();
			throw e;
		}
	}

	/**
	 * Initializes EGL with the specified {@link FunctionProvider}. This method can be used to implement custom EGL library loading.
	 *
	 * @param functionProvider the provider of EGL function addresses
	 */
	public static void create(FunctionProvider functionProvider) {
		if ( EGL.functionProvider != null )
			throw new IllegalStateException("EGL has already been created.");

		EGL.functionProvider = functionProvider;

		caps = createClientCapabilities();
	}

	/** Unloads the EGL native library. */
	public static void destroy() {
		if ( functionProvider == null )
			return;

		caps = null;

		functionProvider.free();
		functionProvider = null;
	}

	/** Returns the {@link FunctionProvider} for the EGL native library. */
	public static FunctionProvider getFunctionProvider() {
		return functionProvider;
	}

	/**
	 * Returns the {@link EGLCapabilities} instance for the EGL client library.
	 *
	 * The capability flags in this instance are only set for the core EGL versions and client extensions. This may only happen if EGL 1.5 or the
	 * {@link EGLCapabilities#EGL_EXT_client_extensions} extension are supported. If not, all flags will be false and the version fields zero.
	 */
	public static EGLCapabilities getCapabilities() {
		return caps;
	}

	private static EGLCapabilities createClientCapabilities() {
		long QueryString = functionProvider.getFunctionAddress("eglQueryString");
		long versionString = invokePIP(QueryString, EGL_NO_DISPLAY, EGL_VERSION);

		Set<String> ext = new HashSet<>(32);

		if ( versionString == NULL )
			invokeI(functionProvider.getFunctionAddress("eglGetError")); // clear error
		else {
			APIVersion version = apiParseVersion(memASCII(versionString), "EGL");

			addEGLVersions(version.major, version.minor, ext);
			addExtensions(memASCII(invokePIP(QueryString, EGL_NO_DISPLAY, EGL_EXTENSIONS)), ext);
		}

		return new EGLCapabilities(functionProvider, ext);
	}

	/**
	 * Creates an {@link EGLCapabilities} instance for the specified EGLDisplay handle.
	 *
	 * <p>This method call is relatively expensive. The result should be cached and reused.</p>
	 *
	 * @param dpy          the EGLDisplay to query
	 * @param majorVersion the major EGL version supported by the EGLDisplay, as returned by {@link EGL10#eglInitialize}
	 * @param minorVersion the minor EGL version supported by the EGLDisplay, as returned by {@link EGL10#eglInitialize}
	 *
	 * @return the {@link EGLCapabilities instance}
	 */
	public static EGLCapabilities createDisplayCapabilities(long dpy, int majorVersion, int minorVersion) {
		Set<String> supportedExtensions = new HashSet<>(32);

		// Add EGL versions
		addEGLVersions(majorVersion, minorVersion, supportedExtensions);
		// Parse display EGL_EXTENSIONS string
		addExtensions(eglQueryString(dpy, EGL_EXTENSIONS), supportedExtensions);

		return new EGLCapabilities(functionProvider, supportedExtensions);
	}

	private static void addEGLVersions(int MAJOR, int MINOR, Set<String> supportedExtensions) {
		int[][] versions = new int[][] {
			{ 0, 1, 2, 3, 4, 5 } // 10, 11, 12, 13, 14, 15
		};

		for ( int major = 1; major <= min(MAJOR, versions.length); major++ ) {
			for ( int minor : versions[major - 1] ) {
				if ( major == MAJOR && MINOR < minor )
					break;

				supportedExtensions.add(String.format("EGL%d%d%s", major, minor, ""));
			}
		}
	}

	private static void addExtensions(String extensionsString, Set<String> supportedExtensions) {
		StringTokenizer tokenizer = new StringTokenizer(extensionsString);
		while ( tokenizer.hasMoreTokens() )
			supportedExtensions.add(tokenizer.nextToken());
	}

	static boolean checkExtension(String extension, boolean supported) {
		if ( supported )
			return true;

		apiLog("[EGL] " + extension + " was reported as available but an entry point is missing.");
		return false;
	}

}