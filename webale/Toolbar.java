package webale;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Toolbar extends JPanel{

    int width=200;
    int height=200;
    JButton backButton = new JButton("Back");
    JButton saveButton= new JButton("Save");
    JButton helpButton= new JButton("Help");
    String playerToMove = "RED";
    JLabel playerLabel = new JLabel("Player to Move : " + playerToMove);
    JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 10));

    public Toolbar(){
        setBorder(new EmptyBorder(2, 3, 2, 3));
        btnPanel.add(backButton);
        btnPanel.add(saveButton);
        btnPanel.add(helpButton);
        btnPanel.add(playerLabel);
        add(btnPanel);
        setPreferredSize(new Dimension(width,height));
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
