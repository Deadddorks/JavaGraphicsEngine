package com.deaddorks.engine.loader;

import com.deaddorks.engine.model.RawModel;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Loader
{

	private List<Integer> vaos;
	private List<Integer> vbos;
	private List<Integer> texs;
	
	public Loader()
	{
		vaos = new ArrayList<>();
		vbos = new ArrayList<>();
		texs = new ArrayList<>();
	}
	
	public RawModel loadToVAO(final float[] positions, final float[] textureCoords, final int indices[])
	{
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		unbindVAO();
		
		return new RawModel(vaoId, indices.length);
	}
	
	public int loadTexture(final String fileName)
	{
		int textureId = -1;
		
		texs.add(textureId);
		return textureId;
	}
	
	public void cleanUp()
	{
		for (int vao : vaos)
		{
			glDeleteVertexArrays(vao);
		}
		for (int vbo : vbos)
		{
			glDeleteBuffers(vbo);
		}
		for (int tex : texs)
		{
			glDeleteTextures(tex);
		}
	}
	
	private int createVAO()
	{
		int vaoId = glGenVertexArrays();
		vaos.add(vaoId);
		glBindVertexArray(vaoId);
		return vaoId;
	}
	
	private void storeDataInAttributeList(final int attributeNumber, final int size, float[] data)
	{
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO()
	{
		glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(final int[] indices)
	{
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(final float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private IntBuffer storeDataInIntBuffer(final int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
