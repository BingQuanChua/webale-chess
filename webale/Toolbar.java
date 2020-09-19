package webale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public class Toolbar extends JPanel {

    final int width = 200;
    final int height = 200;
    int moveCount;
    JButton backButton = new JButton("Back");
    JButton saveButton = new JButton("Save");
    JButton helpButton = new JButton("Help");
    JButton defeatButton = new JButton("Admit Defeat");
    String playerToMove;
    JLabel playerLabel = new JLabel("Player to Move : " + playerToMove);
    JLabel moveCountLabel = new JLabel("Move Count : " + moveCount);
    final Color darkBrown = new Color(74, 59, 47);
    final Font buttonFont = new Font("Leelawadee", Font.BOLD, 15);

    public Toolbar() {
        playerToMove = "Red";
        moveCount = 0;
        backButton.setBackground(darkBrown);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);
        saveButton.setBackground(darkBrown);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(buttonFont);
        helpButton.setBackground(darkBrown);
        helpButton.setForeground(Color.WHITE);
        helpButton.setFont(buttonFont);
        defeatButton.setBackground(darkBrown);
        defeatButton.setForeground(Color.WHITE);
        defeatButton.setFont(buttonFont);
        playerLabel.setForeground(darkBrown);
        moveCountLabel.setForeground(darkBrown);

        setBorder(new EmptyBorder(0, 20, 0, 20)); // TLBR
        setLayout(new GridLayout(10, 1, 0, 10));
        add(backButton);
        add(saveButton);
        add(helpButton);
        add(defeatButton);
        add(playerLabel);
        add(moveCountLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerLabel.setText("Player to Move : " + playerToMove);
        moveCountLabel.setText("Move Count : " + moveCount);
    }

    public void setPlayerToMove(String playerToMove) {
        this.playerToMove = playerToMove;
    }

    public String getPlayerToMove() {
        return playerToMove;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getHelpButton() {
        return helpButton;
    }

    public JButton getDefeatButton() {
        return defeatButton;
    }

    public File openSaveFileDialogAndGetFileToSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a location to save your Webale file.");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Documents (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int response = fileChooser.showSaveDialog(null);
        File selectedFile = null;
        if (response == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        } else if (response == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "A file error has occured.\n(" + fileChooser.getSelectedFile().getPath() + ")", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return selectedFile;
    }
}
