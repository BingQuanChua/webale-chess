package webale;

public class PlusMovement implements Movement {
    
    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
        int destCoordinateY = endPoint.getCoorY();
        Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];

        //check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null){
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor()) || (!sourceCoordinate.getChessPiece().getIsRedColor() && !destCoordinate.getChessPiece().getIsRedColor())){
                return false;
            }
        }

		//move vertical, x-axis no change , y-axis coordinate any
		if (destCoordinateX == sourceCoordinateX && destCoordinateY != sourceCoordinateY)
		{
            int distanceMovedOfY = destCoordinateY - sourceCoordinateY;
            //move upwards
            if(distanceMovedOfY < 0){
                for(int i = 0; i > distanceMovedOfY + 1; i--){
                    Coordinate temp = coordinate[sourceCoordinateY - 1][sourceCoordinateX];
                    if (temp.getChessPiece() != null){
                        if ((temp.getChessPiece().getIsRedColor() && temp.getChessPiece().getIsRedColor()) || (!temp.getChessPiece().getIsRedColor() && !temp.getChessPiece().getIsRedColor())){
                            return false;
                        }
                    }
                }
            }
            //move downwards
            else{
                for(int i = 0; i < distanceMovedOfY - 1; i++){
                    Coordinate temp = coordinate[sourceCoordinateY + 1][sourceCoordinateX];
                    if (temp.getChessPiece() != null){
                        if ((temp.getChessPiece().getIsRedColor() && temp.getChessPiece().getIsRedColor()) || (!temp.getChessPiece().getIsRedColor() && !temp.getChessPiece().getIsRedColor())){
                            return false;
                        }
                    }
                }
            }
            
			return true;
		}

		//move horizontal, x-axis coordinate any, y-axis no change
		else if (destCoordinateY == sourceCoordinateY && destCoordinateX != sourceCoordinateX) 
		{
            int distanceMovedOfX = destCoordinateX - sourceCoordinateX;
            //move left
            if(distanceMovedOfX < 0){
                for(int i = 0; i > distanceMovedOfX + 1; i--){
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX - 1];
                    if (temp.getChessPiece() != null){
                        if ((temp.getChessPiece().getIsRedColor() && temp.getChessPiece().getIsRedColor()) || (!temp.getChessPiece().getIsRedColor() && !temp.getChessPiece().getIsRedColor())){
                            return false;
                        }
                    }
                }
            }
            //move right
            else{
                for(int i = 0; i < distanceMovedOfX - 1; i++){
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX + 1];
                    if (temp.getChessPiece() != null){
                        if ((temp.getChessPiece().getIsRedColor() && temp.getChessPiece().getIsRedColor()) || (!temp.getChessPiece().getIsRedColor() && !temp.getChessPiece().getIsRedColor())){
                            return false;
                        }
                    }
                }
            }
            
			return true;
        }      
        
        return false;
        
    }

}