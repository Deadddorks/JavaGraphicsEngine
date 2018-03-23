
public class Test
{

	public static void main(String[] args)
	{
		Inner in = new Inner();
		
		for (int i = 0; i < 10; i++)
		{
			System.out.println(in.inc());
		}
	}
	
	private static class Inner
	{
		private int i;
		
		private Inner()
		{
			this.i = 0;
		}
		
		private int inc()
		{
			try
			{
				return i;
			}
			finally
			{
				i++;
			}
		}
		
	}

}
