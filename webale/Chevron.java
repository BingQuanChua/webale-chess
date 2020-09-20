// This class is for chess piece, Chevron that inherited from abstract class Piece.

package webale;

import java.io.IOException;

public class Chevron extends Piece {
	private Movement movement;

	public Chevron(boolean isRedColor, String imageUrl) throws IOException {
		super(isRedColor, imageUrl);
		movement = new ChevronMovement();
	}

	// This boolean method is called in movePiece() method to confirm the Chevron Chess Piece can move
	// It will return boolean method move in movement
	public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
		return movement.move(coordinate, startPoint, endPoint);
	}

	// chevron information for saving in file
	public String toString() {
		return (getIsRedColor() ? "Red " : "Blue ") + "Chevron";
	}
	
	public void setState(Movement movement) {

	}

	public Movement getState() {
        return movement;
    }
}