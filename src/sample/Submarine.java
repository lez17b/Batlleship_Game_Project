package sample;

import java.awt.*;
import java.util.ArrayList;

public class Submarine extends Ships
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Submarine() {
        super(3, true);
    }
}
