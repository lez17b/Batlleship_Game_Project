# Battleship Game Project
A Classic Battleship Game designed and deployed in Java using the JavaFx Libraries. 

## Requirements
- The code is wrapped in a Jar file which contains the **JavaFX** library, nevertheless it's preferable to download it from the following website: https://openjfx.io
- The code must be compiled using a **Java16** compiler.
- To run the code you can use ``` java -jar JavaFx.jar ```

### Game Rules

The Game is based on the competition of two players against each other. The game takes place with the location of five ships, the size of the ships is between 1 and 5 spaces in the grid. Each player has a turn to shoot on an unknown 10x10 grid. And if it makes a shot, the other player starts losing until every ship is drawn. Based on the classic Battleship rules. Game will continue until all of the player's ships are destroyed. Good luck!

* ***You can check more info regarding the rules and insights on:*** https://www.thesprucecrafts.com/the-basic-rules-of-battleship-411069

### Game Start

For the game to start you have to look for the menu bar located on the top of the window and select the *Start Game* option which would cause a window to pop-up, once you close that window after reading the instructions, you will be able to locate your ships. After you place your five ships, you can proceed to attack the computer!

### Game Controls

- Right Click: Makes the ship vertically oriented and places it. After placing the ships it is used to shoot in the opponent's board.
- Left-Click: Makes the ship horizontally oriented and places it.

### More Details

The interface is based on a **Menu bar** that appears in the top part of the window. On it, you will find 3 options:
- Start Game, that we mentioned previously.
- Quit, that contains the exit game option (this also closes the window).
- Help, which contains the rules and controls for the game.

## Code Distribution

The code was divided in half for each person (Luciano Zavala and Marco Jaen).

- Luciano Zavala: Ship.java, Carrier.java, Submarine.java, Destroyer.java, Battleship.java, Cruiser.java, Menu.java
- Marco Jaen: Player.java, Square.java, gameGrid.java, manifest.mf
- Together: Main.java, Launcher.java

## References

- https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
- https://docs.oracle.com/javase/8/javafx/api/javafx/event/Event.html
- http://tutorials.jenkov.com/javafx/menubar.html
- https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html
- https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html
- https://openjfx.io/openjfx-docs/
