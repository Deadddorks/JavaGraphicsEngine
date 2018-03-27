package com.deaddorks.engine.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

import static org.lwjgl.stb.STBImage.STBI_rgb_alpha;
import static org.lwjgl.stb.STBImage.stbi_image_free;

public class Texture
{

	private final int id;
	private final int width, height;
	private final int[] pixels;
	private final int components;
	
	public Texture(final String filePath)
	{
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer c = stack.mallocInt(1);
			ByteBuffer buffer = stbi_load(filePath, w, h, c, STBI_rgb_alpha);
			width = w.get(0);
			height = h.get(0);
			components = c.get(0);
			pixels = new int[width * height];
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = buffer.getInt();
			}
			buffer.clear();
			stbi_image_free(buffer);
		}
		
		id = glGenTextures();
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	public int[] getPixels()
	{
		return pixels;
	}
	
	public int getComponents()
	{
		return components;
	}
	
}
