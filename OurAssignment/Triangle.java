package OurAssignment;

import java.io.IOException;

public class Triangle extends Piece {

    public Triangle(String color, boolean flipState, String ImageURL) throws IOException {
        super(color, flipState, ImageURL);
    }

    @Override
    public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint) {
        // we can't move the piece to a Spot that
        // has a piece of the same color
        if (endpoint.getChessPiece().getColor() == this.getColor()) {
            return false;
        }

        int x = Math.abs(startpoint.getCoorX() - endpoint.getCoorX());
        int y = Math.abs(startpoint.getCoorY() - endpoint.getCoorY());
        if ((x == 1 && y == 2) || (x == 2 && y == 1)) {
            return true;
        } else
            return false;
    }
}