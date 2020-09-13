package webale; 
import java.awt.event.*;


public class GameController {
    GameBoard gameboard = null;
    HomeFrame homeFrame = null;
    
    public GameController(GameBoard gameboard){
        this.gameboard = gameboard;
        setListener();
    }

    public GameController(GameBoard gameboard, HomeFrame homeFrame){
        this.gameboard = gameboard;
        this.homeFrame = homeFrame;
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
                isValidMove = movePiece((ChessTiles)e.getSource(), timeClicked);
                //if chesstile clicked for startpoint is empty
                if(!isValidMove && timeClicked == 1){
                    timeClicked = 0;
                }

                //if chess movement to endpoint is valid
                else if (timeClicked == 2) {
                    timeClicked = 0;
                    if(isValidMove){
                        gameboard.rotateBoard();
                        togglePlayerTurn();
                    }
                    
                }
            }
        }
    };

    Coordinate startPoint = null;
    Coordinate endPoint = null;
    boolean isRedPlayer = true;

    public boolean movePiece(ChessTiles chessTileClicked, int timeClicked){
        Coordinate[][] coordinate = gameboard.getCoordinateArray();
        
        //check if the chesstile selected as startPoint is valid first
        if(timeClicked % 2 != 0){
            //if chesstile clicked as startPoint is empty
            if(coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece() == null)
                return false;
            //if piece on chesstile clicked as startPoint is not the same colour as the player's piece
            else if(coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece().getIsRedColor() != isRedPlayer){
                return false;
            }
        }

        if(timeClicked % 2 == 0){
            endPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            //if moving to endPoint is valid
            if(startPoint != null && startPoint.getChessPiece().canMove(coordinate, startPoint, endPoint)){
                endPoint.setChessPiece(startPoint.getChessPiece());
                startPoint.setChessPiece(null);
                gameboard.revalidate();
                gameboard.repaint();
                //if successfully moved return true, if not return false
                return true;
            } else{
                return false;
            }
        }
        else {
            startPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            return true;
        }
    }

    public void togglePlayerTurn(){
        isRedPlayer = !isRedPlayer;
    }

}