package webale;

public class SunMovement implements Movement {
    public boolean move(int sourceCoordinateX, int sourceCoordinateY, int destCoordinateX, int destCoordinateY, Coordinate[][] coordinate){
        
        
		//move forward, x-axis no change , y-axis coordinate +1 
		if (destCoordinateX == sourceCoordinateX && destCoordinateY == sourceCoordinateY + 1)
		{	
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
            } else return true;
		}

		//move backward, x-axis no change, y-axis coordinate -1
		else if (destCoordinateX == sourceCoordinateX && destCoordinateY == sourceCoordinateY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move right, x-axis coordinate + 1, y-axis no change
		else if (destCoordinateX == sourceCoordinateX + 1 && destCoordinateY == sourceCoordinateY)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move left, x-axis coordinate -1, y-axis no change 
		else if (destCoordinateX == sourceCoordinateX - 1 && destCoordinateY == sourceCoordinateY)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece upwards left, x-axis coordinate -1, y-axis coordinate +1
		else if (destCoordinateX == sourceCoordinateX - 1 && destCoordinateY == sourceCoordinateY + 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece upwards right, x-axis coordinate +1, y-axis coordinate +1
		else if (destCoordinateX == sourceCoordinateX + 1 && destCoordinateY == sourceCoordinateY + 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece downwards right, x-axis coordinate +1, y-axis coordinate -1
		else if (destCoordinateX == sourceCoordinateX + 1 && destCoordinateY == sourceCoordinateY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

		//move slant downwards left, x-axis coordinate -1, y-axis coordinate -1
		else if (destCoordinateX == sourceCoordinateX - 1 && destCoordinateY == sourceCoordinateY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoordinateX][destCoordinateY].getChessPiece() != null )
			{
				if (coordinate[sourceCoordinateX][sourceCoordinateY].getChessPiece().getIsRedColor() == (coordinate[destCoordinateX][destCoordinateY].getChessPiece().getIsRedColor()))
				{
					return false;
				}
			}else return true;
		}

        return true;
    }
}
