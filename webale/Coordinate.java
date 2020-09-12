//This is a class to store the Chess piece and it will also store it position on the board
package webale;

public class Coordinate
{
	private Piece chessPiece;
	private int coordinateX;
	private int coordinateY;

	Coordinate()
	{
		this.coordinateX = 0;
		this.coordinateY = 0;
		this.chessPiece = null;
	}

	//initialize the coordinate with its position on the board and the chess piece it will contain
	Coordinate(int coordinateX, int coordinateY, Piece chessPiece)
	{
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.chessPiece = chessPiece;
	}

	//initialize the coordinate with its position
	Coordinate(int coordinateX, int coordinateY)
	{
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public int getCoorX()
	{
		return coordinateX;
	}

	public int getCoorY()
	{
		return coordinateY;
	}

	public Piece getChessPiece()
	{
		return chessPiece;
	}

	public void setChessPiece(Piece chessPiece)
	{
		this.chessPiece = chessPiece;
	}

	
}