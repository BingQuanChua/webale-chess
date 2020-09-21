//This class in responsible to receive command from Controller class and run the game operation
package webale;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game{
    
    private BoardFrame boardFrame;
    private HomeFrame homeFrame;
    private boolean isRedPlayer = true;
      
    // startPoint -> first tile clicked (source coordinate)
    // endPoint -> second tile clicked (destination coordinate)
    // isCheckmated -> to identify the checkmate status of both players, if the Sun of one of the players is checkmated, then it will set to true
    private Coordinate startPoint = null;
    private Coordinate endPoint = null;
    private boolean isCheckmated = false;
    
    public Game(HomeFrame homeFrame, BoardFrame boardFrame){
        this.homeFrame = homeFrame;
        this.boardFrame = boardFrame;
    }
    
    // set state of triangle and plus (triangle <--> plus) when player moves chess pieces twice
    // Chua Bing Quan
    public void toggleState() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece() instanceof StateChangingPiece) {
                    Piece temp = boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece();
                    try {
                        if (temp.getState() instanceof TriangleMovement)
                            temp.setState(new PlusMovement());
                        else if (temp.getState() instanceof PlusMovement)
                            temp.setState(new TriangleMovement());
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    

    // to move piece and check if the movement is valid
    // Denise Lee Chia Xuan, Lee Min Xuan, Tan Jia Qi
    public boolean movePiece(ChessTile chessTileClicked, int timeClicked) {
        
        Coordinate[][] coordinate = boardFrame.getGameBoard().getCoordinateArray();
        // check if the chesstile selected as startPoint is valid
        if (timeClicked % 2 != 0) {
            // if chesstile clicked as startPoint is empty, return false.
            if (coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece() == null){
                return false;
            }
                
            // if piece on chesstile clicked as startPoint is not the same colour as the
            // piece of player on move, return false.
            else if (coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece()
                    .getIsRedColor() != isRedPlayer) {
                return false;
            }
        }
        
        // check if the movement to the chesstile selected as endPoint is valid
        if (timeClicked % 2 == 0) {
            endPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            // if the movement to endPoint is valid
            if (startPoint != null && startPoint.getChessPiece().canMove(coordinate, startPoint, endPoint)) {
                // if endPoint has Piece on it, play kill_piece.wav, else play drop_chess.wav
                if(endPoint.getChessPiece() != null){
                    playSound("./sounds/kill_piece.wav");
                } else{
                    playSound("./sounds/drop_chess.wav");
                }

                // if the Piece on endPoint is Sun, the player on move has won and game over
                if (hasWin(endPoint.getChessPiece())) {
                    playSound("./sounds/win.wav");
                    gameOver();
                }

                // if arrow has reached the end, turn its direction
                if (endPoint.getCoorY() == 0 || endPoint.getCoorY() == 7) {
                    changeArrowState(startPoint.getChessPiece());
                }
                
                // replace piece at the endPoint with the piece at the startPoint
                endPoint.setChessPiece(startPoint.getChessPiece());
                startPoint.setChessPiece(null);
                boardFrame.getGameBoard().repaint();

                // check if the game is draw
                boardFrame.getGameBoard().checkDraw();
                if (boardFrame.getGameBoard().getRemainingPieceSize() == 2) {
                    playSound("./sounds/Rubber-Duck.wav");
                    drawGame();
                } else {
                    boardFrame.getGameBoard().resetCheckDraw();
                }
                
                // check checkmate if no player is checkmated before to ensure only show checkmate message once
                if (isCheckmated == false){
                    boardFrame.getGameBoard().checkmate(); 
                    // only left 1 blue piece (Sun)
                    if (boardFrame.getGameBoard().getRemainingBluePieceSize() == 1) {
                        playSound("./sounds/checkmate.wav");
                        isCheckmated = true;
                        checkmateBlue();               
                    }
                    // only left 1 red piece (Sun)
                    else if (boardFrame.getGameBoard().getRemainingRedPieceSize() == 1) {
                        playSound("./sounds/checkmate.wav");
                        isCheckmated = true;
                        checkmateRed();               
                    }
                    boardFrame.getGameBoard().resetCheckmate();
                }
                return true;
            // if the movement is invalid
            } else {
                playSound("./sounds/wrong.wav"); 
                //can change the path to "./sounds/wrong2.wav" or "./sounds/wrong3.wav" or "./sounds/wrong4.wav"for different sound effects.
                
                return false;
            }
        // if startPoint is valid
        } else {
            playSound("./sounds/pickup_chess.wav");
            startPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            return true;
        }
    }

    // change arrow state when arrow reaches the other edge of gameboard
    // Denise Lee Chia Xuan
    private void changeArrowState(Piece arrow) {
        if (arrow instanceof Arrow) {
            ((Arrow) arrow).changeMovement();
            arrow.toggleFlippedState();
        }
    }

    // Denise Lee Chia Xuan
    private boolean hasWin(Piece pieceKilled) {
        return pieceKilled instanceof Sun ? true : false;
    }

    // Denise Lee Chia Xuan
	private void gameOver() {
        String playerWon = isRedPlayer ? "Red" : "Blue";
        new GameOver(boardFrame, playerWon);
        homeFrame.getContinueButton().setEnabled(false);
        boardFrame.setVisible(false);
    }

    // Lee Min Xuan
    private void drawGame() {
        String playerWon = "Draw";
        new GameOver(boardFrame, playerWon);
        homeFrame.getContinueButton().setEnabled(false);
        boardFrame.setVisible(false);
    }

    // Tan Jia Qi
    private void checkmateRed() {
        String playerWon = "BlueCheckmateRed";
        new GameOver(boardFrame, playerWon);
    }

    // Tan Jia Qi
    private void checkmateBlue() {
        String playerWon = "RedCheckmateBlue";
        new GameOver(boardFrame, playerWon);
    }

    // Denise Lee Chia Xuan
    public void playSound(String soundName){
        try{
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundName));
           
           Clip clip = AudioSystem.getClip();
           clip.open(audioInputStream);
           clip.start();
        }
        catch(Exception ex){
           System.out.println("Error with playing sound.");
           ex.printStackTrace();
        }
   }

   public boolean getIsRedPlayer(){
       return isRedPlayer;
   }

   public void setIsRedPlayer(boolean isRedPlayer){
       this.isRedPlayer = isRedPlayer;
   }

   public void setBoardFrame(BoardFrame boardFrame){
       this.boardFrame = boardFrame;
   }
}



