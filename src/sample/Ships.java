package sample;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import sample.Destroyer;

public class Ships {
    private int shipSize;
    private int life;
    private boolean Vertical = true;
    private ArrayList<Point> Coordinates = new ArrayList<>();
    private ArrayList<Ships> allShips = new ArrayList<>(5);

    public Ships(int size)
    {
        shipSize = size;
    }

    public Ships(int size, boolean Orientation)
    {
        shipSize = size;
        life = size;
        Vertical = Orientation;
    }

    public int getShipSize()
    {
        return shipSize;
    }

    public int getLife()
    {
        return life;
    }

    public boolean getOrientation()
    {
        return Vertical;
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

    public boolean sunkenShip()
    {
        return life > 0;
    }

    public void shipHit()
    {
        life--;
    }
}
