import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private int maxFirstPlayer = 0;
    private int maxSecondPlayer = 0;
    private final GameField field;
    private int currentTurn;

    //0 - 2 человека, 1 - легкий бот, 2 - сложный бот
    public Game(int difficulty) {
        firstPlayer = new HumanPlayer(1);
        if (difficulty == 0) {
            secondPlayer = new HumanPlayer(-1);
        } else if (difficulty == 1) {
            secondPlayer = new EasyBot(-1);
        } else {
            secondPlayer = new HardBot(-1);
        }
        currentTurn = 1;
        field = new GameField();
    }

    public String makeNextTurn() throws IOException {
        var possibleMove = field.getPossibleMoveList(currentTurn);
        if (possibleMove.size() == 0) {
            System.out.println("No turns:( Skip");
            currentTurn = (currentTurn == 1 ? -1 : 1);
            return field.getStringField(currentTurn);
        }
        int choice = 0;
        for (int i = 0; i < possibleMove.size(); ++i) {
            System.out.println(i + ". " + possibleMove.get(i).toString());
        }
        if (currentTurn == 1) {
            if (firstPlayer.getClass() == HumanPlayer.class) {
                choice = getChoice(possibleMove);
                firstPlayer.move(possibleMove.get(choice).getX(), possibleMove.get(choice).getY(), currentTurn, field);
            } else {
                firstPlayer.move(0, 0, currentTurn, field);
            }
        } else {
            if (secondPlayer.getClass() == HumanPlayer.class) {
                choice = getChoice(possibleMove);
                secondPlayer.move(possibleMove.get(choice).getX(), possibleMove.get(choice).getY(), currentTurn, field);
            } else {
                secondPlayer.move(0, 0, currentTurn, field);
            }
        }
        currentTurn = (currentTurn == 1 ? -1 : 1);
        return field.getStringField(currentTurn);
    }

    private static int getChoice(ArrayList<Pair> possibleMove) throws IOException {
        int choice;
        System.out.println("Enter number of turn:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        do {
            command = reader.readLine();
            try {
                choice = Integer.parseInt(command);
            }
            catch (NumberFormatException ex) {
                System.out.println("This isn't a number");
                choice = 0;
            }
        } while (choice < 0 || choice >= possibleMove.size());
        return choice;
    }

    public String printField() {
        return field.getStringField(currentTurn);
    }

    public String cancelTurn() {
        if (field.cancelTurn())
            currentTurn = (currentTurn == 1 ? -1 : 1);
        return field.getStringField(currentTurn);
    }

    public int checkGameOver() {
        var gameScore = field.checkGameOver();
        if (gameScore != 0) {
            if (currentTurn == 1) {
                System.out.println("Win 2 player, score: " + gameScore);
                maxSecondPlayer = Math.max(maxSecondPlayer, gameScore);
            } else {
                System.out.println("Win 1 player, score: " + gameScore);
                maxFirstPlayer = Math.max(maxFirstPlayer, gameScore);
            }
            System.out.println("Max score for 1 player: " + maxFirstPlayer);
            System.out.println("Max score for 2 player: " + maxSecondPlayer);
        }
        return field.checkGameOver();
    }
}
