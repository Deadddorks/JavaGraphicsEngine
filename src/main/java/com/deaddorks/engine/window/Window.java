package com.deaddorks.engine.window;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class Window
{
	
	private long id;
	
	public Window()
	{
	
	}
	
	public void create(final String title, final int width, final int height, final int resizable, final int decorated)
	{
		if (!glfwInit())
		{
			throw new IllegalStateException("glfwInit()");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_RESIZABLE, resizable);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_DECORATED, decorated);
		
		id = glfwCreateWindow(width, height, title, 0, 0);
		if (id == 0)
		{
			throw new IllegalStateException("glfwCreateWindow()");
		}
		
		glfwMakeContextCurrent(id);
		GL.createCapabilities();
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(id, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
		glfwSetWindowIcon(id, null);
		
	}
	
	public long getId()
	{
		return id;
	}
}
