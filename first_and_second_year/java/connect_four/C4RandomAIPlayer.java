package Week10;

import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {

    public C4RandomAIPlayer() {
        setColour();
        Connect4Grid2DArray.players.add(this);
    }

    public int chooseColumn() {
        Random randomColumn = new Random();
        return randomColumn.nextInt(Connect4Grid2DArray.COLUMNS);
    }
}
