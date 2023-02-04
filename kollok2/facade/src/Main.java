public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        for (int i = 0; i < 5; i++) {
            computer.startComputer();
            computer.startBrowser();
            computer.finishComputer();
            System.out.println("");
        }
    }
}