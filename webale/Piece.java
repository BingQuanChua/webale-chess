// This abstract class will be inherited by arrow, chevron, sun, statechangingpiece (plus & triangle)

package webale;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Piece {

	// isRedColor -> to identify the colour of piece, if the piece is of blue
	// player, then it will set to false
	// hasFlipped -> to identify the flip state of piece, if the piece needed to be
	// rotated, then it will be set to true
	private boolean isRedColor = false;
	private boolean hasFlipped = false;
	private ImageIcon imageIcon;
	private ImageIcon flippedImageIcon;

	Piece() {
		this.isRedColor = false;
		this.imageIcon = null;
		this.flippedImageIcon = null;
	}

	// The constructor of this class, we initialize all the attribute here,
	// including assigning picture for each pieces
	Piece(boolean isRedColor, String imageUrl) throws IOException {
		this.isRedColor = isRedColor;
		StringBuilder sb = new StringBuilder(imageUrl.substring(0, imageUrl.length() - 4));
		sb.append("_rotated.png");
		String flippedImageUrl = sb.toString();
		this.setIcon(imageUrl, flippedImageUrl);
	}

	// This is a function to be inherited by all type of pieces, it is used to
	// verify if a movement is valid.
	abstract public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint);

	// This is a function that set Icon for each pieces, it is called in the
	// constructor
	public void setIcon(String imageUrl, String flippedImageUrl) throws IOException {
		imageIcon = new ImageIcon(ImageIO.read(getClass().getResource(imageUrl)));
		flippedImageIcon = new ImageIcon(ImageIO.read(getClass().getResource(flippedImageUrl)));
	}

	// This will return an ImageIcon object
	public ImageIcon getIcon() {
		return hasFlipped ? flippedImageIcon : imageIcon;
	}

	// This will return a boolean value.
	// If the piece is red color, then the value being returned is true;
	public boolean getIsRedColor() {
		return isRedColor;
	}

	// This will return a boolean value.
	// If the piece is flipped then the value being returned is true;
	public boolean getHasFlipped() {
		return hasFlipped;
	}

	// This function is used to toggle the HasFlipped variable.
	// When the function is called, if the HasFlipped is true then it will become
	// false, vice versa.
	public void toggleFlippedState() {
		hasFlipped = !hasFlipped;
	}

	abstract public void setState(Movement movement) throws IOException; // changes state between triangle and plus

	abstract public Movement getState(); // abstract method to get the piece state is whether Traingle or Plus

	abstract public String toString(); // color + " " + name

}