import java.util.Random;

public class Game 
{
	public static void main(String[] args) throws InterruptedException 
	{
		int[][] a = new int[][]{{0,0,0,0,0,0,0,1,0,0},
								{0,0,0,0,0,0,0,1,0,0},
								{1,0,0,0,0,0,0,0,0,0},
								{1,0,0,1,1,1,0,0,0,0},
								{1,0,0,0,0,0,0,1,0,0},
								{0,0,0,0,0,1,0,1,1,0},
								{0,0,0,0,1,1,0,0,0,0},
								{0,0,0,1,0,0,1,0,0,0},
								{0,0,0,1,0,0,0,0,0,0},
								{0,0,0,1,0,0,0,1,0,0}};
		Board b = new Board(generateBoard(a));
		b.printBoard();
		
		while(true)
		{
			b.Iterate();
			b.printBoard();
			
			Thread.sleep(500);
		}
	}
	
	public static Cell[][] generateRandomBoard(int size, int precent, int seed)
	{
		int[][] world = new int[size][size];
		Random rng = new Random(seed);
		
		for (int j = 0; j < size; j++ ) 
		{
		    for (int k = 0; k < size; k++) 
		    {
		    	int livingChance = rng.nextInt(100);
				
		    	world[j][k] = (livingChance < precent) ? 1 : 0;
		    }
		}
		
		return generateBoard(world);
	}
	
	public static Cell[][] generateBoard(int[][] world)
	{
		Cell[][] ret = new Cell[world.length][world.length];
		
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world.length; j++) 
			{
				State currentState = world[i][j] == 0 ? State.DEAD : State.ALIVE;
				
				ret[i][j] = new Cell(currentState, j, i);
			}
		}
		
		return ret;
	}
}