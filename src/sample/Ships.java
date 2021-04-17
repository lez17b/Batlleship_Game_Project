/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

package sample;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import sample.Destroyer;

public class Ships {
    
    // Ship atributtes
    private int shipSize;
    private int life;
    private boolean Vertical = true;
    private ArrayList<Point> Coordinates = new ArrayList<>();
    private ArrayList<Ships> allShips = new ArrayList<>(5);

    // Constructor
    public Ships(int size)
    {
        shipSize = size;
    }
    
    // Parameter constructor
    public Ships(int size, boolean Orientation)
    {
        shipSize = size;
        life = size;
        Vertical = Orientation;
    }
    
    // get ship size method
    public int getShipSize()
    {
        return shipSize;
    }

    // get life method
    public int getLife()
    {
        return life;
    }

    //get orientation method
    public boolean getOrientation()
    {
        return Vertical;
    }

    //get ship coordinates orientation method
    public ArrayList<Point> getShipCoordinates()
    {
        return Coordinates;
    } 

    // add coordinates method
    public void addCoordinates(int x, int y)
    {
        Coordinates.add(new Point(x, y));
    }

    // Is vertical boolean method
    public boolean isVertical(boolean V)
    {
        if (!V) {
            Vertical = false;
        }
        return Vertical;
    }

    // Ship istill alive emthod
    public boolean sunkenShip()
    {
        return life > 0;
    }

    // Hit method
    public void shipHit()
    {
        life--;
    }
}
