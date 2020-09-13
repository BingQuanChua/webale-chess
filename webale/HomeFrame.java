package webale;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.io.IOException;

public class HomeFrame extends JFrame{
    Font  f1  = new Font(Font.DIALOG,  Font.BOLD, 15);
    int width = 500;
    int height = 500;
    JLabel title = new JLabel("Welcome to Webale Chess Game!",SwingConstants.CENTER);
    JButton startGame = new JButton ("Start Game");
    JButton loadGame = new JButton ("Load Game");
    JButton instruction = new JButton ("Instruction");
    JButton quitGame = new JButton ("Quit");
    ImageIcon logoImageIcon = null;
    ImageIcon instructionImageIcon = null;
    
    public HomeFrame(){
        super("Webale Chess");

        title.setFont(new Font("Verdana", Font.PLAIN, 18));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 5));

        try {                
            logoImageIcon = new ImageIcon(ImageIO.read(getClass().getResource(("images/logo.png"))));
            //load instruction image
            instructionImageIcon = new ImageIcon(ImageIO.read(getClass().getResource("images/Instructions_Help.png")));
        } catch (IOException ex) {
            System.out.print("cant load image");
        }
        
        btnPanel.add(startGame);
        btnPanel.add(loadGame);
        btnPanel.add(instruction);
        btnPanel.add(quitGame);

        // startGame.addMouseListener(this);
        // loadGame.addMouseListener(this);
        

        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);
        
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLUE);
        panel.add(title, BorderLayout.NORTH);
        add(panel);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(width, height);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("WEBALE CHESS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    public JButton getStartButton(){
        return startGame;
    }

    public JButton getLoadButton(){
        return loadGame;
    }

    public JButton getInstructionButton(){
        return instruction;
    }

    public JButton getQuitButton(){
        return quitGame;
    }

    public ImageIcon getInstructionImageIcon(){
        return instructionImageIcon;
    }
 
    public static void main (String[] args){
        new HomeFrame();
    }

}

