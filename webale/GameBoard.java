// This class is for initializing the chess pieces on the gameboard, check for self-added different conditions of webale chess game (checkDraw, checkmate).

package webale;

import java.awt.*;


import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameBoard extends JPanel {

    private static final long serialVersionUID = 1L;
    //                                            y  x
    private JButton[][] tileArray = new ChessTile[8][7];
    private ArrayList<Coordinate> remainingCoordinates = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> remainingRedPiece = new ArrayList<Coordinate>();
    private ArrayList<Coordinate> remainingBluePiece = new ArrayList<Coordinate>();
    private Coordinate coordinate[][] = new Coordinate[8][7];
    private boolean hasFlipped = false;

    // Koh Han Yi, Chua Bing Quan
    public GameBoard() {

        // Define the size of the game board
        int width = 700;
        int height = 600;

        // Assigning pieces to the coordinate
        try{
            coordinate[0][0] = new Coordinate(0,0, new StateChangingPiece(false, "./images/blue_plus.png"));        // plus
            coordinate[0][0].getChessPiece().setState(new PlusMovement());
            coordinate[0][1] = new Coordinate(1,0, new StateChangingPiece(false, "./images/blue_triangle.png"));    // triangle
            coordinate[0][1].getChessPiece().setState(new TriangleMovement());
            coordinate[0][2] = new Coordinate(2,0, new Chevron(false, "./images/blue_chevron.png"));
            coordinate[0][3] = new Coordinate(3,0, new Sun(false, "./images/blue_sun.png"));
            coordinate[0][4] = new Coordinate(4,0, new Chevron(false, "./images/blue_chevron.png"));
            coordinate[0][5] = new Coordinate(5,0, new StateChangingPiece(false, "./images/blue_triangle.png"));    // triangle
            coordinate[0][5].getChessPiece().setState(new TriangleMovement());
            coordinate[0][6] = new Coordinate(6,0, new StateChangingPiece(false, "./images/blue_plus.png"));        // plus
            coordinate[0][6].getChessPiece().setState(new PlusMovement());

            coordinate[1][0] = new Coordinate(0,1, new Arrow(false, "./images/blue_arrow.png"));
            coordinate[1][1] = new Coordinate(1,1, null);
            coordinate[1][2] = new Coordinate(2,1, new Arrow(false, "./images/blue_arrow.png"));
            coordinate[1][3] = new Coordinate(3,1, null);
            coordinate[1][4] = new Coordinate(4,1, new Arrow(false, "./images/blue_arrow.png"));
            coordinate[1][5] = new Coordinate(5,1, null);
            coordinate[1][6] = new Coordinate(6,1, new Arrow(false, "./images/blue_arrow.png"));

            coordinate[6][0] = new Coordinate(0,6, new Arrow(true, "./images/red_arrow.png"));
            coordinate[6][1] = new Coordinate(1,6, null);
            coordinate[6][2] = new Coordinate(2,6, new Arrow(true, "./images/red_arrow.png"));
            coordinate[6][3] = new Coordinate(3,6, null);
            coordinate[6][4] = new Coordinate(4,6, new Arrow(true, "./images/red_arrow.png"));
            coordinate[6][5] = new Coordinate(5,6, null);
            coordinate[6][6] = new Coordinate(6,6, new Arrow(true, "./images/red_arrow.png"));
            
            coordinate[7][0] = new Coordinate(0,7, new StateChangingPiece(true,"./images/red_plus.png"));           // plus
            coordinate[7][0].getChessPiece().setState(new PlusMovement());
            coordinate[7][1] = new Coordinate(1,7, new StateChangingPiece(true, "./images/red_triangle.png"));      // triangle
            coordinate[7][1].getChessPiece().setState(new TriangleMovement());
            coordinate[7][2] = new Coordinate(2,7, new Chevron(true, "./images/red_chevron.png"));
            coordinate[7][3] = new Coordinate(3,7, new Sun(true, "./images/red_sun.png"));
            coordinate[7][4] = new Coordinate(4,7, new Chevron(true, "./images/red_chevron.png"));
            coordinate[7][5] = new Coordinate(5,7, new StateChangingPiece(true, "./images/red_triangle.png"));      // triangle
            coordinate[7][5].getChessPiece().setState(new TriangleMovement());
            coordinate[7][6] = new Coordinate(6,7, new StateChangingPiece(true,"./images/red_plus.png"));           // plus
            coordinate[7][6].getChessPiece().setState(new PlusMovement());
            
        } catch(IOException ex){
            ex.printStackTrace();
        }
        
        // assigning coordinates to tiles that don't have pieces in initial stage
        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                coordinate[y][x] = new Coordinate(x, y, null);
            }
        }
        
        // setting the tiles with light beige and light brown colors 
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                tileArray[y][x] = new ChessTile(x, y);
                if (((y % 2 == 0) && (x % 2 == 0)) || ((y % 2 == 1) && (x % 2 == 1))) {
                    tileArray[y][x].setBackground(new Color(242, 227, 211));            
                } else
                    tileArray[y][x].setBackground(new Color(181, 153, 132));
            }
        }

        // add tileArray on gameboard
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                this.add(tileArray[y][x]);
            }
        }

        // design the gameboard
        setBorder(BorderFactory.createLineBorder(new Color(74, 59, 47), 8));
        setLayout(new GridLayout(8, 7));
        setPreferredSize(new Dimension(width, height));
    }

    // put piece icon on tileArray
    // Koh Han Yi, Denise Lee Chia Xuan
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                tileArray[y][x].setIcon(null);
                if (coordinate[y][x].getChessPiece() != null) {
                    tileArray[y][x].setIcon(coordinate[y][x].getChessPiece().getIcon());
                }
            }
        }
    }

    // checking of draw game (both players only left with the sun)
    // Lee Min Xuan
    public void checkDraw() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                if (coordinate[y][x].getChessPiece() != null) {
                    remainingCoordinates.add(coordinate[y][x]);
                }
            }
        }
    }

    // reset the arraylist
    // Lee Min Xuan
    public void resetCheckDraw() {
        remainingCoordinates.removeAll(remainingCoordinates);
    }

    // checking of checkmate condition (one player only left with the sun)
    // Tan Jia Qi
    public void checkmate() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                if (coordinate[y][x].getChessPiece() != null && coordinate[y][x].getChessPiece().getIsRedColor()) {
                    remainingRedPiece.add(coordinate[y][x]);
                } else if (coordinate[y][x].getChessPiece() != null && !coordinate[y][x].getChessPiece().getIsRedColor()) {
                    remainingBluePiece.add(coordinate[y][x]);
                }
            }
        }
    }

    // reset the arraylist
    // Tan Jia Qi
    public void resetCheckmate() {
        remainingRedPiece.removeAll(remainingRedPiece);
        remainingBluePiece.removeAll(remainingBluePiece);
    }

    // reset the gameboard (when starting a new game)
    // Lee Min Xuan
    public void resetBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                tileArray[y][x].setIcon(null);
                coordinate[y][x].setChessPiece(null);
            }
        }
    }

    // rotate the gameboard when changing player's turn
    // Denise Lee Chia Xuan
    public void rotateBoard() {
        hasFlipped = !hasFlipped;

        this.removeAll();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (hasFlipped) {
                    add(tileArray[7 - i][6 - j]);
                } else {
                    add(tileArray[i][j]);
                }
                //if there is piece on the tile, rotate the imageIcon of the piece 
                if (coordinate[i][j].getChessPiece() != null) {
                    coordinate[i][j].getChessPiece().toggleFlippedState();
                }
            }
        }
    }

    // method to get the number of remaining chess piece on Chess Board
    public int getRemainingPieceSize() {
        return remainingCoordinates.size();
    }

    // method to get the number of remaining Red colour chess piece on Chess Board
    public int getRemainingRedPieceSize() {
        return remainingRedPiece.size();
    }

    //method to get the number of remaining Blue colour chess piece on Chess Board
    public int getRemainingBluePieceSize() {
        return remainingBluePiece.size();
    }

    // method to get the 2D coordinate array with coordinate
    public Coordinate[][] getCoordinateArray() {
        return coordinate;
    }

    // method to get the 2D button array with tile
    public JButton[][] getTileArray() {
        return tileArray;
    }

    public void setHasFlipped(boolean hasFlipped){
        this.hasFlipped = hasFlipped;
    }

}
