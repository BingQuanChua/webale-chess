// This class is for Triangle Piece moving in any steps diagonally

package webale;

// This method is called by the method canMove() in the Triangle class to verify if the Piece's movement is valid. 
// If valid, return true; if invalid, return false.
public class TriangleMovement implements Movement {
    public boolean move(final Coordinate[][] coordinate, final Coordinate startPoint, final Coordinate endPoint) {
        final int sourceCoordinateX = startPoint.getCoorX();
        final int sourceCoordinateY = startPoint.getCoorY();
        final int destCoordinateX = endPoint.getCoorX();
        final int destCoordinateY = endPoint.getCoorY();
        final Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        final Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];

        // checks if destination has piece with the same colour
        if (destCoordinate.getChessPiece() != null) {
            // destination piece is own piece
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())
                    || (!sourceCoordinate.getChessPiece().getIsRedColor()
                            && !destCoordinate.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // checking if the piece is moving diagonally
        // after moving diagonally, the coordinate of x and y should different from their source
        if (destCoordinateX == sourceCoordinateX || destCoordinateY == sourceCoordinateY) {
            return false;
        }

        // checking is the piece is moving straight diagonally
        // source (x-y) should equal to destination (x-y)
        //  while moving diagonally right ("\")
        // source (x+y) should equal to destination (x+y)
        //  while moving diagonally left ("/")
        if (((sourceCoordinateX - sourceCoordinateY) != (destCoordinateX - destCoordinateY))
                && ((sourceCoordinateX + sourceCoordinateY) != (destCoordinateX + destCoordinateY))) {
            return false;
        }

        // moving upper left
        if (destCoordinateX < sourceCoordinateX && destCoordinateY > sourceCoordinateY) {
            // checking if there is any pieces blocking its way, making the move invalid
            for (int x = destCoordinateX + 1, y = destCoordinateY - 1; x < sourceCoordinateX; x++, y--) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving upper right
        if (destCoordinateX > sourceCoordinateX && destCoordinateY > sourceCoordinateY) {
            for (int x = destCoordinateX - 1, y = destCoordinateY - 1; x > sourceCoordinateX; x--, y--) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward left
        if (destCoordinateX < sourceCoordinateX && destCoordinateY < sourceCoordinateY) {
            for (int x = destCoordinateX + 1, y = destCoordinateY + 1; x < sourceCoordinateX; x++, y++) {
                final Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward right 
        if (destCoordinateX > sourceCoordinateX && destCoordinateY < sourceCoordinateY) {
            for (int x = destCoordinateX - 1, y = destCoordinateY + 1; x > sourceCoordinateX; x--, y++) {
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