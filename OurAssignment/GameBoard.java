package OurAssignment;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameBoard extends JPanel {

    JFrame f = new JFrame();
    private ChessTiles[][] Tiles;
    private JButton[][] tileArray = new JButton[8][7];
    Boolean flipped = false;

    Graphics2D ig2d;

    private static int roundCount;
    private Coordinate coordinate[][] = new Coordinate[8][7];
    private Piece pieces[] = new Piece[10];

    public GameBoard() throws IOException {

        //-------------------------------------GUI-----------------------------------------//
       

        f.setLayout(new BorderLayout());
        JButton b = new JButton("rotate");

        b.addActionListener(e -> printBoard());
        f.add(b, BorderLayout.PAGE_START);        
        
        InitPiece();
        //-------------------------Assigning pieces to the coordinate----------------------//

        coordinate[0][0] = new Coordinate(0,7, pieces[3]);
		coordinate[0][1] = new Coordinate(1,7, pieces[2]);
		coordinate[0][2] = new Coordinate(2,7, pieces[1]);
		coordinate[0][3] = new Coordinate(3,7, pieces[0]);
		coordinate[0][4] = new Coordinate(4,7, pieces[1]);
		coordinate[0][5] = new Coordinate(5,7, pieces[2]);
		coordinate[0][6] = new Coordinate(6,7, pieces[3]);

		coordinate[1][0] = new Coordinate(0,6, pieces[4]);
		coordinate[1][1] = new Coordinate(1,6, null);
		coordinate[1][2] = new Coordinate(2,6, pieces[4]);
		coordinate[1][3] = new Coordinate(3,6, null);
		coordinate[1][4] = new Coordinate(4,6, pieces[4]);
		coordinate[1][5] = new Coordinate(5,6, null);
		coordinate[1][6] = new Coordinate(6,6, pieces[4]);

		coordinate[7][0] = new Coordinate(0,0, pieces[8]);
		coordinate[7][1] = new Coordinate(0,1, pieces[7]);
		coordinate[7][2] = new Coordinate(0,2, pieces[6]);
		coordinate[7][3] = new Coordinate(0,3, pieces[5]);
		coordinate[7][4] = new Coordinate(0,4, pieces[6]);
		coordinate[7][5] = new Coordinate(0,5, pieces[7]);
		coordinate[7][6] = new Coordinate(0,6, pieces[8]);

		coordinate[6][0] = new Coordinate(1,0, pieces[9]);
		coordinate[6][1] = new Coordinate(1,1, null);
		coordinate[6][2] = new Coordinate(1,2, pieces[9]);
		coordinate[6][3] = new Coordinate(1,3, null);
		coordinate[6][4] = new Coordinate(1,4, pieces[9]);
		coordinate[6][5] = new Coordinate(1,5, null);
		coordinate[6][6] = new Coordinate(1,6, pieces[9]);

		for(int i = 2; i < 6; i ++){
			for(int k = 0; k < 7; k ++){
				coordinate[i][k] = new Coordinate(i, k, null);
			}
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 6; j++) {
                tileArray[i][j] = new ChessTiles(i,j);

                tileArray[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("The X Coordinate for this tile is " + ((ChessTiles)e.getSource()).getCoorX());
                        System.out.println("The Y Coordinate for this tile is " + ((ChessTiles)e.getSource()).getCoorY());
                        System.out.println();
                    } 
                });

                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                    tileArray[i][j].setBackground(new Color(73, 58, 35));
                } else
                    tileArray[i][j].setBackground(new Color(238, 219, 190));
            }
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 6; j++) {
                if(coordinate[i][j].getChessPiece() != null){
                    tileArray[i][j].setIcon(new ImageIcon(coordinate[i][j].getChessPiece().getIcon()));
                }
                this.add(tileArray[i][j]);
            }
        }

        /*
        try {
            Image piece = ImageIO.read(getClass().getResource("red_triangle.png"));
            tileArray[3][2].setIcon(new ImageIcon(piece));
        }
        catch (Exception e) {
            System.out.println("Image not found");
        }
        */
        setLayout(new GridLayout(8, 7));
        f.add(this, BorderLayout.CENTER);
        f.setVisible(true);
        f.setSize(1000, 1000);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ig2d = (Graphics2D) g;
    }

    public void printBoard(){
        removeAll();
        setLayout(new GridLayout(8, 7));
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 6; j++){
                add(tileArray[7-i][6-j]);
            }
        }
        revalidate();
        repaint();
    }

    public void InitPiece(){
        try{
            //-------------------------Initializing the blue pieces-----------------------------//

            pieces[0] = new Sun("blue", true, "./Forward_Img/blue_sun.png");
            pieces[1] = new Chevron("blue", true, "./Forward_Img/blue_chevron.png");
            pieces[2] = new Triangle("blue", true, "./Forward_Img/blue_triangle.png");
            pieces[3] = new Plus("blue", true, "./Forward_Img/blue_plus.png");
            pieces[4] = new Arrow("blue", true, "./Forward_Img/blue_arrow.png");

            //-------------------------Initializing the red pieces-----------------------------//

            pieces[5] = new Sun("red", true, "./Forward_Img/red_sun.png");
            pieces[6] = new Chevron("red", true, "./Forward_Img/red_chevron.png");
            pieces[7] = new Triangle("red", true, "./Forward_Img/red_triangle.png");
            pieces[8] = new Plus("red", true,"./Forward_Img/red_plus.png");
            pieces[9] = new Arrow("red", true, "./Forward_Img/red_arrow.png");

            }catch(IOException ex){
                ex.printStackTrace();
            }
    
    }

}
