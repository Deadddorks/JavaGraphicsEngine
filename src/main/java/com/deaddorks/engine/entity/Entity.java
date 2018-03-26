package com.deaddorks.engine.entity;

import com.deaddorks.engine.model.BasicModel;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.shader.Shader;

public class Entity
{
	
	private Shader shader;
	private RawModel model;
	
	private String shaderUniformVar;
	private double x, y, z;
	
	public Entity(final RawModel model, final String shaderUniformVar, final Shader shader)
	{
		this.model = model;
		this.shaderUniformVar = shaderUniformVar;
		this.shader = shader;
	}
	public Entity(final BasicModel model, final String shaderUniformVar, final String shaderFolderPath)
	{
		this.model = model;
		this.shaderUniformVar = shaderUniformVar;
		this.shader = Shader.parseShaderFromFile(shaderFolderPath + "vertex.shader", shaderFolderPath + "fragment.shader");
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
	
	public RawModel getModel()
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
	
	public void destroy()
	{
		shader.destroy();
		model.destroy();
	}
	
}