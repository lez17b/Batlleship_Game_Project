/*  Names: Marco Jaen & Luciano Zavala
    Instructor: Dr. Katie Brodhead
    Class: COP3252 - Advanced Programming with Java
    Final Project: Battleship Game using JavaFX
    Date: 16 Apr. 2021
*/

// This is the package where we put all of our Source Code files
package sample;

// These are the libraries that we import for the Player Class
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

// This is the player class, that will control the basic controls of the game, from each player. It will
// create a player for the human player, as well as for the computer player
public class Player {
    // Attack variable that we create of type integer
    private int attack;
    private static int boardArray[][] = new int[10][10];            // BoardArray that we use, 10 by 10
    private int turn;                                               // Integer that keeps track of the turn of each player
    private char ship = 'X';                                        // If it is a ship, then it is an 'X'
    private char water = '0';                                       // If it is water, then it is an '0'

    // We create the placeComputerShips, and numOfShips static integers to keep track of the amount of ships that we
    // need to place
    private static int placeComputerShips;
    private static int numOfShips = 5;

    // We set the action of the player action to false
    private static boolean playerAction = false;
    // We create a random variable using the Random object to keep track of the computer's action
    private static Random computerAction = new Random();

    // We create 2 instances of GameGrid, for the computer, as well as for the player board
    private static GameGrid playerBoard;
    private static GameGrid computerBoard;
    private int shipsToPlace2 = 5;              // Num of Ships that we place

    // Static integers that contains the size of the grid, as well as the minimum ships available
    private static final int MAXROWS = 10;
    private static final int MAXCOLS = 10;
    private static final int MINSHIPS = 0;

    // Boolean that we use to keep track of the player's, and computer's turns
    private boolean player1Turn = true;
    private static boolean playerTurn = false;
    private static boolean computerTurn = false;

    // Boolean to see if we can place the ship
    private boolean canPlace = false;

    // Create a BorderPane for the mainBoard
    static BorderPane mainBoard;

    // Constructor without parameters
    public Player()
    {
    }

    // Constructor that we pass in the turn of each player
    public Player(int turn)
    {
        this.turn = turn;
    }

    // Public void class to initialize the board that we created above
    public void initializeBoard()
    {
        // Iterate over the rows
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)            // Iterate over the columns
            {
                boardArray[i][j] = 0;               // Set each cell to 0
            }
        }
    }

    // getTurn method that will return the turn of each player
    public int getTurn()
    {
        return turn;
    }

    // getDecision method that will return the attack of the player
    public int getDecision()
    {
        return attack;
    }

    // setDecision method that will create the border pane, and add the menu in order to display it to the screen
    public static Parent setDecision()
    {
        // We assign the main bord to a new BorderPane
        mainBoard = new BorderPane();

        // We create an instance of the menu for our main board
        sample.Menu menuBar = new sample.Menu(mainBoard);

        // We create a menubar for all of the menu options
        menuBar.getMenus();

        // We set the size of the board to 900 by 550, user will not be able to resize it
        mainBoard.setPrefSize(900, 550);

        // We call the setComputerAction, passing as parameter the menubar that we create above
        setComputerAction(menuBar);

        // We call the setPlayerAction, passing as parameter the menubar that we create above
        setPlayerAction(menuBar);

        HBox hbox = new HBox(110, computerBoard, playerBoard);          // We create an hbox to place both the boards

        // We set these to the center, and position it to the main board
        hbox.setAlignment(Pos.CENTER);
        mainBoard.setCenter(hbox);

        // We call the background image to our game, which will be an ocean image
        mainBoard.setStyle("-fx-background-image: url('/sample/oceanbg3.jpeg');");

        // We create a computerText, and call the setComputerText method
        Text computerText = setComputerText();

        // We create a vsText, and call the setVsText method
        Text vsText = setVsText();

        // We create a playerText, and call the setPlayerText method
        Text playerText = setPlayerText();

        // We add all of the texts to the main board in order to display them
        mainBoard.getChildren().add(computerText);
        mainBoard.getChildren().add(vsText);
        mainBoard.getChildren().add(playerText);

        // We return the main board to display it
        return mainBoard;
    }

    // the computerWait method will take in as parameter the seconds that we want to wait for the action of the computer
    public static void computerWait(int milliSeconds)
    {
        // We use this try catch block to catch an exception in the case that the waiting time is interrupted
        try
        {
            Thread.sleep(milliSeconds);             // We sleep the thread to the parameter passed in
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();         // We catch the exception
        }
    }

    // This is the setComputerText method of type Text that will return the Computer text for the game
    public static Text setComputerText()
    {
        // We create the text, set to Computer
        Text text = new Text("Computer");
        // We set the font of the text
        text.setFont(Font.font ("Georgia", 40));

        // We decide where we want to place them
        text.setTranslateX(155);
        text.setTranslateY(95);

        return text;                    // We return the text
    }

    // This is the setVsText method of type Text that will return the VS. text for the game
    public static Text setVsText()
    {
        // We create the text
        Text text2 = new Text("Player");
        // We add the font
        text2.setFont(Font.font ("Georgia", 40));

        // We decide where we want to put it
        text2.setTranslateX(605);
        text2.setTranslateY(95);

        return text2;               // We return the text
    }

    // This is the setPlayerText method of type Text that will return the Player text for the game
    public static Text setPlayerText()
    {
        // We create the text
        Text text3 = new Text("vs.");

        // We create the font, set to size 30
        text3.setFont(Font.font ("Georgia", 30));

        // We decide where we want to put the text at
        text3.setTranslateX(435);
        text3.setTranslateY(95);

        return text3;               // We return the text
    }

    // This is the setPlaceComputerShips method that will set the variable that we created above to 5, and use
    // a while loop to set each of the computers, ensuring that we do not go over bounds of the grid
    private static void setPlaceComputerShips()
    {
        // Set the variable to 5
        placeComputerShips = 5;

        // Use the while loop to place the 5 ships to the array/board of the computer
        while (placeComputerShips > MINSHIPS)
        {
            compShips();                // We call the compShips method
        }
        playerAction = true;            // We set the boolean to true
    }

    // This is the computerWin method that we use in order to display which player wins, in this case, the computer
    public static void computerWin()
    {
        // We create a new stage, set to a new stage
        Stage newStage = new Stage();

        // We create a new vbox to store these values
        VBox comp = new VBox();
        Text nameField = new Text("Computer WINS!");
        // Add the text and font, and add the field to the vbox
        nameField.setFont(Font.font ("Georgia", 40));
        comp.getChildren().add(nameField);

        // We set the size of the scene to 290 by 100
        Scene stageScene = new Scene(comp, 290, 100);

        // The user will not be able to resize it, and we add it to the scene
        newStage.setResizable(false);
        newStage.setScene(stageScene);

        // We display the scene
        newStage.show();
    }

    // This is the playerWin method that we use to display to the user which player won, by creating a new stage,
    // as well as the text
    public static void playerWin()
    {
        // We create a new stage
        Stage newStage = new Stage();

        // We create a new vbox
        VBox comp = new VBox();

        // We create a new text
        Text nameField = new Text("Player WINS!");

        // We set the font of the text to Georgia, and the size to 40
        nameField.setFont(Font.font("Georgia", 40));

        // We align the text in the center
        nameField.setTextAlignment(TextAlignment.CENTER);
        nameField.setTextAlignment(TextAlignment.JUSTIFY);

        // We add this to the vbox
        comp.getChildren().add(nameField);

        // We set the size of the stage scene
        Scene stageScene = new Scene(comp, 260, 100);

        // User will not be able to resize the screen, and we add it to the stage
        newStage.setResizable(false);
        newStage.setScene(stageScene);

        // We display it by calling show()
        newStage.show();
    }

    // This is the setPlayerAction method that we pass in as parameter the menubar that we created above,
    // and is the method that will take in the action of the user
    public static void setPlayerAction(sample.Menu menuBar)
    {
        // We create a new GameGrid for the player board, setting the boolean to false, and then the event
        // to whatever the user's events are
        playerBoard = new GameGrid(false, event -> {
            if (menuBar.gameStart) {                // This checks that the game is available to start
                // We check the players action using the variable from above
                if (playerAction)
                    return;

                // We create a square for the player and get this source from the event
                Square playerSquare = (Square) event.getSource();

                // We check to ensure that the square has been hit, else we return
                if (playerSquare.isBoatHit)
                    return;

                // We set the boolean that we created above to the user's action, by calling the
                // userAction method from the Square class
                playerTurn = !playerSquare.userAction();

                // This if statement takes as parameter the number of ships, as well as the coordinates of where
                // the user wants to place the ships at
                if (playerBoard.shipPlacement(new Ships(numOfShips,
                                event.getButton() == MouseButton.PRIMARY),
                                            playerSquare.xCoordinate,
                                            playerSquare.yCoordinate))
                {
                    if (--numOfShips == 0)          // We set decrement the number of ships
                    {
                        // We call the setPlaceComputerShips method
                        setPlaceComputerShips();
                    }
                }
            }
        });
    }

    // This is the computerAttack method that we use to call the waiting time for the computer, with
    // 180 milliseconds
    private static void computerAttack()
    {
        // We pass in the 180 milliseconds we want the computer to wait for
        computerWait(180);

        // We use this while loop to iterate until the computer's turn is valid
        while (computerTurn)
        {
            // We create a square for the computer, and call the getSquare method
            Square totalSquare = getSquare();

            // If the square of the computer is hit, then we get another turn
            if (totalSquare.isBoatHit)
                continue;

            // We set the boolean computerTurn to whatever the action was, by calling the userAction method
            computerTurn = totalSquare.userAction();

            // If the result is equal to zero, then we proceed with the statement
            if (playerBoard.result == 0)
            {
                // We call the computerWin method
                computerWin();
            }
        }
    }

    // This is the getSquare method of type square that we use to get the computer's action
    public static Square getSquare()
    {
        // We create an integer of the x coordinate for the computer
        int xCoord = computerAction.nextInt(MAXROWS);

        // We create an integer of the y coordinate for the computer
        int yCoord = computerAction.nextInt(MAXCOLS);

        // We create an intance of a square in order to get the position using the variables above and
        // then returning it
        Square totalSquares = playerBoard.getSquarePosition(xCoord, yCoord);

        // We return the square
        return totalSquares;
    }

    // This is the setAttack method that will set the parameter attack to the variable we created above
    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    // This is the compShips method that is similar to the getSquare, but this time we place the computer's ships onto
    // the grid
    public static void compShips()
    {
        // We create an integer of the x coordinate for the computer's ships
        int xCoord = computerAction.nextInt(MAXROWS);

        // We create an integer of the y coordinate for the computer's ships
        int yCoord = computerAction.nextInt(MAXCOLS);

        // We use this if statement to place the ships of the computer, as well as using the y and x coordinates
        // we created above
        if (computerBoard.shipPlacement(new Ships(placeComputerShips, Math.random() < 0.5), xCoord, yCoord))
        {
            // We decrement the total amount of computer ships
            placeComputerShips--;
        }
    }

    // This is the hitOrMiss method that takes in as parameter the attack and returns true or false
    // depending on it
    public boolean hitOrMiss(int attack)
    {
        // If the attack is equal to 1, we return true
        if (attack == 1)
        {
            return true;
        }
        return false;                   // Else, we return false
    }

    // This is the setComputerAction method that takes in as parameter the menu bar that we created
    // in the border pane in order to keep the functionality going
    public static void setComputerAction(sample.Menu menuBar)
    {
        // We create an instance of the GameGrid from the class that we created, setting the boolean
        // to true, and the event of what the computer wants to do
        computerBoard = new GameGrid(true, event -> {
            // We check that the game is starter
            if (menuBar.gameStart) {
                if (!playerAction)          // If is it not true, then we return
                    return;

                // We call the enemySquare instance and call getSource to place the action/event
                Square enemySquare = (Square) event.getSource();

                // We check to see if the square is hit
                if (enemySquare.isBoatHit)
                    return;                 // We return if so

                // We set the computerTurn boolean to the userAction method that we call
                computerTurn = !enemySquare.userAction();

                // We see if the result is equal to 0, if so, we proceed with the statement
                if (computerBoard.result == 0) {
                    // We call the playerWin method
                    playerWin();
                }
                if (computerTurn)       // We check to see if it is still the computer's turn
                    computerAttack();       // We call the computerAttack method created above
            }
        });
    }
}
