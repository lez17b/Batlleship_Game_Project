package sample;
import sample.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Submarine extends Ship
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Submarine() {
        super(3);
    }

    @Override
    public int getShipSize() {
        return 3;
    }


}
