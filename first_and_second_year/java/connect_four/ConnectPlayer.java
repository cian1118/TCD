package Week10;

abstract class ConnectPlayer {
    private char colour;

    void setColour() {
        if (Connect4Grid2DArray.players.size() > 0) {
            this.colour = Connect4Grid2DArray.YELLOW;
        } else {
            this.colour = Connect4Grid2DArray.RED;
        }
    }

    public char getColour() {
        return this.colour;
    }

    public abstract int chooseColumn();
}