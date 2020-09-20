// This is a interface to be used by different pieces for moving
// It has a method to be overwritten for the classes that implement this class.
// Koh Han Yi

package webale;

public interface Movement {

        public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint);

}