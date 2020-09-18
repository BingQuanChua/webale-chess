//edited by Justin Tey
//This class is the model class for the game to let the controller class to modify the game value and playingframe to project it. The class will include the initialization
// of the board, keeping track on the number of move maked by each user, the player to move, to change the state of some of the pieces like excel , tercel , advancer , Trident
// when it achieve certain point, this class also include the method to save and load the game and flipping the board when its needed.
package webale;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import javax.swing.UIManager.*;
import javax.swing.*;

public class Game {
	public static void main(String[] args) throws IOException {
		// try {
		// for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		// if ("Nimbus".equals(info.getName())) {
		// UIManager.setLookAndFeel(info.getClassName());
		// break;
		// }
		// }
		// } catch (Exception e) {
		// // If Nimbus is not available, you can set the GUI to another look and feel.
		// }

		HomeFrame homeFrame = new HomeFrame();
		BoardFrame boardFrame = new BoardFrame();
		GameController gameController = new GameController(homeFrame, boardFrame);
	}

}

