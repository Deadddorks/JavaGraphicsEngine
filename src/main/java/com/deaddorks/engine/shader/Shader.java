package com.deaddorks.engine.shader;

import com.deaddorks.engine.textures.Texture;
import com.deaddorks.engine.vectors.Vec1f;
import com.deaddorks.engine.vectors.Vec2f;
import com.deaddorks.engine.vectors.Vec3f;
import com.deaddorks.engine.vectors.Vec4f;
import org.lwjgl.BufferUtils;

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
	
	public int getUniformLoc(final String uniformName)
	{
		return glGetUniformLocation(getId(), uniformName);
	}
	
	public void uniform1f(final String uniformName, final Vec1f v)
	{
		uniform1f(uniformName, v.getV1());
	}
	public void uniform1f(final String uniformName, final float v1)
	{
		use();
		glUniform1f(getUniformLoc(uniformName), v1);
		unbind();
	}
	public void uniform2f(final String uniformName, final Vec2f v)
	{
		uniform2f(uniformName, v.getV1(), v.getV2());
	}
	public void uniform2f(final String uniformName, final float v1, final float v2)
	{
		use();
		glUniform2f(getUniformLoc(uniformName), v1, v2);
		unbind();
	}
	public void uniform3f(final String uniformName, final Vec3f v)
	{
		uniform3f(uniformName, v.getV1(), v.getV2(), v.getV3());
	}
	public void uniform3f(final String uniformName, final float v1, final float v2, final float v3)
	{
		use();
		glUniform3f(getUniformLoc(uniformName), v1, v2, v3);
		unbind();
	}
	public void uniform4f(final String uniformName, final Vec4f v)
	{
		uniform4f(uniformName, v.getV1(), v.getV2(), v.getV3(), v.getV4());
	}
	public void uniform4f(final String uniformName, final float v1, final float v2, final float v3, final float v4)
	{
		use();
		glUniform4f(getUniformLoc(uniformName), v1, v2, v3, v4);
		unbind();
	}
	
	public void uniform1i(final String uniformName, final int i1)
	{
		use();
		glUniform1i(getUniformLoc(uniformName), i1);
		unbind();
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
	public static void unbind()
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
			throw new IllegalStateException("Shader is not active");
		}
		return id;
	}
	
	public static Shader parseShaderFromFile(final String vertexPath, final String fragmentPath)
	{
		Shader shader = new Shader(readShaderFromFile(vertexPath), readShaderFromFile(fragmentPath));
		shader.compile();
		return shader;
	}
	public static Shader parseShaderFromFile(final String shaderDirPath)
	{
		return parseShaderFromFile(shaderDirPath + "vertex.shader", shaderDirPath + "fragment.shader");
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
			System.out.println("Error reading shader from file [" + file.getPath() + "] on line (" + lineNum + ")");
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
