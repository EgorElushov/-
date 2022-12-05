import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ConsoleInterface {
    private Game game;

    public ConsoleInterface() {

    }

    public void initGame() throws IOException {
        System.out.println("Hello, select difficulty!\n0 - second human\n1 - Easy");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        do {
            command = reader.readLine();
        } while (!Objects.equals(command, "0") && !Objects.equals(command, "1"));
        int difficulty = Integer.parseInt(command);
        game = new Game(difficulty);
    }

    public void selectNextTurn() throws IOException {
        System.out.println(game.printField());
        System.out.println("1 - Next turn\n2 - Cancel turn");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        do {
            command = reader.readLine();
        } while (!Objects.equals(command, "1") && !Objects.equals(command, "2"));
        if (command.equals("1")) {
            makeNextTurn();
        } else {
            cancelTurn();
        }
    }

    private void cancelTurn() {
        System.out.println(game.cancelTurn());
    }

    private void makeNextTurn() throws IOException {
        System.out.println(game.makeNextTurn());
    }

    public boolean checkGameOver() {
        return game.checkGameOver() != 0;
    }

    public boolean checkExit() throws IOException {
        System.out.println("You want to exit? (y/sth else)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        command = reader.readLine();
        return Objects.equals(command, "y");
    }
}
