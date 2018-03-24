package com.deaddorks.engine.render;

import com.deaddorks.engine.model.Model;

import static org.lwjgl.opengl.GL11.*;

public class Renderer
{

	public static void renderModel(final Model model)
	{
	
		model.getShader().use();
		model.getVao().bind();
		model.getIbo().bind();
		
		glDrawElements(GL_TRIANGLES, model.getIbo().getElementCount(), GL_UNSIGNED_INT, 0);
		
		model.getShader().unbind();
		model.getVao().unbind();
		model.getIbo().unbind();
	
	}

}
