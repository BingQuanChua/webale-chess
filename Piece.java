package OurAssignment;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class Piece {

	private String pieceColor;
	private boolean flippedState;
	private Image image;
	private String name;

	Piece(){
		this.name = "";
		this.pieceColor = "";
		this.image = null;
		this.flippedState = true;
	}

	Piece(String name, String pieceColor, boolean flippedState, String ImageUrl) throws IOException {
		this.name = name;
		this.pieceColor = pieceColor;
		this.flippedState = flippedState;
		this.setIcon(ImageUrl);
	}

	public void setIcon(String ImageUrl) throws IOException {
        image = ImageIO.read(getClass().getResource(ImageUrl));
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