package sample;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;


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
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
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
