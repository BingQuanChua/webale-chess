package webale;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Toolbar extends JPanel{

    int width=800;
    int height=800;
    JLabel playerLabel = new JLabel("Player to Move : Red / Blue");
    JButton backButton = new JButton("Back");
    JButton saveButton= new JButton("Save");
    JButton helpButton= new JButton("Help");
    JPanel btnPanel = new JPanel(new GridLayout(10, 1, 10, 10));
    JPanel layout = new JPanel(new GridBagLayout());

    public Toolbar(){
        setBorder(new EmptyBorder(2, 3, 2, 3));
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnPanel.add(backButton);
        btnPanel.add(saveButton);
        btnPanel.add(helpButton);
        btnPanel.add(playerLabel);
        layout.add(btnPanel);
        setLayout(new BorderLayout());
        add(layout, BorderLayout.EAST);
    }
}
