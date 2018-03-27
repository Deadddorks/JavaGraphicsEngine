#version 330 core

uniform sampler2D texture;

int vec2 textureCoord;

out vec4 out_Color;

void main(void)
{
    out_Color = texture(texture, textureCoord);
}