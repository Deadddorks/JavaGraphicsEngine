package com.deaddorks.engine.window;

import com.deaddorks.engine.ui.WindowHint;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Window
{
	
	private long id;
	
	public Window(final String title, final int width, final int height, final WindowHint... hints)
	{
		if (!glfwInit())
		{
			throw new IllegalStateException("glfwInit()");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		for (WindowHint hint : hints)
		{
			glfwWindowHint(hint.getHint(), hint.getValue());
		}
		
		id = glfwCreateWindow(width, height, title, 0, 0);
		if (id == 0)
		{
			throw new IllegalStateException("glfwCreateWindow()");
		}
		
		// glfwSetWindowIcon(id, );
		
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
	
	public WindowPos getWindowPos()
	{
		IntBuffer x = BufferUtils.createIntBuffer(1);
		IntBuffer y = BufferUtils.createIntBuffer(1);
		glfwGetWindowPos(id, x, y);
		return new WindowPos(x.get(), y.get());
	}
	public void setWindowPos(final int x, final int y)
	{
		glfwSetWindowPos(id, x, y);
	}
	
	public void close()
	{
		glfwSetWindowShouldClose(id, true);
	}
	
}
