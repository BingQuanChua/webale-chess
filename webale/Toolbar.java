package webale;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Toolbar extends JPanel{

    int width=200;
    int height=200;
    JButton backButton = new JButton("Back");
    JButton saveButton = new JButton("Save");
    JButton helpButton = new JButton("Help");
    String playerToMove = "RED";
    JLabel playerLabel = new JLabel("Player to Move : " + playerToMove);
    Color btnColor = new Color(103, 100, 107);

    public Toolbar(){
        backButton.setBackground(btnColor);
        backButton.setForeground(Color.WHITE);
        saveButton.setBackground(btnColor);
        saveButton.setForeground(Color.WHITE);
        helpButton.setBackground(btnColor);
        helpButton.setForeground(Color.WHITE);

        setBorder(new EmptyBorder(0, 20, 0, 20)); //TLBR
        setLayout(new GridLayout(10, 1, 0, 10));
        add(backButton);
        add(saveButton);
        add(helpButton);
        add(playerLabel);
        //setPreferredSize(new Dimension(width,height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerLabel.setText("Player to Move : " + playerToMove);
    }

    public void setPlayerToMove(String playerToMove) {
        this.playerToMove = playerToMove;
    }
}
