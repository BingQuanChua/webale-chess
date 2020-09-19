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
                for(int i = 1; i < Math.abs(distanceMovedOfY); i++){
                    Coordinate temp = coordinate[sourceCoordinateY - i][sourceCoordinateX];
                    if (temp.getChessPiece() != null){
                            return false;
                    }
                }
            }
            
            //move downwards
            else{
                for(int i = 1; i < distanceMovedOfY; i++){
                    Coordinate temp = coordinate[sourceCoordinateY + i][sourceCoordinateX];
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
                for(int i = 1; i < Math.abs(distanceMovedOfX); i++){
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX - i];
                    if (temp.getChessPiece() != null){
                        return false;
                    }
                }
            }
            //move right
            else{
                for(int i = 1; i < distanceMovedOfX; i++){
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX + i];
                    if (temp.getChessPiece() != null){
                        return false;
                    }
                }
            }
            
			return true;
        }      
        
        return false;
        
    }

}