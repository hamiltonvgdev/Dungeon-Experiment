/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.system;

import java.nio.ByteBuffer;

import static org.lwjgl.system.MemoryStack.*;

/** A platform/device/context specific provider of native function addresses. */
public interface FunctionProviderLocal extends FunctionProvider {

	/** {@link CharSequence} version of {@link #getFunctionAddress(long, ByteBuffer)}. */
	default long getFunctionAddress(long handle, CharSequence functionName) {
		MemoryStack stack = stackPush();
		try {
			return getFunctionAddress(handle, stack.ASCII(functionName));
		} finally {
			stack.pop();
		}
	}

	/**
	 * Returns the function address of the specified function for the specified {@code handle}. If the function is not supported, returns 0L.
	 *
	 * @param handle       the handle to a platform/device/context
	 * @param functionName the encoded name of the function to query
	 *
	 * @return the function address or 0L if the function is not supported
	 */
	long getFunctionAddress(long handle, ByteBuffer functionName);

}