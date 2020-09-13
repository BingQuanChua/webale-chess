package webale;

import java.io.IOException;

public class Sun extends Piece{ 
	private Movement movement;
    
	public Sun(boolean isRed, boolean flipState, String imageURL) throws IOException{
        super(isRed, flipState, imageURL); 
			movement = new SunMovement();
	}

	public Sun(boolean b, String string) {
	}

	@Override
	public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
	} 
} 
