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

// This is the Submarine class that inherits from the Ships class, creating an ArrayList
// called Coordinates and calling the super method
public class Submarine extends Ships
{
    // We create the ArrayList coordinates
    private ArrayList<Point> Coordinates = new ArrayList<>();

    // In the constructor, we call super to set the size to 3, and the orientation to vertical
    public Submarine() {
        super(3, true);
    }
}
