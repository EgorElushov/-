import java.util.ArrayList;
import java.util.Stack;

public class GameField {
    public static class FieldCell {
        public int occupancy;
        // 0 - не занято, 1 - белый, -1 - черный

         public FieldCell() {
            occupancy = 0;
        }
    }

    private Stack<ArrayList<Pair>> turns;

    private final int sizeN;
    private final int sizeM;

    private final FieldCell[][] field;

    public GameField() {
        sizeN = sizeM = 8;
        field = new FieldCell[sizeN][sizeM];
        for (int i = 0; i < sizeN; ++i) {
            for (int j = 0; j < sizeM; ++j) {
                field[i][j] = new FieldCell();
            }
        }
        field [3][3].occupancy = 1;
        field [4][4].occupancy = 1;
        field [4][3].occupancy = -1;
        field [3][4].occupancy = -1;
        turns = new Stack<>();
    }

    private int getOppositeMove(int currentMove) {
        return currentMove == 1 ? -1 : 1;
    }

    public ArrayList<Pair> getPossibleMoveList(int currentTurn) {
        ArrayList<Pair> possibleMove = new ArrayList<>();
        for (int i = 0; i < sizeN; ++i) {
            for (int j = 0; j < sizeM; ++j) {
                if (checkIsMovePossible(i, j, currentTurn)) {
                    possibleMove.add(new Pair(i, j));
                }
            }
        }
        return possibleMove;
    }

    public boolean checkIsMovePossible(int x, int y, int currentMove) {
        if (field[x][y].occupancy != 0)
            return false;
        var oppositeMove = getOppositeMove(currentMove);
        int i;
        if (x != 0) {
            if (y != 0) {
                if (field[x - 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y - 1 - i >= 0 && field[x - 1 - i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y - 1 - i >= 0) {
                        if (field[x - 1 - i][y - 1 - i].occupancy == currentMove) {
                            return true;
                        }
                    }
                }
            }
            if (field[x - 1][y].occupancy == oppositeMove) {
                for (i = 0; x - 1 - i >= 0 && field[x - i - 1][y].occupancy == oppositeMove; ++i);
                if (x - 1 - i >= 0) {
                    if (field[x - 1 - i][y].occupancy == currentMove) {
                        return true;
                    }
                }
            }
            if (y != sizeM - 1) {
                if (field[x - 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y + 1 + i < sizeM && field[x - 1 - i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y + 1 + i < sizeM) {
                        if (field[x - 1 - i][y + 1 + i].occupancy == currentMove) {
                            return true;
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
                        return true;
                    }
                }
            }
        }
        if (y != sizeM - 1) {
            if (field[x][y + 1].occupancy == oppositeMove) {
                for (i = 0; y + 1 + i < sizeM && field[x][y + 1 + i].occupancy == oppositeMove; ++i);
                if (y + 1 + i < sizeM ) {
                    if (field[x][y + 1 + i].occupancy == currentMove) {
                        return true;
                    }
                }
            }
        }
        if (x != sizeN - 1) {
            if (y != 0) {
                if (field[x + 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < sizeN && y - 1 - i >= 0 && field[x + 1 + i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < sizeN && y - 1 - i >= 0) {
                        if (field[x + 1 + i][y - 1 - i].occupancy == currentMove) {
                            return true;
                        }
                    }
                }
            }
            if (field[x + 1][y].occupancy == oppositeMove) {
                for (i = 0; x + 1 + i < sizeN && field[x + i + 1][y].occupancy == oppositeMove; ++i);
                if (x + 1 + i < sizeN) {
                    if (field[x + 1 + i][y].occupancy == currentMove) {
                        return true;
                    }
                }
            }
            if (y != sizeM - 1) {
                if (field[x + 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < sizeN && y + 1 + i < sizeM && field[x + 1 + i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < sizeN && y + 1 + i < sizeM) {
                        if (field[x + 1 + i][y + 1 + i].occupancy == currentMove) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void makeMove(int x, int y, int currentMove) {
        var oppositeMove = getOppositeMove(currentMove);
        var turn = new ArrayList<Pair>();
        int i;
        if (x != 0) {
            if (y != 0) {
                if (field[x - 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y - 1 - i >= 0 && field[x - 1 - i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y - 1 - i >= 0) {
                        if (field[x - 1 - i][y - 1 - i].occupancy == currentMove) {
                            for (var j = i - 1; j >= -1; --j) {
                                field[x - 1 - j][y - 1 - j].occupancy = currentMove;
                                turn.add(new Pair(x - 1 - j, y - 1 - j));
                            }
                        }
                    }
                }
            }
            if (field[x - 1][y].occupancy == oppositeMove) {
                for (i = 0; x - 1 - i >= 0 && field[x - i - 1][y].occupancy == oppositeMove; ++i);
                if (x - 1 - i >= 0) {
                    if (field[x - 1 - i][y].occupancy == currentMove) {
                        for (var j = i - 1; j >= -1; --j) {
                            field[x - 1 - j][y].occupancy = currentMove;
                            turn.add(new Pair(x - 1 - j, y));
                        }
                    }
                }
            }
            if (y != sizeM - 1) {
                if (field[x - 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x - 1 - i >= 0 && y + 1 + i < sizeM && field[x - 1 - i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x - 1 - i >= 0 && y + 1 + i < sizeM) {
                        if (field[x - 1 - i][y + 1 + i].occupancy == currentMove) {
                            for (var j = i - 1; j >= -1; --j) {
                                field[x - 1 - j][y + 1 + j].occupancy = currentMove;
                                turn.add(new Pair(x - 1 - j, y + 1 + j));
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
                        for (var j = i - 1; j >= -1; --j) {
                            field[x][y - 1 - j].occupancy = currentMove;
                            turn.add(new Pair(x, y - 1 - j));
                        }
                    }
                }
            }
        }
        if (y != sizeM - 1) {
            if (field[x][y + 1].occupancy == oppositeMove) {
                for (i = 0; y + 1 + i < sizeM && field[x][y + 1 + i].occupancy == oppositeMove; ++i);
                if (y + 1 + i < sizeM) {
                    if (field[x][y + 1 + i].occupancy == currentMove) {
                        for (var j = i - 1; j >= -1; --j) {
                            field[x][y + 1 + j].occupancy = currentMove;
                            turn.add(new Pair(x, y + 1 + j));
                        }
                    }
                }
            }
        }
        if (x != sizeN - 1) {
            if (y != 0) {
                if (field[x + 1][y - 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < sizeN && y - 1 - i >= 0 && field[x + 1 + i][y - 1 - i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < sizeN && y - 1 - i >= 0) {
                        if (field[x + 1 + i][y - 1 - i].occupancy == currentMove) {
                            for (var j = i - 1; j >= -1; --j) {
                                field[x + 1 + j][y - 1 - j].occupancy = currentMove;
                                turn.add(new Pair(x + 1 + j, y - 1 - j));
                            }
                        }
                    }
                }
            }
            if (field[x + 1][y].occupancy == oppositeMove) {
                for (i = 0; x + 1 + i < sizeN && field[x + i + 1][y].occupancy == oppositeMove; ++i);
                if (x + 1 + i < sizeN) {
                    if (field[x + 1 + i][y].occupancy == currentMove) {
                        for (var j = i - 1; j >= -1; --j) {
                            field[x + 1 + j][y].occupancy = currentMove;
                            turn.add(new Pair(x + 1 + j, y));
                        }
                    }
                }
            }
            if (y != sizeM - 1) {
                if (field[x + 1][y + 1].occupancy == oppositeMove) {
                    for (i = 0; x + 1 + i < sizeN && y + 1 + i < sizeM && field[x + 1 + i][y + 1 + i].occupancy == oppositeMove; ++i);
                    if (x + 1 + i < sizeN && y + 1 + i < sizeM) {
                        if (field[x + 1 + i][y + 1 + i].occupancy == currentMove) {
                            for (var j = i - 1; j >= -1; --j) {
                                field[x + 1 + j][y + 1 + j].occupancy = currentMove;
                                turn.add(new Pair(x + 1 + j, y + 1 + j));
                            }
                        }
                    }
                }
            }
        }
        turns.add(turn);
    }

    public String getStringField(int currentMove) {
        StringBuilder stringField = new StringBuilder();
        for (int i = 0; i < sizeN; ++i) {
            stringField.append( (char)('0' + i));
            for (int j = 0; j <= 2 * sizeM; ++j) {
                if (j % 2 == 0) {
                    stringField.append('|');
                } else {
                    if (field[i][j / 2].occupancy == -1) {
                        stringField.append('●');
                    } else if (field[i][j / 2].occupancy == 1) {
                        stringField.append('○');
                    } else {
                        if (checkIsMovePossible(i, j / 2, currentMove)) {
                            stringField.append('◌');
                        } else {
                            stringField.append(' ');
                        }
                    }
                }
            }
            stringField.append('\n');
        }
        stringField.append("  ");
        for (int i = 0; i < 2 * sizeN; ++i) {
            if (i % 2 == 0) {
                stringField.append((char)('A' + i / 2));
            } else {
                stringField.append(' ');
            }
        }
        return stringField.toString();
    }

    public int checkGameOver() {
        int whiteCount = 0, blackCount = 0;
        boolean isFull = true;
        for (int i = 0; i < sizeN; ++i) {
            for (int j = 0; j < sizeM; ++j) {
                if (field[i][j].occupancy == 1) {
                    whiteCount++;
                } else if (field[i][j].occupancy == -1) {
                    blackCount++;
                } else {
                    isFull = false;
                }
            }
        }
        if (isFull) {
            return Math.max(whiteCount, blackCount);
        }
        if (whiteCount == 0 || blackCount == 0) {
            return Math.max(whiteCount, blackCount);
        }
        return 0;
    }

    public boolean cancelTurn() {
        if (turns.size() != 0) {
            var canceledTurn = turns.pop();
            for (int i = 0; i < canceledTurn.size() - 1; i++) {
                field[canceledTurn.get(i).getX()][canceledTurn.get(i).getY()].occupancy =
                        getOppositeMove(field[canceledTurn.get(i).getX()][canceledTurn.get(i).getY()].occupancy);
            }
            field[canceledTurn.get(canceledTurn.size() - 1).getX()]
                    [canceledTurn.get(canceledTurn.size() - 1).getY()].occupancy = 0;
            return true;
        }
        return false;
    }

    public FieldCell[][] getField() {
        return field;
    }
}
