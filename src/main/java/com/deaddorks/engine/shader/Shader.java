package com.deaddorks.engine.shader;

import org.lwjgl.BufferUtils;
import sun.plugin.dom.exception.InvalidStateException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.IntBuffer;

public class Shader
{
	
	private int id;
	private final String vertex, fragment;
	
	public Shader(final String vertex, final String fragment)
	{
		id = -1;
		this.vertex = vertex;
		this.fragment = fragment;
	}
	
	public void compile()
	{
		int id = glCreateProgram();
		int vs = compileShader(GL_VERTEX_SHADER, vertex);
		int fs = compileShader(GL_FRAGMENT_SHADER, fragment);
		
		glAttachShader(id, vs);
		glAttachShader(id, fs);
		glLinkProgram(id);
		glValidateProgram(id);
		
		glDeleteShader(vs);
		glDeleteShader(fs);
		
		this.id = id;
	}
	
	public void destroy()
	{
		glUseProgram(0);
		glDeleteProgram(id);
		id = -1;
	}
	
	public void use()
	{
		glUseProgram(getId());
	}
	public void unbind()
	{
		glUseProgram(0);
	}
	
	private static int compileShader(final int type, final String code)
	{
		int id = glCreateShader(type);
		
		glShaderSource(id, code);
		glCompileShader(id);
		
		// Check for proper compilation (Int buffer?)
		IntBuffer result = BufferUtils.createIntBuffer(1);
		glGetShaderiv(id, GL_COMPILE_STATUS, result);
		if (result.get() == GL_FALSE)
		{
			System.out.println("Error Compiling shader: " + glGetShaderInfoLog(id));
			
		}
		
		return id;
	}
	
	public int getId()
	{
		if (id == -1)
		{
			throw new InvalidStateException("Shader is not active");
		}
		return id;
	}
	
	public static Shader parseShaderFromFile(final String vertexPath, final String fragmentPath)
	{
		Shader shader = new Shader(readShaderFromFile(vertexPath), readShaderFromFile(fragmentPath));
		shader.compile();
		return shader;
	}
	
	private static String readShaderFromFile(final String path)
	{
		File file = new File(path);
		StringBuilder string = new StringBuilder();
		
		int lineNum = 0;
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			while ((line = reader.readLine()) != null)
			{
				string.append(line).append('\n');
			}
		}
		catch (IOException e)
		{
			System.out.println("Error shader from file [" + file.getPath() + "] on line (" + lineNum + ")");
			System.exit(-1);
		}
		
		return string.toString();
	}
	
	@Override
	public String toString()
	{
		return "--- Vertex ---\n" + vertex + "--- Fragment ---\n" + fragment;
	}
}
