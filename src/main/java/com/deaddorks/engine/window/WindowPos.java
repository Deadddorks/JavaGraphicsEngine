package com.deaddorks.engine.window;

public class WindowPos
{
	
	private final int xPos, yPos;
	
	public WindowPos(final int xPos, final int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int xPos()
	{
		return xPos;
	}
	public int yPos()
	{
		return yPos;
	}
	
	@Override
	public String toString()
	{
		return "{WindowPos} {xPos: ["+ xPos +"], yPos: ["+ yPos +"]}";
	}
	
}
