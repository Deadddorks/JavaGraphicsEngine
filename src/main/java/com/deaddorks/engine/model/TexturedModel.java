package com.deaddorks.engine.model;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.textures.Texture;

public class TexturedModel extends RawModel
{
	
	private Shader shader;
	private Texture texture;
	private VAO vao;
	private IBO ibo;
	
	public TexturedModel(final IBO ibo, final VBO positions, final VBO textureUV, final Texture texture)
	{
		this.texture = texture;
		this.shader = Shader.parseShaderFromFile("shaders/textured/");
		this.ibo = ibo;
		
		vao = new VAO();
		vao.bind();
		vao.bindVBO(0, 2, positions);
		vao.bindVBO(1, 2, textureUV);
		VAO.unbind();
		
	}
	
	@Override
	public void render()
	{
		shader.uniformTexture("texture", texture);
	}
	
	@Override
	public void destroy()
	{
		ibo.destroy();
		vao.destroy();
		shader.destroy();
	}
	
}
