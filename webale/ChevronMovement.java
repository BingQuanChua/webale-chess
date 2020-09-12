package webale;

public class ChevronMovement implements Movement {
    public boolean move(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint) {
        int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
		int destCoordinateY = endPoint.getCoorY();
        
        return true;
    }
}