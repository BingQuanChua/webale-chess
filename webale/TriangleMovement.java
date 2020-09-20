// This class is for Triangle Piece moving in any steps diagonally

package webale;

// This method is called by the method canMove() in the Triangle class to verify if the Piece's movement is valid. 
// If valid, return true; if invalid, return false.
public class TriangleMovement implements Movement {
    public boolean move(final Coordinate[][] coordinate, final Coordinate START_POINT, final Coordinate END_POINT) {
        final int SOURCE_COORDINATE_X = START_POINT.getCoorX();
        final int SOURCE_COORDINATE_Y = START_POINT.getCoorY();
        final int DEST_COORDINATE_X = END_POINT.getCoorX();
        final int DEST_COORDINATE_Y = END_POINT.getCoorY();
        final Coordinate SOURCE_COORDINATE = coordinate[SOURCE_COORDINATE_Y][SOURCE_COORDINATE_X];
        final Coordinate DEST_COORDINATE = coordinate[DEST_COORDINATE_Y][DEST_COORDINATE_X];

        // checks if destination has piece with the same colour
        if (DEST_COORDINATE.getChessPiece() != null) {
            // destination piece is own piece
            if ((SOURCE_COORDINATE.getChessPiece().getIsRedColor() && DEST_COORDINATE.getChessPiece().getIsRedColor())
                    || (!SOURCE_COORDINATE.getChessPiece().getIsRedColor()
                            && !DEST_COORDINATE.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // checking if the piece is moving diagonally
        // after moving diagonally, the coordinate of x and y should different from their source
        if (DEST_COORDINATE_X == SOURCE_COORDINATE_X || DEST_COORDINATE_Y == SOURCE_COORDINATE_Y) {
            return false;
        }

        // checking is the piece is moving straight diagonally
        // source (x-y) should equal to destination (x-y)
        //  while moving diagonally right ("\")
        // source (x+y) should equal to destination (x+y)
        //  while moving diagonally left ("/")
        if (((SOURCE_COORDINATE_X - SOURCE_COORDINATE_Y) != (DEST_COORDINATE_X - DEST_COORDINATE_Y))
                && ((SOURCE_COORDINATE_X + SOURCE_COORDINATE_Y) != (DEST_COORDINATE_X + DEST_COORDINATE_Y))) {
            return false;
        }

        // moving upper left
        if (DEST_COORDINATE_X < SOURCE_COORDINATE_X && DEST_COORDINATE_Y > SOURCE_COORDINATE_Y) {
            // checking if there is any pieces blocking its way, making the move invalid
            for (int x = DEST_COORDINATE_X + 1, y = DEST_COORDINATE_Y - 1; x < SOURCE_COORDINATE_X; x++, y--) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving upper right
        if (DEST_COORDINATE_X > SOURCE_COORDINATE_X && DEST_COORDINATE_Y > SOURCE_COORDINATE_Y) {
            for (int x = DEST_COORDINATE_X - 1, y = DEST_COORDINATE_Y - 1; x > SOURCE_COORDINATE_X; x--, y--) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward left
        if (DEST_COORDINATE_X < SOURCE_COORDINATE_X && DEST_COORDINATE_Y < SOURCE_COORDINATE_Y) {
            for (int x = DEST_COORDINATE_X + 1, y = DEST_COORDINATE_Y + 1; x < SOURCE_COORDINATE_X; x++, y++) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward right 
        if (DEST_COORDINATE_X > SOURCE_COORDINATE_X && DEST_COORDINATE_Y < SOURCE_COORDINATE_Y) {
            for (int x = DEST_COORDINATE_X - 1, y = DEST_COORDINATE_Y + 1; x > SOURCE_COORDINATE_X; x--, y++) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}