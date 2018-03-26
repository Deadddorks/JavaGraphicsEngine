package com.deaddorks.engine.input;

import static org.lwjgl.glfw.GLFW.*;

public class Inputs
{
	
	private static final boolean[] keys  = new boolean[GLFW_KEY_LAST];
	
	public static void set(final int key, final int action)
	{
		keys[key] = (action != GLFW_RELEASE);
	}
	
	public boolean isKeyPressed(final int key)
	{
		return keys[key];
	}
	
}
