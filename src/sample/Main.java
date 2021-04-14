package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import sample.TestGrid.Cell;

public class Main extends Application {

    private boolean running = false;
    private TestGrid player1Board, player2Board;
    private int shipsToPlace = 5;
    private boolean playerTurn = false;

    private Parent createContent()
    {
        BorderPane root = new BorderPane();

        root.setPrefSize(800, 600);

        player1Board = new TestGrid(false, event ->{
            if (running)
                return;
            Cell cell = (Cell) event.getSource();
        });

        player2Board = new TestGrid(false, event ->{
            if (running)
                return;
            Cell cell = (Cell) event.getSource();
        });

        HBox hbox = new HBox(50, player1Board, player2Board);
        hbox.setAlignment(Pos.CENTER);
        root.setCenter(hbox);

        return root;
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
