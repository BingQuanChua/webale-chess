//LOW CHI HAN
//This class is used by Chessboard class to create chesstiles that would be packed together to form a chessboard.
package OurAssignment;
import javax.swing.*;  

public class ChessTiles extends JButton
{
	private int coordinateX;
	private int coordinateY;
	private Coordinate coordinate;
	private Piece piece;

	public ChessTiles(int coordinateX, int coordinateY)
	{
		this.coordinateX =  coordinateX;
		this.coordinateY =  coordinateY;
	}

	public ChessTiles(Coordinate coordinate){
		this(coordinate, null);
	}

	public ChessTiles(Coordinate coordinate, Piece chesspiece){
		this.coordinate = coordinate;
		this.piece = chesspiece;
	}

	//Get the x coordinate relative to other ChessTiles.
	public int getCoorX()
	{
		return coordinateX;
	}

	//Get the x coordinate relative to other ChessTiles.
	public int getCoorY()
	{
		return coordinateY;
	}

}