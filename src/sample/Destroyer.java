package sample;

import sample.Ships;
import java.awt.*;
import java.util.ArrayList;

public class Destroyer extends Ships {

    private ArrayList<Point> Coordinates = new ArrayList<>();

    public Destroyer()
    {
        super(2, true);
    }

}
