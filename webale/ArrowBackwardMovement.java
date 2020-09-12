package webale;


public class ArrowBackwardMovement implements Movement 
{
        public boolean move(int sourceCoordinateX, int sourceCoordinateY, int destCoordinateX, int destCoordinateY, Coordinate[][] coordinate){
            
                //move 1 step backward at one time
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1))
                {
                        

                }
        }
}