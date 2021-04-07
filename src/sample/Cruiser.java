package sample;

import sample.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Cruiser extends Ship
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Cruiser() {
        super(3);
    }

    @Override
    public int getShipSize() {
        return 3;
    }
}
