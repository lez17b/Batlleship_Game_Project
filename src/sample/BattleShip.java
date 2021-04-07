package sample;

import sample.Ship;

import java.awt.*;
import java.util.ArrayList;

public class BattleShip extends Ship
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public BattleShip() {
        super(4);
    }

    @Override
    public int getShipSize() {
        return 4;
    }
}
