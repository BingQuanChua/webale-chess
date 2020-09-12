package OurAssignment;

public class SunMovement implements Movement {
    public boolean move(int sourceCoorX, int sourceCoorY, int destCoorX, int destCoorY, Coordinate[][] coordinate){
        
        
		//move forward, x-axis no change , y-axis coordinate +1 
		if (destCoorX == sourceCoorX && destCoorY == sourceCoorY + 1)
		{	
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
            } else return true;
		}

		//move backward, x-axis no change, y-axis coordinate -1
		else if (destCoorX == sourceCoorX && destCoorY == sourceCoorY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move right, x-axis coordinate + 1, y-axis no change
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move left, x-axis coordinate -1, y-axis no change 
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece upwards left, x-axis coordinate -1, y-axis coordinate +1
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY + 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece upwards right, x-axis coordinate +1, y-axis coordinate +1
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY + 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move piece downwards right, x-axis coordinate +1, y-axis coordinate -1
		else if (destCoorX == sourceCoorX + 1 && destCoorY == sourceCoorY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

		//move slant downwards left, x-axis coordinate -1, y-axis coordinate -1
		else if (destCoorX == sourceCoorX - 1 && destCoorY == sourceCoorY - 1)
		{
			//check destination got piece, got piece cannot move
			if (coordinate[destCoorX][destCoorY].getChessPiece() != null )
			{
				if (coordinate[sourceCoorX][sourceCoorY].getChessPiece().getColor().equals(coordinate[destCoorX][destCoorY].getChessPiece().getColor()))
				{
					return false;
				}
			}else return true;
		}

        return true;
    }
}
