// This class is for chevron moving in L shape, chevron can skip other pieces
// Koh Han Yi

package webale;

public class ChevronMovement implements Movement {

	// This method is called by the method canMove() in the Chevron class to verify if the Piece's movement is valid. 
	// If valid, return true; if invalid, return false.
	public boolean move(Coordinate[][] COORDINATE, Coordinate START_POINT, Coordinate END_POINT) {
		final int SOURCE_COORDINATE_X = START_POINT.getCoorX();
		final int SOURCE_COORDINATE_Y = START_POINT.getCoorY();
		final int DEST_COORDINATE_X = END_POINT.getCoorX();
		final int DEST_COORDINATE_Y = END_POINT.getCoorY();

		int differenceX = Math.abs(DEST_COORDINATE_X - SOURCE_COORDINATE_X);
		int differenceY = Math.abs(DEST_COORDINATE_Y - SOURCE_COORDINATE_Y);

		//          L shape in horizontal                        L shape in vertical
		if ((differenceX == 2 && differenceY == 1) || (differenceX == 1 && differenceY == 2)) {
			// check destination got piece or not, if no piece can move
			// if destination have same colour piece, then cannot move
			if (COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece() == null) {
				return true;
			} else if ((COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece()
					.getIsRedColor()) == COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X].getChessPiece()
							.getIsRedColor()) {
				return false;
			} else {
				return true;
			}
		}
		
		return false;
	}
}
