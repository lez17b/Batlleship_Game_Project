package sample;

import java.awt.Point;
import java.util.ArrayList;


public class Ship {

    private int shipSize = 0;
    private int life = 0;
    private boolean Vertical = true;
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Ship(int size, boolean Orientation)
    {
        this.shipSize = size;
        this.life = size;
        this.Vertical = Orientation;
    }

    public int getShipSize()
    {
        return shipSize;
    }

    public ArrayList<Point> getShipCoordinates()
    {
        return Coordinates;
    }

    public void addCoordinates(int x, int y)
    {
        Coordinates.add(new Point(x, y));
    }

    public boolean isVertical(boolean V)
    {
        if (!V) {
            Vertical = false;
        }
        return Vertical;
    }

    public boolean isAlive()
    {
        return life > 0;
    }

    public void hit()
    {
        life--;
    }

}
