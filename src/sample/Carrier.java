package sample;

import java.awt.*;
import java.util.ArrayList;

public class Carrier extends Ships
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Carrier() {
        super(5, false);
    }

}
