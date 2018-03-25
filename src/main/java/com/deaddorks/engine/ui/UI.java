package com.deaddorks.engine.ui;

import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.window.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public abstract class UI
{

	protected Window window;
	
	public UI(final String title, final int width, final int height, final boolean resizable)
	{
		window = new Window();
		window.create(title, width, height, resizable ? GLFW_TRUE : GLFW_FALSE);
	}
	
	protected abstract void init();
	
	protected abstract void gameLoop();
	
	protected abstract void cleanUp();
	
	public void run()
	{
		
		init();
		
		glfwShowWindow(window.getId());
		while (!glfwWindowShouldClose(window.getId()))
		{
			gameLoop();
		}
		
		cleanUp();
		glfwTerminate();
		
	}

}
