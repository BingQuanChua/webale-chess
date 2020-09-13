package webale;

import java.io.IOException;

public class Arrow extends Piece{
    private Movement movement;

    public Arrow(boolean isRed, boolean flipState, String imageURL) throws IOException{
        super(isRed, flipState, imageURL); 
        movement = new ArrowForwardMovement();
    }

	public Advancer(boolean isRed, boolean flipState, String imageURL, String movement)
	{
		super(isRed, flipState, imageURL);

		if (movement == "Forward")
		{
			this.movement = new ArrowForwardMovement();
		}
		else
		{
			this.movement= new ArrowBackwardMovement();
		}
    }
    
    public Movement getMovement()
	{
		return movement;
	}
    
    public void changeMovement()
    {
        if (movement instanceof ArrowForwardMovement)
        {
            movement = new ArrowBackwardMovement();
        }
        else
        {
            movement = new ArrowForwardMovement();
        }
    }

    @Override
    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
    } 
    
    // make the type of piece to string for saving
	public String pieceToString() 
	{
		return "Arrow";
	}

	// make the movement type to string for saving
	public String movementToString() 
	{
		if (movement instanceof ArrowForwardMovement)
		{
			return "Forward";	
		}

		else
		{
			return "Backward";
		}
	}
}
