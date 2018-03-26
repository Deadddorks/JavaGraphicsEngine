package com.deaddorks.engine.ui;

import com.deaddorks.engine.input.KeyboardHandler;
import com.deaddorks.engine.window.Window;
import javaLibraries.util.time.Timer;

import static org.lwjgl.glfw.GLFW.*;

public abstract class UI
{

	private final Timer timer;
	protected FrameInfoObject frameInfoObject;
	
	protected Window window;
	protected final int width, height;
	
	public UI(final String title, final int width, final int height, final boolean resizable, final boolean decorated)
	{
		window = new Window();
		window.create(title, width, height, resizable ? GLFW_TRUE : GLFW_FALSE, decorated ? GLFW_TRUE : GLFW_FALSE);
		this.width = width;
		this.height = height;
		
		timer = new Timer();
	}
	
	protected abstract void init();
	
	protected abstract void gameLoop();
	
	protected abstract void handleInputs();
	
	protected abstract void cleanUp();
	
	public void run()
	{
		
		glfwSetKeyCallback(window.getId(), new KeyboardHandler());
		init();
		timer.start();
		
		glfwShowWindow(window.getId());
		while (!glfwWindowShouldClose(window.getId()))
		{
			handleInputs();
			frameInfoObject.frameDelay = timer.readSecsExact();
			timer.start();
			gameLoop();
		}
		
		cleanUp();
		glfwTerminate();
		
	}
	
	public FrameInfoObject getFrameInfoObject()
	{
		return frameInfoObject;
	}
	
	public class FrameInfoObject
	{
		
		protected double frameDelay;
		
		public double getFrameDelay()
		{
			return frameDelay;
		}
		
		public int getFrameRate()
		{
			return (int) Math.round(getFrameRateRaw());
		}
		public double getFrameRateRaw()
		{
			return 1 / frameDelay;
		}
		
	}

}
