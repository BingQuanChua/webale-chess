package webale;

import java.io.IOException;

public class Arrow extends Piece{
    private Movement movement;

    public Arrow(boolean isRed, boolean flipState, String imageURL) throws IOException{
        super(isRed, flipState, imageURL); 
        movement = new ArrowBackwardMovement();
    }

    @Override
    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
	} 
}
