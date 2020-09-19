package webale;

import java.io.IOException;

public class StateChangingPiece extends Piece {
    private Movement movement;
    private boolean isTriangle = true;
    private String color;

    public StateChangingPiece(boolean isRedColor, String imageURL) throws IOException {
        super(isRedColor, imageURL);
        movement = new TriangleMovement();
        color = isRedColor ? "red" : "blue";
    }

    public void setState(Movement movement) throws IOException{
        this.movement = movement;

        if (isTriangle) {
            // change to Plus
            StringBuilder img = new StringBuilder("images/");
            img.append(color);
            img.append("_plus.png");
            String imageURL = img.toString();

            StringBuilder flippedImg = new StringBuilder(img.substring(0, img.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
            isTriangle = false;
        }
        else {
            // change to Triangle
            StringBuilder img = new StringBuilder("images/");
            img.append(color);
            img.append("_triangle.png");
            String imageURL = img.toString();

            StringBuilder flippedImg = new StringBuilder(img.substring(0, img.length() - 4));
		    flippedImg.append("_rotated.png");
		    String flippedImageUrl = flippedImg.toString();
            
            super.setIcon(imageURL, flippedImageUrl);
            isTriangle = true;
        } 
    }

    public boolean getState() {
        return isTriangle;
    }

    public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        return movement.move(coordinate, startPoint, endPoint);
    }

    public String toString() {
        return isTriangle ? ((getIsRedColor() ? "Red " : "Blue ") + "Triangle") : ((getIsRedColor() ? "Red " : "Blue ") + "Plus");            
    }
}