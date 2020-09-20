// This class is for the GUI will be seen by user in the beginning, containing all images in the program except pieces images

package webale;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class HomeFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    int width = 556;
    int height = 574;
    JLabel title = new JLabel("Welcome to Webale Chess Game!", SwingConstants.CENTER);
    JButton startGame = new JButton("Start New Game");
    JButton continueGame = new JButton("Continue Last Game");
    JButton loadGame = new JButton("Load Game");
    JButton instruction = new JButton("Instruction");
    JButton quitGame = new JButton("Quit");
    ImageIcon logoImageIcon;
    ImageIcon instructionImageIcon;
    ImageIcon bgImageIcon;
    ImageIcon defeatImageIcon;

    public HomeFrame() {
        super("Webale Chess");

        continueGame.setEnabled(false);

        try {
            logoImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/logo_resized.png")));
            instructionImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/Instructions_Help.png")));
            bgImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/background.png")));
            defeatImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/admit_defeat.png")));
        } catch (IOException ex) {
            System.out.print("cant load image");
        }

        JPanel logoPanel = new JPanel();
        JLabel imageLabel = new JLabel(logoImageIcon, JLabel.CENTER);
        logoPanel.add(imageLabel);
        logoPanel.setOpaque(false);
        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 0, 5));
        JButton[] buttonArr = { startGame, continueGame, loadGame, instruction, quitGame };
        final Color darkBrown = new Color(74, 59, 47);
        final Font buttonFont = new Font("Leelawadee", Font.BOLD, 12);
        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i].setForeground(Color.WHITE);
            buttonArr[i].setBackground(darkBrown);
            buttonArr[i].setFont(buttonFont);
            buttonArr[i].setFocusable(false);
            btnPanel.add(buttonArr[i]);
        }
        btnPanel.setOpaque(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;

        // contriants for logoPanel
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth = 3;
        panel.add(logoPanel, c);

        // contriants for btnPanel
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 30, 0);
        c.ipady = 50;
        c.ipadx = 10;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridheight = 2;
        c.gridwidth = 1;
        panel.add(btnPanel, c);

        panel.setOpaque(false);

        JLabel contentPane = new JLabel();
        contentPane.setIcon(bgImageIcon);
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        add(panel);

        setSize(width, height);
        setResizable(true);
        setVisible(true);
        setTitle("WEBALE CHESS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getStartButton() {
        return startGame;
    }

    public JButton getContinueButton() {
        return continueGame;
    }

    public JButton getLoadButton() {
        return loadGame;
    }

    public JButton getInstructionButton() {
        return instruction;
    }

    public JButton getQuitButton() {
        return quitGame;
    }

    public ImageIcon getInstructionImageIcon() {
        return instructionImageIcon;
    }

    public ImageIcon getDefeatImageIcon() {
        return defeatImageIcon;
    }

    public File openLoadDialogAndGetFileToLoad() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file;
        } else if (result == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "A file error has occured.\n(" + fileChooser.getSelectedFile().getPath() + ")", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
