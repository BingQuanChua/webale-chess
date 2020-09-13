package webale;

public class ChevronMovement implements Movement {
    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        int sourceCoordinateX = startPoint.getCoorX();
		int sourceCoordinateY = startPoint.getCoorY();
		int destCoordinateX = endPoint.getCoorX();
		int destCoordinateY = endPoint.getCoorY();
        boolean success = true;

        // Moving Direction : Bottom Right

		if (destCoordinateX > sourceCoordinateX && destCoordinateY > sourceCoordinateY)
		{
            for (int i = sourceCoordinateX + 1 , j = sourceCoordinateY + 1 ; i <= destCoordinateX; i++, j++)
				{
					//check not final des
					if (i != destCoordinateX && j != destCoordinateY)
					{
						// check in between got obstruct
						if (coordinate[i][j].getChessPiece() != null)
						{
                            success = !success;
							return success;
						}	
					}

					else
					{
						//if it is final des
						if (i == destCoordinateX && j == destCoordinateY)
						{
							//check desti got obstruct 
							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece().getIsRedColor() == (coordinate[j][i].getChessPiece().getIsRedColor()))
								{
									//if got pieces then check colour
                                    success = !success;
                                    return success;
                                }
                            }
						}

						else
						{
							return false;
						}
					}
				}
		}



		else if (destCoordinateX > sourceCoordinateX && destCoordinateY < sourceCoordinateY)
		{
            for (int i = sourceCoordinateX + 1 , j = sourceCoordinateY - 1 ; i <= destCoordinateX; i++, j--)
				{
					if (i != destCoordinateX && j != destCoordinateY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							success = !success;
							return success;
						}
						
					}

					else
					{
						if (i == destCoordinateX && j == destCoordinateY)
						{
							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece().getIsRedColor() == (coordinate[i][j].getChessPiece().getIsRedColor()))
								{
									success = !success;
							        return success;
                                }
						}
						else
						{
							return success;
						}
					}
                }
            }
		}

		else if (destCoordinateX < sourceCoordinateX && destCoordinateY > sourceCoordinateY)
		{
			for (int i = sourceCoordinateX - 1 , j = sourceCoordinateY + 1 ; i >= destCoordinateX; i--, j++)
				{
					if (i != destCoordinateX && j != destCoordinateY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{	
							return false;
						}

						
					}
					else
					{
						if (i == destCoordinateX && j == destCoordinateY)
						{

							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece().getIsRedColor() == (coordinate[i][j].getChessPiece().getIsRedColor()))
								{
									return false;
                                }
						}
						else
						{
							return false;
						}
					}
                }
            }
		}

		else if (destCoordinateX < sourceCoordinateX && destCoordinateY < sourceCoordinateY)
		{
			for (int i = sourceCoordinateX - 1 , j = sourceCoordinateY - 1 ; i >= destCoordinateX; i--, j--)
				{
					if (i != destCoordinateX && j != destCoordinateY)
					{
						if (coordinate[i][j].getChessPiece() != null)
						{
							return false;
						}
						
					}

					else
					{
						if (i == destCoordinateX && j == destCoordinateY)
						{
							if (coordinate[i][j].getChessPiece() != null )
							{
								if (coordinate[sourceCoordinateY][sourceCoordinateX].getChessPiece().getIsRedColor() == (coordinate[i][j].getChessPiece().getIsRedColor()))
								{
									
									return false;
								}
							}
						}

						else
						{
							return false;
						}

					}
					
				}
		}

		else
		{
			return false;
		}

        return true;
    }
}
