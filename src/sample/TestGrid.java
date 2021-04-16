package sample;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import sample.Ships;


public class TestGrid extends Parent {
    public int result = 5;
    protected boolean canWin = false;
    protected double score;
    private boolean human = false;
    private VBox gameGrid = new VBox();
    private boolean computer = false;
    private Destroyer destroyer;
    private Carrier carrier;
    private Cruiser cruiser;
    private BattleShip batShip;
    private Submarine submarine;
    private static final int TOTALROWS = 10;
    private static final int TOTALCOLS = 10;

    public TestGrid(boolean computer, boolean human)
    {
        this.computer = computer;
        this.human = human;
        int index = 0;
        int counter;
        score = 0;
    }

    public TestGrid(boolean computer, boolean human, EventHandler<? super ContextMenuEvent> menuOption)
    {
        this.computer = computer;
        this.human = human;
        int index = 0;
        int counter;
        score = 0;

        addCols(computer, human, menuOption);
    }

    public TestGrid(boolean computer, EventHandler<? super MouseEvent> setShips)
    {
        this.computer = computer;
        score = 0;

        addColumns(computer, setShips);
    }

    public void addShipsToArray()
    {
        ArrayList<Ships> shipsArray = new ArrayList<>();
        shipsArray.add(destroyer);
        shipsArray.add(carrier);
        shipsArray.add(submarine);
        shipsArray.add(batShip);
        shipsArray.add(cruiser);
    }

    public void addColumns(boolean c, EventHandler<? super MouseEvent> mouseClick)
    {
        int firstCol = 0;
        int firstRow;

        for (; firstCol < TOTALCOLS; firstCol++)
        {
            HBox columns = new HBox();
            for (firstRow = 0; firstRow < TOTALROWS; firstRow++)
            {
                Square gridSquares = new Square(firstRow, firstCol, this);
                gridSquares.setOnMouseClicked(mouseClick);
                destroyer = null;
                cruiser = null;
                submarine = null;
                carrier = null;
                batShip = null;
                columns.getChildren().add(gridSquares);
            }
            gameGrid.getChildren().add(columns);
        }
        getChildren().add(gameGrid);
        addShipsToArray();
    }


    public void placeShipVertical(Ships battleShip, int xCoordinate, int yCoordinate, int shipSize, int limit)
    {
        int counter = yCoordinate;

        while (counter < limit)
        {
            Square playerSquare = getSquarePosition(xCoordinate, counter);
            playerSquare.battleShip = battleShip;
            if (!computer)
            {
                playerSquare.setFill(Color.GRAY);
                playerSquare.setStroke(Color.WHITE);
            }
            counter++;
        }
    }

    public boolean checkVerticalPlacement(Ships battleShip, int xCoordinate, int yCoordinate, int limit)
    {
        boolean res = true;
        int current = yCoordinate;
        int temp;

        while (current < limit)
        {
            if(!isValidPlacement(xCoordinate, current))
                res = false;

            current++;
        }
        return res;
    }

    public Square getSquarePosition(int xCoordinate, int yCoordinate)
    {
        Node s = ((HBox) gameGrid.getChildren().get(yCoordinate)).getChildren().get(xCoordinate);
        return (Square) s;
    }

    private boolean validPlacement(Ships battleShip, int xCoordinate, int yCoordinate) {
        int position;
        int temp;
        int totalSize = battleShip.getLife();

        if (battleShip.getOrientation()) {
            int limit = yCoordinate + totalSize;
            position = yCoordinate;

            while (position < limit) {
                if (!isValidPlacement(xCoordinate, position))
                    return false;

                Square currentSquare = getSquarePosition(xCoordinate, position);

                if (currentSquare.battleShip != null)
                    return false;

                position++;
            }
        } else {
            int upperLimit = xCoordinate + totalSize;
            temp = xCoordinate;

            while (temp < upperLimit) {
                if (!isValidPlacement(temp, yCoordinate))
                    return false;

                Square cell = getSquarePosition(temp, yCoordinate);

                if (cell.battleShip != null)
                    return false;

                temp++;
            }
        }
        return true;
    }

    public void placeShipHorizontal(Ships battleShip, int xCoordinate, int yCoordinate, int shipSize, int limit)
    {
        int counterHorizontal = xCoordinate;

        while (counterHorizontal < limit)
        {
            Square horizontalShip = getSquarePosition(counterHorizontal, yCoordinate);
            horizontalShip.battleShip = battleShip;
            if (!computer)
            {
                horizontalShip.setFill(Color.GRAY);
                horizontalShip.setStroke(Color.WHITE);
            }
            counterHorizontal++;
        }
    }

    public void addCols(boolean computer, boolean human, EventHandler<? super ContextMenuEvent> menuOption)
    {
        int firstCol = 0;
        int firstRow;

        for (; firstCol < TOTALCOLS; firstCol++)
        {
            HBox columns = new HBox();
            for (firstRow = 0; firstRow < TOTALROWS; firstRow++)
            {
                Square gridSquares = new Square(firstRow, firstCol, this);
                gridSquares.setOnMouseClicked((EventHandler<? super MouseEvent>) menuOption);
                columns.getChildren().add(gridSquares);
            }
            gameGrid.getChildren().add(columns);
        }
        getChildren().add(gameGrid);
        addShipsToArray();
    }

    private boolean isValidPlacement(double xCoordinate, double yCoordinate)
    {
        return xCoordinate >= 0 && xCoordinate < TOTALROWS && yCoordinate >= 0 && yCoordinate < TOTALCOLS;
    }

    public boolean shipPlacement(Ships battleShip, int xCoordinate, int yCoordinate)
    {
        int sizeOfShip;
        int shipOrientation;
        boolean flag = false;
        if (validPlacement(battleShip, xCoordinate, yCoordinate))
        {
            sizeOfShip = battleShip.getLife();

            if (battleShip.getOrientation())
            {
                int limit = yCoordinate + sizeOfShip;
                placeShipVertical(battleShip, xCoordinate, yCoordinate, sizeOfShip, limit);
            }
            else
            {
                int limit = xCoordinate + sizeOfShip;
                placeShipHorizontal(battleShip, xCoordinate, yCoordinate, sizeOfShip, limit);
            }
            flag = true;
            return flag;
        }
        return flag;
    }
}
