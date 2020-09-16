package webale;

public class ChevronMovement implements Movement {
	public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
		int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
		int destCoordinateY = endPoint.getCoorY();

		int differenceX = Math.abs(destCoordinateX - sourceCoordinateX);
		int differenceY = Math.abs(destCoordinateY - sourceCoordinateY);

		if (differenceX == 2 && differenceY == 1) {
			if (coordinate[destCoordinateY][destCoordinateX].getChessPiece() == null) {
				return true;
			} else if ((coordinate[destCoordinateY][destCoordinateX].getChessPiece()
					.getIsRedColor()) == coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece()
							.getIsRedColor()) {
				return false;
			} else {
				return true;
			}
		}

		else if (differenceX == 1 && differenceY == 2) {
			if (coordinate[destCoordinateY][destCoordinateX].getChessPiece() == null) {
				return true;
			} else if ((coordinate[destCoordinateY][destCoordinateX].getChessPiece()
					.getIsRedColor()) == coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece()
							.getIsRedColor()) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
}
