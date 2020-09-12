package webale;


public class ArrowBackwardMovement implements Movement 
{
        public boolean move(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint){
                int sourceCoordinateX = startPoint.getCoorX();
                int sourceCoordinateY = startPoint.getCoorY();
                int destCoordinateX = endPoint.getCoorX();
                int destCoordinateY = endPoint.getCoorY();
                
                return true;
                //move 1 step backward at one time
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1))
                {
                        
            
                }
        }
}