package com.deaddorks.engine;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.entity.Entity;
import com.deaddorks.engine.model.BasicModel;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.model.TexturedModel;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.ui.UI;
import com.deaddorks.engine.ui.WindowHint;
import com.deaddorks.engine.window.WindowPos;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

public class Main
{
	
	public static void main(String[] args)
	{
		
		UI ui = new UI("Engine 2.0", 500, 500,
				new WindowHint(GLFW_RESIZABLE, false), new WindowHint(GLFW_DECORATED, true))
		{
			
			@Override
			protected void init()
			{
				addModel(new TexturedModel(
						new IBO(0, 1, 2, 0, 2, 3),
						new VBO(-0.5f, -0.5f, 0.0f,
								0.5f, -0.5f, 0.0f,
								0.5f, 0.5f, 0.0f,
								-0.5f, 0.5f, 0.0f),
						new VBO(0, 0, 1, 0, 1, 1, 0, 1),
						loadTexture("res/hexagon.jpg")));
			}
			
			@Override
			protected void gameLoop()
			{
				glClear(GL_COLOR_BUFFER_BIT);
				glClearColor(1f, 0f, 0f, 1f);
				
				renderer.render();
				
				glfwSwapBuffers(window.getId());
				glfwPollEvents();
			}
			
			@Override
			protected void handleInputs()
			{
				if (key(GLFW_KEY_ESCAPE))
				{
					window.close();
				}
				
				int scale = 5;
				int xD = (key(GLFW_KEY_LEFT) ? -1 : 0) + (key(GLFW_KEY_RIGHT) ? 1 : 0);
				int yD = (key(GLFW_KEY_DOWN) ? 1 : 0) + (key(GLFW_KEY_UP) ? -1 : 0);
				WindowPos pos = window.getWindowPos();
				window.setWindowPos(pos.xPos() + xD * scale, pos.yPos() + yD * scale);
			}
			
			@Override
			protected void cleanUp()
			{
				renderer.destroy();
			}
		};
		
		ui.run();
		
	}
	
}
