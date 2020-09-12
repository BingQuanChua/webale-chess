package OurAssignment;

import java.io.IOException;

public class Chevron extends Piece {
    public Chevron(String color, boolean flipState, String ImageURL) throws IOException 
	{ 
        super(color, flipState, ImageURL);
	} 

	@Override
	public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) 
	{ 
		if (endpoint.getChessPiece().getColor() == this.getColor()) { 
			return false; 
		} 

		int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX()); 
		int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY()); 
		if (x == y) { 
			return true; 
		}else return false;
	} 
}