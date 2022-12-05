public class Pair {
    private Integer x;
    private Integer y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return x.toString() + " " + y.toString();
    }
}
