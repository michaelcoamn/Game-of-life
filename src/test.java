

import org.junit.Assert;
import org.junit.Test;

public class test 
{
	@Test
	public void LiveCellLessThan2Neighbors() 
	{
		int[][] cells = new int[][]{{0,0,0},
									{0,1,0},
									{0,0,0}};
		
		Board board = getBoardAfterIteration(cells);
		
		Cell curr = board.m_world[1][1];
		
		Assert.assertEquals(curr.m_state, State.DEAD);
	}
	
	@Test
	public void LiveCell2or3Neighbors() 
	{
		int[][] cells = new int[][]{{0,1,1},
									{0,1,0},
									{0,0,0}};
									

		Board board = getBoardAfterIteration(cells);
		
		Assert.assertEquals(board.m_world[0][1].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[0][2].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[1][1].m_state, State.ALIVE);
	}
	
	@Test
	public void LiveCellMoreThan3Neighbors() 
	{
		int[][] cells = new int[][]{{1,1,1},
									{0,1,1},
									{0,0,0}};

		Board board = getBoardAfterIteration(cells);
		Assert.assertEquals(board.m_world[0][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[0][1].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[0][2].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[1][1].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[1][2].m_state, State.DEAD);
	}
	
	@Test
	public void DeadCellLessThan3Neighbors() 
	{
		int[][] cells = new int[][]{{0,0,0},
									{0,1,0},
									{0,0,0}};
			
		Board board = getBoardAfterIteration(cells);
		Assert.assertEquals(board.m_world[0][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[0][1].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[0][2].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[1][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[1][2].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[2][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[2][1].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[2][2].m_state, State.DEAD);
	}
	
	@Test
	public void DeadCell3Neighbors() 
	{
		int[][] cells = new int[][]{{0,1,0},
									{0,1,1},
									{0,0,0}};
		
		Board board = getBoardAfterIteration(cells);
		
		Assert.assertEquals(board.m_world[0][0].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[0][2].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[1][0].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[2][0].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[2][1].m_state, State.ALIVE);
		Assert.assertEquals(board.m_world[2][2].m_state, State.ALIVE);
	}
	
	@Test
	public void DeadCellLMoreThan3Neighbors() 
	{
		int[][] cells = new int[][]{{0,1,0},
									{0,1,1},
									{0,0,1}};
			
		Board board = getBoardAfterIteration(cells);
		Assert.assertEquals(board.m_world[0][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[0][2].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[1][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[2][0].m_state, State.DEAD);
		Assert.assertEquals(board.m_world[2][1].m_state, State.DEAD);
	}
	
	private Board getBoardAfterIteration(int[][] startingBoard)
	{
		Board board = new Board(Game.generateBoard(startingBoard));
		
		board.Iterate();
		
		return board;
	}
}