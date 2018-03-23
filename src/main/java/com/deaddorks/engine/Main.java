package com.deaddorks.engine;

import com.deaddorks.engine.loader.Loader;
import com.deaddorks.engine.model.RawModel;
import com.deaddorks.engine.model.TexturedModel;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.textures.ModelTexture;
import com.deaddorks.engine.window.Window;
import org.lwjgl.stb.STBImage;

import static org.lwjgl.glfw.GLFW.*;

public class Main
{
	
	public static void main(String[] args)
	{
		
		Window window = new Window();
		
		window.create("Engine", 720, 480);
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		RawModel model = loader.loadToVAO(
				new float[] {
						-0.5f,  0.5f, 0f,
						-0.5f, -0.5f, 0f,
						 0.5f, -0.5f, 0f,
						 0.5f,  0.5f, 0f
				},
				new float[] {
						0f, 0f,
						0f, 1f,
						1f, 1f,
						1f, 0f
				},
				new int[] {
						0, 1, 3,
						3, 1, 2
				});
		
		ModelTexture texture = new ModelTexture(loader.loadTexture("pwn"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Shader shader = Shader.parseShaderFromFile("shaders/vertex.shader", "shaders/fragment.shader");
		shader.bindAttribute(0, "position");
		shader.bindAttribute(1, "textureCoords");
		// System.out.println(shader.toString());
		shader.use();
		
		glfwShowWindow(window.getId());
		while (!glfwWindowShouldClose(window.getId()))
		{
			renderer.prepare();
			
			renderer.render(texturedModel);
			
			glfwSwapBuffers(window.getId());
			
			glfwPollEvents();
		}
		
		loader.cleanUp();
		shader.destroy();
		glfwTerminate();
		
	}
	
}
