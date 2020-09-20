// This class is for plus moving in any directions (up/down/left/right) and any steps player wants

package webale;

public class PlusMovement implements Movement {

    // This method is called by the method canMove() in the Plus class to verify if the Piece's movement is valid.
    // If valid, return true; if invalid, return false.
    public boolean move(final Coordinate[][] coordinate, final Coordinate startPoint, final Coordinate endPoint) {
        final int sourceCoordinateX = startPoint.getCoorX();
        final int sourceCoordinateY = startPoint.getCoorY();
        final int destCoordinateX = endPoint.getCoorX();
        final int destCoordinateY = endPoint.getCoorY();
        final Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        final Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];

        // check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null) {
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())
                    || (!sourceCoordinate.getChessPiece().getIsRedColor()
                            && !destCoordinate.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // move vertical, x-axis no change , y-axis coordinate any
        if (destCoordinateX == sourceCoordinateX && destCoordinateY != sourceCoordinateY) {
            final int distanceMovedOfY = destCoordinateY - sourceCoordinateY;
            // move upwards
            if (distanceMovedOfY < 0) {
                for (int i = 1; i < Math.abs(distanceMovedOfY); i++) {
                    final Coordinate temp = coordinate[sourceCoordinateY - i][sourceCoordinateX];
                    if (temp.getChessPiece() != null) {
                        return false;
                    }
                }
            }

            // move downwards
            else {
                for (int i = 1; i < distanceMovedOfY; i++) {
                    final Coordinate temp = coordinate[sourceCoordinateY + i][sourceCoordinateX];
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

        // move horizontal, x-axis coordinate any, y-axis no change
        else if (destCoordinateY == sourceCoordinateY && destCoordinateX != sourceCoordinateX) {
            final int distanceMovedOfX = destCoordinateX - sourceCoordinateX;
            // move left
            if (distanceMovedOfX < 0) {
                for (int i = 1; i < Math.abs(distanceMovedOfX); i++) {
                    final Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX - i];
                    if (temp.getChessPiece() != null) {
                        return false;
                    }
                }
            }
            // move right
            else {
                for (int i = 1; i < distanceMovedOfX; i++) {
                    final Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX + i];
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
