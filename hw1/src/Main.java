import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var consoleInterface = new ConsoleInterface();
        boolean exit = false;
        consoleInterface.initGame();
        do {
            consoleInterface.selectNextTurn();
            if (consoleInterface.checkGameOver()) {
                exit = consoleInterface.checkExit();
                if (!exit)
                    consoleInterface.initGame();
            }
        } while (!exit);
    }
}