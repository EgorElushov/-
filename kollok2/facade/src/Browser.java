public class Browser {
    static int numberOfComputer = 0;

    public Browser() {
        numberOfComputer++;
    }
    public void startBrowser() {
        System.out.println("start browser #" + numberOfComputer);
    }

    public void finishBrowser() {
        System.out.println("finish browser #" + numberOfComputer);
    }
}
