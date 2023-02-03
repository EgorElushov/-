public class Main {
    public static void main(String[] args) {
        DigitalClock digitalClock = new DigitalClock();
        ClockWithHands clockWithHands = new ClockWithHands();
        digitalClock.generateTime();
        clockWithHands.generateTime();
        DigitalClockInterface clockWithHandsAdapter = new ClockWithHandAdapter(clockWithHands);

        printTime(digitalClock);
        printTime(clockWithHandsAdapter);
    }

    static void printTime(DigitalClockInterface clock) {
        String str = String.format("%02d:%02d", clock.getHours(), clock.getMinutes());
        System.out.println(str);
    }
}