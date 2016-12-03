

import java.util.HashSet;
import java.util.Set;

public class Cell implements ICellStateListener
{
	public int m_x;
	public int m_y;
	public State m_state;
	public Set<Cell> m_neighbors = new HashSet<>();
	public int m_numOfLivingNeighbors = 0;
	
	public Cell(int x, int y) 
	{
		this(State.DEAD, x , y);
	}
	
	public Cell(State state, int x, int y)
	{
		m_state = state;
		m_x = x;
		m_y = y;
	}
	
	public void addNeighbor(Cell neighbor)
	{
		if(neighbor != null && !neighbor.equals(this))
		{
			m_neighbors.add(neighbor);
		}
	}
	
	public boolean Iterate()
	{
		if (m_state == State.ALIVE) 
		{
		    if (m_numOfLivingNeighbors < 2 || m_numOfLivingNeighbors > 3)
		    {
		    	m_state = State.DEAD;
		    	return true;
		    } 
		} 
		else if (m_state == State.DEAD)
		{
		    if (m_numOfLivingNeighbors == 3)
		    {
		    	m_state = State.ALIVE;
		    	return true;
		    } 
		}
		
		return false;
	}
	
	public void notifyStateChanged()
	{
		for (Cell gameCell : m_neighbors) 
		{
			gameCell.stateChanged(m_state);
		}
	}
	
	@Override
	public void stateChanged(State newState)
	{
		if (newState == State.ALIVE)
		{
			m_numOfLivingNeighbors++;
		}
		else
		{
			m_numOfLivingNeighbors--;
		}
	}
	
	public State getState() 
	{
		return m_state;
	}
	
	@Override
	public String toString() 
	{
		return m_state == State.ALIVE ? "X" : ".";
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
	    int result = 1;
	    result = prime * result + (int) (m_x ^ (m_x >>> 32));
	    result = prime * result + (int) (m_y ^ (m_y >>> 32));
	    return result;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj != null && obj instanceof Cell)
		{
			Cell cell = (Cell) obj;
			return m_x == cell.m_x && m_y == cell.m_y;
		}
		
		return false;
	}
}