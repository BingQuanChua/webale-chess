// This class is the panel that contain button (back,save,help,admitdefeat), label (movecount, playertomove)
// The panel is placed on the right of boardframe which is beside of gameboard

package webale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public class Toolbar extends JPanel {

    private static final long serialVersionUID = 1L;
    private int moveCount;
    private JButton backButton = new JButton("Back");
    private JButton saveButton = new JButton("Save");
    private JButton helpButton = new JButton("Help");
    private JButton defeatButton = new JButton("Admit Defeat");
    private String playerToMove;
    private JLabel playerLabel = new JLabel("Player to Move : " + playerToMove);
    private JLabel moveCountLabel = new JLabel("Move Count : " + moveCount);
    private final Color DARK_BROWN = new Color(74, 59, 47);
    private Font BUTTON_FONT = new Font("Leelawadee", Font.BOLD, 15);

    public Toolbar() {
        playerToMove = "Red";
        moveCount = 0;
        backButton.setBackground(DARK_BROWN);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(BUTTON_FONT);
        saveButton.setBackground(DARK_BROWN);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(BUTTON_FONT);
        helpButton.setBackground(DARK_BROWN);
        helpButton.setForeground(Color.WHITE);
        helpButton.setFont(BUTTON_FONT);
        defeatButton.setBackground(DARK_BROWN);
        defeatButton.setForeground(Color.WHITE);
        defeatButton.setFont(BUTTON_FONT);
        playerLabel.setForeground(DARK_BROWN);
        moveCountLabel.setForeground(DARK_BROWN);

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

    // method to pop out JFileChooser to prompt player to Save File
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
