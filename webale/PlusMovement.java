// This class is for plus moving in any directions (up/down/left/right) and any steps player wants
// Denise Lee Chia Xuan

package webale;

public class PlusMovement implements Movement {

    // This method is called by the method canMove() in the Plus class to verify if the Piece's movement is valid.
    // If valid, return true; if invalid, return false.
    public boolean move(final Coordinate[][] COORDINATE, final Coordinate START_POINT, final Coordinate END_POINT) {
        final int SOURCE_COORDINATE_X = START_POINT.getCoorX();
        final int SOURCE_COORDINATE_Y = START_POINT.getCoorY();
        final int DEST_COORDINATE_X = END_POINT.getCoorX();
        final int DEST_COORDINATE_Y = END_POINT.getCoorY();
        final Coordinate SOURCE_COORDINATE = COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X];
        final Coordinate DEST_COORDINATE = COORDINATE[DEST_COORDINATE_Y][DEST_COORDINATE_X];

        // check if destination has piece that is same colour
        if (DEST_COORDINATE.getChessPiece() != null) {
            if ((SOURCE_COORDINATE.getChessPiece().getIsRedColor() && DEST_COORDINATE.getChessPiece().getIsRedColor())
                    || (!SOURCE_COORDINATE.getChessPiece().getIsRedColor()
                            && !DEST_COORDINATE.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // move vertical, x-axis no change , y-axis COORDINATE any
        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X && DEST_COORDINATE_Y != SOURCE_COORDINATE_Y) {
            final int distanceMovedOfY = DEST_COORDINATE_Y - SOURCE_COORDINATE_Y;
            // move upwards
            if (distanceMovedOfY < 0) {
                for (int i = 1; i < Math.abs(distanceMovedOfY); i++) {
                    final Coordinate temp = COORDINATE[SOURCE_COORDINATE_Y - i][SOURCE_COORDINATE_X];
                    if (temp.getChessPiece() != null) {
                        return false;
                    }
                }
            }

            // move downwards
            else {
                for (int i = 1; i < distanceMovedOfY; i++) {
                    final Coordinate temp = COORDINATE[SOURCE_COORDINATE_Y + i][SOURCE_COORDINATE_X];
                    if (temp.getChessPiece() != null) {
                        if ((temp.getChessPiece().getIsRedColor() && temp.getChessPiece().getIsRedColor())
                                || (!temp.getChessPiece().getIsRedColor() && !temp.getChessPiece().getIsRedColor())) {
                            return false;
                        }
                    }
                }
            }

            return true;
        }

        // move horizontal, x-axis COORDINATE any, y-axis no change
        else if (DEST_COORDINATE_Y == SOURCE_COORDINATE_Y && DEST_COORDINATE_X != SOURCE_COORDINATE_X) {
            final int distanceMovedOfX = DEST_COORDINATE_X - SOURCE_COORDINATE_X;
            // move left
            if (distanceMovedOfX < 0) {
                for (int i = 1; i < Math.abs(distanceMovedOfX); i++) {
                    final Coordinate temp = COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X - i];
                    if (temp.getChessPiece() != null) {
                        return false;
                    }
                }
            }
            // move right
            else {
                for (int i = 1; i < distanceMovedOfX; i++) {
                    final Coordinate temp = COORDINATE[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X + i];
                    if (temp.getChessPiece() != null) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;

    }

}
