package webale;

public interface Movement {

        Coordinate initCoordinate = new Coordinate();
        Coordinate finalCoordinate = new Coordinate();

        public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate);

}