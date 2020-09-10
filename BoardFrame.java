package OurAssignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.*;

public class BoardFrame{
    int width=800;
    int height=800;
    JLabel playerLabel = new JLabel("Player to Move : Red / Blue");
    JButton backButton = new JButton("Back");
    JButton saveButton= new JButton("Save");
    JButton helpButton= new JButton("Help");
    JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 10));
    JPanel layout = new JPanel(new GridBagLayout());
    JPanel panel = new JPanel(new BorderLayout());
    JPanel gamePanel = new JPanel();

    JFrame frame = new JFrame("Game Board Frame");

    public BoardFrame(){
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnPanel.add(backButton);
        btnPanel.add(saveButton);
        btnPanel.add(helpButton);
        btnPanel.add(playerLabel);
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.EAST);
        gamePanel.add(panel);
        // 1,1 is chess Board 1,2 is toolBar
        gamePanel.setLayout(new GridLayout(1,2));

        frame.add(gamePanel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new BoardFrame();
    }
}
