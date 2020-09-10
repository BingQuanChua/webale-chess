package OurAssignment;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.image.*;

public class HomeFrame {
    JFrame frame =new JFrame("Webale Chess");
    int width = 500;
    int height = 500;
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
                    //load instruction image
                    ImageIcon instructionPic = new ImageIcon("Instructions_Help.png");
                    JOptionPane.showMessageDialog(null,"Instruction","Webale-Chess",JOptionPane.INFORMATION_MESSAGE,instructionPic);
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
        
        frame.add(panel);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("WEBALE CHESS");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
    }
 
    public static void main (String[] args){
        new HomeFrame();
    }

}

