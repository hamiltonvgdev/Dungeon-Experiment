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
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/ARB/indirect_parameters.txt">ARB_indirect_parameters</a> extension.
 * 
 * <p>OpenGL 4.3 (with the introduction of the {@link ARBMultiDrawIndirect ARB_multi_draw_indirect} extension) enhanced the ability of OpenGL to allow a large sets of parameters
 * for indirect draws (introduced with OpenGL 4.0) into a buffer object and dispatch the entire list with one API call. This allows, for example, a shader
 * (such as a compute shader via shader storage buffers, or a geometry shader via transform feedback) to produce lists of draw commands that can then be
 * consumed by OpenGL without a server-client round trip. However, when a variable and potentially unknown number of draws are produced by such a shader,
 * it becomes difficult to know how many draws are in the output array(s). Applications must resort to techniques such as transform feedback primitive
 * queries, or mapping buffers containing the content of atomic counters, which can cause stalls or bubbles in the OpenGL pipeline.</p>
 * 
 * <p>This extension introduces the concept of the "parameter buffer", which is a target allowing buffers to store parameters for certain drawing commands.
 * Also in this extension, new variants of {@link GL43#glMultiDrawArraysIndirect MultiDrawArraysIndirect} and {@link GL43#glMultiDrawElementsIndirect MultiDrawElementsIndirect} are introduced that source some of their
 * parameters from this buffer. Further commands could potentially be introduced that source other parameters from a buffer.</p>
 * 
 * <p>Requires {@link GL42 OpenGL 4.2}.</p>
 */
public class ARBIndirectParameters {

	/**
	 * Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, GetBufferPointerv,
	 * MapBufferRange, FlushMappedBufferRange, GetBufferParameteriv, and CopyBufferSubData.
	 */
	public static final int GL_PARAMETER_BUFFER_ARB = 0x80EE;

	/** Accepted by the {@code value} parameter of GetIntegerv, GetBooleanv, GetFloatv, and GetDoublev. */
	public static final int GL_PARAMETER_BUFFER_BINDING_ARB = 0x80EF;

	protected ARBIndirectParameters() {
		throw new UnsupportedOperationException();
	}

	static boolean isAvailable(GLCapabilities caps) {
		return checkFunctions(
			caps.glMultiDrawArraysIndirectCountARB, caps.glMultiDrawElementsIndirectCountARB
		);
	}

	// --- [ glMultiDrawArraysIndirectCountARB ] ---

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawArraysIndirect MultiDrawArraysIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indirect     an array of structures containing the draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void nglMultiDrawArraysIndirectCountARB(int mode, long indirect, long drawcount, int maxdrawcount, int stride) {
		long __functionAddress = GL.getCapabilities().glMultiDrawArraysIndirectCountARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callIPPIIV(__functionAddress, mode, indirect, drawcount, maxdrawcount, stride);
	}

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawArraysIndirect MultiDrawArraysIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indirect     an array of structures containing the draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void glMultiDrawArraysIndirectCountARB(int mode, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS ) {
			checkBuffer(indirect, maxdrawcount * (stride == 0 ? (4 * 4) : stride));
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, false);
		}
		nglMultiDrawArraysIndirectCountARB(mode, memAddress(indirect), drawcount, maxdrawcount, stride);
	}

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawArraysIndirect MultiDrawArraysIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param indirect     an array of structures containing the draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void glMultiDrawArraysIndirectCountARB(int mode, long indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS )
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, true);
		nglMultiDrawArraysIndirectCountARB(mode, indirect, drawcount, maxdrawcount, stride);
	}

	/** IntBuffer version of: {@link #glMultiDrawArraysIndirectCountARB MultiDrawArraysIndirectCountARB} */
	public static void glMultiDrawArraysIndirectCountARB(int mode, IntBuffer indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS ) {
			checkBuffer(indirect, (maxdrawcount * (stride == 0 ? (4 * 4) : stride)) >> 2);
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, false);
		}
		nglMultiDrawArraysIndirectCountARB(mode, memAddress(indirect), drawcount, maxdrawcount, stride);
	}

	// --- [ glMultiDrawElementsIndirectCountARB ] ---

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawElementsIndirect MultiDrawElementsIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param type         the type of data in the buffer bound to the GL_ELEMENT_ARRAY_BUFFER binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 * @param indirect     a structure containing an array of draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void nglMultiDrawElementsIndirectCountARB(int mode, int type, long indirect, long drawcount, int maxdrawcount, int stride) {
		long __functionAddress = GL.getCapabilities().glMultiDrawElementsIndirectCountARB;
		if ( CHECKS )
			checkFunctionAddress(__functionAddress);
		callIIPPIIV(__functionAddress, mode, type, indirect, drawcount, maxdrawcount, stride);
	}

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawElementsIndirect MultiDrawElementsIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param type         the type of data in the buffer bound to the GL_ELEMENT_ARRAY_BUFFER binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 * @param indirect     a structure containing an array of draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void glMultiDrawElementsIndirectCountARB(int mode, int type, ByteBuffer indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS ) {
			checkBuffer(indirect, maxdrawcount * (stride == 0 ? (5 * 4) : stride));
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, false);
		}
		nglMultiDrawElementsIndirectCountARB(mode, type, memAddress(indirect), drawcount, maxdrawcount, stride);
	}

	/**
	 * Behaves similarly to {@link GL43#glMultiDrawElementsIndirect MultiDrawElementsIndirect}, except that {@code drawcount} defines an offset (in bytes) into the buffer object bound to the
	 * {@link #GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB} binding point at which a single {@code sizei} typed value is stored, which contains the draw count. {@code maxdrawcount} specifies
	 * the maximum number of draws that are expected to be stored in the buffer. If the value stored at {@code drawcount} into the buffer is greater than
	 * {@code maxdrawcount}, an implementation stop processing draws after {@code maxdrawcount} parameter sets. {@code drawcount} must be a multiple of four.
	 *
	 * @param mode         what kind of primitives to render. One of:<br><table><tr><td>{@link GL11#GL_POINTS POINTS}</td><td>{@link GL11#GL_LINE_STRIP LINE_STRIP}</td><td>{@link GL11#GL_LINE_LOOP LINE_LOOP}</td><td>{@link GL11#GL_LINES LINES}</td><td>{@link GL11#GL_POLYGON POLYGON}</td><td>{@link GL11#GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link GL11#GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link GL11#GL_TRIANGLES TRIANGLES}</td><td>{@link GL11#GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link GL11#GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
	 * @param type         the type of data in the buffer bound to the GL_ELEMENT_ARRAY_BUFFER binding. One of:<br><table><tr><td>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
	 * @param indirect     a structure containing an array of draw parameters
	 * @param drawcount    the offset into the parameter buffer object
	 * @param maxdrawcount the maximum number of draws
	 * @param stride       the distance in basic machine units between elements of the draw parameter array
	 */
	public static void glMultiDrawElementsIndirectCountARB(int mode, int type, long indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS )
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, true);
		nglMultiDrawElementsIndirectCountARB(mode, type, indirect, drawcount, maxdrawcount, stride);
	}

	/** IntBuffer version of: {@link #glMultiDrawElementsIndirectCountARB MultiDrawElementsIndirectCountARB} */
	public static void glMultiDrawElementsIndirectCountARB(int mode, int type, IntBuffer indirect, long drawcount, int maxdrawcount, int stride) {
		if ( CHECKS ) {
			checkBuffer(indirect, (maxdrawcount * (stride == 0 ? (5 * 4) : stride)) >> 2);
			GLChecks.ensureBufferObject(GL40.GL_DRAW_INDIRECT_BUFFER_BINDING, false);
		}
		nglMultiDrawElementsIndirectCountARB(mode, type, memAddress(indirect), drawcount, maxdrawcount, stride);
	}

}