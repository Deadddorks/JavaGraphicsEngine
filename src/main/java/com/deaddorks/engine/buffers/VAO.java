package com.deaddorks.engine.buffers;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.glfw.GLFW.*;

public class VAO
{
	
	private int id;
	private List<VBO> vbos;
	
	public VAO()
	{
		id = glGenVertexArrays();
		vbos = new ArrayList<>();
	}
	
	public void bind()
	{
		glBindVertexArray(id);
	}
	
	public static void unbind()
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
	
	public void bindVBO(final int index, final int components, final VBO vbo)
	{
		vbos.add(vbo);
		enableVertexAttribute(index, components, GL_FLOAT, vbo.getId());
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
		for (VBO vbo : vbos)
		{
			vbo.destroy();
		}
	}
	
}
