/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Main Class
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main class that extends the application for our JavaFX project
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Battleship");                // We set the title of the stage to Battleship
        Player player = new Player();                       // We create an insance of the player
        Scene scene = new Scene(Player.setDecision());      // We create a scene using the player class

        // User will not be able to resize the window
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);                   // We place the scene to the stage
        primaryStage.show();                            // And we diplay it by calling show
    }

    // Main method that simply launches the application for the BattleShip project
    public static void main(String[] args) {
        launch(args);
    }

}
