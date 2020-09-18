package webale;

import java.io.IOException;

public class Plus extends Piece {
	private Movement movement;

	public Plus(boolean isRedColor, String imageUrl) throws IOException {
		super(isRedColor, imageUrl);
		movement = new PlusMovement();
	}

	// @Override
	// public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate
	// endpoint)
	// {
	// if (endpoint.getChessPiece().getColour() == this.getColour()) {
	// return false;
	// }

	// int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX());
	// int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY());

	// if (x == startpoint.getCoorX() || y == startpoint.getCoorY()){
	// return true;
	// }else return false;
	// }

	public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
		return movement.move(coordinate, startPoint, endPoint);
	}

	public String toString() {
		return (getIsRedColor() ? "Red " : "Blue ") + "Plus";
	}
}