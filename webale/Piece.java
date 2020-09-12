package webale;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public abstract class Piece {

	private boolean isRedColor;
	private boolean hasFlipped;
	private Image image;

	Piece(){
		this.isRedColor = false;
		this.hasFlipped = true;
		this.image = null;
	}

	Piece(boolean isRedColor, boolean hasFlipped, String ImageUrl) throws IOException {
		this.isRedColor = isRedColor;
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

	public boolean getIsRedColor() {
		return isRedColor;
	}

	public boolean getHasFlipped() {
		return hasFlipped;
	}

	public void toggleFlippedState() {
		hasFlipped = !hasFlipped;
	}
}