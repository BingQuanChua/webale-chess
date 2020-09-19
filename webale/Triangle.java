// This class is for chess piece, Triangle that inherited from abstract class Piece.

package webale;

import java.io.IOException;

public class Triangle extends Piece {
  private Movement movement;

  public Triangle(boolean isRedColor, String imageUrl) throws IOException{
      super(isRedColor, imageUrl); 
      movement = new TriangleMovement();
    }

  public boolean canMove(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) { 
		return movement.move(coordinate, startPoint, endPoint);
    } 
    
  public String toString(){
		return (getIsRedColor() ? "Red " : "Blue ") + "Triangle"; 
  }

  public void setState(Movement movement) {

  }
  
  public boolean getState() {
		return true;
	}
}