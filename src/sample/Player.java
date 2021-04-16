package sample;

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

public class Player {
    private int attack;
    private static int boardArray[][] = new int[10][10];
    private int turn;
    private char ship = 'X';
    private char water = '0';
    private static int placeComputerShips;
    private static int numOfShips = 5;
    private static boolean playerAction = false;
    private static Random computerAction = new Random();
    private static TestGrid playerBoard;
    private static TestGrid computerBoard;
    private int shipsToPlace2 = 5;
    private static final int MAXROWS = 10;
    private static final int MAXCOLS = 10;
    private static final int MINSHIPS = 0;
    private boolean player1Turn = true;
    private static boolean playerTurn = false;
    private static boolean computerTurn = false;
    private boolean canPlace = false;
    static BorderPane mainBoard;


    public Player()
    {
    }

    public Player(int turn)
    {
        this.turn = turn;
    }

    public void initializeBoard()
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                boardArray[i][j] = 0;
            }
        }
    }

    public int getTurn()
    {
        return turn;
    }

    public int getDecision()
    {
        return attack;
    }

    public static Parent setDecision()
    {
        mainBoard = new BorderPane();
        sample.Menu menuBar = new sample.Menu(mainBoard);
        menuBar.getMenus();

        mainBoard.setPrefSize(900, 550);

        setComputerAction(menuBar);
        setPlayerAction(menuBar);

        HBox hbox = new HBox(110, computerBoard, playerBoard);
        hbox.setAlignment(Pos.CENTER);
        mainBoard.setCenter(hbox);

        mainBoard.setStyle("-fx-background-image: url('/sample/oceanbg3.jpeg');");

        Text computerText = setComputerText();
        Text vsText = setVsText();
        Text playerText = setPlayerText();

        mainBoard.getChildren().add(computerText);
        mainBoard.getChildren().add(vsText);
        mainBoard.getChildren().add(playerText);

        return mainBoard;
    }

    public static void computerWait(int milliSeconds)
    {
        try
        {
            Thread.sleep(milliSeconds);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static Text setComputerText()
    {
        Text text = new Text("Computer");
        text.setFont(Font.font ("Georgia", 40));
        text.setTranslateX(155);
        text.setTranslateY(95);

        return text;
    }

    public static Text setVsText()
    {
        Text text2 = new Text("Player");
        text2.setFont(Font.font ("Georgia", 40));
        text2.setTranslateX(605);
        text2.setTranslateY(95);

        return text2;
    }

    public static Text setPlayerText()
    {
        Text text3 = new Text("vs.");
        text3.setFont(Font.font ("Georgia", 30));
        text3.setTranslateX(435);
        text3.setTranslateY(95);

        return text3;
    }

    private static void setPlaceComputerShips()
    {
        placeComputerShips = 5;

        while (placeComputerShips > MINSHIPS)
        {
            compShips();
        }
        playerAction = true;
    }

    public static void computerWin()
    {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Text nameField = new Text("Player 1 WINS!");
        nameField.setFont(Font.font ("Georgia", 40));
        comp.getChildren().add(nameField);

        Scene stageScene = new Scene(comp, 290, 100);

        newStage.setResizable(false);
        newStage.setScene(stageScene);
        newStage.show();
    }

    public static void playerWin()
    {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Text nameField = new Text("Player WINS!");

        nameField.setFont(Font.font("Georgia", 40));
        nameField.setTextAlignment(TextAlignment.CENTER);
        nameField.setTextAlignment(TextAlignment.JUSTIFY);
        comp.getChildren().add(nameField);

        Scene stageScene = new Scene(comp, 260, 100);

        newStage.setResizable(false);
        newStage.setScene(stageScene);
        newStage.show();
    }

    public static void setPlayerAction(sample.Menu menuBar)
    {
        playerBoard = new TestGrid(false, event -> {
            if (menuBar.gameStart) {
                if (playerAction)
                    return;

                Square playerSquare = (Square) event.getSource();

                if (playerSquare.isBoatHit)
                    return;

                playerTurn = !playerSquare.userAction();

                if (playerBoard.shipPlacement(new Ships(numOfShips,
                                event.getButton() == MouseButton.PRIMARY),
                                            playerSquare.xCoordinate,
                                            playerSquare.yCoordinate))
                {
                    if (--numOfShips == 0)
                    {
                        setPlaceComputerShips();
                    }
                }
            }
        });
    }

    private static void computerAttack()
    {
        computerWait(200);
        while (computerTurn)
        {
            Square totalSquare = getSquare();

            if (totalSquare.isBoatHit)
                continue;

            computerTurn = totalSquare.userAction();

            if (playerBoard.result == 0)
            {
                computerWin();
            }
        }
    }

    public static Square getSquare()
    {
        int xCoord = computerAction.nextInt(MAXROWS);
        int yCoord = computerAction.nextInt(MAXCOLS);

        Square totalSquares = playerBoard.getSquarePosition(xCoord, yCoord);

        return totalSquares;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public static void compShips()
    {
        int xCoord = computerAction.nextInt(MAXROWS);
        int yCoord = computerAction.nextInt(MAXCOLS);

        if (computerBoard.shipPlacement(new Ships(placeComputerShips, Math.random() < 0.5), xCoord, yCoord))
        {
            placeComputerShips--;
        }
    }

    public boolean hitOrMiss(int attack)
    {
        if (attack == 1)
        {
            return true;
        }
        return false;
    }

    public static void setComputerAction(sample.Menu menuBar)
    {
        computerBoard = new TestGrid(true, event -> {
            if (menuBar.gameStart) {
                if (!playerAction)
                    return;

                Square enemySquare = (Square) event.getSource();

                if (enemySquare.isBoatHit)
                    return;

                computerTurn = !enemySquare.userAction();

                if (computerBoard.result == 0) {
                    playerWin();
                }
                if (computerTurn)
                    computerAttack();
            }
        });
    }
}
