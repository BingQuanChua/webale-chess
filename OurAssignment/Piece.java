package OurAssignment;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Piece {

	private String pieceColor;
	private boolean flippedState;
	private Image image;

	Piece(){
		this.pieceColor = "";
		this.image = null;
		this.flippedState = true;
	}

	Piece(String pieceColor, boolean flippedState, String ImageUrl) throws IOException {
		this.pieceColor = pieceColor;
		this.flippedState = flippedState;
		this.setIcon(ImageUrl);
	}

	abstract public boolean canMove(GameBoard gameboard, Coordinate startpoint, Coordinate endpoint);

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