import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.*;

public class BoardFrame{

    public BoardFrame(){
        JFrame frame = new JFrame("Game Board Frame");
        JPanel gamePanel = new JPanel();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 10));
        JLabel label = new JLabel("Player to Move : Red / Blue");
        JButton backButton = new JButton("Back");
        JButton saveButton= new JButton("Save");
        JButton helpButton= new JButton("Help");
        btnPanel.add(backButton);
        btnPanel.add(saveButton);
        btnPanel.add(helpButton);
        btnPanel.add(label);
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.EAST);
        gamePanel.add(panel);
        // 1,1 is chess Board 1,2 is toolBar
        gamePanel.setLayout(new GridLayout(1,2));
        

        frame.add(gamePanel);
        frame.setSize(800,800);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new BoardFrame();
    }
}
