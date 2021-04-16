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
import sample.Square;
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
    private boolean player1Turn = true;
    private boolean player2Turn = false;
    private boolean enemyTurn = false;
    private boolean canPlace = false;

    private Random random = new Random();

    private Parent createContent()
    {
        BorderPane root = new BorderPane();

        root.setPrefSize(900, 550);

        enemyBoard = new TestGrid(true, event ->{
            if (!running)
                return;
            Square cell = (Square) event.getSource();

            if (cell.isBoatHit)
                return;

            enemyTurn = !cell.userAction();

            if (enemyBoard.result == 0){
                Stage newStage = new Stage();
                VBox comp = new VBox();
                Text nameField = new Text("Player 2 WINS!");

                nameField.setFont(Font.font ("Georgia", 40));
                nameField.setTextAlignment(TextAlignment.CENTER);
                nameField.setTextAlignment(TextAlignment.JUSTIFY);
                comp.getChildren().add(nameField);

                Scene stageScene = new Scene(comp, 290, 100);

                newStage.setResizable(false);
                newStage.setScene(stageScene);
                newStage.show();

            }
            if (enemyTurn)
                enemyMove();
        });


        player2Board = new TestGrid(false, event ->{
            if (running)
                return;

            Square cell = (Square) event.getSource();

            if (cell.isBoatHit)
                return;

            player2Turn = !cell.userAction();

            if (player2Board.shipPlacement(new Ships(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.xCoordinate, cell.yCoordinate)) {
                if (--shipsToPlace == 0) {
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

                Text textField = new Text(" Rules:\n" +
                        "\n" +
                        "  The Game is based on the competition of two players against each other. The game\n" +
                        "  takes place with the location of five ships, the size of the ships\n" +
                        "  is between 1 and 5 spaces in the grid. Each player has a turn to shoot on an\n" +
                        "  unknown 10x10 grid. And if it makes a shot, the other player starts losing until\n" +
                        "  every ship is drawn. Based on the classic Battleship rules. Game will continue until\n" +
                        "  all of the player's ships are destroyed. Good luck!\n" + "\n" +
                        " Controls:\n" +
                        "\n" +
                        "  Right Click: Makes the ship vertically oriented and places it.\n" +
                        "  Left-Click: Makes the ship horizontally oriented and places it.\n" +
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

            Square cell = player2Board.getCell(x, y);

            if (cell.isBoatHit)
                continue;

            enemyTurn = cell.userAction();

            if (player2Board.result == 0)
            {
                System.out.println("YOU LOSE!");
                Stage newStage = new Stage();
                VBox comp = new VBox();
                Text nameField = new Text("Player 1 WINS!");
                nameField.setFont(Font.font ("Georgia", 40));
                comp.getChildren().add(nameField);

                Scene stageScene = new Scene(comp, 290, 100);

                newStage.setResizable(false);
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

            if (enemyBoard.shipPlacement(new Ships(type, Math.random() < 0.5), x, y))
            {
                type--;
            }
        }
        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
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
