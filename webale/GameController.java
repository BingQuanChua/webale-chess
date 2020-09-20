// This class is for controlling the game value, setting actionlistener, counting the number of moves by players, tracking the player to move, 
// changing arrow state when needed, saving game in txt file, reading txt file, loading game from txt file, rotating gameboard, checking winning conditions.

package webale;

import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameController {
    BoardFrame boardFrame = null;
    HomeFrame homeFrame = null;
    boolean isFirstGame = true;

    public GameController(HomeFrame homeFrame, BoardFrame boardFrame) {
        this.homeFrame = homeFrame;
        this.boardFrame = boardFrame;
        setHomeFrameListener();
        setBoardFrameListener();
    }

    // setting actionlistener for every button in homeframe
    public void setHomeFrameListener() {
        homeFrame.getStartButton().addActionListener(startBtnListener);
        homeFrame.getContinueButton().addActionListener(continueBtnListener);
        homeFrame.getLoadButton().addActionListener(loadFileBtnListener);
        homeFrame.getInstructionButton().addActionListener(instructionBtnListener);
        homeFrame.getQuitButton().addActionListener(quitBtnListener);
    }

    // setting actionlistener for every button in boardframe
    public void setBoardFrameListener() {
        boardFrame.getToolbar().getBackButton().addActionListener(backBtnListener);
        boardFrame.getToolbar().getSaveButton().addActionListener(saveBtnListener);
        boardFrame.getToolbar().getHelpButton().addActionListener(instructionBtnListener);
        boardFrame.getToolbar().getDefeatButton().addActionListener(defeatBtnListener);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                boardFrame.getGameBoard().getTileArray()[y][x].addActionListener(chessTileListener);
            }
        }
    }

    ActionListener startBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            //If it is not the first time the start new game button is clicked, initialize everything again.
            if (!isFirstGame) {        
                isRedPlayer = true;
                hasFlipped = false;
                moveCount = 0;        
                boardFrame = new BoardFrame();
                setBoardFrameListener();
            }
            boardFrame.setVisible(true);
            isFirstGame = false;
        }
    };

    ActionListener continueBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            boardFrame.setVisible(true);
        }
    };

    int moveCount = 0;
    boolean isRedPlayer = true;
    FileOperation fileOp = new FileOperation(moveCount, isRedPlayer, boardFrame);
    
    ActionListener loadFileBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            isFirstGame = false;

            boardFrame.setVisible(false);
            File file = homeFrame.openLoadDialogAndGetFileToLoad();
            boardFrame.getGameBoard().resetBoard();
            fileOp.readFile(file);
            
            //if the player on move is blue, rotate the board.
            if(!isRedPlayer){
                hasFlipped = false;
                rotateBoard();
            }
        }
    };

    ActionListener instructionBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            JLabel instructionLabel = new JLabel(homeFrame.getInstructionImageIcon());
            JOptionPane.showMessageDialog(null, instructionLabel, "Instruction", JOptionPane.PLAIN_MESSAGE, null);
        }
    };

    ActionListener defeatBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            JLabel defeatLabel = new JLabel(homeFrame.getDefeatImageIcon());
            JOptionPane.showMessageDialog(null, defeatLabel, "You have admitted defeat!", JOptionPane.PLAIN_MESSAGE, null);
            homeFrame.getContinueButton().setEnabled(false);
            boardFrame.setVisible(false);
        }
    };

    ActionListener quitBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            System.exit(0);
        }
    };

    ActionListener backBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            homeFrame.getContinueButton().setEnabled(true);
            boardFrame.setVisible(false);
        }
    };

    ActionListener saveBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("./sounds/press_button.wav");
            File file = boardFrame.getToolbar().openSaveFileDialogAndGetFileToSave();
            fileOp.writeSaveFile(file);
        }
    };

    
    // counting of moves made by players
    ActionListener chessTileListener = new ActionListener() {
        int timeClicked = 0;
        boolean isValidMove = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (boardFrame.getGameBoard() != null) {
                timeClicked++;
                isValidMove = movePiece((ChessTile) e.getSource(), timeClicked);

                //a piece to be moved is selected when timeClicked == 1
                if (timeClicked == 1) {
                    //if the chesstile clicked as startpoint is empty, or, the piece clicked is not the piece of the player on move
                    if(!isValidMove){
                        //reset timeClicked to 0 and reselect the startpoint
                        timeClicked = 0;
                    }
                    //if the chesstile clicked as startpoint is valid
                    else{
                        playSound("./sounds/pickup_chess.wav");
                    }
                    
                }

                //a chesstile to move to is selected when timeClicked == 2
                else if (timeClicked == 2) {
                    //reset timeClicked to 0 to select another startpoint at the next click
                    timeClicked = 0;
                    //if the piece movement to the endpoint is valid
                    if (isValidMove) {
                        playSound("./sounds/drop_chess.wav");
                        // change gameboard state
                        rotateBoard();

                        // change toolbar state
                        isRedPlayer = !isRedPlayer;
                        boardFrame.getToolbar().setPlayerToMove(isRedPlayer ? "Red" : "Blue");
                        moveCount = boardFrame.getToolbar().getMoveCount() + 1;
                        boardFrame.getToolbar().setMoveCount(moveCount);
                        
                        // add state change
                        if (moveCount % 4 == 0) {
                            toggleState();
                        }

                        boardFrame.repaint();

                    //if the piece movement to the endpoint is invalid
                    } else{
                        playSound("./sounds/wrong.wav"); 
                        //can change the path to "./sounds/wrong2.wav" or "./sounds/wrong3.wav" for different sound effects
                    }

                }
            }
        }
    };

    boolean hasFlipped = false;

    // rotate the gameboard when changing player's turn
    public void rotateBoard() {
        hasFlipped = !hasFlipped;

        boardFrame.getGameBoard().removeAll();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (hasFlipped) {
                    boardFrame.getGameBoard().add(boardFrame.getGameBoard().getTileArray()[7 - i][6 - j]);
                } else {
                    boardFrame.getGameBoard().add(boardFrame.getGameBoard().getTileArray()[i][j]);
                }
                //if there is piece on the tile, rotate the imageIcon of the piece 
                if (boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece() != null) {
                    boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece().toggleFlippedState();
                }
            }
        }
    }

    // set state of triangle and plus (triangle <--> plus) when player moves chess pieces twice
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
    
    // startPoint -> first tile clicked (source coordinate)
    // endPoint -> second tile clicked (destination coordinate)
    // isRedPlayer -> to identify the current player is red or blue, if it is blue player, then it will set to false
    // isCheckmated -> to identify the checkmate status of both players, if the Sun of one of the players is checkmated, then it will set to true
    Coordinate startPoint = null;
    Coordinate endPoint = null;
    boolean isCheckmated = false;

    // to move piece and check if the movement is valid
    public boolean movePiece(ChessTile chessTileClicked, int timeClicked) {
        Coordinate[][] coordinate = boardFrame.getGameBoard().getCoordinateArray();

        // check if the chesstile selected as startPoint is valid
        if (timeClicked % 2 != 0) {
            // if chesstile clicked as startPoint is empty, return false.
            if (coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece() == null)
                return false;
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
                // if the Piece on endPoint is Sun, the player on move has won and game over
                if (hasWin(endPoint.getChessPiece())) {
                    gameOver();
                }

                // if arrow has reached the end, turn its direction
                if (endPoint.getCoorY() == 0 || endPoint.getCoorY() == 7) {
                    changeArrowState(startPoint.getChessPiece());
                }
                endPoint.setChessPiece(startPoint.getChessPiece());
                startPoint.setChessPiece(null);
                boardFrame.getGameBoard().repaint();

                // check if the game is draw
                boardFrame.getGameBoard().checkDraw();
                if (boardFrame.getGameBoard().getRemainingPieceSize() == 2) {
                    drawGame();
                } else {
                    boardFrame.getGameBoard().resetCheckDraw();
                }
                
                // check checkmate if no player is checkmated before to ensure only show checkmate message once
                if (isCheckmated == false){
                    boardFrame.getGameBoard().checkmate(); 
                    // only left 1 blue piece (Sun)
                    if (boardFrame.getGameBoard().getRemainingBluePieceSize() == 1) {
                        isCheckmated = true;
                        checkmateBlue();               
                    }
                    // only left 1 red piece (Sun)
                    else if (boardFrame.getGameBoard().getRemainingRedPieceSize() == 1) {
                        isCheckmated = true;
                        checkmateRed();               
                    }
                    boardFrame.getGameBoard().resetCheckmate();
                }
                // if the movement is valid and successfully moved return true, if not, return false.
                return true;
            } else {
                return false;
            }
        } else {
            startPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            
            return true;
        }
    }


    // change arrow state when arrow reaches the other edge of gameboard
    private void changeArrowState(Piece arrow) {
        if (arrow instanceof Arrow) {
            ((Arrow) arrow).changeMovement();
            arrow.toggleFlippedState();
        }
    }

    private boolean hasWin(Piece pieceKilled) {
        return pieceKilled instanceof Sun ? true : false;
    }

    private void gameOver() {
        String playerWon = isRedPlayer ? "Red" : "Blue";
        new GameOver(boardFrame, playerWon);
        homeFrame.getContinueButton().setEnabled(false);
        boardFrame.setVisible(false);
    }

    private void drawGame() {
        String playerWon = "Draw";
        new GameOver(boardFrame, playerWon);
        homeFrame.getContinueButton().setEnabled(false);
        boardFrame.setVisible(false);
    }

    private void checkmateRed() {
        String playerWon = "BlueCheckmateRed";
        new GameOver(boardFrame, playerWon);
    }

    private void checkmateBlue() {
        String playerWon = "RedCheckmateBlue";
        new GameOver(boardFrame, playerWon);
    }

    // // write and save current game situation in txt file 
    // private void writeSaveFile(File file) {
    //     if (file == null) {
    //         return;
    //     }

    //     BufferedWriter bw = null;
    //     try {
    //         bw = new BufferedWriter(new FileWriter(file));
    //         bw.write("###################################################");
    //         bw.newLine();
    //         bw.write("|              WEBALE CHESS SAVE FILE             |");
    //         bw.newLine();
    //         bw.write("###################################################");
    //         bw.newLine();
    //         bw.write("Turn: " + (isRedPlayer ? "Red" : "Blue"));
    //         bw.newLine();
    //         bw.write("Move Count: " + moveCount);
    //         bw.newLine();
    //         bw.write("###################################################");
    //         bw.newLine();
    //         for (int y = 0; y < 8; y++) {
    //             for (int x = 0; x < 7; x++) {
    //                 Piece piece = boardFrame.getGameBoard().getCoordinateArray()[y][x].getChessPiece();
    //                 if (piece != null) {
    //                     bw.write(piece + " (" + x + ", " + y + ")");
    //                     bw.newLine();
    //                 }
    //             }
    //         }
    //         bw.write("###################################################");
    //         bw.newLine();
    //         bw.write("########################END########################");
    //     } catch (Exception e) {
    //         JOptionPane.showMessageDialog(null, "Error saving file. ", "Error", JOptionPane.ERROR_MESSAGE);
    //     } finally {
    //         try {
    //             if (bw != null) {
    //                 bw.close();
    //             }
    //         } catch (Exception e) {
    //             System.out.println("Error in closing the BufferedWriter. ");
    //             e.printStackTrace();
    //             System.out.println();
    //         }
    //     }
    // }

    // // read the saved txt file 
    // private void readFile(File file) {
    //     if (file == null) {
    //         return;
    //     }
    //     BufferedReader br = null;
    //     try {
    //         String fileName = file.getName();
    //         int i = fileName.lastIndexOf('.');
    //         String extension = "";

    //         // To check if the selected file is of txt
    //         if (i > 0) {
    //             extension = fileName.substring(i + 1);
    //             if (!extension.equals("txt")) {
    //                 throw new Exception("Incorrect file type.");
    //             }
    //         }
            
    //         br = new BufferedReader(new FileReader(file));
    //         String strCurrentLine;
    //         boolean isWebaleChessFile = false;

    //         // Start reading the file line by line
    //         while ((strCurrentLine = br.readLine()) != null) {

    //             // We are using ##### to separate the information in the file 
    //             // So we need to ignore this line while reading
    //             if (strCurrentLine.trim().indexOf('#') == 0 || strCurrentLine.startsWith(" "))
    //                 continue;

    //             // This is to check if the selected txt is for Webale Chess or not 
    //             if (strCurrentLine.startsWith("|")) {
    //                 String[] tokens = strCurrentLine.substring(1).trim().split(" ");
    //                 if (tokens[0].equals("WEBALE") && tokens[1].equals("CHESS") && tokens[2].equals("SAVE")
    //                         && tokens[3].equals("FILE")) {
    //                     isWebaleChessFile = true;
    //                 }
    //             }

    //             // This is to check if the current turn is blue player or not
    //             // if current turn is blue player, then isRedPlayer will be set to false, vice versa
    //             if (strCurrentLine.startsWith("T")) {
    //                 String[] tokens = strCurrentLine.split(" ");
    //                 if (tokens[1].equals("Blue")) {
    //                     isRedPlayer = false;
    //                 } else
    //                     isRedPlayer = true;

    //                 boardFrame.getToolbar().setPlayerToMove(isRedPlayer? "Red" : "Blue");    
    //             }

    //             // This is to get the current move count
    //             if (strCurrentLine.startsWith("M")) {
    //                 StringBuilder countStr = new StringBuilder();
    //                 for (int r = 12; r < strCurrentLine.toCharArray().length; r++)
    //                     countStr.append(strCurrentLine.toCharArray()[r]);

    //                 moveCount = Integer.parseInt(countStr.toString());
    //                 boardFrame.getToolbar().setMoveCount(moveCount);
    //             }

    //             // From this line onwards, the file contains the information for pieces
    //             // Format: Color Piece (CoordinateX, CoordinateY) For example: Red Plus (1,1) / Blue Plus (1,1)
    //             // We are using another method which is iniPiece to handle it  
    //             if (strCurrentLine.startsWith("B") || strCurrentLine.startsWith("R")) {
    //                 initPiece(strCurrentLine);
    //             }
    //         }
    //         if (!isWebaleChessFile) {
    //             throw new Exception("Webale Chess save file is not selected.");
    //         }
    //         boardFrame.setVisible(true);
    //     } catch (Exception e) {
    //         JOptionPane.showMessageDialog(null,
    //                 "Error reading file. " + e.getMessage() + "\n(" + file.getAbsolutePath() + ")", "Error",
    //                 JOptionPane.ERROR_MESSAGE);
    //     } finally {
    //         try {
    //             if (br != null) {
    //                 br.close();
    //             }
    //         } catch (Exception e) {
    //             System.out.print("Error in closing the BufferedReader. ");
    //             e.printStackTrace();
    //             System.out.println();
    //         }
    //     }
    // }

    // // restore the game board from saved txt file
    // public void initPiece(String line) throws IOException {
    //     if (line.startsWith("B") || line.startsWith("R")) {
    //         String[] tokens = line.split(" ");
    //         String colour;
    //         String piece;
    //         String arrowDirection = "Forward";
    //         int coorX;
    //         int coorY;
    //         boolean isRedColour = true;

    //         colour = tokens[0].toLowerCase();
    //         piece = tokens[1];

    //         if (colour.equals("blue")) {
    //             isRedColour = false;
    //         }

    //         if (tokens.length == 5) {
    //             coorX = Character.getNumericValue(tokens[3].toCharArray()[1]);
    //             coorY = Character.getNumericValue(tokens[4].toCharArray()[0]);

    //             arrowDirection = tokens[2];

    //         } else {
    //             coorX = Character.getNumericValue(tokens[2].toCharArray()[1]);
    //             coorY = Character.getNumericValue(tokens[3].toCharArray()[0]);
    //         }
    //         try {
    //             switch (piece) {
    //                 case "Plus":
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                             new StateChangingPiece(isRedColour, String.format("./images/%s_plus.png", colour)));
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX].getChessPiece().setState(new PlusMovement());
    //                 break;
    //                 case "Triangle":
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                             new StateChangingPiece(isRedColour, String.format("./images/%s_triangle.png", colour)));
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX].getChessPiece().setState(new TriangleMovement());
    //                     break;
    //                 case "Chevron":
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                             new Chevron(isRedColour, String.format("./images/%s_chevron.png", colour)));
    //                     break;
    //                 case "Sun":
    //                     boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                             new Sun(isRedColour, String.format("./images/%s_sun.png", colour)));
    //                     break;
    //                 case "Arrow":
    //                     if (arrowDirection.equals("Forward"))
    //                         boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                                 new Arrow(isRedColour, String.format("./images/%s_arrow.png", colour),
    //                                         arrowDirection));
    //                     else
    //                         boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
    //                                 new Arrow(isRedColour, String.format("./images/%s_arrow.png", colour),
    //                                         arrowDirection));
    //                     break;

    //             }
    //         } catch (Exception ex) {
    //             ex.printStackTrace();
    //             System.out.println();
    //         }

    //     }
    // }

    public void playSound(String soundName){
        try{
           //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundName));
           
           Clip clip = AudioSystem.getClip( );
           clip.open(audioInputStream);
           clip.start( );
        }
        catch(Exception ex){
           System.out.println("Error with playing sound.");
           ex.printStackTrace( );
        }
   }
}
