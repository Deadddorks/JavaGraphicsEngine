package com.deaddorks.engine.utils;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffers
{
	
	private Buffers() {}
	
	public static IntBuffer genIntBuffer(final int... vals)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(vals.length);
		buffer.put(vals);
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer genFloatBuffer(final float... vals)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vals.length);
		buffer.put(vals);
		buffer.flip();
		return buffer;
	}
	
}
