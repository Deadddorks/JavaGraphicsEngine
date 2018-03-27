package com.deaddorks.engine.vectors;

public class Vec2f implements VecF
{
	
	private final float v1, v2;
	
	public Vec2f(final float v1, final float v2)
	{
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public float getV1()
	{
		return v1;
	}
	public float getV2()
	{
		return v2;
	}
	
	@Override
	public float[] toFloatArray()
	{
		return new float[] {v1, v2};
	}
	
}
