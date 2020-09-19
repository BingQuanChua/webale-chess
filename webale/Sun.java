package webale;

import java.io.IOException;

public class Sun extends Piece{ 
	private Movement movement;
    
	public Sun(boolean isRedColor, String imageUrl) throws IOException{
        super(isRedColor, imageUrl);
		movement = new SunMovement();
	}

	@Override
	public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
	} 

	public String toString(){
		return (getIsRedColor() ? "Red " : "Blue ") + "Sun"; 
	}

	public void setState(Movement movement) {

	}

	public boolean getState() {
		return true;
	}
} 
