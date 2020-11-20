/* SELF ASSESSMENT

Connect4Game class (35/35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win.
Comment: grid created, and 2 players - 2 human or 1 human & 1 AI. Methods are called on each play until grid is full or a winner is found.

Connect4Grid interface (10/10 marks)
I define all 7 methods within this interface.
Comment: all 7 methods are defined

Connect4Grid2DArray class (25/25 marks)
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: class implements the interface, creates a 2D array for the grid. Methods for valid column and is column full. Method for dropping the piece. Method for checking if last piece caused a win.

ConnectPlayer abstract class (10/10 marks)
My class provides at lest one non-abstract method and at least one abstract method.
Comment: both abstract and non-abstracts methods in the class.

C4HumanPlayer class (10/10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides the Human player functionality.
Comment: this class extends the connect player class and overrides abstract methods, it allows a human to choose the next column.

C4RandomAIPlayer class (10/10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides AI player functionality.
Comment: extends the connectplayer class and overrides the abstract methods. It uses a random number generator to choose the next column.

Total Marks out of 100:100

*/
package Week10;

import java.util.Scanner;

public class Connect4Game {
    public static void main(String[] args) {
        Connect4Grid2DArray grid = new Connect4Grid2DArray();
        grid.emptyGrid();
        boolean gameTypeSelected = false;
        String playerSelection = null;
        while (!gameTypeSelected) {
            System.out.println("Human ('h') or Computer ('c') player?");
            Scanner inputScanner = new Scanner(System.in);
            if (inputScanner.hasNext("[hcHC]")) {
                playerSelection = inputScanner.nextLine();
                gameTypeSelected = true;
            } else {
                System.out.println("Error: invalid input, only enter the letter 'h' or 'c'!");
            }
        }
        ConnectPlayer player1 = new C4HumanPlayer();
        if (playerSelection.equals("h")) {
            ConnectPlayer player2 = new C4HumanPlayer();
        } else if (playerSelection.equals("c")) {
            ConnectPlayer player2 = new C4RandomAIPlayer();
        }

        boolean finished = false;
        ConnectPlayer currentPlayer;
        int count = 0;
        while (!finished && !grid.isGridFull()) {
            System.out.println(grid.toString());
            if (count % 2 == 0) {
                currentPlayer = grid.players.get(0);
            } else {
                currentPlayer = grid.players.get(1);
            }

            int column = currentPlayer.chooseColumn();
            if (grid.isColumnFull(column)) {
                System.out.println("Error: column full.");
            } else {
                grid.dropPiece(currentPlayer, column);
                count++;
            }
            if (grid.didLastPieceConnect4()) {
                finished = true;
                System.out.println(grid.toString());
                System.out.println("GAME OVER - " + currentPlayer.getColour() + " wins.");
            }
        }
        if (grid.isGridFull()) {
            System.out.println("GAME OVER - Grid full.");
        }
    }
}
