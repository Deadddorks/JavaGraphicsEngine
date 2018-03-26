package com.deaddorks.engine;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.entity.Entity;
import com.deaddorks.engine.model.BasicModel;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.ui.UI;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

public class Main
{
	
	public static void main(String[] args)
	{
		
		UI ui = new UI("Engine 2.0", 500, 500, false, true)
		{
			
			private Shader shader;
			private List<RawModel> models;
			
			@Override
			protected void init()
			{
				
				models = new ArrayList<>();
				shader = Shader.parseShaderFromFile("shaders/basic/vertex.shader", "shaders/basic/fragment.shader");
				
				// -------------------------
				
				VAO vao = new VAO();
				vao.bind();
				vao.bindVBO(0, 3, new VBO(new float[] {
						0.75f,  0.75f, 0f,
						-0.75f,  0.75f, 0f,
						-0.75f, -0.75f, 0f,
						0.75f, -0.75f, 0f,
				}));
				vao.bindVBO(1, 4, new VBO(new float[] {
						1.0f, 1.0f, 1.0f, 1.0f,
						0.5f, 0.5f, 0.5f, 1.0f,
						0.0f, 0.0f, 0.0f, 1.0f,
						0.5f, 0.5f, 0.5f, 1.0f
				}));
				VAO.unbind();
				models.add(new BasicModel(new IBO(new int[] {
						0, 1, 2,
						2, 3, 0
				}), vao, shader));
				
				VAO vao2 = new VAO();
				vao2.bind();
				vao2.bindVBO(0, 3, new VBO(new float[] {
						-0.5f, -0.5f, 0.0f,
						0.5f, -0.5f, 0.0f,
						0.0f,  0.5f, 0.0f
				}));
				vao2.bindVBO(1, 4, new VBO(new float[] {
						1.0f, 0.0f, 0.0f, 1.0f,
						0.0f, 1.0f, 0.0f, 1.0f,
						0.0f, 0.0f, 1.0f, 1.0f
				}));
				VAO.unbind();
				
				IBO ibo2 =new IBO(new int[] {
						0, 1, 2
				});
				
				shader = Shader.parseShaderFromFile("shaders/entity/vertex.shader", "shaders/entity/fragment.shader");
				Entity entity = new Entity(vao2, ibo2, shader, "location");
				models.add(entity);
				
				entity.setLocation(-0.75, -0.75, 0);
				
			}
			
			@Override
			protected void gameLoop()
			{
				glClear(GL_COLOR_BUFFER_BIT);
				
				for (RawModel model : models)
				{
					Renderer.renderModel(model);
				}
				
				glfwSwapBuffers(window.getId());
				glfwPollEvents();
			}
			
			@Override
			protected void handleInputs()
			{
			
			}
			
			@Override
			protected void cleanUp()
			{
				for (RawModel model : models)
				{
					model.destroy();
				}
			}
		};
		
		ui.run();
		
	}
	
}
