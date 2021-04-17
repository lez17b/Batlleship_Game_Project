/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the BattleShip Class
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// We create a BattleShip class that inherits from the Ships class, that will create a Coordinates
// ArrayList and then call super in the constructor
public class BattleShip extends Ships
{
    // We create an ArrayList called Coordinates
    private ArrayList<Point> Coordinates = new ArrayList<>();

    // In the constructor, we call super, which is from the Ships class, and we set the orientation
    // to false
    public BattleShip() throws FileNotFoundException {
        super(4, false);
    }
}
