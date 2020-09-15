package webale;

import java.io.IOException;

public class Triangle extends Piece {

    private Movement movement;

    public Triangle(boolean isRedColor, String imageUrl) throws IOException{
        super(isRedColor, imageUrl); 
        movement = new TriangleMovement();
    }


    // public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) {
    //     // we can't move the piece to a Spot that
    //     // has a piece of the same color
    //     if (endpoint.getChessPiece().getColour() == this.getColour()) {
    //         return false;
    //     }

    //     int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX());
    //     int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY());
    //     if ((x == 1 && y == 2) || (x == 2 && y == 1)) {
    //         return true;
    //     } else
    //         return false;
    // }
    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
    } 
    
    public String toString(){
		return (getIsRedColor() ? "Red " : "Blue ") + "Triangle"; 
	}
}