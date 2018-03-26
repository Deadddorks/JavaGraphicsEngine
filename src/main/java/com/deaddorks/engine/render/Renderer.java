package com.deaddorks.engine.render;

import com.deaddorks.engine.model.BasicModel;
import com.deaddorks.engine.model.RawModel;

import static org.lwjgl.opengl.GL11.*;

public class Renderer
{
	
	public static void renderModel(final RawModel model)
	{
		model.render();
	}

}
