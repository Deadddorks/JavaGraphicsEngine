package com.deaddorks.engine.entity;

import com.deaddorks.engine.model.Model;
import com.deaddorks.engine.shader.Shader;

public class Entity
{
	
	private Shader shader;
	private Model model;
	
	private String shaderUniformVar;
	private double x, y, z;
	
	public Entity(final Model model, final String shaderUniformVar, final Shader shader)
	{
		this.model = model;
		this.shaderUniformVar = shaderUniformVar;
		this.shader = shader;
	}
	public Entity(final Model model, final String shaderUniformVar, final String shaderFolder)
	{
		this.model = model;
		this.shaderUniformVar = shaderUniformVar;
		this.shader = Shader.parseShaderFromFile(shaderFolder + "vertex.shader", shaderFolder + "fragment.shader");
	}
	
	public void setLocation(final double x, final double y, final double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		shader.uniform3f(shaderUniformVar, (float) x, (float) y, (float) z);
	}
	
	public Shader getShader()
	{
		return shader;
	}
	
	public Model getModel()
	{
		return model;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getZ()
	{
		return z;
	}
	
}
