package webale;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public abstract class Piece {

	private String pieceColor;
	private boolean hasFlipped;
	private Image image;

	Piece(){
		this.pieceColor = "";
		this.image = null;
		this.hasFlipped = true;
	}

	Piece(String pieceColor, boolean hasFlipped, String ImageUrl) throws IOException {
		this.pieceColor = pieceColor;
		this.hasFlipped = hasFlipped;
		this.setIcon(ImageUrl);
	}

	abstract public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint);

	public void setIcon(String ImageUrl) throws IOException {
        image = ImageIO.read(getClass().getResource(ImageUrl));
    }

    public Image getIcon() {
        return image;
    }

	public String getColour()
	{
		return pieceColor;
	}

	public boolean getHasFlipped()
	{
		return hasFlipped;
	}

	public void toggleFlippedState()
	{
		hasFlipped = !hasFlipped;
	}
}