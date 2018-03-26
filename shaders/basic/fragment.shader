#version 330 core

in struct Vertex {
    vec3 position;
    vec4 color;
    vec2 uv;
} v;

out vec4 out_Color;

void main(void)
{
    out_Color = v.color;
}