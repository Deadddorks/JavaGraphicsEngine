package com.deaddorks.engine.vectors;

public class Vec1f implements VecF
{
	
	private final float v1;
	
	public Vec1f(final float v1)
	{
		this.v1 = v1;
	}
	
	public float getV1()
	{
		return v1;
	}
	
	@Override
	public float[] toFloatArray()
	{
		return new float[] {v1};
	}
	
}
