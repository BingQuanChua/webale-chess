package webale; 
import java.awt.event.*;


public class GameController {
    GameBoard gameboard = null;
    
    public GameController(GameBoard gameboard){
        this.gameboard = gameboard;
        setListener();
    }

    public void setListener(){
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                gameboard.getTileArray()[y][x].addActionListener(chessTileListener);
            }
        }
    }

    ActionListener chessTileListener = new ActionListener(){
        int timeClicked = 0;
        boolean isValidMove = false;

        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println(timeClicked);
            if(gameboard != null){
                timeClicked++;
                isValidMove = gameboard.movePiece((ChessTiles)e.getSource(), timeClicked);
                //if chesstile clicked for startpoint is empty
                if(!isValidMove && timeClicked == 1){
                    timeClicked = 0;
                }

                //if chess movement to endpoint is valid
                else if (timeClicked == 2) {
                    timeClicked = 0;
                    if(isValidMove){
                        gameboard.rotateBoard();
                    }
                    
                }
            }
        }
    };
    //Game controller

}