//This class is used by Chessboard class to create chesstiles that would be packed together to form a chessboard.
package webale;
import javax.swing.*;  

public class ChessTiles extends JButton {
	private int coordinateX;
	private int coordinateY;

	public ChessTiles(int coordinateX, int coordinateY) {
		this.coordinateX =  coordinateX;
		this.coordinateY =  coordinateY;
	}

	//Get the x coordinate relative to other ChessTiles.
	public int getCoorX() {
		return coordinateX;
	}

	//Get the x coordinate relative to other ChessTiles.
	public int getCoorY() {
		return coordinateY;
	}

}