package webale;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class HomeFrame {
    JFrame frame =new JFrame("Webale Chess");
    int width = 500;
    int height = 500;
    JLabel title = new JLabel("Welcome to Webale Chess Game!",SwingConstants.CENTER);
    JButton startGame = new JButton ("Start Game");
    JButton loadGame = new JButton ("Load Game");
    JButton instruction = new JButton ("Instruction");
    JButton quitGame = new JButton ("Quit");
    //private BufferedImage imageIcon;
    
    public HomeFrame(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 5));

    //     try {                
    //       imageIcon = ImageIO.read(getClass().getResource(("logo.png")));
    //    } catch (IOException ex) {
    //         System.out.print("cant load image");
    //    }
        
        btnPanel.add(startGame);
        btnPanel.add(loadGame);
        btnPanel.add(instruction);
        btnPanel.add(quitGame);
        // startGame.addMouseListener(this);
        // loadGame.addMouseListener(this);
        instruction.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getButton()==MouseEvent.BUTTON1){
                    try{
                        //load instruction image
                        JLabel instructionLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("images/Instructions_Help.png"))));
                        JOptionPane.showMessageDialog(null, instructionLabel, "Instruction", JOptionPane.PLAIN_MESSAGE, null);
                        // btnPanel.removeAll();
                        // btnPanel.add(instructionLabel);
                    }
                    catch (Exception ex) {
                        System.out.println("Image not found");
                    }
                }
            }
        });
        quitGame.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getButton()==MouseEvent.BUTTON1){
                    System.exit(0);
                }
            }
        });
        layout.add(btnPanel);
        panel.add(layout, BorderLayout.CENTER);
        
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLUE);
        panel.add(title, BorderLayout.NORTH);
        frame.add(panel);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("WEBALE CHESS");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
    }
 
    public static void main (String[] args){
        new HomeFrame();
    }

}

