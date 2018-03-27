package com.deaddorks.engine.ui;

import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;

public class WindowHint
{
	
	private final int hint, value;
	
	public WindowHint(final int hint, final int value)
	{
		this.hint = hint;
		this.value = value;
	}
	public WindowHint(final int hint, final boolean value)
	{
		this.hint = hint;
		this.value = value ? GLFW_TRUE : GLFW_FALSE;
	}
	
	public int getHint()
	{
		return hint;
	}
	public int getValue()
	{
		return value;
	}
	
}
