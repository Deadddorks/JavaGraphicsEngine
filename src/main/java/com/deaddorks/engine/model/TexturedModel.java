package com.deaddorks.engine.model;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.textures.Texture;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

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
		vao.bindVBO(0, 3, positions);
		vao.bindVBO(1, 2, textureUV);
		VAO.unbind();
	}
	
	@Override
	public void render()
	{
		shader.uniform1i("tex", texture.getSlot());
		
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
