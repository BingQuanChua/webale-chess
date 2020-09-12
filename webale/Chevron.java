package webale;

import java.io.IOException;

public class Chevron extends Piece {
	private Movement movement;

    public Chevron(boolean isRed, boolean flipState, String imageURL) throws IOException{
		super(isRed, flipState, imageURL); 
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
	public boolean canMove(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(gameBoard, startPoint, endPoint);
	} 
}