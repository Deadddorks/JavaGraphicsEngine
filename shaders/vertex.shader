#version 400 core

in vec3 position;

out vec4 pass_Color;

void main(void)
{
    gl_Position = vec4(position, 1.0);
    pass_Color = vec4(1.0, 0.0, 1.0, 1.0);
}