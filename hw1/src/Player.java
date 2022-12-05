public abstract class Player {
    protected int turn;

    public abstract void move(int x, int y, int currentMove, GameField field);
}
