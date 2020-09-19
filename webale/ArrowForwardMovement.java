// This class is for arrow moving forward starting from its original position

package webale;

public class ArrowForwardMovement implements Movement {
        public boolean move(final Coordinate[][] coordinate, final Coordinate startPoint, final Coordinate endPoint) {
                final int sourceCoordinateX = startPoint.getCoorX();
                final int sourceCoordinateY = startPoint.getCoorY();
                final int destCoordinateX = endPoint.getCoorX();
                final int destCoordinateY = endPoint.getCoorY();
                boolean success = true;

                // check destination got piece or not, if got piece cannot move
                if (coordinate[destCoordinateY][destCoordinateX].getChessPiece() != null) {
                        if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece()
                                        .getIsRedColor() == coordinate[destCoordinateY][destCoordinateX].getChessPiece()
                                                        .getIsRedColor()) {
                                success = false;
                                return success;
                        }
                }

                // when moving red arrow
                if (startPoint.getChessPiece().getIsRedColor()) {
                        // move 1 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 1
                        // eg.(0,6) --> (0,5)
                        if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1)) {
                                success = true;
                                return success;
                        }

                        // move 2 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 2
                        // eg.(6,6) --> (6,4)
                        if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 2)) {
                                // check path between destination and source have other pieces or not
                                // no piece in between: can move, got piece: cannot move
                                // eg.(6,5)
                                if (coordinate[sourceCoordinateY - 1][sourceCoordinateX].getChessPiece() == null) {
                                        success = true;
                                        return success;         
                                } else {
                                        success = false;
                                        return success;
                                }
                        }
                }
                // moving blue arrow
                else if (!startPoint.getChessPiece().getIsRedColor()) {
                        // move 1 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 1
                        // eg.(0,1) --> (0,2)
                        if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY + 1)) {
                                success = true;
                                return success;
                        }

                        // move 2 step forward at one time
                        // on y-axis & forward, so destination Y is in front of source Y by 2
                        // eg.(6,1) --> (6,3)
                        if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY + 2)) {
                                // check path between destination and source have other pieces or not
                                // no piece in between: can move, got piece: cannot move
                                // eg.(6,2)
                                if (coordinate[sourceCoordinateY + 1][sourceCoordinateX].getChessPiece() == null) {
                                        success = true;
                                        return success;
                                } else {
                                        success = false;
                                        return success;
                                }
                        }
                }

                return false;
        }
}