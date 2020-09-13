package webale;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;

//import javax.imageio.ImageIO;

public class GameBoard extends JPanel{

    JFrame f = new JFrame("WEBALE CHESS");   
    //                                             y  x
    private JButton[][] tileArray = new ChessTiles[8][7];
    Boolean hasFlipped = false;

    //private static int roundCount;
    private Coordinate coordinate[][] = new Coordinate[8][7];
    private Piece pieces[] = new Piece[10];

    public GameBoard(){

        //-------------------------------------GUI-----------------------------------------//
       

        f.setLayout(new BorderLayout());
        JButton b = new JButton("rotate");

        b.addActionListener(e -> rotateBoard());
        f.add(b, BorderLayout.PAGE_START);        
        
        InitPiece();
        //-------------------------Assigning pieces to the coordinate----------------------//



        coordinate[0][0] = new Coordinate(0,0, pieces[3]);
		coordinate[0][1] = new Coordinate(1,0, pieces[2]);
		coordinate[0][2] = new Coordinate(2,0, pieces[1]);
		coordinate[0][3] = new Coordinate(3,0, pieces[0]);
		coordinate[0][4] = new Coordinate(4,0, pieces[1]);
		coordinate[0][5] = new Coordinate(5,0, pieces[2]);
		coordinate[0][6] = new Coordinate(6,0, pieces[3]);

		coordinate[1][0] = new Coordinate(0,1, pieces[4]);
		coordinate[1][1] = new Coordinate(1,1, null);
		coordinate[1][2] = new Coordinate(2,1, pieces[4]);
		coordinate[1][3] = new Coordinate(3,1, null);
		coordinate[1][4] = new Coordinate(4,1, pieces[4]);
		coordinate[1][5] = new Coordinate(5,1, null);
		coordinate[1][6] = new Coordinate(6,1, pieces[4]);

        coordinate[6][0] = new Coordinate(0,6, pieces[9]);
		coordinate[6][1] = new Coordinate(1,6, null);
		coordinate[6][2] = new Coordinate(2,6, pieces[9]);
		coordinate[6][3] = new Coordinate(3,6, null);
		coordinate[6][4] = new Coordinate(4,6, pieces[9]);
		coordinate[6][5] = new Coordinate(5,6, null);
        coordinate[6][6] = new Coordinate(6,6, pieces[9]);
        
		coordinate[7][0] = new Coordinate(0,7, pieces[8]);
		coordinate[7][1] = new Coordinate(1,7, pieces[7]);
		coordinate[7][2] = new Coordinate(2,7, pieces[6]);
		coordinate[7][3] = new Coordinate(3,7, pieces[5]);
		coordinate[7][4] = new Coordinate(4,7, pieces[6]);
		coordinate[7][5] = new Coordinate(5,7, pieces[7]);
		coordinate[7][6] = new Coordinate(6,7, pieces[8]);		

		for(int y = 2; y < 6; y ++){
			for(int x = 0; x < 7; x ++){
				coordinate[y][x] = new Coordinate(x, y, null);
			}
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                tileArray[y][x] = new ChessTiles(x,y);

                // tileArray[y][x].addActionListener(new ActionListener(){
                //     int timeClicked = 0;
                //     Coordinate endPoint = null;
                //     Coordinate startPoint = null;
                //     @Override
                //     public void actionPerformed(ActionEvent e) {
                //         ChessTiles chessTileClicked = (ChessTiles)e.getSource();
                //         timeClicked++;
                //         System.out.println(timeClicked);
                //         if(timeClicked % 2 == 0){
                //             endPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
                //             if(startPoint.getChessPiece().canMove(coordinate, startPoint, endPoint)){
                //                 endPoint.setChessPiece(startPoint.getChessPiece());
                //                 startPoint.setChessPiece(null);
                //                 repaint();
                //             }
                //         } else{
                //             startPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
                //             return;
                //         }
                        
                //         System.out.println("The X Coordinate for this tile is " + ((ChessTiles)e.getSource()).getCoorX());
                //         System.out.println("The Y Coordinate for this tile is " + ((ChessTiles)e.getSource()).getCoorY());
                //         System.out.println();
                //     } 
                // });

                if (((y % 2 == 0) && (x % 2 == 0)) || ((y % 2 == 1) && (x % 2 == 1))) {
                    tileArray[y][x].setBackground(new Color(198, 198, 199));
                } else
                    tileArray[y][x].setBackground(new Color(112, 108, 117));
            }
        }

        // for (int y = 0; y < 8; y++) {
        //     for (int x = 0; x < 7; x++) {
        //         if(coordinate[y][x].getChessPiece() != null){
        //             tileArray[y][x].setIcon(coordinate[y][x].getChessPiece().getIcon());
        //         }
        //         this.add(tileArray[y][x]);
        //     }
        // }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                this.add(tileArray[y][x]);
            }
        }

        setLayout(new GridLayout(8, 7));
        f.add(this, BorderLayout.CENTER);
        f.setVisible(true);
        f.setSize(1000, 1000);  
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                tileArray[y][x].setIcon(null);
                if(coordinate[y][x].getChessPiece() != null){
                    tileArray[y][x].setIcon(coordinate[y][x].getChessPiece().getIcon());
                }
            }
        }
        
    }

    public void rotateBoard(){
        hasFlipped = !hasFlipped;
        this.removeAll();
        if(hasFlipped){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 7; j++){
                  this.add(tileArray[7-i][6-j]);
                }
            }
        } else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 7; j++){
                  this.add(tileArray[i][j]);
                }
            }
        }
        
        revalidate();
        repaint();
    }

    Coordinate startPoint = null;
    Coordinate endPoint = null;

    public boolean movePiece(ChessTiles chessTileClicked, int timeClicked){
        
        if(timeClicked % 2 != 0 && coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()].getChessPiece() == null){
            return false;
        }

        if(timeClicked % 2 == 0){
            endPoint = coordinate[chessTileClicked.getCoorY()][chessTileClicked.getCoorX()];
            if(startPoint != null && startPoint.getChessPiece().canMove(coordinate, startPoint, endPoint)){
                endPoint.setChessPiece(startPoint.getChessPiece());
                startPoint.setChessPiece(null);
                revalidate();
                repaint();
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

        // //same colour cannot move 
        // //发现到有一些同样颜色的piece还是可以吃掉对方 所以这里check多一次 如果movement都有check正确的话这里应该不用check了
        // 
        // if(startPoint.getChessPiece().getIsRedColor() == endPoint.getChessPiece().getIsRedColor()){
        //     return false;
        // }
        // else if(!startPoint.getChessPiece().getIsRedColor() == !endPoint.getChessPiece().getIsRedColor()){
        //     return false;
        // }
        // else if(startPoint.getChessPiece().getIsRedColor() != endPoint.getChessPiece().getIsRedColor()){
        //     return true;
        // }

        //我想check sun赢没有 可是check不到:( 还是在其他地方放method check bah 
        //最好不要一次过在同个method做 分开哦哦^^  因为还没做kill piece，这个endpoint是在指那个吃掉sun的piece
        // if (startPoint != null && (endPoint.getChessPiece().equals(pieces[5])) || endPoint.getChessPiece().equals(pieces[0])) {
        //     System.out.println("The game is END");
        //     return true;
        // } 

        //return false;        
    }
    
    public Coordinate[][] getCoordinateArray(){
        return coordinate;
    }

    public JButton[][] getTileArray(){
        return tileArray;
    }

    public void InitPiece(){
        try{
            //-------------------------Initializing the blue pieces-----------------------------//

            pieces[0] = new Sun(false, true, "./images/blue_sun.png");
            pieces[1] = new Chevron(false, true, "./images/blue_chevron.png");
            pieces[2] = new Triangle(false, true, "./images/blue_triangle.png");
            pieces[3] = new Plus(false, true, "./images/blue_plus.png");
            pieces[4] = new Arrow(false, true, "./images/blue_arrow.png");

            //-------------------------Initializing the red pieces-----------------------------//

            pieces[5] = new Sun(true, true, "./images/red_sun.png");
            pieces[6] = new Chevron(true, true, "./images/red_chevron.png");
            pieces[7] = new Triangle(true, true, "./images/red_triangle.png");
            pieces[8] = new Plus(true, true,"./images/red_plus.png");
            pieces[9] = new Arrow(true, true, "./images/red_arrow.png");

        } catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
}















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































