/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import java.util.List;
import sample.Ships;

public class Square extends Rectangle
{
    private GameGrid grid;
    private GameGrid computerGrid;
    public int xCoordinate;
    public int yCoordinate;
    public int shipPos;
    public Ships battleShip;
    public boolean isBoatHit;
    private static final double XPOSITION = 30;
    private static final double YPOSITION = 30;

    public Square()
    {
    }

    public Square(int xCoordinate, int yCoordinate, GameGrid grid) {
        super(XPOSITION, YPOSITION);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;
        this.battleShip = null;
        isBoatHit = false;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    public Square(int xCoordinate, int yCoordinate, GameGrid grid, int shipPos, GameGrid computerGrid)
    {
        super(XPOSITION, YPOSITION);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;
        this.computerGrid = computerGrid;
        this.battleShip = null;
        this.shipPos = shipPos;
        isBoatHit = false;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    public void checkAction()
    {
        isBoatHit = true;
        setFill(Color.DARKBLUE);
    }

    public boolean shipExists()
    {
        if(battleShip != null)
        {
            battleShip.shipHit();
            setFill(Color.RED);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void decrementScore()
    {
        grid.result = grid.result - 1;
    }

    public void incrementScore()
    {
        grid.result = grid.result + 1;
    }

    protected boolean userAction()
    {
        boolean flag = false;
        checkAction();

        if (shipExists())
        {
            if (!battleShip.sunkenShip())
            {
                decrementScore();
            }
            flag = true;
            return flag;
        }
        else
            return flag;
    }
}
