package sample;

import sample.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Carrier extends Ship
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Carrier() {
        super(5);
    }

    @Override
    public int getShipSize() {
        return 5;
    }
}
