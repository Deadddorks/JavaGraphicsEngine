package com.deaddorks.engine.entity;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.shader.Shader;

import static org.lwjgl.opengl.GL11.*;

public class Entity extends RawModel
{
	
	private VAO vao;
	private IBO ibo;
	private Shader shader;
	private final String shaderPosString;
	
	private double x, y, z;
	
	public Entity(final VAO vao, final IBO ibo, final String shaderDirPath, final String shaderPosString)
	{
		this.shaderPosString = shaderPosString;
		this.shader = Shader.parseShaderFromFile(shaderDirPath + "vertex.shader", shaderDirPath + "fragment.shader");
		
		this.vao = vao;
		this.ibo = ibo;
	}
	
	@Override
	public void render()
	{
		shader.use();
		vao.bind();
		ibo.bind();
		
		shader.uniform3f(shaderPosString, (float) x, (float) y, (float) z);
		glDrawElements(GL_TRIANGLES, ibo.getElementCount(), GL_UNSIGNED_INT, 0);
		
		Shader.unbind();
		VAO.unbind();
		IBO.unbind();
	}
	
	@Override
	public void destroy()
	{
		shader.destroy();
		ibo.destroy();
		vao.destroy();
	}
	
	public void setLocation(final double x, final double y, final double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setX(final double x)
	{
		this.x = x;
	}
	public void setY(final double y)
	{
		this.y = y;
	}
	public void setZ(final double z)
	{
		this.z = z;
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
