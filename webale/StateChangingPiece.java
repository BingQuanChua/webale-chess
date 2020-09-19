package webale;

import java.io.IOException;

public class StateChangingPiece extends Piece {
    private Movement movement;
    private String color;

    public StateChangingPiece(boolean isRedColor, String imageURL) throws IOException {
        super(isRedColor, imageURL);
        movement = null; // by default no movement type, need to be set
        color = isRedColor ? "red" : "blue";
    }

    public void setState(Movement movement) throws IOException{
        this.movement = movement;

        if (movement instanceof PlusMovement) {
            // change to Plus
            StringBuilder img = new StringBuilder("images/");
            img.append(color);
            img.append("_plus.png");
            String imageURL = img.toString();

            StringBuilder flippedImg = new StringBuilder(img.substring(0, img.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
        }
        else if (movement instanceof TriangleMovement) {
            // change to Triangle
            StringBuilder img = new StringBuilder("images/");
            img.append(color);
            img.append("_triangle.png");
            String imageURL = img.toString();

            StringBuilder flippedImg = new StringBuilder(img.substring(0, img.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
        } 

        // if other movement are passed in, nothing will happen to the image
    }

    public Movement getState() {
        return movement;
    }

    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        return movement.move(coordinate, startPoint, endPoint);
    }

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