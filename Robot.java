public class Robot {

    boolean shootLowAuto;
    boolean shootLowTele;
    long autoAccuracy; // percentage
    long teleAccuracy;
    int secondsPerCycleAuto;
    int secondsPerCycleTele;
    int hangPoints;
    int hangTime; // in seconds

    public Robot() {
        // prompt user here
    }

    public Robot(boolean shootLowAuto, boolean shootLowTele, long autoAccuracy, long teleAccuracy,
            int secondsPerCycleAuto, int secondsPerCycleTele, int hangPoints, int hangTime) {
        this.shootLowAuto = shootLowAuto;
        this.shootLowTele = shootLowTele;
        this.autoAccuracy = autoAccuracy;
        this.teleAccuracy = teleAccuracy;
        this.secondsPerCycleAuto = secondsPerCycleAuto;
        this.secondsPerCycleTele = secondsPerCycleTele;
        this.hangPoints = hangPoints;
        this.hangTime = hangTime;
    }

    public int pointsScored() {
        int points = 0;
        // Auto
        int multiplier = 2;
        if (!shootLowAuto) {
            multiplier = 4;
        }
        double autoPoints = multiplier * (15 / secondsPerCycleAuto) * autoAccuracy;
        points = (int) autoPoints;

        // Tele
        multiplier = 1;
        if (!shootLowTele) {
            multiplier = 2;
        }
        int teleShootingTime = 135 - hangTime;
        double telePoints = multiplier * (teleShootingTime / secondsPerCycleTele) * teleAccuracy;
        points += (int) telePoints;

        // Endgame
        points += hangPoints;

        return points;
    }

    public int autoBallsScored() {
        return (int) ((15 / secondsPerCycleAuto) * autoAccuracy);
    }

    public int teleopBallsScored() {
        int teleShootingTime = 135 - hangTime;
        return (int) ((teleShootingTime / secondsPerCycleTele) * teleAccuracy);
    }
}