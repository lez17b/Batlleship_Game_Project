/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Carrier Class
import java.awt.*;
import java.util.ArrayList;

// Carrier class that we create that extends the Ships class, which we use to create
// the Coordinates ArrayList, and calling super
public class Carrier extends Ships
{
    // We create the Coordinates ArrayList
    private ArrayList<Point> Coordinates = new ArrayList<>();

    // We then call super, which we use from the Ships class, and set the size to 5, and
    // the orientation of the ship to false
    public Carrier() {
        super(5, false);
    }

}
