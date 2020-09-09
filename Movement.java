package OurAssignment;

public interface Movement {

        Coordinate initCoordinate = new Coordinate();
        Coordinate finalCoordinate = new Coordinate();
        Piece piece = new Piece();

        public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate);

}