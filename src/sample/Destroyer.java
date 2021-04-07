package sample;
import sample.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Destroyer extends Ship {

    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Destroyer()
    {
        super(2);
    }

    @Override
    public int getShipSize() {
        return 2;
    }

    public ArrayList<Point> getShipCoordinates()
    {
        return Coordinates;
    }

}
