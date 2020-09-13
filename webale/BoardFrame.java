package webale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BoardFrame extends JFrame{
    private JPanel gameboard = new GameBoard();
    private JPanel toolbar = new Toolbar();
    private JPanel gamePanel = new JPanel();

    public BoardFrame(){
        super("WEBALE CHESS");

        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //contriants for gameboard
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,20,20,0);
        c.weightx = 1.0;
        c.weighty = 0.5;
        c.gridwidth = 4;
        gamePanel.add(gameboard, c);

        //contraints for toolbar
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 4;
        c.gridy = 0;
        c.insets = new Insets(300,0,300,0);
        c.weightx = 0;
        c.weighty = 0.5;
        c.gridwidth = 1;
        gamePanel.add(toolbar, c);

        add(gamePanel);
        setSize(1000,1000);
        pack();
        setVisible(true);

    }

    public Toolbar getToolbar(){
        return (Toolbar)toolbar;
    }

    public GameBoard getGameBoard(){
        return (GameBoard)gameboard;
    }
}
