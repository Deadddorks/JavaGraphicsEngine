package com.deaddorks.engine.buffers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.glfw.GLFW.*;

public class VAO
{
	
	private int id;
	
	public VAO()
	{
		id = glGenVertexArrays();
	}
	
	public void bind()
	{
		glBindVertexArray(id);
	}
	
	public void unbind()
	{
		glBindVertexArray(0);
	}
	
	public void enableVertexAttribute(int index, int components, int glType, int vbo)
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glEnableVertexAttribArray(index);
		glVertexAttribPointer(index, components, glType, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void disableVertexAttribute(int index)
	{
		glDisableVertexAttribArray(index);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void destroy()
	{
		unbind();
		glDeleteVertexArrays(id);
	}
	
}
