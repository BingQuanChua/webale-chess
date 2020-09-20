// This class is the model class for the game to let the controller class (GameController) to control the game value and view class (BoardFrame) to execute. 
// The class includes the initialization of the HomeFrame (home page), BoardFrame (game page), and GameController.
package webale;

import java.io.IOException;

public class WebaleDriver {

	//Main method
	public static void main(String[] args) throws IOException {

		HomeFrame homeFrame = new HomeFrame();
		BoardFrame boardFrame = new BoardFrame();
		Game game = new Game(homeFrame, boardFrame);
		new GameController(homeFrame, boardFrame, game);
	}
}
