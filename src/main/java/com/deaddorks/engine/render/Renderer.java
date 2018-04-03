package com.deaddorks.engine.render;

import com.deaddorks.engine.model.BasicModel;
import com.deaddorks.engine.model.RawModel;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Renderer
{

	private List<RawModel> models;
	
	public Renderer()
	{
		models = new ArrayList<>();
	}
	
	public void addModel(final RawModel model)
	{
		models.add(model);
	}
	
	public void render()
	{
		for (RawModel model : models)
		{
			model.render();
		}
	}
	
	public void destroy()
	{
		for (RawModel model : models)
		{
			model.destroy();
		}
		models = null;
	}

}
