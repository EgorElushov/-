public class ClockWithHandAdapter implements DigitalClockInterface {
    private final ClockWithHands clockWithHands;

    public ClockWithHandAdapter(ClockWithHands clockWithHands) {
        this.clockWithHands = clockWithHands;
    }

    public int getMinutes() {
        return clockWithHands.getMinutesAngle() / 6;
    }

    public int getHours() {
        int hours;
        if (clockWithHands.getIsNight()) {
            hours = 0;
        } else {
            hours = 12;
        }
        hours += clockWithHands.getHoursAngle() / 30;
        return hours;
    }
}
