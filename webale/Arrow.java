// This class is for chess piece, Arrow that inherited from abstract class Piece.

package webale;

import java.io.IOException;

public class Arrow extends Piece {
	private Movement movement;

	public Arrow(boolean isRedColor, String imageUrl) throws IOException {
		super(isRedColor, imageUrl);
		movement = new ArrowForwardMovement();
	}

	public Arrow(boolean isRedColor, String imageUrl, String movement) throws IOException {
		super(isRedColor, imageUrl);

		if (movement.equals("Forward")) {
			this.movement = new ArrowForwardMovement();
		} else {
			super.toggleFlippedState();
			this.movement = new ArrowBackwardMovement();
		}
	}

	public Movement getMovement() {
		return movement;
	}

	// change movement to backward when Arrow reaches the other edge of the gameboard
	public void changeMovement() {
		if (movement instanceof ArrowForwardMovement) {
			movement = new ArrowBackwardMovement();
		} else {
			movement = new ArrowForwardMovement();
		}
	}

	// This boolean method is called in movePiece() method to confirm the Arrow Chess Piece can move
	// It will return boolean method move in movement
	@Override
	public boolean canMove(final Coordinate[][] coordinate, final Coordinate startPoint, final Coordinate endPoint) {
		return movement.move(coordinate, startPoint, endPoint);
	}

	// make the movement to string for saving in file
	public String movementToString() {
		if (movement instanceof ArrowForwardMovement) {
			return "Forward";
		} else {
			return "Backward";
		}
	}

	// Arrow information for saving in file
	public String toString() {
		return (getIsRedColor() ? "Red " : "Blue ") + "Arrow " + movementToString();
	}

	public void setState(Movement movement) {

	}

	public Movement getState() {
        return movement;
    }

}
