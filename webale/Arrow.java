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
		} 
		else {
			this.movement = new ArrowBackwardMovement();
		}
	}

	public Movement getMovement() {
		return movement;
	}

	public void changeMovement() {
		if (movement instanceof ArrowForwardMovement) {
			movement = new ArrowBackwardMovement();
		}
		else {
			movement = new ArrowForwardMovement();
		}
	}

	@Override
	public boolean canMove(final Coordinate[][] coordinate, final Coordinate startPoint, final Coordinate endPoint) {
		return movement.move(coordinate, startPoint, endPoint);
	}

	// make the movement type to string for saving
	public String movementToString() {
		if (movement instanceof ArrowForwardMovement) {
			return "Forward";
		}
		else {
			return "Backward";
		}
	}

	public String toString() {
		return (getIsRedColor() ? "Red " : "Blue ") + "Arrow " + movementToString();
	}
}
