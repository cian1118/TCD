package Week10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {

    public C4HumanPlayer() {
        setColour();
        Connect4Grid2DArray.players.add(this);
    }

    public int chooseColumn() {
        System.out.println("Enter column for " + this.getColour());
        Scanner inputScanner = new Scanner(System.in);
        int column = -1;
        boolean selected = false;
        while (!selected) {
            try {
                column = inputScanner.nextInt();
                if (column > -1 && column < 7) {
                    selected = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input.");
            }
        }
        return column;
    }

}
