// This class is for the usage of gameboard class to create multiple chess tiles that being added on top of blank gameboard, 
// to form a gameboard that can be clicked.

package webale;

import javax.swing.*;

public class ChessTile extends JButton {

	private static final long serialVersionUID = 1L;
	private final int coordinateX;
	private final int coordinateY;

	public ChessTile(final int coordinateX, final int coordinateY) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	// Get the x coordinate relative to other ChessTile.
	public int getCoorX() {
		return coordinateX;
	}

	// Get the x coordinate relative to other ChessTile.
	public int getCoorY() {
		return coordinateY;
	}

}