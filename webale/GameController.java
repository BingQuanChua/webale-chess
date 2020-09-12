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
        boolean hasPiece = false;

        @Override
        public void actionPerformed(ActionEvent e){
            if(gameboard != null){
                timeClicked++;
                System.out.println(timeClicked);
                hasPiece = gameboard.movePiece((ChessTiles)e.getSource(), timeClicked);
                if(!hasPiece){
                    timeClicked--;
                }
            }
        }
    };
    //Game controller

}