package webale;


public class ArrowBackwardMovement implements Movement 
{
        public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint){
                int sourceCoordinateX = startPoint.getCoorX();
                int sourceCoordinateY = startPoint.getCoorY();
                int destCoordinateX = endPoint.getCoorX();
                int destCoordinateY = endPoint.getCoorY();
                boolean success = true;

                //check destination got piece or not, got piece cannot move  
                if (coordinate[destCoordinateY][destCoordinateX].getChessPiece() != null )
                {
                        if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece().getIsRedColor() == coordinate[destCoordinateY][destCoordinateX].getChessPiece().getIsRedColor())
                        {
                                success = false;
                        }
                }

                //move 1 step backward at one time
                //on y-axis & backward, so destination Y is behind source Y by 1
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 1))
                {
                                success = true;
                }

                //move 2 step backward at one time
                //on y-axis & backward, so destination Y is behind source Y by 2
                if (destCoordinateX == sourceCoordinateX && (destCoordinateY == sourceCoordinateY - 2))
                {
                        //check path between destination and source have other pieces or not
                         if (coordinate[sourceCoordinateY-1][sourceCoordinateX].getChessPiece() == null)
                        {
                                success = true;
                        }
                        else
                        {
                                success = false;
                        }
                }            
                return success;
        }      
}