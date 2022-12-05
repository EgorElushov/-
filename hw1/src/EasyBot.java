import java.util.ArrayList;

public class EasyBot extends Player {
    static final int SIZE_N = 8, SIZE_M = 8;
    //static final int
    public EasyBot(int turn) {
        this.turn = turn;
    }

    private double[][] calculateOptimalCell(int currentMove, GameField field) {
        double[][] utility = new double[SIZE_N][SIZE_M];
        for (int i = 0; i < SIZE_N; i++)
            for (int j = 0; j < SIZE_M; j++)
                utility[i][j] = calculateCell(field, i, j, currentMove);
        return utility;
    }

    private double calculateCell(GameField gameField, int x, int y, int currentMove) {
        var oppositeMove = (currentMove == 1 ? -1 : 1);
        var field = gameField.getField();
        double sum = 0;
        if (x == 0 && y == 0 || x == SIZE_N - 1 && y == 0 || x == 0 && y == SIZE_M - 1 || x == SIZE_N - 1 && y == SIZE_M - 1)
            sum = 0.8;
        else if (x == 0 || y == 0 || x == SIZE_N - 1 || y == SIZE_M - 1)
            sum = 0.4;
        int i;
        if (x != 0) {
            if (y != 0) {
                if (field[x - 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y - 1 - i >= 0 && field[x - 1 - i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y - 1 - i >= 0) {
                        if (field[x - 1 - i][y - 1 - i].occupancy == currentMove) {
                            for (var j = i - 1; j > -1; --j) {
                                if (x - 1 - j == 0 || y - 1 - j == 0)
                                    sum += 2;
                                else
                                    sum += 1;
                            }
                        }
                    }
                }
            }
            if (field[x - 1][y].occupancy == oppositeMove) {
                for (i = 0; x - 1 - i >= 0 && field[x - i - 1][y].occupancy == oppositeMove; ++i);
                if (x - 1 - i >= 0) {
                    if (field[x - 1 - i][y].occupancy == currentMove) {
                        for (var j = i - 1; j > -1; --j) {
                            if (x - 1 - j == 0 || y == 0 || y == SIZE_M - 1)
                                sum += 2;
                            else
                                sum += 1;
                        }
                    }
                }
            }
            if (y != SIZE_M - 1) {
                if (field[x - 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y + 1 + i < SIZE_M && field[x - 1 - i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y + 1 + i < SIZE_M) {
                        if (field[x - 1 - i][y + 1 + i].occupancy == currentMove) {
                            for (var j = i - 1; j > -1; --j) {
                                if (x - 1 - j == 0 || y + 1 + j == SIZE_M - 1)
                                    sum += 2;
                                else
                                    sum += 1;
                            }
                        }
                    }
                }
            }
        }
        if (y != 0) {
            if (field[x][y - 1].occupancy == oppositeMove) {
                for (i = 0; y - 1 - i >= 0 && field[x][y - 1 - i].occupancy == oppositeMove; ++i);
                if (y - 1 - i >= 0) {
                    if (field[x][y - 1 - i].occupancy == currentMove) {
                        for (var j = i - 1; j > -1; --j) {
                            if (x == 0 || x == SIZE_N || y - 1 - j == 0)
                                sum += 2;
                            else
                                sum += 1;
                        }
                    }
                }
            }
        }
        if (y != SIZE_M - 1) {
            if (field[x][y + 1].occupancy == oppositeMove) {
                for (i = 0; y + 1 + i < SIZE_M && field[x][y + 1 + i].occupancy == oppositeMove; ++i);
                if (y + 1 + i < SIZE_M) {
                    if (field[x][y + 1 + i].occupancy == currentMove) {
                        for (var j = i - 1; j > -1; --j) {
                            if (x== 0 || x == SIZE_N - 1 || y + 1 + j == SIZE_M - 1)
                                sum += 2;
                            else
                                sum += 1;
                        }
                    }
                }
            }
        }
        if (x != SIZE_N - 1) {
            if (y != 0) {
                if (field[x + 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < SIZE_N && y - 1 - i >= 0 && field[x + 1 + i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < SIZE_N && y - 1 - i >= 0) {
                        if (field[x + 1 + i][y - 1 - i].occupancy == currentMove) {
                            for (var j = i - 1; j > -1; --j) {
                                if (x + 1 + j == SIZE_N - 1 || y - 1 - j == 0)
                                    sum += 2;
                                else
                                    sum += 1;
                            }
                        }
                    }
                }
            }
            if (field[x + 1][y].occupancy == oppositeMove) {
                for (i = 0; x + 1 + i < SIZE_N && field[x + i + 1][y].occupancy == oppositeMove; ++i);
                if (x + 1 + i < SIZE_N) {
                    if (field[x + 1 + i][y].occupancy == currentMove) {
                        for (var j = i - 1; j > -1; --j) {
                            if (x + 1 + j == SIZE_N - 1 || y == 0 || y == SIZE_M - 1)
                                sum += 2;
                            else
                                sum += 1;
                        }
                    }
                }
            }
            if (y != SIZE_M - 1) {
                if (field[x + 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < SIZE_N && y + 1 + i < SIZE_M && field[x + 1 + i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < SIZE_N && y + 1 + i < SIZE_M) {
                        if (field[x + 1 + i][y + 1 + i].occupancy == currentMove) {
                            for (var j = i - 1; j > -1; --j) {
                                if (x + 1 + j == SIZE_N - 1 || y + 1 + j == SIZE_M - 1)
                                    sum += 2;
                                else
                                    sum += 1;
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public void move(int x, int y, int currentMove, GameField field) {
        int maxI = 0, maxJ = 0;
        double max = 0;
        var utility = calculateOptimalCell(currentMove, field);
        for (int i = 0; i < SIZE_N; i++)
            for (int j = 0; j < SIZE_M; j++)
                if (utility[i][j] > max) {
                    max = utility[i][j];
                    maxI = i;
                    maxJ = j;
                }
        field.makeMove(maxI, maxJ, currentMove);
    }
}
