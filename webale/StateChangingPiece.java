// This class is for changing plus and triangle pieces (plus change to triangle & triangle change to plus)

package webale;

import java.io.IOException;

public class StateChangingPiece extends Piece {
    private Movement movement;
    private String color;

    public StateChangingPiece(boolean isRedColor, String imageURL) throws IOException {
        super(isRedColor, imageURL);
        movement = null; // by default no movement type, need to be set, image is based on what is passed in
        color = isRedColor ? "red" : "blue";
    }

    // This method enables this piece to switch its movement type based on the state
    // It only accepts PlusMovement and TriangleMovement
    public void setState(Movement movement) throws IOException{
        this.movement = movement;

        if (movement instanceof PlusMovement) {
            // change to Plus
            String imageURL = String.format("./images/%s_plus.png", color);
            StringBuilder flippedImg = new StringBuilder(imageURL.substring(0, imageURL.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
        }
        else if (movement instanceof TriangleMovement) {
            // change to Triangle
            String imageURL = String.format("./images/%s_triangle.png", color);
            StringBuilder flippedImg = new StringBuilder(imageURL.substring(0, imageURL.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
        } 

        // if other movement are passed in, nothing will happen to the image
    }

    public Movement getState() {
        return movement;
    }

    @Override
    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        return movement.move(coordinate, startPoint, endPoint);
    }

    // plus and triangle information for saving in file
    public String toString() {
        String c = color.toUpperCase().charAt(0) + color.substring(1, color.length()) + " ";
        if (movement instanceof TriangleMovement) {
            return c + "Triangle";
        }
        else {
            if (movement instanceof PlusMovement) {
                return c + "Plus";
            }
            else {
                return c + "Piece";  
            }
        }
    }
}