package webale;

//public class ArrowForwardMovement implements Movement {
    
///}

public class ArrowForwardMovement implements Movement 
{
        public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint){
                int sourceCoordinateX = startPoint.getCoorX();
                int sourceCoordinateY = startPoint.getCoorY();
                int destCoordinateX = endPoint.getCoorX();
                int destCoordinateY = endPoint.getCoorY();
            
                //move 1 step backward at one time
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1))
                {
                        

                }
        }
}