package webale;

import java.io.IOException;

public class Plus extends Piece {
    public Plus(String color, boolean flipState, String ImageURL) throws IOException
	{ 
        super(color, flipState, ImageURL); 
	} 

	@Override
	public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) 
	{ 
		if (endpoint.getChessPiece().getColour() == this.getColour()) { 
			return false; 
		} 

		int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX()); 
        int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY()); 
        
		if (x == startpoint.getCoorX() || y == startpoint.getCoorY()){ 
			return true; 
		}else return false;
	} 
}