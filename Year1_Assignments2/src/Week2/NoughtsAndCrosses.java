/* SELF ASSESSMENT
   1. clearBoard:
Did I use the correct method definition?
Mark out of 5: 5
Comment: void clearBoard(char[][] board) - void, parameter: board 2D array
Did I use loops to set each position to the BLANK character?
Mark out of 5: 5
Comment: nested for loops - sets each position to ' '.

   2. printBoard
Did I use the correct method definition?
Mark out of 5: 5
Comment: void printBoard(char[][] board) - void, parameter: board 2D array
Did I loop through the array and prints out the board in a way that it looked like a board?
Mark out of 5: 5
Comment: Yes, grid references and lines are printed correctly, it looks like a noughts and crosses board

   3. canMakeMove
Did I have the correct function definition and returned the correct item?
Mark out of 5: 5
Comment: Parameters: board 2D array, row and column number. returns a boolean type
Did I check if a specified location was BLANK?
Mark out of 5: 5
Comment: Yes, returns true if the space is clear (ie: == ' ') and false if the space is occupied already (== 'X' || 'O')

   4. makeMove
Did I have the correct function definition?
Mark out of 5: 5
Comment: void, parameters: board 2D array, row, column and current player piece (X or O)
Did I set the  currentPlayerPiece in the specified location?
Mark out of 5: 5
Comment: Yes, if canMakeMove == true, the player piece is put in the correct space on the board (using the user-supplied
        grid reference)

   5. isBoardFull
Did I have the correct function definition and returned the correct item?
Mark out of 5: 5
Comment: returns boolean, parameter board 2D array.
Did I loop through the board to check if there are any BLANK characters?
Mark out of 5: 5
Comment: nested for loops go through the board and count occupied spaces, if count is nine, the board must be full and
        true is returned.

   6. winner
Did I have the correct function definition and returned the winning character
Mark out of 5: 5
Comment: returns char, parameter: board 2D array, the winning character (X or O) is returned, if no winner ' ' is returned.
Did I identify all possible horizontal, vertical and diagonal winners
Mark out of 15: 15
Comment: winner() checks horizontal and vertical winners, if none are found, the diagonals are also checked. Both player
        pieces are checked.

   7.main
Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
Mark out of 3: 3
Comments: In the main method a 2D char array is declared of size 3x3, then clearBoard() is called to set all spaces to blank in.
Did I loop asking the user for a location until wither the board was full or there was a winner?
Mark out of 5: 5
Comments: Yes, the while loop continues until isBoardFull() is true or winner() returns a player piece (X or O)
Did I call all of the methods above?
Mark out of 5: 5
Comments: All methods are called and used correctly in the main method
Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
Mark out of 3: 3
Comments: Yes, the canMakeMove() method ensures the move can be made and returns an error if not. An error will also be
        returned if the user input is formatted incorrectly or the location entered does not exist.
Did I switch the current player piece from cross to nought and vice versa after every valid move?
Mark out of 3: 3
Comments: Yes, a count is used and after every move it is incremented. The player piece changes depending on if count is
        odd or even.
Did I display the winning player piece or a draw at the end of the game?
Mark out of 3: 3
Comments: Yes, the winning player piece is displayed at the end of the game, if the board is full and there is no winner,
        a draw message is displayed.

   8. Overall
Is my code indented correctly?
Mark out of 3: 3
Comments: Code indented correctly using IDE auto-indent feature.
Do my variable names and Constants (at least four of them) make sense?
Mark out of 3: 3
Comments: The variable names make sense and are understandable, variable and constant names are formatted correctly by convention.
Do my variable names, method names and class name follow the Java coding standard
Mark out of 2: 2
Comments: Yes, all names follow the coding standard correctly.
      Total Mark out of 100 (Add all the previous marks): 100
*/
package Week2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NoughtsAndCrosses {
    static final int MAX_ROWS = 3;
    static final int MAX_COLUMNS = 3;
    static final int WIN = 3;
    static final char NOUGHT = 'O';
    static final char CROSS = 'X';
    static final char BLANK = ' ';

    public static void main(String[] args) {
        char[][] board = new char[MAX_ROWS][MAX_COLUMNS];
        clearBoard(board);
        printBoard(board);

        int count = 1;
        char currentPlayerPiece;
        while (!isBoardFull(board) && winner(board) == BLANK) {
            currentPlayerPiece = ((count % 2 == 0) ? NOUGHT : CROSS);
            System.out.println("Enter grid reference for " + currentPlayerPiece + "'s next turn.");
            try {
                Scanner inputScanner = new Scanner(System.in);
                String userGridRef = inputScanner.nextLine();
                if (userGridRef.matches("[a-c]( |,)?[1-3]")) {
                    Scanner stringScanner = new Scanner(userGridRef);
                    char column = stringScanner.next().charAt(0);
                    int columnNumb = 0;
                    if (column == 'a') columnNumb = 0;
                    else if (column == 'b') columnNumb = 1;
                    else if (column == 'c') columnNumb = 2;
                    String[] part = userGridRef.split("([a-c]( |,)?)(?=[1-3])");
                    int row = (Integer.parseInt(part[1])) - 1;
                    if (canMakeMove(board, row, columnNumb)) {
                        count++;
                    }
                    makeMove(board, currentPlayerPiece, row, columnNumb);
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input. Please enter a letter(a-c) then a number(1-3), " +
                        "the letter and number may be separated by a space, a comma, or not at all.");
            }
        }

        if (winner(board) != BLANK) {
            System.out.println("GAME OVER - " + winner(board) + " wins!");
        } else if (isBoardFull(board)) {
            System.out.println("GAME OVER - it's a draw, the board is full.");
        }
    }

    public static void clearBoard(char[][] board) {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                board[row][column] = BLANK;
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("  a b c");
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (column == 0) {
                    System.out.print(row + 1 + " ");
                }
                System.out.print(board[row][column]);
                if (column == 0 || column == 1) {
                    System.out.print("|");
                }
            }
            if (row == 0 || row == 1) {
                System.out.print("\n  -+-+-");
            }
            System.out.println();
        }
    }

    public static boolean canMakeMove(char[][] board, int row, int column) {
        return (board[row][column] == BLANK);
    }

    public static void makeMove(char[][] board, char currentPlayerPiece, int row, int column) {
        if (canMakeMove(board, row, column) && !isBoardFull(board)) {
            board[row][column] = currentPlayerPiece;
            printBoard(board);
        } else if (!canMakeMove(board, row, column)) {
            System.out.println("Error: Cannot make move, space already occupied.");
        }
    }

    public static boolean isBoardFull(char[][] board) {
        int count = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (board[row][column] != BLANK) {
                    count++;
                }
            }
        }
        return (count == 9);
    }

    public static char winner(char[][] board) {
        char winner = BLANK;
        boolean finished = false;

        //Checking rows and columns
        int countX, countO;
        for (int checkCount = 0; checkCount < 2 && !finished; checkCount++) {
            for (int row = 0; row < MAX_ROWS && !finished; row++) {
                countX = 0;
                countO = 0;
                for (int column = 0; column < MAX_COLUMNS; column++) {
                    if (board[(checkCount==0)?row:column][(checkCount==0)?column:row] == CROSS) {
                        countX++;
                    } else if (board[(checkCount==0)?row:column][(checkCount==0)?column:row] == NOUGHT) {
                        countO++;
                    }
                }
                if (countX == WIN) {
                    winner = CROSS;
                    finished = true;
                } else if (countO == WIN) {
                    winner = NOUGHT;
                    finished = true;
                }
            }

        }
        //Checking diagonals
        if (!finished) {
            char playerPiece = CROSS;
            for (int count = 0; count < 2 && !finished; count++) {
                if (board[0][0] == playerPiece && board[1][1] == playerPiece && board[2][2] == playerPiece) {
                    winner = playerPiece;
                    finished = true;
                }
                if (board[0][2] == playerPiece && board[1][1] == playerPiece && board[2][0] == playerPiece) {
                    winner = playerPiece;
                    finished = true;
                }
                playerPiece = NOUGHT;
            }
        }
        return winner;
    }
}