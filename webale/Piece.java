package webale;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Piece {

	private boolean isRedColor;
	private boolean hasFlipped = false;
	private ImageIcon imageIcon;
	private ImageIcon flippedImageIcon;

	Piece() {
		this.isRedColor = false;
		this.imageIcon = null;
		this.flippedImageIcon = null;
	}

	Piece(boolean isRedColor, String imageUrl) throws IOException {
		this.isRedColor = isRedColor;
		StringBuilder sb = new StringBuilder(imageUrl.substring(0, imageUrl.length() - 4));
		sb.append("_rotated.png");
		String flippedImageUrl = sb.toString();
		this.setIcon(imageUrl, flippedImageUrl);
	}

	abstract public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint);

	public void setIcon(String imageUrl, String flippedImageUrl) throws IOException {
		imageIcon = new ImageIcon(ImageIO.read(getClass().getResource(imageUrl)));
		flippedImageIcon = new ImageIcon(ImageIO.read(getClass().getResource(flippedImageUrl)));
	}

	public ImageIcon getIcon() {
		return hasFlipped ? flippedImageIcon : imageIcon;
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

	abstract public String toString(); // color + " " + name

}