// This class is for plus moving in any directions (up/down/left/right) by one step
// Lee Min Xuan

package webale;

public class SunMovement implements Movement {

	// This method is called by the method canMove() in the Sun class to verify if
	// the Piece's movement is valid.
	// If valid, return true; if invalid, return false.
	public boolean move(final Coordinate[][] COORDINATE, final Coordinate START_POINT, final Coordinate END_POINT) {

		final int SOURCE_COORDINATE_X = START_POINT.getCoorX();
		final int SOURCE_COORDINATE_Y = START_POINT.getCoorY();
		final int DEST_COORDINATE_X = END_POINT.getCoorX();
		final int DEST_COORDINATE_Y = END_POINT.getCoorY();
		boolean isDifferentColour = true;

		// check destination tile got piece, got same colour piece cannot move
		if (COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece() != null) {
			if (COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X].getChessPiece()
					.getIsRedColor() == COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece().getIsRedColor()) {
				isDifferentColour = false;
			}
		}

		// move forward, x-axis no change , y-axis COORDINATE -1
		if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y - 1 && isDifferentColour) {
			return true;
		}

		// move backward, x-axis no change, y-axis COORDINATE +1
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y + 1
				&& isDifferentColour) {
			return true;
		}

		// move right, x-axis COORDINATE + 1, y-axis no change
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X + 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y
				&& isDifferentColour) {
			return true;
		}

		// move left, x-axis COORDINATE -1, y-axis no change
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X - 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y
				&& isDifferentColour) {
			return true;
		}

		// move piece upwards left, x-axis COORDINATE -1, y-axis COORDINATE -1
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X - 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y - 1
				&& isDifferentColour) {
			return true;
		}

		// move piece upwards right, x-axis COORDINATE +1, y-axis COORDINATE -1
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X + 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y - 1
				&& isDifferentColour) {
			return true;
		}

		// move piece downwards right, x-axis COORDINATE +1, y-axis COORDINATE +1
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X + 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y + 1
				&& isDifferentColour) {
			return true;
		}

		// move piece downwards left, x-axis COORDINATE -1, y-axis COORDINATE +1
		else if (DEST_COORDINATE_X == SOURCE_COORDINATE_X - 1 && DEST_COORDINATE_Y == SOURCE_COORDINATE_Y + 1
				&& isDifferentColour) {
			return true;
		}

		return false;
	}
}
