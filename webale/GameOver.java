// This class is a GUI that will pop up when the game end. It takes in a parameter to determine the end game outcome.
package webale;

import javax.swing.*;

public class GameOver extends JOptionPane {

	public GameOver(JDialog jDialog, String playerWon) {
		if (playerWon == "Draw") {
			showMessageDialog(jDialog, "Draw!");
		} else {
			showMessageDialog(jDialog, playerWon + " has won the game!");
		}
	}
}