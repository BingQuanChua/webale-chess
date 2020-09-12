package webale;

import java.io.IOException;

public class Sun extends Piece{ 
	//private Movement movement;
    
	public Sun(String color, boolean flipState, String ImageURL) throws IOException{
			super(color, flipState, ImageURL); 
			//movement = new SunMovement();
	}

	@Override
	public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) 
	{ 
		// we can't move the piece to a Spot that 
		// has a piece of the same color 
		if (endpoint.getChessPiece().getColour() == this.getColour()) { 
			return false; 
		} 

		int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX()); 
		int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY()); 
		if (x + y == 1) { 
			// check if this move will not result in the king 
			// being attacked if so return true 
			return true; 
		}else return false;
	} 
} 
