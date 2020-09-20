// This is a class for storing the pieces and its respective position on the gameboard
// Koh Han Yi

package webale;

public class Coordinate {
	private Piece chessPiece;
	private int coordinateX;
	private int coordinateY;

	Coordinate() {
		this.coordinateX = 0;
		this.coordinateY = 0;
		this.chessPiece = null;
	}

	// initialize the coordinate with its position on the board and the chess piece
	// it will contain
	Coordinate(int coordinateX, int coordinateY, Piece chessPiece) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.chessPiece = chessPiece;
	}

	// initialize the coordinate with its position
	Coordinate(int coordinateX, int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	// method to get Coordinate - X
	public int getCoorX() {
		return coordinateX;
	}

	// method to get Coordinate - Y 
	public int getCoorY() {
		return coordinateY;
	}

	// method to get the Chess Piece
	public Piece getChessPiece() {
		return chessPiece;
	}

	// method to set a Chess Piece
	public void setChessPiece(Piece chessPiece) {
		this.chessPiece = chessPiece;
	}

	// method to print Coordinate in a Coordinate format : (x,y)
	public String toString() {
		return "(" + coordinateX + ", " + coordinateY + ")";
	}

}