package OurAssignment;

public class King { 
    
	public King(boolean white) 
	{ 
		super(white); 
	} 

	public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) 
	{ 
		// we can't move the piece to a Spot that 
		// has a piece of the same color 
		if (endpoint.getChessPiece().getColor() == this.getColor()) { 
			return false; 
		} 

		int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX()); 
		int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY()); 
		if (x + y == 1) { 
			// check if this move will not result in the king 
			// being attacked if so return true 
			return true; 
		} 
	} 
} 
