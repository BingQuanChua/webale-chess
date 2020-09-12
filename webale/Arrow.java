package webale;

import java.io.IOException;

public class Arrow extends Piece{
    private Movement movement;

    public Arrow(boolean isRed, boolean flipState, String imageURL) throws IOException{
        super(isRed, flipState, imageURL); 
        movement = new ArrowBackwardMovement();
    }

    // @Override
    // public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) {
    //     // we can't move the piece to a Spot that
    //     // has a piece of the same color
    //     if (endpoint.getChessPiece().getColour() == this.getColour()) {
    //         return false;
    //     }

    //     int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX());
    //     int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY());
    //     if ((x == 1 && y == 0) || (x == 0 && y == 1)) {
    //         return true;
    //     } else
    //         return false;
    // }
    public boolean canMove(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(gameBoard, startPoint, endPoint);
	} 
}
