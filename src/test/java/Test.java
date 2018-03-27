import com.deaddorks.engine.textures.Texture;

public class Test
{
	
	public static void main(String[] args)
	{
		Texture texture = new Texture("res/pwn.png");
		System.out.println("size: " + texture.getPixels().length);
		for (int i : texture.getPixels())
		{
			System.out.println(i);
		}
		
	}
	
}
