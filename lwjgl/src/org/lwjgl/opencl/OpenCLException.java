/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.opencl;

/** A runtime exception thrown by LWJGL when it encounters an OpenCL error. */
public class OpenCLException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OpenCLException() {
		super();
	}

	public OpenCLException(String message) {
		super(message);
	}

	public OpenCLException(String message, Throwable cause) {
		super(message, cause);
	}

	public OpenCLException(Throwable cause) {
		super(cause);
	}

}