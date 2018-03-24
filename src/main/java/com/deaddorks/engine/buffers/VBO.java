package com.deaddorks.engine.buffers;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VBO
{

	private int id;

	public VBO(final float[] vertices)
	{
		id = glGenBuffers();
		bind();
		FloatBuffer buffer = arrayToBuffer(vertices);
		glBufferData(id, buffer, GL_STATIC_DRAW);
	}
	
	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, id);
	}
	
	public void unbind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void destroy()
	{
		glDeleteBuffers(id);
	}
	
	private FloatBuffer arrayToBuffer(final float[] vals)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vals.length);
		buffer.put(vals);
		buffer.flip();
		return buffer;
	}
	
}
