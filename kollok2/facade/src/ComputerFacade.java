public class ComputerFacade {
    private Computer computer;
    private OS os;
    private Browser browser;
    public void startComputer() {
        computer = new Computer();
        os = new OS();
        computer.startComputer();
        os.startOS();
    }

    public void startBrowser() {
        browser = new Browser();
        browser.startBrowser();
    }

    public void finishComputer() {
        browser.finishBrowser();
        os.finishOS();
        computer.finishComputer();
    }
}
