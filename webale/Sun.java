package webale;

import java.io.IOException;

public class Sun extends Piece{ 
	private Movement movement;

	
    
	public Sun(boolean isRed, boolean flipState, String imageURL) throws IOException{
        super(isRed, flipState, imageURL); 
			movement = new SunMovement();
	}

	@Override
	public boolean canMove(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(gameBoard, startPoint, endPoint);
	} 
} 
