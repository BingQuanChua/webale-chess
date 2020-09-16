package webale;

public class TriangleMovement implements Movement {
    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
        int destCoordinateY = endPoint.getCoorY();
        Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];
        
        // check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null){
            // destination piece is own piece
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())|| (!sourceCoordinate.getChessPiece().getIsRedColor() && !destCoordinate.getChessPiece().getIsRedColor())){
                return false;
            }
        }

        // not moving diagonally
        if (destCoordinateX == sourceCoordinateX || destCoordinateY == sourceCoordinateY){
            return false;
        }

        // moving diagonally right (\)
        if ((sourceCoordinateX - sourceCoordinateY) == (destCoordinateX - destCoordinateY)) {
            return true;
        }
        
        // moving diagonally left (/)
        if ((sourceCoordinateX + sourceCoordinateY) == (destCoordinateX + destCoordinateY))  {
            return true;
        }
        
        return false;
    }
}