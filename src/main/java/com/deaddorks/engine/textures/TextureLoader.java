package com.deaddorks.engine.textures;

import com.deaddorks.engine.textures.exception.TextureSlotsFullException;

import java.util.PriorityQueue;

public class TextureLoader
{
	
	private final int maxTexture;
	
	private int next;
	private final PriorityQueue<Integer> free;
	
	public TextureLoader(final int maxTexture)
	{
		next = 0;
		free = new PriorityQueue<>();
		this.maxTexture = maxTexture;
	}
	
	public Texture loadTexture(final String filePath)
	{
		int slot;
		if (free.isEmpty())
		{
			if (next >= maxTexture)
			{
				throw new TextureSlotsFullException(next, maxTexture);
			}
			
			slot = next;
			next++;
		}
		else
		{
			slot = free.poll();
		}
		
		return new Texture(filePath, slot);
	}
	
}
