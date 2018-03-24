package com.deaddorks.engine.buffers;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class IBO
{
	
	private int id;
	private int elementCount;
	
	public IBO(final int[] indices)
	{
		id = glGenBuffers();
		elementCount = indices.length;
		IntBuffer buffer = arrayToBuffer(indices);
		bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
	
	public void bind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
	}
	
	public static void unbind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private IntBuffer arrayToBuffer(final int[] vals)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(vals.length);
		buffer.put(vals);
		buffer.flip();
		return buffer;
	}
	
	public int getId()
	{
		return id;
	}
	public int getElementCount()
	{
		return elementCount;
	}
	
	public void destroy()
	{
		unbind();
		glDeleteBuffers(id);
	}
	
}
