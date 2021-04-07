package sample;

import java.awt.Point;
import java.util.ArrayList;


public class Ship {

    private int shipSize;
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Ship(int size)
    {
        this.shipSize = size;
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

}
