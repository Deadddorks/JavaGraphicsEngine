package com.deaddorks.engine.textures;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

import static org.lwjgl.stb.STBImage.STBI_rgb_alpha;
import static org.lwjgl.stb.STBImage.stbi_image_free;

public class Texture
{

	private final int id;
	private final int slot;
	
	public Texture(final String filePath, final int slot)
	{
		int width;
		int height;
		int components;
		int[] pixels;
		
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
		
		this.slot = slot;
		id = glGenTextures();
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0 ,GL_RGBA, GL_UNSIGNED_BYTE, pixels);
	}
	
	public int getId()
	{
		return id;
	}
	public int getSlot()
	{
		return slot;
	}
	
}
