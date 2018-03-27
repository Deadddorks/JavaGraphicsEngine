package com.deaddorks.engine.vectors;

import java.util.LinkedList;
import java.util.Queue;

public class Vectors
{
	
	private Vectors() {}
	
	public static float[] combineVectors(final float[]... vecs)
	{
		Queue<Float> floats = new LinkedList<>();
		
		for (float[] fs : vecs)
		{
			for (float f : fs)
			{
				floats.add(f);
			}
		}
		
		int size = floats.size();
		float[] combined = new float[size];
		for (int i = 0; i < size; i++)
		{
			combined[i] = floats.poll();
		}
		
		return combined;
	}
	public static float[] combineVectors(final VecF... vecs)
	{
		Queue<Float> floats = new LinkedList<>();
		
		for (VecF fs : vecs)
		{
			for (float f : fs.toFloatArray())
			{
				floats.add(f);
			}
		}
		
		int size = floats.size();
		float[] combined = new float[size];
		for (int i = 0; i < size; i++)
		{
			combined[i] = floats.poll();
		}
		
		return combined;
	}
	
}
