/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Destroyer Class
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

    // Game grid attributes 
    private GameGrid grid;
    private GameGrid computerGrid;
    public int xCoordinate;
    public int yCoordinate;
    public int shipPos;
    public Ships battleShip;
    public boolean isBoatHit;
    private static final double XPOSITION = 30;
    private static final double YPOSITION = 30;

    // constructor with no parameters
    public Square()
    {
    }

    // parameter constructor:  creates a grid and adds coordinates to it
    public Square(int xCoordinate, int yCoordinate, GameGrid grid) {
        super(XPOSITION, YPOSITION);   // We call super
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.grid = grid;
        this.battleShip = null;
        isBoatHit = false;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }

    //parameter construcotr: Creates a grid with coordinates and ships located, also cretes an oponent grid
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

    // Check action method
    public void checkAction()
    {
        // we set the boolean to true, and color it dark blue
        isBoatHit = true;
        setFill(Color.DARKBLUE);
    }

    // Ship exists method
    public boolean shipExists()
    {
        // If the ship is not null
        if(battleShip != null)
        {
            // That means that the boolean is hit, so we color it red and return true
            battleShip.shipHit();
            setFill(Color.RED);
            return true;
        }
        else
        {
            return false;           // Return false
        }
    }

    // Decrement score emthod
    public void decrementScore()
    {
        grid.result = grid.result - 1;
    }

    // increment score emthod
    public void incrementScore()
    {
        grid.result = grid.result + 1;
    }

    // user action method: returns if there was an action or not
    protected boolean userAction()
    {
        boolean flag = false;           // Set to false
        checkAction();                  // Call the checkAction method

        // If the ship exists, we proceed
        if (shipExists())
        {
            if (!battleShip.sunkenShip())           // We call the sunkenShip method, and decrement the score
            {
                decrementScore();
            }
            flag = true;
            return flag;                    // Return the boolean we created
        }
        else
            return flag;
    }
}
