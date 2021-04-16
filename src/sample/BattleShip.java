package sample;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BattleShip extends Ships
{
    private ArrayList<Point> Coordinates = new ArrayList<>();

    public BattleShip() throws FileNotFoundException {
        super(4, false);
        //FileInputStream inputstream = new FileInputStream("sample/1x1.png");
        //Image image = new Image(inputstream);
    }



}
