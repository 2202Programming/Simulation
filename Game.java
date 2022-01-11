import java.util.ArrayList;
import java.util.Random;

public class Game {
    // give everything a difficulty
    // generate random robots based on difficulty distribution

    ArrayList<Robot> blueAlliance; // Our team
    ArrayList<Robot> redAlliance = new ArrayList<Robot>(); // Opposing team
    int redPoints;
    int bluePoints;
    int redRPoints;
    int blueRPoints;

    public Game(ArrayList<Robot> blueAlliance) {
        this.blueAlliance = blueAlliance;
        int teamSize = blueAlliance.size();
        for (int i = 0; i < 3 - teamSize; i++) {
            blueAlliance.add(randomizeRobot());
        }

        for (int i = 0; i < 3; i++) {
            redAlliance.add(randomizeRobot());
        }
    }

    // always randomize opponents, ask how many alliacne memebers to assign values
    // to
    public Robot randomizeRobot() {
        Random numGen = new Random();
        boolean shootLowAuto = false;
        if (numGen.nextInt(2) == 1) {
            shootLowAuto = true;
        }
        boolean shootLowTele = false;
        if (numGen.nextInt(2) == 1) {
            shootLowTele = true;
        }
        double autoAccuracy = (Constants.MIN_RANDOM_AUTO_ACCURACY + numGen.nextInt(Constants.MAX_RANDOM_AUTO_ACCURACY-Constants.MIN_RANDOM_AUTO_ACCURACY)) / 100; // percentage
        double teleAccuracy = (Constants.MIN_RANDOM_TELE_ACCURACY + numGen.nextInt(Constants.MAX_RANDOM_TELE_ACCURACY-Constants.MIN_RANDOM_TELE_ACCURACY)) / 100;
        int secondsPerCycleAuto = Constants.MIN_RANDOM_AUTO_CYCLE + numGen.nextInt(Constants.MAX_RANDOM_AUTO_CYCLE-Constants.MIN_RANDOM_AUTO_CYCLE);
        int secondsPerCycleTele = Constants.MIN_RANDOM_TELE_CYCLE + numGen.nextInt(Constants.MAX_RANDOM_TELE_CYCLE-Constants.MIN_RANDOM_TELE_CYCLE);
        int hangPoints = 0;
        switch (numGen.nextInt(5)) {
            case 0:
                hangPoints = 0;
                break;
            case 1:
                hangPoints = 4;
                break;
            case 2:
                hangPoints = 6;
                break;
            case 3:
                hangPoints = 10;
                break;
            case 4:
                hangPoints = 15;
                break;
        }
        int hangTime = numGen.nextInt(16) + 15; // in seconds

        Robot robot = new Robot(shootLowAuto, shootLowTele, autoAccuracy, teleAccuracy, secondsPerCycleAuto,
                secondsPerCycleTele, hangPoints, hangTime);
        return robot;
    }

    public int getBlueAlliancePoints() {
        int totalPoints = 0;
        for (Robot robot : blueAlliance) {
            totalPoints += robot.pointsScored();
        }
        return totalPoints;
    }

    public int getRedAlliancePoints() {
        int totalPoints = 0;
        for (Robot robot : redAlliance) {
            totalPoints += robot.pointsScored();
        }
        return totalPoints;
    }

    public int getBlueAllianceRPs() {
        int totalPoints = 0;
        if (getBlueAlliancePoints() > getRedAlliancePoints()) {
            totalPoints = 2;
        } else if (getRedAlliancePoints() == getBlueAlliancePoints()) {
            totalPoints = 1;
        }

        int totalHangPoints = 0;
        for (Robot robot : blueAlliance) {
            totalHangPoints += robot.getHangPoints();
        }
        if (totalHangPoints >= 16) {
            totalPoints += 1;
        }

        int totalBallsScored = 0;
        for (Robot robot : blueAlliance) {
            totalBallsScored += robot.teleopBallsScored();
            totalBallsScored += robot.autoBallsScored();
        }

        if (quintet(blueAlliance)) {
            if (totalBallsScored >= 18) {
                totalPoints += 1;
            }
        } else {
            if (totalBallsScored >= 20) {
                totalPoints += 1;
            }
        }

        return totalPoints;
    }

    public int getRedAllianceRPs() {
        int totalPoints = 0;
        if (getRedAlliancePoints() > getBlueAlliancePoints()) {
            totalPoints = 2;
        } else if (getRedAlliancePoints() == getBlueAlliancePoints()) {
            totalPoints = 1;
        }

        int totalHangPoints = 0;
        for (Robot robot : redAlliance) {
            totalHangPoints += robot.getHangPoints();
        }
        if (totalHangPoints >= 16) {
            totalPoints += 1;
        }

        int totalBallsScored = 0;
        for (Robot robot : redAlliance) {
            totalBallsScored += robot.teleopBallsScored();
            totalBallsScored += robot.autoBallsScored();
        }

        if (quintet(redAlliance)) {
            if (totalBallsScored >= 18) {
                totalPoints += 1;
            }
        } else {
            if (totalBallsScored >= 20) {
                totalPoints += 1;
            }
        }

        return totalPoints;
    }

    public String winner() {
        String winner = "Blue Alliance";
        if (getRedAlliancePoints() > getBlueAlliancePoints()) {
            winner = "Red Alliance";
        } else if (getRedAlliancePoints() == getBlueAlliancePoints()) {
            winner = "Tie";
        }
        return winner;
    }

    public boolean quintet(ArrayList<Robot> alliance) {
        int totalBallsScored = 0;
        for (Robot robot : alliance) {
            totalBallsScored += robot.autoBallsScored();
        }

        return (totalBallsScored >= 5);
    }

}
