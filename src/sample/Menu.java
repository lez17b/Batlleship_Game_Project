/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Menu extends javafx.scene.control.MenuBar implements EventHandler<ActionEvent>
{

    // game status bools
    public boolean gameStart = false;
    public boolean gameRestart = false;

    // Menu class constructor
    public Menu(BorderPane root) {
    
        // menu creation
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("Battleship");
        
        // *** Start Game tab ***
        CheckMenuItem startItem = new CheckMenuItem("Start Game");
        startItem.setText("Start Game");
        menu.getItems().add(startItem);

        // Icon asignation to the tab
        Image startIcon = new Image(getClass().getResourceAsStream("play-512.png"));
        ImageView startView = new ImageView(startIcon);
        startView.setFitWidth(30);
        startView.setFitHeight(30);

        startItem.setGraphic(startView);

        // Action hanlder for the tab
        startItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startItem.isSelected())
                {
                    // sets the staus to true
                    gameStart = true;
                    
                    // Creates a Info box
                    Stage stage = new Stage();
                    VBox textVbox = new VBox();
                    Text textx = new Text("\n" +
                            "                                                 Welcome to the Battleship Game\n"
                            + " Once you have chose start game you will be able to place your ships, and once\n "
                            + " they are placed, you will be able to shoot; the same logic will take place for the CPU.\n"
                            + " If you want to pause the game, you can just click the start game button and uncheck it. \n"
                            + "                                                              Enjoy the game!   \n"
                            + "                                                     (Close this window to start)");
                    textx.setFont(Font.font ("Georgia", 20));
                    textx.setTextAlignment(TextAlignment.CENTER);
                    textx.setTextAlignment(TextAlignment.JUSTIFY);
                    textVbox.getChildren().add(textx);

                    Scene stageS = new Scene(textVbox, 775, 200);

                    stage.setResizable(false);

                    stage.setScene(stageS);
                    stage.show();
                }
                else
                    gameStart = false;
            }
        });
        

        // *** Exit Game tab ***
        javafx.scene.control.Menu menu2 = new javafx.scene.control.Menu("Exit");
        MenuItem quitItem = new MenuItem("Quit Game");
        menu2.getItems().add(quitItem);

        // Icon assignation code for the tab
        Image quitIcon = new Image(getClass().getResourceAsStream("stop-512.png"));
        ImageView quitView = new ImageView(quitIcon);
        quitView.setFitWidth(30);
        quitView.setFitHeight(30);

        quitItem.setGraphic(quitView);

        // Action handler that quits the game and window
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

        Image viewIcon = new Image(getClass().getResourceAsStream("icon.png"));
        ImageView viewView = new ImageView(viewIcon);
        viewView.setFitWidth(30);
        viewView.setFitHeight(30);

        helpItem.setGraphic(viewView);

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

