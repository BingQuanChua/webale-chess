// This class is responsible for write .txt file to save file saving and loading operation
package webale;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class FileOperation {
    private int moveCount;
    private boolean isRedPlayer;
    private BoardFrame boardFrame;
    
    FileOperation(int moveCount, boolean isRedPlayer, BoardFrame boardFrame){
        this.moveCount = moveCount;
        this.isRedPlayer = isRedPlayer;
        this.boardFrame= boardFrame;
    }

    public void writeSaveFile(File fileToSave) {
        if (fileToSave == null) {
            return;
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileToSave));
            bw.write("###################################################");
            bw.newLine();
            bw.write("|              WEBALE CHESS SAVE FILE             |");
            bw.newLine();
            bw.write("###################################################");
            bw.newLine();
            bw.write("Turn: " + (isRedPlayer ? "Red" : "Blue"));
            bw.newLine();
            bw.write("Move Count: " + moveCount);
            bw.newLine();
            bw.write("###################################################");
            bw.newLine();
            for (int y = 0; y < 8; y++){
                for (int x = 0; x < 7; x++) {
                    Piece piece = boardFrame.getGameBoard().getCoordinateArray()[y][x].getChessPiece();
                    if (piece != null) {
                        bw.write(piece + " (" + x + ", " + y + ")");
                        bw.newLine();
                    }
                }
            }
            bw.write("###################################################");
            bw.newLine();
            bw.write("########################END########################");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving file. ", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                System.out.println("Error in closing the BufferedWriter. ");
                e.printStackTrace();
                System.out.println();
            }
        }
    }

    // read the saved txt file 
    public void readFile(File file) {
        if (file == null) {
            return;
        }
        BufferedReader br = null;
        try {
            String fileName = file.getName();
            int i = fileName.lastIndexOf('.');
            String extension = "";

            // To check if the selected file is of txt
            if (i > 0) {
                extension = fileName.substring(i + 1);
                if (!extension.equals("txt")) {
                    throw new Exception("Incorrect file type.");
                }
            }
            
            br = new BufferedReader(new FileReader(file));
            String strCurrentLine;
            boolean isWebaleChessFile = false;

            // Start reading the file line by line
            while ((strCurrentLine = br.readLine()) != null) {

                // We are using ##### to separate the information in the file 
                // So we need to ignore this line while reading
                if (strCurrentLine.trim().indexOf('#') == 0 || strCurrentLine.startsWith(" "))
                    continue;

                // This is to check if the selected txt is for Webale Chess or not 
                if (strCurrentLine.startsWith("|")) {
                    String[] tokens = strCurrentLine.substring(1).trim().split(" ");
                    if (tokens[0].equals("WEBALE") && tokens[1].equals("CHESS") && tokens[2].equals("SAVE")
                            && tokens[3].equals("FILE")) {
                        isWebaleChessFile = true;
                    }
                }

                // This is to check if the current turn is blue player or not
                // if current turn is blue player, then isRedPlayer will be set to false, vice versa
                if (strCurrentLine.startsWith("T")) {
                    String[] tokens = strCurrentLine.split(" ");
                    if (tokens[1].equals("Blue")) {
                        isRedPlayer = false;
                    } else
                        isRedPlayer = true;

                    boardFrame.getToolbar().setPlayerToMove(isRedPlayer? "Red" : "Blue");    
                }

                // This is to get the current move count
                if (strCurrentLine.startsWith("M")) {
                    StringBuilder countStr = new StringBuilder();
                    for (int r = 12; r < strCurrentLine.toCharArray().length; r++)
                        countStr.append(strCurrentLine.toCharArray()[r]);

                    moveCount = Integer.parseInt(countStr.toString());
                    boardFrame.getToolbar().setMoveCount(moveCount);
                }

                // From this line onwards, the file contains the information for pieces
                // Format: Color Piece (CoordinateX, CoordinateY) For example: Red Plus (1,1) / Blue Plus (1,1)
                // We are using another method which is iniPiece to handle it  
                if (strCurrentLine.startsWith("B") || strCurrentLine.startsWith("R")) {
                    initPiece(strCurrentLine);
                }
            }
            if (!isWebaleChessFile) {
                throw new Exception("Webale Chess save file is not selected.");
            }
            boardFrame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error reading file. " + e.getMessage() + "\n(" + file.getAbsolutePath() + ")", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.print("Error in closing the BufferedReader. ");
                e.printStackTrace();
                System.out.println();
            }
        }
    }

    // restore the game board from saved txt file
    public void initPiece(String line) throws IOException {
        if (line.startsWith("B") || line.startsWith("R")) {
            String[] tokens = line.split(" ");
            String colour;
            String piece;
            String arrowDirection = "Forward";
            int coorX;
            int coorY;
            boolean isRedColour = true;

            colour = tokens[0].toLowerCase();
            piece = tokens[1];

            if (colour.equals("blue")) {
                isRedColour = false;
            }

            if (tokens.length == 5) {
                coorX = Character.getNumericValue(tokens[3].toCharArray()[1]);
                coorY = Character.getNumericValue(tokens[4].toCharArray()[0]);

                arrowDirection = tokens[2];

            } else {
                coorX = Character.getNumericValue(tokens[2].toCharArray()[1]);
                coorY = Character.getNumericValue(tokens[3].toCharArray()[0]);
            }
            try {
                switch (piece) {
                    case "Plus":
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                new StateChangingPiece(isRedColour, String.format("./images/%s_plus.png", colour)));
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX].getChessPiece().setState(new PlusMovement());
                    break;
                    case "Triangle":
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                new StateChangingPiece(isRedColour, String.format("./images/%s_triangle.png", colour)));
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX].getChessPiece().setState(new TriangleMovement());
                        break;
                    case "Chevron":
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                new Chevron(isRedColour, String.format("./images/%s_chevron.png", colour)));
                        break;
                    case "Sun":
                        boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                new Sun(isRedColour, String.format("./images/%s_sun.png", colour)));
                        break;
                    case "Arrow":
                        if (arrowDirection.equals("Forward"))
                            boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                    new Arrow(isRedColour, String.format("./images/%s_arrow.png", colour),
                                            arrowDirection));
                        else
                            boardFrame.getGameBoard().getCoordinateArray()[coorY][coorX] = new Coordinate(coorX, coorY,
                                    new Arrow(isRedColour, String.format("./images/%s_arrow.png", colour),
                                            arrowDirection));
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println();
            }

        }
    }
}