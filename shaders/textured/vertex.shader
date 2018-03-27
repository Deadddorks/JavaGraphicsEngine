#version 330 core

layout (location = 0) in vec2 position;
layout (location = 1) in vec2 uv;

out vec2 pass_TextureCoord;

out vec2 pass_TextureCoord;

void main(void) {
	gl_Position = vec4(in_Position, 0.0, 1.0);

	pass_TextureCoord = in_TextureCoord;
}