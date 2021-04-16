package sample;

import sample.Ships;

import java.awt.*;
import java.util.ArrayList;

public class BattleShip extends Ships
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public BattleShip() {
        super(4, false);
    }

}
