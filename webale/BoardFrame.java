package webale;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.*;

public class BoardFrame extends JFrame{
    int width=800;
    int height=800;
    JLabel playerLabel = new JLabel("Player to Move : Red / Blue");
    JPanel toolbar = new Toolbar();
    JPanel gamePanel = new JPanel();

    public BoardFrame(){
        super("Game Board Frame");
        
        // 1,1 is chess Board 1,2 is toolBar
        gamePanel.setLayout(new GridLayout(1,2));
        gamePanel.add(toolbar);

        add(gamePanel);
        setSize(width, height);
        setVisible(true);
    }

    public static void main(String[] args){
        new BoardFrame();
    }
}
