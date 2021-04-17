/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Destroyer Class
import java.awt.*;
import java.util.ArrayList;

// This is the Destroyer class that inherits from the Ships class, creating an ArrayList
// called Coordinates and calling the super method
public class Destroyer extends Ships {
    // We create the Coordinates arraylist
    private ArrayList<Point> Coordinates = new ArrayList<>();

    // In the constructor, we call super, setting the size to 2, and the orientation to true
    public Destroyer()
    {
        super(2, true);
    }

}
