package webale;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Piece {

	private boolean isRedColor;
	private boolean hasFlipped;
	private ImageIcon image;

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

	abstract public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint);

	public void setIcon(String ImageUrl) throws IOException {
        image = new ImageIcon(ImageIO.read(getClass().getResource(ImageUrl)));
    }

    public ImageIcon getIcon() {
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