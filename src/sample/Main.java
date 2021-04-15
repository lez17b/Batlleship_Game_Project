package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
//import sample.Menu;

//import java.awt.Menu;
import java.util.Random;

public class Main extends Application {

    private boolean running = false;
    private TestGrid player1Board, player2Board, enemyBoard;
    private Menu menu;
    private int shipsToPlace = 5;
    private int shipsToPlace2 = 5;
    private boolean player1Turn = false;
    private boolean player2Turn = false;
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
                Stage newStage = new Stage();
                VBox comp = new VBox();
                Text nameField = new Text("Player 2 WINS!");

                nameField.setFont(Font.font ("Georgia", 40));
                nameField.setTextAlignment(TextAlignment.CENTER);
                nameField.setTextAlignment(TextAlignment.JUSTIFY);
                comp.getChildren().add(nameField);

                Scene stageScene = new Scene(comp, 100, 100);
                newStage.setScene(stageScene);
                newStage.show();

                System.out.println("YOU WIN!");
                //System.exit(0);
            }
            if (enemyTurn)
                enemyMove();
        });

        player1Board = new TestGrid(false, event ->{
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();

            if (cell.wasShot)
                return;

            player1Turn = !cell.shoot();

            if (player1Board.shipPlacement(new Ship(shipsToPlace2, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y))
            {
                if (--shipsToPlace2 == 0)
                {
                    startGame();
                }
            }
        });

        player2Board = new TestGrid(false, event ->{
            if (running)
                return;

            Cell cell = (Cell) event.getSource();

            if (cell.wasShot)
                return;

            player2Turn = !cell.shoot();

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

        Menu menu = new Menu("Start Game");
        Menu menu2 = new Menu("Quit Game");
        Menu menu3 = new Menu("Help");

        MenuItem helpItem = new MenuItem("View Controls");

        menu3.getItems().add(helpItem);

        helpItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Stage stage = new Stage();
                VBox comp2 = new VBox();
                stage.setTitle("Help/Rules");

                Text textField = new Text("Rules:\n" +
                        "\n" +
                        "  The Game is based on the competition of two players against each other. The game\n" +
                        "  takes place with the location of five ships, each player and the size of the ships\n" +
                        "  is between 1 and 5 spaces in the grid. Each player has a turn to shoot on an\n" +
                        "  unknown 10x10 grid. And if it makes a shot the other player starts loosing until\n" +
                        "  every ship is drawn.\n" +
                        "\n" +
                        "Controls:\n" +
                        "\n" +
                        "  Right Click: Makes the ship vertically oriented and places it.\n" +
                        "  Left-Click: Makes the ship Horizontally oriented and places it.\n" +
                        "\n" +
                        "  Start game option in the menu to start the game.");
                textField.setFont(Font.font ("Georgia", 20));
                textField.setTextAlignment(TextAlignment.CENTER);
                textField.setTextAlignment(TextAlignment.JUSTIFY);

                comp2.getChildren().add(textField);

                Scene stageScene = new Scene(comp2, 750, 400);

                stage.setResizable(false);

                stage.setScene(stageScene);
                stage.show();
            }
        });
        //MenuItem menuItem1 = new MenuItem("Item 1");
        //MenuItem menuItem2 = new MenuItem("Item 2");

        //menu.getItems().add(menuItem1);
        //menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu, menu2, menu3);
        root.setTop(menuBar);

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
                Stage newStage = new Stage();
                VBox comp = new VBox();
                Text nameField = new Text("Player 1 WINS!");
                nameField.setFont(Font.font ("Georgia", 40));
                comp.getChildren().add(nameField);

                Scene stageScene = new Scene(comp, 100, 100);
                newStage.setScene(stageScene);
                newStage.show();

                //System.exit(0);
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


        Menu menu = new Menu("Menu 1");
        MenuItem menuItem1 = new MenuItem("Item 1");
        MenuItem menuItem2 = new MenuItem("Item 2");

        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);


        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
