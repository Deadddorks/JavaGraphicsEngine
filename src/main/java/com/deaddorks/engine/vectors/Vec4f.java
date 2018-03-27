package com.deaddorks.engine.vectors;

public class Vec4f implements VecF
{
	
	private final float v1, v2, v3,v4;
	
	public Vec4f(final float v1, final float v2, final float v3, final float v4)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
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
	public float getV4()
	{
		return v4;
	}
	
	@Override
	public float[] toFloatArray()
	{
		return new float[] {v1, v2, v3, v4};
	}
	
}
