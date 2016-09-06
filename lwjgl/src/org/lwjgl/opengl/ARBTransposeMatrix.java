/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

import java.nio.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/ARB/transpose_matrix.txt">ARB_transpose_matrix</a> extension.
 * 
 * <p>New functions and tokens are added allowing application matrices stored in row major order rather than column major order to be transferred to the
 * OpenGL implementation. This allows an application to use standard C-language 2-dimensional arrays ({@code m[row][col]}) and have the array indices match the
 * expected matrix row and column indexes. These arrays are referred to as transpose matrices since they are the transpose of the standard matrices passed
 * to OpenGL.</p>
 * 
 * <p>This extension adds an interface for transfering data to and from the OpenGL pipeline, it does not change any OpenGL processing or imply any changes in
 * state representation.</p>
 * 
 * <p>Promoted to core in {@link GL13 OpenGL 1.3}.</p>
 */
public class ARBTransposeMatrix {

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_TRANSPOSE_MODELVIEW_MATRIX_ARB  = 0x84E3,
		GL_TRANSPOSE_PROJECTION_MATRIX_ARB = 0x84E4,
		GL_TRANSPOSE_TEXTURE_MATRIX_ARB    = 0x84E5,
		GL_TRANSPOSE_COLOR_MATRIX_ARB      = 0x84E6;

	protected ARBTransposeMatrix() {
		throw new UnsupportedOperationException();
	}

	static boolean isAvailable(GLCapabilities caps) {
		return checkFunctions(
			caps.glLoadTransposeMatrixfARB, caps.glLoadTransposeMatrixdARB, caps.glMultTransposeMatrixfARB, caps.glMultTransposeMatrixdARB
		);
	}

	// --- [ glLoadTransposeMatrixfARB ] ---

	/**
	 * Sets the current matrix to a 4 &times; 4 matrix in row-major order.
	 * 
	 * <p>The matrix is stored as 16 consecutive values, i.e. as:</p>
	 * 
	 * <table class="lwjgl matrix">
	 * <tr><td>a1</td><td>a2</td><td>a3</td><td>a4</td></tr>
	 * <tr><td>a5</td><td>a6</td><td>a7</td><td>a8</td></tr>
	 * <tr><td>a9</td><td>a10</td><td>a11</td><td>a12</td></tr>
	 * <tr><td>a13</td><td>a14</td><td>a15</td><td>a16</td></tr>
	 * </table>
	 *
	 * @param m the matrix data
	 */
	public static void nglLoadTransposeMatrixfARB(long m) {
		long __functionAddress = GL.getCapabilities().glLoadTransposeMatrixfARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callPV(__functionAddress, m);
	}

	/**
	 * Sets the current matrix to a 4 &times; 4 matrix in row-major order.
	 * 
	 * <p>The matrix is stored as 16 consecutive values, i.e. as:</p>
	 * 
	 * <table class="lwjgl matrix">
	 * <tr><td>a1</td><td>a2</td><td>a3</td><td>a4</td></tr>
	 * <tr><td>a5</td><td>a6</td><td>a7</td><td>a8</td></tr>
	 * <tr><td>a9</td><td>a10</td><td>a11</td><td>a12</td></tr>
	 * <tr><td>a13</td><td>a14</td><td>a15</td><td>a16</td></tr>
	 * </table>
	 *
	 * @param m the matrix data
	 */
	public static void glLoadTransposeMatrixfARB(FloatBuffer m) {
		if ( CHECKS )
			checkBuffer(m, 16);
		nglLoadTransposeMatrixfARB(memAddress(m));
	}

	// --- [ glLoadTransposeMatrixdARB ] ---

	/**
	 * Double version of {@link #glLoadTransposeMatrixfARB LoadTransposeMatrixfARB}.
	 *
	 * @param m the matrix data
	 */
	public static void nglLoadTransposeMatrixdARB(long m) {
		long __functionAddress = GL.getCapabilities().glLoadTransposeMatrixdARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callPV(__functionAddress, m);
	}

	/**
	 * Double version of {@link #glLoadTransposeMatrixfARB LoadTransposeMatrixfARB}.
	 *
	 * @param m the matrix data
	 */
	public static void glLoadTransposeMatrixdARB(DoubleBuffer m) {
		if ( CHECKS )
			checkBuffer(m, 16);
		nglLoadTransposeMatrixdARB(memAddress(m));
	}

	// --- [ glMultTransposeMatrixfARB ] ---

	/**
	 * Multiplies the current matrix with a 4 &times; 4 matrix in row-major order. See {@link #glLoadTransposeMatrixfARB LoadTransposeMatrixfARB} for details.
	 *
	 * @param m the matrix data
	 */
	public static void nglMultTransposeMatrixfARB(long m) {
		long __functionAddress = GL.getCapabilities().glMultTransposeMatrixfARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callPV(__functionAddress, m);
	}

	/**
	 * Multiplies the current matrix with a 4 &times; 4 matrix in row-major order. See {@link #glLoadTransposeMatrixfARB LoadTransposeMatrixfARB} for details.
	 *
	 * @param m the matrix data
	 */
	public static void glMultTransposeMatrixfARB(FloatBuffer m) {
		if ( CHECKS )
			checkBuffer(m, 16);
		nglMultTransposeMatrixfARB(memAddress(m));
	}

	// --- [ glMultTransposeMatrixdARB ] ---

	/**
	 * Double version of {@link #glMultTransposeMatrixfARB MultTransposeMatrixfARB}.
	 *
	 * @param m the matrix data
	 */
	public static void nglMultTransposeMatrixdARB(long m) {
		long __functionAddress = GL.getCapabilities().glMultTransposeMatrixdARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callPV(__functionAddress, m);
	}

	/**
	 * Double version of {@link #glMultTransposeMatrixfARB MultTransposeMatrixfARB}.
	 *
	 * @param m the matrix data
	 */
	public static void glMultTransposeMatrixdARB(DoubleBuffer m) {
		if ( CHECKS )
			checkBuffer(m, 16);
		nglMultTransposeMatrixdARB(memAddress(m));
	}

}