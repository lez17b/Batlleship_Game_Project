package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import sample.TestGrid.Cell;
import javafx.scene.text.Font;
import sample.Menu;

import java.awt.*;
import java.util.Random;

public class Main extends Application {

    private boolean running = false;
    private TestGrid player1Board, player2Board, enemyBoard;
    private Menu menu;
    private int shipsToPlace = 5;
    private boolean playerTurn = false;
    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent()
    {
        BorderPane root = new BorderPane();

        root.setPrefSize(900, 550);

        enemyBoard = new TestGrid(true, event ->{
            if (!running)
                return;
            Cell cell = (Cell) event.getSource();

            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.result == 0){
                System.out.println("YOU WIN!");
                System.exit(0);
            }
            if (enemyTurn)
                enemyMove();

        });

        player2Board = new TestGrid(false, event ->{
            if (running)
                return;
            Cell cell = (Cell) event.getSource();

            if (player2Board.shipPlacement(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y))
            {
                if (--shipsToPlace == 0)
                {
                    startGame();
                }
            }
        });

        HBox hbox = new HBox(110, enemyBoard, player2Board);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);
        root.setStyle("-fx-background-image: url('/sample/oceanbg3.jpeg');");

        Text text = new Text("Player 1");
        text.setFont(Font.font ("Georgia", 40));
        text.setTranslateX(165);
        text.setTranslateY(95);

        Text text2 = new Text("Player 2");
        text2.setFont(Font.font ("Georgia", 40));
        text2.setTranslateX(585);
        text2.setTranslateY(95);

        root.getChildren().add(text);
        root.getChildren().add(text2);

        return root;
    }

    private void enemyMove()
    {
        while (enemyTurn)
        {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = player2Board.getCell(x, y);

            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (player2Board.result == 0)
            {
                System.out.println("YOU LOSE!");
                System.exit(0);
            }
        }
    }

    private void startGame()
    {
        int type = 5;

        while (type > 0)
        {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.shipPlacement(new Ship(type, Math.random() < 0.5), x, y))
            {
                type--;
            }
        }
        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //TestGrid testGrid = new TestGrid();
        primaryStage.setTitle("Battleship");
        Scene scene = new Scene(createContent());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
