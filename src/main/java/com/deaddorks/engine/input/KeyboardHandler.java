package com.deaddorks.engine.input;

import org.lwjgl.glfw.GLFWKeyCallbackI;

public class KeyboardHandler implements GLFWKeyCallbackI
{
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		Inputs.set(key, action);
	}
	
}
