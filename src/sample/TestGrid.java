package sample;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
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

    public boolean shipPlacement(Ships battleShip, int xCoordinate, int yCoordinate)
    {
        if (validPlacement(battleShip, xCoordinate, yCoordinate))
        {
            int sizeOfShip = battleShip.getLife();

            if (battleShip.getOrientation())
            {
                for(int i = yCoordinate; i < yCoordinate + sizeOfShip; i++)
                {
                    Square cell = getCell(xCoordinate, i);
                    cell.battleShip = battleShip;
                    if (!computer)
                    {
                        cell.setFill(Color.GRAY);
                        cell.setStroke(Color.WHITE);
                    }
                }
            }
            else
            {
                for(int i = xCoordinate; i < xCoordinate + sizeOfShip; i++)
                {
                    Square cell = getCell(i, yCoordinate);
                    cell.battleShip = battleShip;
                    if (!computer)
                    {
                        cell.setFill(Color.GRAY);
                        cell.setStroke(Color.WHITE);
                    }
                }
            }
            return true;
        }
        return false;
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

    public Square getCell(int x, int y)
    {
        return (Square) ((HBox) gameGrid.getChildren().get(y)).getChildren().get(x);
    }

    private boolean validPlacement(Ships battleShip, int x, int y)
    {
        int length = battleShip.getLife();

        if (battleShip.getOrientation())
        {
            for (int j = y; j < y + length; j++)
            {
                if(!isValidPlacement(x, j))
                    return false;

                Square cell = getCell(x, j);

                if (cell.battleShip != null)
                    return false;

                for (Square neighbor : getNeighbors(x, j))
                {
                    if(!isValidPlacement(x, j))
                        return false;

                    if (cell.battleShip != null)
                        return false;
                }
            }
        }
        else
        {
            for (int j = x; j < x + length; j++)
            {
                if(!isValidPlacement(j, y))
                    return false;

                Square cell = getCell(j, y);

                if (cell.battleShip != null)
                    return false;

                for (Square neighbor : getNeighbors(j, y))
                {
                    if(!isValidPlacement(j, y))
                        return false;

                    if (cell.battleShip != null)
                        return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(Point2D point)
    {
        return isValidPlacement(point.getX(), point.getY());
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

    private boolean isValidPlacement(double x, double y)
    {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    private Square[] getNeighbors(int x, int y)
    {
        Point2D[] points = new Point2D[]
                {
                        new Point2D(x - 1, y),
                        new Point2D(x + 1, y),
                        new Point2D(x, y - 1),
                        new Point2D(x, y + 1)
                };
        List<Square> neighbors = new ArrayList<Square>();

        for (Point2D p : points)
        {
            if (isValidPlacement(p)){
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }
        return neighbors.toArray(new Square[0]);
    }
}
