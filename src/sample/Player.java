package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Player {
    private int attack;
    private static int boardArray[][] = new int[10][10];
    private int turn;
    private char ship = 'X';
    private char water = '0';
    private int numberOfShips = 5;

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

    public void setDecision(int attack)
    {
        int attackRow = 0;
        int attackCol = 0;

        if (attack > 0 && attack <= 10)
        {
            attackCol = attack;
            attackRow = 0;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 10 && attack <= 20)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 1;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 20 && attack <= 30)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 2;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 30 && attack <= 40)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 3;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 40 && attack <= 50)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 4;
        }
        else if (attack > 50 && attack <= 60)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 5;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 60 && attack <= 70)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 6;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 70 && attack <= 80)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 7;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 80 && attack <= 90)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 8;
            boardArray[attackRow][attackCol] = 1;
        }
        else if (attack > 90 && attack <= 100)
        {
            attack = attack % 10;
            attackCol = attack;
            attackRow = 9;
            boardArray[attackRow][attackCol] = 1;
        }
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public boolean hitOrMiss(int attack)
    {
        if (attack == 1)
        {
            return true;
        }
        return false;
    }

    EventHandler<ActionEvent> attackHandler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            setDecision(attack);
        }
    };


}
