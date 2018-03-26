package com.deaddorks.engine.ui;

import com.deaddorks.engine.window.Window;
import javaLibraries.util.time.Timer;

import static org.lwjgl.glfw.GLFW.*;

public abstract class UI
{

	private Timer timer;
	protected Window window;
	
	private double frameDelay;
	
	public UI(final String title, final int width, final int height, final boolean resizable)
	{
		window = new Window();
		window.create(title, width, height, resizable ? GLFW_TRUE : GLFW_FALSE);
		
		timer = new Timer();
	}
	
	protected abstract void init();
	
	protected abstract void gameLoop();
	
	protected abstract void cleanUp();
	
	public void run()
	{
		
		init();
		timer.start();
		
		glfwShowWindow(window.getId());
		while (!glfwWindowShouldClose(window.getId()))
		{
			frameDelay = timer.readSecsExact();
			timer.start();
			gameLoop();
		}
		
		cleanUp();
		glfwTerminate();
		
	}
	
	protected double getFrameDelay()
	{
		return frameDelay;
	}
	
	protected int getFrameRate()
	{
		return (int) Math.round(getFrameRateRaw());
	}
	protected double getFrameRateRaw()
	{
		return 1 / frameDelay;
	}

}
