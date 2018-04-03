package com.deaddorks.engine.textures.exception;

public class TextureSlotsFullException extends RuntimeException
{
	
	public TextureSlotsFullException(final int attempted, final int max)
	{
		super("Attempted to access out of bounds texture slot ["+ attempted +"], max is ["+ max +"].");
	}
	
}
