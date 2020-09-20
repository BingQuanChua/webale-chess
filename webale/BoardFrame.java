// This class is for containing the gameboard, toolbar and gamepanel, which will be viewed/used by user after starting a game
// Toolbar is placed on the right of boardframe
// Gameboard is placed on the left of boardframe, and occupies most space on the boardframe
package webale;

import java.awt.*;
import javax.swing.*;

public class BoardFrame extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel gameboard;
    private final JPanel toolbar;
    private final JPanel gamePanel;

    public BoardFrame() {
        super(null, "WEBALE CHESS", ModalityType.MODELESS);

        gameboard = new GameBoard();
        toolbar = new Toolbar();
        gamePanel = new JPanel();

        gamePanel.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();

        // constraints for gameboard
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 20, 0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 4;
        gamePanel.add(gameboard, c);
        gameboard.setOpaque(false);

        // constraints for toolbar
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 0;
        c.ipady = 200;
        c.insets = new Insets(200, 0, 0, 0);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        gamePanel.add(toolbar, c);
        toolbar.setOpaque(false);

        gamePanel.setOpaque(false);
        add(gamePanel);
        getContentPane().setBackground(new Color(249, 241, 232));       //light beige
        pack();

    }

    public Toolbar getToolbar() {
        return (Toolbar) toolbar;
    }

    public GameBoard getGameBoard() {
        return (GameBoard) gameboard;
    }

}
