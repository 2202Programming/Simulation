import java.util.Scanner;

public class Robot {

    boolean shootLowAuto = true;
    boolean shootLowTele = false;
    double autoAccuracy = 0.5; // percentage
    double teleAccuracy = 0.5;
    int secondsPerCycleAuto = 5;
    int secondsPerCycleTele = 10;
    int hangPoints = 6;
    int hangTime = 30; // in seconds

    public Robot() {
        // prompt user here
        Scanner input = new Scanner(System.in);
        System.out.println("Will it shoot low for auto? (2 = All defaults, 1 = yes, 0 = no)");
        int lowAuto = input.nextInt();
        if (lowAuto < 2) {
            if (lowAuto == 1) {
                shootLowAuto = true;
            }
            System.out.println("Will it shoot low for tele? (1 = yes, 0 = no)");
            int lowTele = input.nextInt();
            if (lowTele == 1) {
                shootLowTele = true;
            }
            System.out.println("Auto accuracy (a decimal between 0 and 1): ");
            autoAccuracy = input.nextDouble();
            System.out.println("Teleop accuracy (a decimal between 0 and 1): ");
            teleAccuracy = input.nextDouble();
            System.out.println("How many seconds does a cycle take in AUTO? (integer)");
            secondsPerCycleAuto = input.nextInt();
            System.out.println("How many seconds does a cycle take in TELEOP? (integer)");
            secondsPerCycleTele = input.nextInt();
            System.out.println("How many hang points will the robot get? (0, 4, 6, 10, 15)");
            hangPoints = input.nextInt();
            System.out.println("How many seconds will it take for your robot to hang? (integer)");
            hangTime = input.nextInt();
        }
    }

    public Robot(boolean shootLowAuto, boolean shootLowTele, double autoAccuracy, double teleAccuracy,
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

    public int getHangPoints() {
        return hangPoints;
    }
}