package OurAssignment;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class GameBoard extends JPanel {

    JFrame f = new JFrame();

    private JButton[][] tileArray = new JButton[8][7];
    Boolean flipped = false;

    Graphics2D ig2d;

    public GameBoard(Coordinate coordinate[][]) {
        f.setLayout(new BorderLayout());
        JButton b = new JButton("rotate");

        b.addActionListener(e -> dontwantsleep());
        f.add(b, BorderLayout.PAGE_START);

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 6; j++) {
                tileArray[i][j] = new JButton();
                tileArray[i][j].setOpaque(true);
                if (((j % 2 == 0) && (i % 2 == 0)) || ((j % 2 == 1) && (i % 2 == 1))) {
                    tileArray[i][j].setBackground(new Color(73, 58, 35));
                } else
                    tileArray[i][j].setBackground(new Color(238, 219, 190));
                this.add(tileArray[i][j]);
            }
        }
        try {
            Image piece = ImageIO.read(getClass().getResource("red_triangle.png"));
            tileArray[3][2].setIcon(new ImageIcon(piece));
        }
        catch (Exception e) {
            System.out.println("Image not found");
        }
        setLayout(new GridLayout(8, 7));
        f.add(this, BorderLayout.CENTER);
        f.setVisible(true);
        f.setSize(1000, 1000);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ig2d = (Graphics2D) g;
    }

    public void dontwantsleep(){
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

    /*
    public void rotate2(){
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 7; x++) {
                JButton temp = tileArray[y][x];
                tileArray[y][x] = tileArray[7-y][6-x];
                tileArray[7-y][6-x] = temp; 
            }
        }
        repaint();
        revalidate();     
    }

    public void IhaveNoDream(){
        contain = f.getContentPane();
        contain.removeAll();
        JPanel newPanel = new JPanel(null);

        for (int j = 0, x = 7; j < 8; j++) {
            for (int i = 7, y = 0; i > 0;  i--) {
                tileArray1[j][i] = tileArray[x][y];
                newPanel.add(tileArray1[j][i]);
                y++;
            }
            x--;
        }

        //tileArray[i][j].setBackground(Color.BLACK);
        newPanel.setLayout(new GridLayout(8,7));
        contain.add(newPanel, BorderLayout.CENTER);
        contain.setVisible(true);
        contain.validate();
        contain.repaint();

    }
*/
}
