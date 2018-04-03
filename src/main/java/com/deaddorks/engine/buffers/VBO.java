package com.deaddorks.engine.buffers;

import com.deaddorks.engine.utils.Buffers;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VBO
{

	private int id;

	public VBO(final float... vertices)
	{
		id = glGenBuffers();
		FloatBuffer buffer = Buffers.genFloatBuffer(vertices);
		bind();
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
	
	public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, id);
	}
	
	public static void unbind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void destroy()
	{
		unbind();
		glDeleteBuffers(id);
	}
	
	
}
