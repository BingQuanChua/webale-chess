// This class is for chess piece, Arrow that inherited from abstract class Piece.

package webale;

import java.io.IOException;

public class Arrow extends Piece {
	private Movement movement;

	public Arrow(final boolean isRedColor, final String imageUrl) throws IOException {
		super(isRedColor, imageUrl);
		movement = new ArrowForwardMovement();
	}

	public Arrow(final boolean isRedColor, final String imageUrl, final String movement) throws IOException {
		super(isRedColor, imageUrl);

		if (movement.equals("Forward")) {
			this.movement = new ArrowForwardMovement();
		} else {
			this.movement = new ArrowBackwardMovement();
		}
	}

	public Movement getMovement() {
		return movement;
	}

	// change movement to backward when arrow reaches the other edge of the gameboard
	public void changeMovement() {
		if (movement instanceof ArrowForwardMovement) {
			movement = new ArrowBackwardMovement();
		} else {
			movement = new ArrowForwardMovement();
		}
	}

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

	// arrow information for saving in file
	public String toString() {
		return (getIsRedColor() ? "Red " : "Blue ") + "Arrow " + movementToString();
	}
}
