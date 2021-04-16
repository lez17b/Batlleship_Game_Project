package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
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
import javafx.stage.WindowEvent;
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

        sample.Menu Menubar = new sample.Menu(root);
        Menubar.getMenus();



        enemyBoard = new TestGrid(true, event ->{
            if(Menubar.gameStart) {
                if (!running)
                    return;
                Square cell = (Square) event.getSource();

                if (cell.isBoatHit)
                    return;

                enemyTurn = !cell.userAction();

                if (enemyBoard.result == 0) {
                    Stage newStage = new Stage();
                    VBox comp = new VBox();
                    Text nameField = new Text("Player 2 WINS!");

                    nameField.setFont(Font.font("Georgia", 40));
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
            }

        });


        player2Board = new TestGrid(false, event ->{
            if(Menubar.gameStart) {
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
            }



        });

        HBox hbox = new HBox(110, enemyBoard, player2Board);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);
        if(Menubar.gameRestart)
        {
            HBox hbox2 = new HBox(110, enemyBoard, player2Board);
            hbox2.setAlignment(Pos.CENTER);
            root.setCenter(hbox2);
        }



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
