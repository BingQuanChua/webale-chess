package webale;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;

public class Toolbar extends JPanel{

    int width = 200;
    int height = 200;
    JButton backButton = new JButton("Back");
    JButton saveButton = new JButton("Save");
    JButton helpButton = new JButton("Help");
    String playerToMove = "RED";
    JLabel playerLabel = new JLabel("Player to Move : " + playerToMove);
    final Color darkBrown = new Color(74, 59, 47);
    final Font buttonFont = new Font("Leelawadee", Font.BOLD, 15);

    public Toolbar(){
        backButton.setBackground(darkBrown);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);
        saveButton.setBackground(darkBrown);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(buttonFont);
        helpButton.setBackground(darkBrown);
        helpButton.setForeground(Color.WHITE);
        helpButton.setFont(buttonFont);
        playerLabel.setForeground(darkBrown);

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

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getSaveButton(){
        return saveButton;
    }

    public JButton getHelpButton(){
        return helpButton;
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
        } else if(response == JFileChooser.ERROR_OPTION){
            JOptionPane.showMessageDialog(null, "A file error has occured.\n(" + fileChooser.getSelectedFile().getPath() + ")", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return selectedFile;
    }
}
