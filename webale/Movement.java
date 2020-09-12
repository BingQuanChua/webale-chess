package webale;

public interface Movement {

        Coordinate initCoordinate = new Coordinate();
        Coordinate finalCoordinate = new Coordinate();

        public boolean move(int sourceCoordinateX, int sourceCoordinateY, int destCoordinateX, int destCoordinateY, Coordinate[][] coordinate);

}