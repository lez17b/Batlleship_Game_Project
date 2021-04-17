/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Destroyer Class
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import sample.Ships;

// This is the GameGrid class that will extend from the Parent class (which we import) in order to create the
// functioning game grid for our Battleship game
public class GameGrid extends Parent {
    public int result = 5;                       // Variable that stores the result of each grid
    protected boolean canWin = false;           // Boolean that checks if player can win
    protected double score;                     // Boolean that we use to keep track of the score
    private boolean human = false;              // Boolean that we use to see if the grid if for a human (player)
    private VBox gameGrid = new VBox();         // We create a VBox, where we will store the gameGrid at
    private boolean computer = false;           // Boolean that we use to see if it is a computer player
    // Create variables for each of the ships that we use
    private Destroyer destroyer;
    private Carrier carrier;
    private Cruiser cruiser;
    private BattleShip batShip;
    private Submarine submarine;
    // Creating constant/final variables to store the total amount of rows and columns of the game (10 by 10 grid)
    private static final int TOTALROWS = 10;
    private static final int TOTALCOLS = 10;

    // Constructor with 2 parameters that we use to see if the parameters passed in are for
    // human or computer players
    public GameGrid(boolean computer, boolean human)
    {
        // We set the variables we created above to the parameters passed in
        this.computer = computer;
        this.human = human;
        int index = 0;
        int counter;
        score = 0;                              // We set the score to 0
    }

    // Constructor with 3 parameters that we use to see if the parameters passed in are for
    // human or computer players, as well as an eventHandler for the menu options of the grid
    public GameGrid(boolean computer, boolean human, EventHandler<? super ContextMenuEvent> menuOption)
    {
        // We set the variables we created above to the parameters passed in
        this.computer = computer;
        this.human = human;
        int index = 0;
        int counter;
        score = 0;                          // We set the score to 0

        // We call the addCols function that we pass in the 3 parameters, in order to iterate over the
        // entire 10 by 10 grid to create it
        addCols(computer, human, menuOption);
    }

    // Constructor with 2 parameters, with a boolean to check if it is a computer player, as
    // well as an eventhandler that we use to place the ships
    public GameGrid(boolean computer, EventHandler<? super MouseEvent> setShips)
    {
        // We set the variables we created above to the parameters passed in
        this.computer = computer;
        score = 0;

        // We call the addColumns function that we pass in the 2 parameters, in order to iterate over the
        // entire 10 by 10 grid to create it
        addColumns(computer, setShips);
    }

    // The addShipsToArray method will add all of the ships that we created to the shipsArray ArrayList
    public void addShipsToArray()
    {
        // We create the shipsArray ArrayList
        ArrayList<Ships> shipsArray = new ArrayList<>();

        // We then add of the ships we created into the arrayList
        shipsArray.add(destroyer);
        shipsArray.add(carrier);
        shipsArray.add(submarine);
        shipsArray.add(batShip);
        shipsArray.add(cruiser);
    }

    // This addColumns method will take in 2 parameters, with a boolean to check if we have a computer player,
    // as well as a mouseclick eventhandler that we use to place the ships
    public void addColumns(boolean c, EventHandler<? super MouseEvent> mouseClick)
    {
        // We create 2 variables in order to iterate over all of the rows
        int firstCol = 0;                           // Column variable
        int firstRow;                               // Row variable

        // We use a for loop in order to iterate over each of the rows, which is 10 times in total
        for (; firstCol < TOTALCOLS; firstCol++)
        {
            // We create an HBOX that will store all of the columns of the grid
            HBox columns = new HBox();
            // We now iterate over each of the rows, which is 10 times in total
            for (firstRow = 0; firstRow < TOTALROWS; firstRow++)
            {
                // We create squares for each iteration, this means that we will have 100 squares in total,
                // since we iterate 10 times for the rows and 10 times for the columns
                Square gridSquares = new Square(firstRow, firstCol, this);

                // We add each of the events from the mouse into the grids of the squares
                gridSquares.setOnMouseClicked(mouseClick);

                // We set all of the ships to null
                destroyer = null;
                cruiser = null;
                submarine = null;
                carrier = null;
                batShip = null;

                // We now add all of the gridsquares into the columns
                columns.getChildren().add(gridSquares);
            }
            gameGrid.getChildren().add(columns);            // We add all of the columns into the grid, including the squares
        }
        getChildren().add(gameGrid);                // We add the gameGrid to our VBox that we created
        addShipsToArray();                          // We call the addShipsToArray method to add them to the
                                                    // arraylist
    }

    // This placeShipVertial method will take in 5 parameters, which are the ships that we use, the x and y coordinates
    // as well as the size of the ships, and the limit of where we we can add it
    public void placeShipVertical(Ships battleShip, int xCoordinate, int yCoordinate, int shipSize, int limit)
    {
        int counter = yCoordinate;                      // We set a counter as the y coordinate passed in

        // We use a while loop in order to iterate until the counter reaches the limit
        while (counter < limit)
        {
            // We create a player square, which will be where the user will be adding the ship on the grid
            Square playerSquare = getSquarePosition(xCoordinate, counter);          // We use the Square class

            // We set the player square as the battleship parameter passed in
            playerSquare.battleShip = battleShip;

            // If it is not a computer player, then we will set the grids to gray, and the lines between each
            // square as white
            if (!computer)
            {
                // We set the color to gray of each square
                playerSquare.setFill(Color.GRAY);

                // We set the color to white, which are the lines between each square
                playerSquare.setStroke(Color.WHITE);
            }
            // We increment our counter, as we keep iterating until we reach the limit
            counter++;
        }
    }

    // The checkVerticalPlacement method will take in 4 parameters, which is the ship that we want to add
    // to the grid, as well as the x and y coordinates, and the limit
    public boolean checkVerticalPlacement(Ships battleShip, int xCoordinate, int yCoordinate, int limit)
    {
        // We create 3 variables, a boolean which we use to return the result, as well as a current integer
        // that will store the ycoordinate value
        boolean res = true;
        int current = yCoordinate;
        int temp;

        // We use this while loop to iterate until we reach the limit
        while (current < limit)
        {
            // If the placement of the ship is not valid, then we set the boolean that we created above to false,
            // making sure that we pass in the x coordinate and the current variable
            if(!isValidPlacement(xCoordinate, current))
                res = false;

            current++;                              // We increment the counter
        }
        return res;                     // We return our boolean
    }

    // This is the getSquarePosition method that we use in order to get both of the coordinates that the player uses,
    // and set it to our HBOX, and then returning that to our square
    public Square getSquarePosition(int xCoordinate, int yCoordinate)
    {
        // This is the Node s that we create which gets the y coordinate and x coordinate of both of the children
        Node s = ((HBox) gameGrid.getChildren().get(yCoordinate)).getChildren().get(xCoordinate);

        // We then return the square that we created, taking into account the coordinates, casting that to a Square
        return (Square) s;
    }

    // This is the validPlacement method that takes in 3 parameters, which is the ship that we check to ensure
    // that it is a valid cell, as well the coordinates of where the user will place it
    private boolean validPlacement(Ships battleShip, int xCoordinate, int yCoordinate) {
        int position;                           // Position of where the ship will go
        int temp;                               // Temporary variable that we create for our iterations
        // The total size of the ship, calling the getLife() method
        int totalSize = battleShip.getLife();

        // We check to what the orientation of the battleship is
        if (battleShip.getOrientation()) {
            // We then create a limit variable that will be assigned to the y coordinate and the total size we got above
            int limit = yCoordinate + totalSize;

            // The position variable will be assigned to the y coordinate for the while loop
            position = yCoordinate;

            // We use this while loop to iterate until we reach the limit, which means that the
            // placement is valid
            while (position < limit) {
                // We check to see that the placement is valid, and returning false if not
                if (!isValidPlacement(xCoordinate, position))
                    return false;

                // We create a Square by calling the x coordinate and position of the variables
                Square currentSquare = getSquarePosition(xCoordinate, position);

                // If the current square that we created above is not equal to null, we return false
                if (currentSquare.battleShip != null)
                    return false;

                // We increment the position variable
                position++;
            }
        } else {
            // This means that the orientation of the ship is horizontal, so we create an upperlimit for the grid,
            // setting that as the x coordinate, and the total size of the ship
            int upperLimit = xCoordinate + totalSize;

            // We use the temp variable to assign that to the x coordiante
            temp = xCoordinate;

            // We use a while loop to iterate until we reach the upper limit of the grid
            while (temp < upperLimit) {
                // We check that the placement is valid, passing as parameters the temporary variable and the y coordinate,
                // returning false if not
                if (!isValidPlacement(temp, yCoordinate))
                    return false;

                // We create a cell for the square that will get the position of the square, in order to place the
                // ship, passing in as parameters
                Square cell = getSquarePosition(temp, yCoordinate);

                // If the cell of the battleship is not null, then we return false
                if (cell.battleShip != null)
                    return false;

                // We increment the temporary variable
                temp++;
            }
        }
        return true;            // We then return true if the placement if valid
    }

    // This is the placeShipHorizontal method that takes in 5 parameters, which are the battleships, the x and y coordinates,
    // as well as the size of the ship and the limit of the grid
    public void placeShipHorizontal(Ships battleShip, int xCoordinate, int yCoordinate, int shipSize, int limit)
    {
        // This is a variable that we set to the x coordinate
        int counterHorizontal = xCoordinate;

        // We use this while loop to check the boudnary limits of the grid, using the variable that we created above
        while (counterHorizontal < limit)
        {
            // We create a horizontal ship using the Square class in order to get the position of the square,
            // passing as parameters the counter, and the y coordinate of where it can be placed
            Square horizontalShip = getSquarePosition(counterHorizontal, yCoordinate);
            // We create a horizontal ship instance and set that to the parameter we pass in
            horizontalShip.battleShip = battleShip;

            // If it is a computer move, we set the color of the grid to gray, and the lines between each
            // square to white
            if (!computer)
            {
                // We call the setFill method
                horizontalShip.setFill(Color.GRAY);
                horizontalShip.setStroke(Color.WHITE);              // We call the setStroke method
            }
            counterHorizontal++;                // We increment the counter for our while loop
        }
    }

    // This is the addCols method that takes 3 parameters, that we use in the constructors above. It takes in
    // 2 booleans, as well as an event handler for the menu options
    public void addCols(boolean computer, boolean human, EventHandler<? super ContextMenuEvent> menuOption)
    {
        // We create 2 variables for the columns and the rows
        int firstCol = 0;           // We set it to 0
        int firstRow;

        // We use this for loop to iterate until 10, which are the total columns in the grid
        for (; firstCol < TOTALCOLS; firstCol++)
        {
            // We create an hbox that will contain all of the columns of the grid
            HBox columns = new HBox();
            // We iterate over all of the rows in the grid, which are 10 in total
            for (firstRow = 0; firstRow < TOTALROWS; firstRow++)
            {
                // We create this grid squares that takes as parameters the rows and columns iterations, until we reach 10
                Square gridSquares = new Square(firstRow, firstCol, this);

                // This method will ensure that we add it to the menu option, when the user clicks using the events
                gridSquares.setOnMouseClicked((EventHandler<? super MouseEvent>) menuOption);

                // We add the grid squares to the columns (10 in total)
                columns.getChildren().add(gridSquares);
            }
            gameGrid.getChildren().add(columns);            // We add these to the game grid
        }
        getChildren().add(gameGrid);                        // We add these to the grid, and call the addShipsToArray
        addShipsToArray();                                  // method to add the ships to the ArrayList
    }

    // This is the isValidPlacement method that takes as parameters the x and y coordinates, and checks to ensure
    // that we do not go over our index bounds.
    private boolean isValidPlacement(double xCoordinate, double yCoordinate)
    {
        // We will use this boolean condition to check that the x coordinate is greater than 10 and less than
        // 10, as well as the y coordinate being greater than 0, and less than the total rows.
        return xCoordinate >= 0 && xCoordinate < TOTALROWS && yCoordinate >= 0 && yCoordinate < TOTALCOLS;
    }

    // This is the shipPlacement method that takes in as parameters the ship, as well as the y and
    // x coordinates
    public boolean shipPlacement(Ships battleShip, int xCoordinate, int yCoordinate)
    {
        // We create these 3 variables, which will store the size of the ship, the orientation (vertical
        // or horizontal), as well as a flag of what we return in the method
        int sizeOfShip;
        int shipOrientation;
        boolean flag = false;

        // We use this if condition to check that the placement of the ship is valid, passing the ship, and the
        // coordinates
        if (validPlacement(battleShip, xCoordinate, yCoordinate))
        {
            // We get the size of the ship by calling the getLife method
            sizeOfShip = battleShip.getLife();

            // We check the orientation of the ship, if it is true, then it is vertical
            if (battleShip.getOrientation())
            {
                // We create the limit variable that is set to the y coordinate and the size of the ship
                int limit = yCoordinate + sizeOfShip;
                // We call the placeShipVertical method to place the ship in a vertical fashion
                placeShipVertical(battleShip, xCoordinate, yCoordinate, sizeOfShip, limit);
            }
            else
            {
                // We create the limit variable that is set to the x coordinate and the size of the ship,
                // since the ship will be horizontal
                int limit = xCoordinate + sizeOfShip;
                // We call the placeShipHorizontal method to place the ship in a horizontal fashion
                placeShipHorizontal(battleShip, xCoordinate, yCoordinate, sizeOfShip, limit);
            }
            flag = true;                // We set the boolean to true
            return flag;
        }
        return flag;                    // We return the flag
    }
}