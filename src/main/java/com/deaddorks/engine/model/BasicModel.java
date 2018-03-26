package com.deaddorks.engine.model;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.shader.Shader;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

public class BasicModel extends RawModel
{
	
	private IBO ibo;
	private VAO vao;
	private Shader shader;
	
	public BasicModel(final IBO ibo, final VAO vao, final Shader shader)
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
	
	@Override
	public void render()
	{
		shader.use();
		vao.bind();
		ibo.bind();
		
		glDrawElements(GL_TRIANGLES, ibo.getElementCount(), GL_UNSIGNED_INT, 0);
		
		Shader.unbind();
		VAO.unbind();
		IBO.unbind();
	}
	
	@Override
	public void destroy()
	{
		ibo.destroy();
		vao.destroy();
		shader.destroy();
	}
	
}
