// This class is for the usage of gameboard class to create multiple chess tiles that being added on top of blank gameboard, 
// to form a gameboard that can be clicked.
// Koh Han Yi

package webale;

import javax.swing.*;

public class ChessTile extends JButton {

	private static final long serialVersionUID = 1L;
	private final int COORDINATEX;
	private final int COORDINATEY;

	public ChessTile(final int COORDINATEX, final int COORDINATEY) {
		this.COORDINATEX = COORDINATEX;
		this.COORDINATEY = COORDINATEY;
	}

	// Get the x coordinate relative to other ChessTile.
	public int getCoorX() {
		return COORDINATEX;
	}

	// Get the x coordinate relative to other ChessTile.
	public int getCoorY() {
		return COORDINATEY;
	}

}