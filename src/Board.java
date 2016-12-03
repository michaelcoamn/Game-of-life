

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board 
{
	public int m_size;
    public Cell[][] m_world;
    public Random m_rng;
    public int m_precent;
	
    public Board(Cell[][] world) 
    {
    	m_world = world;
    	m_size = m_world.length;
    	
    	init();
	}
    
    private void init()
    {
		setNeighbors();
    }
    
    private void setNeighbors() 
    {
    	for (int y = 0; y < m_size; y++ ) 
		{
		    for (int x = 0; x < m_size; x++) 
		    {
		    	Cell currentCell = m_world[x][y];
		    	
		    	int leftX = (x - 1 >= 0 ) ? x - 1 : m_size - 1;
		    	
		    	int rightX = (x + 1) % m_size;
		    	
		    	int upY = (y - 1) >= 0 ?  y - 1 : m_size - 1;
		    	
		    	int downY = (y + 1) % m_size;
		    		
		    	// Make cell connections
		    	currentCell.addNeighbor(m_world[leftX][upY]);
		    	currentCell.addNeighbor(m_world[leftX][downY]);
		    	currentCell.addNeighbor(m_world[leftX][y]);
		    	
		    	currentCell.addNeighbor(m_world[rightX][upY]);
		    	currentCell.addNeighbor(m_world[rightX][downY]);
		    	currentCell.addNeighbor(m_world[rightX][y]);

		    	currentCell.addNeighbor(m_world[x][upY]);
		    	currentCell.addNeighbor(m_world[x][downY]);
		    	
		    	if(currentCell.getState() == State.ALIVE)
		    	{
		    		currentCell.notifyStateChanged();
		    	}
		    }
		}
 	}
    
    public String Iterate()
    {
    	String retVal = "";
    	Set<Cell> modifiedCells = new HashSet<>();
    	
    	for (int y = 0; y < m_size; y++ ) 
		{
    		String line = ""
    				;
		    for (int x = 0; x < m_size; x++) 
		    {
		    	Cell currentCell = m_world[y][x];
		    	
		    	boolean stateChanged = currentCell.Iterate();
		    	
		    	if(stateChanged)
		    	{
		    		modifiedCells.add(currentCell);
		    	}
		    	
		    	line += currentCell.toString();
		    }
		    
		    retVal += line + "\n";
		}
    	
    	for (Cell gameCell : modifiedCells) {
			gameCell.notifyStateChanged();
		}
    	
    	
    	return retVal;
    }
    
    public void printBoard()
    {
    	String retVal = "";
    	
    	for (int y = 0; y < m_size; y++ ) 
		{
    		String line = "";
    		
		    for (int x = 0; x < m_size; x++) 
		    {
		    	Cell currentCell = m_world[y][x];
		    	line += currentCell.toString();
		    }
		    
		    retVal += line + "\n";
		}
    	
    	System.out.println(retVal);
    }
}
