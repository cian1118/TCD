package Week10;

import java.util.ArrayList;
import java.util.Arrays;

public class Connect4Grid2DArray implements Connect4Grid {
    public static final char YELLOW = 'Y';
    public static final char RED = 'R';
    private static final char BLANK = ' ';

    public static final int COLUMNS = 7;
    private static final int ROWS = 6;

    public static char[][] grid;
    public static ArrayList<ConnectPlayer> players;

    public Connect4Grid2DArray() {
        grid = new char[ROWS][COLUMNS];
        players = new ArrayList<>();
    }

    public void emptyGrid() {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                grid[row][column] = BLANK;
            }
        }
    }

    public String toString() {
        String gridString = "";
        for (char[] row : grid) {
            gridString += Arrays.toString(row);
            gridString += "\n";
        }
        return gridString;
    }

    public boolean isValidColumn(int column) {
        return (column > -1 && column < 7);
    }

    public boolean isColumnFull(int column) {
        boolean isFull = true;
        for (int row = 0; row < grid.length && isFull; row++) {
            if (grid[row][column] == BLANK) {
                isFull = false;
            }
        }
        return isFull;
    }

    public void dropPiece(ConnectPlayer player, int column) {
        if (isValidColumn(column) && !isColumnFull(column)) {
            int position = -1;
            for (int index = 0; index < grid.length && position == -1; index++) {
                if (grid[index][column] != BLANK) {
                    position = index - 1;
                } else if (index == grid.length-1) {
                    position = grid.length-1;
                }
            }
            if (position != -1) {
                grid[position][column] = player.getColour();
            }
        }
    }

    public boolean didLastPieceConnect4() {
        boolean lastPieceConnect4 = false;
        char currentChar = RED;
        int count = 0;
        int runCount = 0;

        while (!lastPieceConnect4 && runCount < 2) {
            for (int row = 0; row < grid.length && !lastPieceConnect4; row++) {
                for (int column = 0; column < grid[0].length && !lastPieceConnect4; column++) {
                    if (grid[row][column] == currentChar) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        lastPieceConnect4 = true;
                    }
                }
            }

            count = 0;
            for (int column = 0; column < grid[0].length && !lastPieceConnect4; column++) {
                for (int row = 0; row < grid.length && !lastPieceConnect4; row++) {
                    if (grid[row][column] == currentChar) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == 4) {
                        lastPieceConnect4 = true;
                    }
                }
            }

            currentChar = YELLOW;
            runCount++;
        }
        return lastPieceConnect4;
    }

    public boolean isGridFull() {
        boolean isFull = true;
        for (int column = 0; column < grid.length; column++) {
            if (!isColumnFull(column)) {
                isFull = false;
            }
        }
        return isFull;
    }
}
