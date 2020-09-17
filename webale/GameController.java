package webale;

import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;

public class GameController {
    BoardFrame boardFrame = null;
    HomeFrame homeFrame = null;
    boolean isFirstGame = true;

    public GameController(HomeFrame homeFrame, BoardFrame boardFrame) {
        this.homeFrame = homeFrame;
        this.boardFrame = boardFrame;
        setListener();
    }

    public void setListener() {
        homeFrame.getStartButton().addActionListener(startBtnListener);
        homeFrame.getContinueButton().addActionListener(continueBtnListener);
        homeFrame.getLoadButton().addActionListener(loadFileBtnListener);
        homeFrame.getInstructionButton().addActionListener(instructionBtnListener);
        homeFrame.getQuitButton().addActionListener(quitBtnListener);
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

    ActionListener defeatBtnListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            JLabel defeatLabel = new JLabel(homeFrame.getDefeatImageIcon());
            JOptionPane.showMessageDialog(null, defeatLabel, "You have admitted defeat!", JOptionPane.PLAIN_MESSAGE, null);
            boardFrame.setVisible(false);
        }
    };

    ActionListener startBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isFirstGame) {
                boardFrame = new BoardFrame();
            }
            boardFrame.setVisible(true);

        }
    };

    ActionListener continueBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boardFrame.setVisible(true);
        }
    };

    ActionListener loadFileBtnListener  = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                boardFrame.getGameBoard().resetBoard();
                readFile(file);
                boardFrame.setVisible(true);   
            }
         
        }};

    ActionListener instructionBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel instructionLabel = new JLabel(homeFrame.getInstructionImageIcon());
            JOptionPane.showMessageDialog(null, instructionLabel, "Instruction", JOptionPane.PLAIN_MESSAGE, null);
        }
    };

    ActionListener quitBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    ActionListener backBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            homeFrame.getContinueButton().setEnabled(true);
            boardFrame.setVisible(false);
            isFirstGame = false;
        }
    };

    ActionListener saveBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File file = boardFrame.getToolbar().openSaveFileDialogAndGetFileToSave();
            writeSaveFile(file);
        }
    };

    int moveCount = 0;

    ActionListener chessTileListener = new ActionListener() {
        int timeClicked = 0;
        boolean isValidMove = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (boardFrame.getGameBoard() != null) {
                timeClicked++;
                isValidMove = movePiece((ChessTiles) e.getSource(), timeClicked);
                // if chesstile clicked for startpoint is empty
                if (!isValidMove && timeClicked == 1) {
                    timeClicked = 0;
                }

                // if chess movement to endpoint is valid
                else if (timeClicked == 2) {
                    timeClicked = 0;
                    if (isValidMove) {
                        moveCount++;
                        rotateBoard();
                        togglePlayerTurn();
                    }

                }
            }
        }
    };

    Boolean hasFlipped = false;

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

                if (boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece() != null) {
                    boardFrame.getGameBoard().getCoordinateArray()[i][j].getChessPiece().toggleFlippedState();
                }
            }
        }

        boardFrame.getGameBoard().repaint();
    }

    Coordinate startPoint = null;
    Coordinate endPoint = null;
    boolean isRedPlayer = true;

    public boolean movePiece(ChessTiles chessTileClicked, int timeClicked) {
        Coordinate[][] coordinate = boardFrame.getGameBoard().getCoordinateArray();

        // check if the chesstile selected as startPoint is valid first
        if (timeClicked % 2 != 0) {
            // if chesstile clicked as startPoint is empty
            if (coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece() == null)
                return false;
            // if piece on chesstile clicked as startPoint is not the same colour as the
            // player's piece
            else if (coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece()
                    .getIsRedColor() != isRedPlayer) {
                return false;
            }
        }

        if (timeClicked % 2 == 0) {
            endPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            // if moving to endPoint is valid
            if (startPoint != null && startPoint.getChessPiece().canMove(coordinate, startPoint, endPoint)) {
                if (hasWin(endPoint.getChessPiece())) {
                    gameOver();
                }

                //if arrow reach the end, turn its direction
                if(endPoint.getCoorY() == 0 || endPoint.getCoorY() == 7){
                    changeArrowState(startPoint.getChessPiece());
                }
                endPoint.setChessPiece(startPoint.getChessPiece());
                startPoint.setChessPiece(null);
                boardFrame.getGameBoard().repaint();
                //check Draw
                boardFrame.getGameBoard().checkDraw();
                if (boardFrame.getGameBoard().getLeftPieceSize()==2){
                    drawGame();
                }else {
                    boardFrame.getGameBoard().resetCheckDraw();
                }
                // if successfully moved return true, if not return false
                return true;
            } else {
                return false;
            }
        } else {
            startPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            return true;
        }
    }

    private void changeArrowState(Piece arrow){
        if(arrow instanceof Arrow){
            ((Arrow)arrow).changeMovement();
            arrow.toggleFlippedState();
        }
    }

    private void togglePlayerTurn() {
        isRedPlayer = !isRedPlayer;
        String playerToMove = isRedPlayer ? "Red" : "Blue";
        boardFrame.getToolbar().setPlayerToMove(playerToMove);
        boardFrame.getToolbar().repaint();
    }

    private boolean hasWin(Piece pieceKilled) {
        return pieceKilled instanceof Sun ? true : false;
    }

    private void gameOver() {
        String playerWon = isRedPlayer ? "Red" : "Blue";
        new GameOver(boardFrame, playerWon);
        isFirstGame = false;
        boardFrame.setVisible(false);
    }

    private void drawGame(){
        String playerWon = "Draw";
        new GameOver(boardFrame,playerWon);
        isFirstGame= false;
        boardFrame.setVisible(false);
    }

    private void writeSaveFile(File file){
        if(file == null){
            return;
        }

        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("###################################################");
            bw.newLine();
            bw.write("              WEBALE CHESS SAVE FILE");
            bw.newLine();
            bw.write("###################################################");
            bw.newLine();
            bw.write("Turn: " + (isRedPlayer ? "Red" : "Blue"));
            bw.newLine();
            bw.write("Move Count: " + moveCount);
            bw.newLine();
            bw.write("###################################################");
            bw.newLine();
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 7; x++){
                    Piece piece = boardFrame.getGameBoard().getCoordinateArray()[y][x].getChessPiece();
                    if(piece != null){
                        bw.write(piece + " (" + x + ", " + y + ")");
                        bw.newLine();
                    }
                }
            }
            bw.write("###################################################");
            bw.newLine();
            bw.write("########################END########################");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error saving file. ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                if(bw != null){
                    bw.close();
                }
            } catch(Exception e){
                 System.out.println("Error in closing the BufferedWriter. ");
                 e.printStackTrace();
                 System.out.println();
            }
        }        
    }    

    //readfile only, load save file algorithm put in different method better(i think?)
    private String readFile(File file){
        if(file == null){
            return null;
        }
        BufferedReader br = null;
        try {
            String fileName = file.getName();
            int i = fileName.lastIndexOf('.');
            String extension = "";
            if (i > 0) {
                extension = fileName.substring(i+1);
                if(extension != "txt"){
                    throw new Exception("Incorrect file type.");
                }
            }
            //possible error now: file content not correct or string is null -> fix in loadfile method
            br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String strCurrentLine;
            ArrayList<String> Colours = new ArrayList<String>();
            while ((strCurrentLine = br.readLine()) != null) {
                
                if(strCurrentLine.trim().indexOf('#') == 0 || strCurrentLine.startsWith(" "))
                     continue;

                if(strCurrentLine.startsWith("T")){
                    String[] tokens = strCurrentLine.split(" ");
                    if(tokens[1].equals("Blue")){
                        isRedPlayer = false;
                    }
                    else
                        isRedPlayer = true;
                }

                if(strCurrentLine.startsWith("M")){
                    StringBuilder countStr = new StringBuilder();
                    for(int r = 12; r < strCurrentLine.toCharArray().length; r++)
                    countStr.append(strCurrentLine.toCharArray()[r]);
                    
                    moveCount = Integer.parseInt(countStr.toString());
                }

                if(strCurrentLine.startsWith("B") || strCurrentLine.startsWith("R")){
                    initPiece(strCurrentLine);
                }
                

                // sb.append(strCurrentLine);
            }
            return sb.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading file. " + e.getMessage() + "\n(" + file.getAbsolutePath() + ")", "Error", JOptionPane.ERROR_MESSAGE);
        } finally{
            try{
                if(br != null){
                    br.close();
                }
            } catch(Exception e){
                 System.out.print("Error in closing the BufferedReader. ");
                 e.printStackTrace();
                 System.out.println();
            }
        }  
        return null;
    }

   public void initPiece(String line) throws IOException {
        if(line.startsWith("B") || line.startsWith("R")){
            String[] tokens = line.split(" ");
            String colour;
            String piece;
            String arrowDirection = "Forward";
            int coorX;
            int coorY;
            boolean isRedColour = true;

            colour = tokens[0].toLowerCase();
            piece = tokens[1];


            if(colour == "Blue"){
                isRedColour = false;
            }               
            System.out.println(tokens.length);

            if(tokens.length == 5){
                coorX = Character.getNumericValue(tokens[3].toCharArray()[1]);
                coorY = Character.getNumericValue(tokens[4].toCharArray()[0]);
                // System.out.println("coorX " + coorX);
                // System.out.println("coorY " + coorY);
                // System.out.println("Colour " + colour);
                // System.out.println("Piece " + piece);
                // System.out.println("Dirrection " + tokens[2]);

                arrowDirection = tokens[2];

            }else{
                coorX = Character.getNumericValue(tokens[2].toCharArray()[1]);
                coorY = Character.getNumericValue(tokens[3].toCharArray()[0]);
            }
            try{
                switch(piece){
                    case "Plus": 
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Plus(isRedColour, String.format("./images/%s_plus.png", colour)));
                    break;
                    case "Triangle": 
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Triangle(isRedColour, String.format("./images/%s_triangle.png", colour)));
                    break;
                    case "Chevron": 
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Chevron(isRedColour, String.format("./images/%s_chevron.png", colour)));
                    break;
                    case "Sun": 
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Sun(isRedColour, String.format("./images/%s_sun.png", colour)));
                    break;
                    case "Arrow": 
                        if (arrowDirection.equals("Forward"))
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Arrow(isRedColour, String.format("./images/%s_arrow.png", colour), arrowDirection));
                        else
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY, new Arrow(isRedColour, String.format("./images/%s_arrow_rotated.png", colour), arrowDirection));
                    break;
                    
                }
            }catch(Exception ex){
                ex.printStackTrace();
                 System.out.println();
            }
            

        }
    }
}
