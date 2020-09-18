package webale;

public class TriangleMovement implements Movement {
    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        final int sourceCoordinateX = startPoint.getCoorX();
        final int sourceCoordinateY = startPoint.getCoorY();
        final int destCoordinateX = endPoint.getCoorX();
        final int destCoordinateY = endPoint.getCoorY();
        final int midCoordinateX = (int) (startPoint.getCoorX() + endPoint.getCoorX() / 2);
        final int midCoordinateY = (int) (startPoint.getCoorY() + endPoint.getCoorY() / 2);
        Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];

        // check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null) {
            // destination piece is own piece
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())
                    || (!sourceCoordinate.getChessPiece().getIsRedColor()
                            && !destCoordinate.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // not moving diagonally
        if (destCoordinateX == sourceCoordinateX || destCoordinateY == sourceCoordinateY) {
            return false;
        }

        // if(Math.abs(sourceCoordinateX-destCoordinateX) == 1 &&
        // Math.abs(sourceCoordinateY-destCoordinateY) == 1 &&
        // destCoordinate.getChessPiece()==null){
        // // moving diagonally right(\)
        // if ((sourceCoordinateX - sourceCoordinateY) == (destCoordinateX -
        // destCoordinateY)){
        // return true;
        // }

        // // moving diagonally left(/)
        // if ((sourceCoordinateX + sourceCoordinateY) == (destCoordinateX +
        // destCoordinateY)) {
        // return true;
        // }

        // }else if (Math.abs(sourceCoordinateX-destCoordinateX) > 1 &&
        // Math.abs(sourceCoordinateY-destCoordinateY) > 1 &&
        // coordinate[midCoordinateX][midCoordinateY].getChessPiece() == null &&
        // destCoordinate.getChessPiece()==null){
        // // moving diagonally right(\)
        // if ((sourceCoordinateX - sourceCoordinateY) == (destCoordinateX -
        // destCoordinateY)){
        // return true;
        // }

        // // moving diagonally left(/)
        // if ((sourceCoordinateX + sourceCoordinateY) == (destCoordinateX +
        // destCoordinateY)) {
        // return true;
        // }
        // }else return false;

        // moving diagonally right(\)
        if ((sourceCoordinateX - sourceCoordinateY) == (destCoordinateX - destCoordinateY)) {
            return true;
        }

        // moving diagonally left(/)
        if ((sourceCoordinateX + sourceCoordinateY) == (destCoordinateX + destCoordinateY)) {
            return true;
        }
        return false;
    }
}