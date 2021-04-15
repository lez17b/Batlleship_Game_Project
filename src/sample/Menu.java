package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
public class Menu implements EventHandler<ActionEvent>
{
    private Button start;
    private Button exit;
    private Label name;
    private Label title;
    private Scene Scene1;


    public Menu(Stage s, Font f) {
        s.centerOnScreen();

        f = new Font(f.getName(), 50);

        start = new Button();
        start.setText("Start");
        start.setOnAction(this);
        start.setFont(f);

        exit = new Button();
        exit.setText("Exit");
        exit.setOnAction(e -> s.close());
        exit.setFont(f);

        title = new Label();
        title.setText("Battleship");
        title.setFont(f);

        StackPane panes = new StackPane();
        VBox buttons = new VBox(20);
        BorderPane border = new BorderPane();

        border.setStyle("-fx-background-color: lightblue;");
        border.setBottom(name);

        buttons.getChildren().addAll(title, start, exit, name);
        buttons.setAlignment(Pos.CENTER);


    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
*/