public class Computer {
    static int numberOfComputer = 0;

    public Computer() {
        numberOfComputer++;
    }
    public void startComputer() {
        System.out.println("start computer #" + numberOfComputer);
    }

    public void finishComputer() {
        System.out.println("finish computer #" + numberOfComputer);
    }
}
