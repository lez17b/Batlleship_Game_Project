package sample;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;


public class TestGrid extends Parent {
    private BorderPane grid;
    private VBox rows = new VBox();
    private boolean enemy = false;
    public int result = 5;

    public TestGrid(boolean enemy, EventHandler<? super MouseEvent> handler)
    {
        this.enemy = enemy;
        int index = 0;
        int counter;

        for (; index < 10; index++)
        {
            HBox row = new HBox();
            for (counter = 0; counter < 10; counter++)
            {
                Cell c = new Cell(counter, index, this);
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);

    }

    private void createGrid()
    {
        GridPane playerGrid = new GridPane();
        for (int i = 0; i < 10; i++)
        {
            playerGrid.getColumnConstraints().add(new ColumnConstraints(50));
            playerGrid.getRowConstraints().add(new RowConstraints(50));
        }
    }

    public Pane getRoot()
    {
        return grid;
    }

    public boolean shipPlacement(Ship ship, int xCoordinate, int yCoordinate)
    {
        if (validPlacement(ship, xCoordinate, yCoordinate))
        {
            int sizeOfShip = ship.life;

            if (ship.Vertical)
            {
                for(int i = yCoordinate; i < yCoordinate + sizeOfShip; i++)
                {
                    Cell cell = getCell(xCoordinate, i);
                    cell.ship = ship;
                    if (!enemy)
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
                    Cell cell = getCell(i, yCoordinate);
                    cell.ship = ship;
                    if (!enemy)
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

    public Cell getCell(int x, int y)
    {
        return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
    }

    private boolean validPlacement(Ship ship, int x, int y)
    {
        int length = ship.life;

        if (ship.Vertical)
        {
            for (int j = y; j < y + length; j++)
            {
                if(!isValidPlacement(x, j))
                    return false;

                Cell cell = getCell(x, j);

                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(x, j))
                {
                    if(!isValidPlacement(x, j))
                        return false;

                    if (cell.ship != null)
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

                Cell cell = getCell(j, y);

                if (cell.ship != null)
                    return false;

                for (Cell neighbor : getNeighbors(j, y))
                {
                    if(!isValidPlacement(j, y))
                        return false;

                    if (cell.ship != null)
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

    private boolean isValidPlacement(double x, double y)
    {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    private Cell[] getNeighbors(int x, int y)
    {
        Point2D[] points = new Point2D[]
                {
                        new Point2D(x - 1, y),
                        new Point2D(x + 1, y),
                        new Point2D(x, y - 1),
                        new Point2D(x, y + 1)
                };
        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points)
        {
            if (isValidPlacement(p)){
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }
        return neighbors.toArray(new Cell[0]);
    }

    public class Cell extends Rectangle
    {
        public int x;
        public int y;
        public Ship ship = null;
        public boolean wasShot = false;

        private TestGrid board;

        public Cell(int x, int y, TestGrid board)
        {
            super(30,30);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.WHITE);
            setStroke(Color.GRAY);
        }

        public boolean shoot()
        {
            wasShot = true;
            setFill(Color.BLACK);

            if (ship != null)
            {
                ship.hit();
                setFill(Color.RED);
                if (!ship.isAlive())
                {
                    board.result--;
                }
                return true;
            }
            return false;
        }
    }
}
