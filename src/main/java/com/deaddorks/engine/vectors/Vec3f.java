package com.deaddorks.engine.vectors;

public class Vec3f implements VecF
{
	
	private final float v1, v2, v3;
	
	public Vec3f(final float v1, final float v2, final float v3)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	public float getV1()
	{
		return v1;
	}
	public float getV2()
	{
		return v2;
	}
	public float getV3()
	{
		return v3;
	}
	
	@Override
	public float[] toFloatArray()
	{
		return new float[] {v1, v2, v3};
	}
	
}
