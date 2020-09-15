package webale;

import java.io.IOException;

public class Chevron extends Piece {
	private Movement movement;

    public Chevron(boolean isRedColor, String imageUrl) throws IOException{
		super(isRedColor, imageUrl); 
		movement = new ChevronMovement();
	}
	
	// @Override
	// public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) 
	// { 
	// 	if (endpoint.getChessPiece().getColour() == this.getColour()) { 
	// 		return false; 
	// 	} 

	// 	int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX()); 
	// 	int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY()); 
	// 	if (x == y) { 
	// 		return true; 
	// 	}else return false;
	// } 
	public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
	} 

	public String toString(){
		return "Chevron"; 
	}
}