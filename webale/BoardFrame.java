package webale;

import java.awt.*;
import javax.swing.*;

public class BoardFrame extends JDialog{
    private JPanel gameboard = new GameBoard();
    private JPanel toolbar = new Toolbar();
    private JPanel gamePanel = new JPanel();

    public BoardFrame(){
        //super("WEBALE CHESS");
        super(null, "WEBALE CHESS",ModalityType.MODELESS);

        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //contriants for gameboard
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,20,20,0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 4;
        gamePanel.add(gameboard, c);
        gameboard.setOpaque(false);

        //contraints for toolbar
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 0;
        c.ipady = 200;
        c.insets = new Insets(200,0,0,0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        gamePanel.add(toolbar, c);
        toolbar.setOpaque(false);

        gamePanel.setOpaque(false);
        add(gamePanel);
        getContentPane().setBackground(new Color(249, 241, 232));
        pack();

    }

    public Toolbar getToolbar(){
        return (Toolbar)toolbar;
    }

    
    public GameBoard getGameBoard(){
        return (GameBoard)gameboard;
    }

}
