package com.deaddorks.engine.model;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.shader.Shader;

public class Model
{
	
	private IBO ibo;
	private VAO vao;
	private Shader shader;
	
	public Model(final IBO ibo, final VAO vao, final Shader shader)
	{
		this.ibo = ibo;
		this.vao = vao;
		this.shader = shader;
	}
	
	public IBO getIbo()
	{
		return ibo;
	}
	public VAO getVao()
	{
		return vao;
	}
	public Shader getShader()
	{
		return shader;
	}
	
	public void destroy()
	{
		ibo.destroy();
		vao.destroy();
	}
	
}
