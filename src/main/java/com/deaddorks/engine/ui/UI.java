package com.deaddorks.engine.ui;

import com.deaddorks.engine.input.Inputs;
import com.deaddorks.engine.input.KeyboardHandler;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.textures.Texture;
import com.deaddorks.engine.textures.TextureLoader;
import com.deaddorks.engine.window.Window;
import com.deaddorks.engine.window.WindowPos;
import javaLibraries.util.time.Timer;
import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public abstract class UI
{

	private final Timer timer;
	protected final FrameInfoObject frameInfoObject;
	
	protected final Window window;
	protected final int width, height;
	
	private TextureLoader textureLoader;
	protected Renderer renderer;
	
	public UI(final String title, final int width, final int height, final WindowHint... hints)
	{
		this.window = new Window(title, width, height, hints);
		this.width = width;
		this.height = height;
		
		this.timer = new Timer();
		this.frameInfoObject = new FrameInfoObject();
		
		textureLoader = new TextureLoader(32);
		renderer = new Renderer();
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
	
	protected boolean key(final int keyCode)
	{
		return Inputs.isKeyPressed(keyCode);
	}
	
	protected Texture loadTexture(final String filePath)
	{
		return textureLoader.loadTexture(filePath);
	}
	
	protected void addModel(final RawModel model)
	{
		renderer.addModel(model);
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
