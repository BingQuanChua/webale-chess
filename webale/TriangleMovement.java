package webale;

public class TriangleMovement implements Movement {
    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        final int sourceCoordinateX = startPoint.getCoorX();
        final int sourceCoordinateY = startPoint.getCoorY();
        final int destCoordinateX = endPoint.getCoorX();
        final int destCoordinateY = endPoint.getCoorY();
        Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];

        // check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null) {
            // destination piece is own piece
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())
                    || (!sourceCoordinate.getChessPiece().getIsRedColor() && !destCoordinate.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // not moving diagonally
        if (destCoordinateX == sourceCoordinateX || destCoordinateY == sourceCoordinateY) {
            return false;
        }

        if (((sourceCoordinateX - sourceCoordinateY) != (destCoordinateX - destCoordinateY)) &&  ((sourceCoordinateX + sourceCoordinateY) != (destCoordinateX + destCoordinateY))){
            return false;
        }

        // moving upper left
        if (destCoordinateX < sourceCoordinateX && destCoordinateY > sourceCoordinateY) {
            for (int x = destCoordinateX+1, y = destCoordinateY-1; x < sourceCoordinateX; x++, y--) {
                Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving upper right
        if (destCoordinateX > sourceCoordinateX && destCoordinateY > sourceCoordinateY) {
            for (int x = destCoordinateX-1, y = destCoordinateY-1; x > sourceCoordinateX; x--, y--) {
                Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward left
        if (destCoordinateX < sourceCoordinateX && destCoordinateY < sourceCoordinateY) {
            for (int x = destCoordinateX+1, y = destCoordinateY+1; x < sourceCoordinateX; x++, y++) {
                Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        // moving downward right
        if (destCoordinateX > sourceCoordinateX && destCoordinateY < sourceCoordinateY) {
            for (int x = destCoordinateX-1, y = destCoordinateY+1; x > sourceCoordinateX; x--, y++) {
                Coordinate temp = coordinate[y][x];
                if (temp.getChessPiece() != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}