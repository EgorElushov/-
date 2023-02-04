public class OS {
    static int numberOfComputer = 0;

    public OS() {
        numberOfComputer++;
    }
    public void startOS() {
        System.out.println("start OS #" + numberOfComputer);
    }

    public void finishOS() {
        System.out.println("finish OS #" + numberOfComputer);
    }
}
