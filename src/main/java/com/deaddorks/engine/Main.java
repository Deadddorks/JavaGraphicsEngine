package com.deaddorks.engine;

import com.deaddorks.engine.buffers.IBO;
import com.deaddorks.engine.buffers.VAO;
import com.deaddorks.engine.buffers.VBO;
import com.deaddorks.engine.model.Model;
import com.deaddorks.engine.render.Renderer;
import com.deaddorks.engine.shader.Shader;
import com.deaddorks.engine.window.Window;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

public class Main
{
	
	public static void main(String[] args)
	{
		
		// Create a Window
		Window window = new Window();
		window.create("Engine", 720, 480);
		
		// Load shaders from files
		Shader shader = Shader.parseShaderFromFile("shaders/vertex.shader", "shaders/fragment.shader");
		
		
		VBO vbo = new VBO(new float[] {
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0f, 0.5f, 0f
		});
		IBO ibo = new IBO(new int[] {
				0, 1, 2
		});
		VAO vao = new VAO();
		vao.bind();
		vao.enableVertexAttribute(0, 3, GL_FLOAT, vbo.getId());
		vao.unbind();
		
		Model model = new Model(ibo, vao, shader);
		
		
		// Show window and draw game-loop
		glfwShowWindow(window.getId());
		while (!glfwWindowShouldClose(window.getId()))
		{
			glClear(GL_COLOR_BUFFER_BIT);
			
			Renderer.renderModel(model);
			
			glfwSwapBuffers(window.getId());
			glfwPollEvents();
		}
		
		// Clean up
		model.destroy();
		shader.destroy();
		glfwTerminate();
		
	}
	
}
