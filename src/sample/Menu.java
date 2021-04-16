package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Menu extends javafx.scene.control.MenuBar implements EventHandler<ActionEvent>
{
    public boolean gameStart = false;

    public Menu(BorderPane root) {
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("Battleship");
        CheckMenuItem startItem = new CheckMenuItem("Start Game");
        menu.getItems().add(startItem);

        javafx.scene.control.Menu menu2 = new javafx.scene.control.Menu("Exit");
        MenuItem quitItem = new MenuItem("Quit Game");
        menu2.getItems().add(quitItem);

        startItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (startItem.isSelected())
                {
                    gameStart = true;
                }
                else
                {
                    gameStart = false;
                }
            }
        });

        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        // Help tab
        javafx.scene.control.Menu menu3 = new javafx.scene.control.Menu("Help");
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
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}