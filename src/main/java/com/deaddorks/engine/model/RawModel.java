package com.deaddorks.engine.model;

public class RawModel
{
	
	private int vaoId;
	private int vertexCount;
	
	public RawModel(final int vaoId, final int vertexCount)
	{
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}
	
	public int getVaoId()
	{
		return vaoId;
	}
	public int getVertexCount()
	{
		return vertexCount;
	}
	
}
