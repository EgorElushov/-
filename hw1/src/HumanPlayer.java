public class HumanPlayer extends Player {
    public HumanPlayer(int turn) {
        this.turn = turn;
    }

    @Override
    public void move(int x, int y, int currentMove, GameField field) {
        field.makeMove(x, y, currentMove);
    }
}
