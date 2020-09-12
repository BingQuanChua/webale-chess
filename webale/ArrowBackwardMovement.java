package webale;


public class ArrowBackwardMovement implements Movement 
{
        public boolean move(GameBoard gameBoard, Coordinate startPoint, Coordinate endPoint){

                return true;
                //move 1 step backward at one time
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1))
                {
                        

                }
        }
}