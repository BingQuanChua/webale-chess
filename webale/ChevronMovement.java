// This class is for chevron moving in L shape, chevron can skip other pieces

package webale;

public class ChevronMovement implements Movement {
	public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
		int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
		int destCoordinateY = endPoint.getCoorY();

		int differenceX = Math.abs(destCoordinateX - sourceCoordinateX);
		int differenceY = Math.abs(destCoordinateY - sourceCoordinateY);

		// L shape in horizontal                  L shape in vertical
		if ((differenceX == 2 && differenceY == 1) || (differenceX == 1 && differenceY == 2)) {
			// check destination got piece or not, if no piece can move
			// if destination have same colour piece, then cannot move
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
