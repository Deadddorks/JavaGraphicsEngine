package com.deaddorks.engine.render;

import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.model.TexturedModel;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer
{

	public Renderer()
	{
	
	}
	
	public void prepare()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0, 0, 1, 1);
	}
	
	public void render(final RawModel model)
	{
		glBindVertexArray(model.getVaoId());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	public void render(final TexturedModel texturedModel)
	{
		RawModel model = texturedModel.getRawModel();
		
		glBindVertexArray(model.getVaoId());
		glEnableVertexAttribArray(0);
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE, texturedModel.getTexture().getTextureId());
		
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}

}
