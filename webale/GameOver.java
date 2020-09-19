// This class is a GUI (dialog message) will pop up for informing the players that the game ends. 
// Different parameters will determine different game outcomes.

package webale;

import javax.swing.*;

public class GameOver extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public GameOver(JDialog jDialog, String playerWon) {
		
		if (playerWon.equals("Draw")) {
			showMessageDialog(jDialog, "Draw!");
		} else if (playerWon.equals("RedCheckmateBlue")) {
			showMessageDialog(jDialog, "Warning! Blue Sun is checkmated.\nPlayer Blue, you only left with one chess piece, the Sun.");
		} else if (playerWon.equals("BlueCheckmateRed")) {
			showMessageDialog(jDialog, "Warning! Red Sun is checkmated.\nPlayer Red, you only left with one chess piece, the Sun.");
		} else {
			showMessageDialog(jDialog, playerWon + " has won the game!");
		}
	}
}