package OurAssignment;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece {

	private String pieceColor;
    private boolean flippedState;
    private Image image;
    private String name;

	Piece(String name, String pieceColor, boolean flippedState)
	{
        this.name = name;
		this.pieceColor = pieceColor;
        this.flippedState = flippedState;
    }
    
    public void setIcon(String ImageUrl){
        ImageIcon icon = new ImageIcon(ImageUrl);
        image = icon.getImage();
    }

    public Image getIcon() {
        return image;
    }

	public String getColor()
	{
		return pieceColor;
	}

	public boolean getFlippedState()
	{
		return flippedState;
	}

	public void flipState()
	{
		flippedState = !flippedState;
	}
}