// This class is for arrow moving forward starting from its original position
// Tan Jia Qi

package webale;

public class ArrowForwardMovement implements Movement {

        // This method is called by the method canMove() in the Arrow class to verify if the Piece's movement is valid. 
	// If valid, return true; if invalid, return false.
        public boolean move(final Coordinate[][] COORDINATE, final Coordinate START_POINT, final Coordinate END_POINT) {
                final int SOURCE_COORDINATE_X = START_POINT.getCoorX();
                final int SOURCE_COORDINATE_Y = START_POINT.getCoorY();
                final int DEST_COORDINATE_X = END_POINT.getCoorX();
                final int DEST_COORDINATE_Y = END_POINT.getCoorY();
                boolean success = true;                         // success -> the moving of arrow by players is valid: true; 
                                                                // success -> the moving of arrow by players is not valid: false 

                // check destination got piece or not, if got piece cannot move
                if (COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece() != null) {
                        if (COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X].getChessPiece()
                                        .getIsRedColor() == COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X].getChessPiece()
                                                        .getIsRedColor()) {
                                success = false;
                                return success;
                        }
                }

                // when moving red arrow
                if (START_POINT.getChessPiece().getIsRedColor()) {
                        // move 1 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 1
                        // eg.(0,6) --> (0,5)
                        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && (DEST_COORDINATE_Y == SOURCE_COORDINATE_Y - 1)) {
                                success = true;
                                return success;
                        }

                        // move 2 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 2
                        // eg.(6,6) --> (6,4)
                        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && (DEST_COORDINATE_Y == SOURCE_COORDINATE_Y - 2)) {
                                // check path between destination and source have other pieces or not
                                // no piece in between: can move, got piece: cannot move
                                // eg.(6,5)
                                if (COORDINATE[SOURCE_COORDINATE_Y - 1][SOURCE_COORDINATE_X].getChessPiece() == null) {
                                        success = true;
                                        return success;         
                                } else {
                                        success = false;
                                        return success;
                                }
                        }
                }
                // moving blue arrow
                else if (!START_POINT.getChessPiece().getIsRedColor()) {
                        // move 1 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 1
                        // eg.(0,1) --> (0,2)
                        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && (DEST_COORDINATE_Y == SOURCE_COORDINATE_Y + 1)) {
                                success = true;
                                return success;
                        }

                        // move 2 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 2
                        // eg.(6,1) --> (6,3)
                        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && (DEST_COORDINATE_Y == SOURCE_COORDINATE_Y + 2)) {
                                // check path between destination and source have other pieces or not
                                // no piece in between: can move, got piece: cannot move
                                // eg.(6,2)
                                if (COORDINATE[SOURCE_COORDINATE_Y + 1][SOURCE_COORDINATE_X].getChessPiece() == null) {
                                        success = true;
                                        return success;
                                } else {
                                        success = false;
                                        return success;
                                }
                        }
                }

                return false;
        }
}