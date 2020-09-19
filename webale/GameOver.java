// This class is a GUI that will pop up when the game end. It takes in a parameter to determine the end game outcome.
package webale;

import javax.swing.*;

public class GameOver extends JOptionPane {

	public GameOver(JDialog jDialog, String playerWon) {
		if (playerWon == "Draw") {
			showMessageDialog(jDialog, "Draw!");
		}

		else if (playerWon == "RedCheckmateBlue") {
			showMessageDialog(jDialog, "Red player has won the game! Blue Sun is checkmated.");
		} else if (playerWon == "BlueCheckmateRed") {
			showMessageDialog(jDialog, "Blue player has won the game! Red Sun is checkmated.");
		}

		else {
			showMessageDialog(jDialog, playerWon + " has won the game!");
		}
	}
}