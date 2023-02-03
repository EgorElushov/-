import java.util.Random;

public class ClockWithHands implements ClockWithHandsInterface {
    private int minutesAngle;
    private int hoursAngle;
    private boolean isNight;

    public ClockWithHands() {
        this.minutesAngle = 0;
        this.hoursAngle = 0;
        this.isNight = true;
    }

    public ClockWithHands(int minutesAngle, int hoursAngle, boolean isNight) {
        this.minutesAngle = minutesAngle;
        this.hoursAngle = hoursAngle;
        this.isNight = isNight;
    }

    public int getHoursAngle() {
        return hoursAngle;
    }

    public int getMinutesAngle() {
        return minutesAngle;
    }

    public boolean getIsNight() {
        return isNight;
    }

    public void generateTime() {
        Random random = new Random();
        this.minutesAngle = random.nextInt(360);
        this.hoursAngle = random.nextInt(360);
        this.isNight = random.nextBoolean();
    }
}
