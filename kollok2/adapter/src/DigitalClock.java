import java.util.Random;

public class DigitalClock implements DigitalClockInterface {
    private int minutes;
    private int hours;

    public DigitalClock() {
        this.minutes = 0;
        this.hours = 0;
    }

    public DigitalClock(int minutes, int hours) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void generateTime() {
        Random random = new Random();
        this.minutes = random.nextInt(60);
        this.hours = random.nextInt(24);
    }
}
