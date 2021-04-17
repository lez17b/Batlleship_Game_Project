/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Cruiser Class
import java.awt.*;
import java.util.ArrayList;

// We create the Cruiser class that inherits from the Ships class, which we use to
// create the Coordinates ArrayList and call super
public class Cruiser extends Ships {
    // We create the Coordinates Arraylist
    private ArrayList<Point> Coordinates = new ArrayList<>();

    // We then call super, setting the size of the cruiser to 3, and setting the orientation
    // to true
    public Cruiser() {
        super(3, true);
    }

}
