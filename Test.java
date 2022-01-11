import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Run Game(0) or Optimize Shooting(1)?");
        if (input.nextInt()==0) {
            runGame();
        } else optimizeShooting();

    }

    public static void optimizeShooting(){
        boolean shootLowAuto = true;
        boolean shootLowTele = false;
        double autoAccuracy = 0.5; // percentage
        double teleAccuracy = 0.5;
        int secondsPerCycleAuto = 5;
        int secondsPerCycleTele = 10;
        int hangPoints = 6;
        int hangTime = 30; // in seconds
        Robot robot1;
        Robot robot2;
        System.out.println("For YOUR ROBOT: ");

        Scanner input = new Scanner(System.in);
        System.out.println("Will it shoot low for auto? (2 = All defaults, 1 = yes, 0 = no)");
        int lowAuto = input.nextInt();
        if (lowAuto < 2) {
            if (lowAuto == 1) {
                shootLowAuto = true;
            }
            // System.out.println("Will it shoot low for tele? (1 = yes, 0 = no)");
            // int lowTele = input.nextInt();
            // if (lowTele == 1) {
            //     shootLowTele = true;
            // }
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

        System.out.println("Alliance Member #1: ");
        robot1 = new Robot();
        System.out.println("Alliance Member #2: ");
        robot2 = new Robot();


        float totalRPs = 0;
        float totalPoints = 0;
        int totalWins = 0;

        for (int i = 0; i < Constants.NUM_GAMES; i++) {
            
            ArrayList<Robot> redAlliance = new ArrayList<Robot>();
            for (int x = 0; x < 3; x++) {
                redAlliance.add(Game.randomizeRobot());
            }

            ArrayList<Robot> blueAlliance = new ArrayList<Robot>();
            blueAlliance.add(robot1);
            blueAlliance.add(robot2);
            blueAlliance.add(new Robot(shootLowAuto, shootLowTele, autoAccuracy, teleAccuracy, secondsPerCycleAuto, secondsPerCycleTele, hangPoints, hangTime));
      
            Game game = new Game(blueAlliance, redAlliance);
            totalRPs += game.getBlueAllianceRPs();
            totalPoints += game.getBlueAlliancePoints();
            if (game.winner() == "Blue Alliance") {
                totalWins++;
            }
        }

        System.out.print("SHOOT LOW TELE SUMMARY: ");
        System.out.print("TOTAL WINS: " + totalWins + " / " + Constants.NUM_GAMES);
        System.out.print(", AVERAGE RANKING POINTS: " + (totalRPs / Constants.NUM_GAMES));
        System.out.println(", AVERAGE (ALLIANCE) POINTS: " + (totalPoints / Constants.NUM_GAMES));

        totalRPs = 0;
        totalPoints = 0;
        totalWins = 0;
        shootLowTele = false;

        for (int i = 0; i < Constants.NUM_GAMES; i++) {
            
            ArrayList<Robot> redAlliance = new ArrayList<Robot>();
            for (int x = 0; x < 3; x++) {
                redAlliance.add(Game.randomizeRobot());
            }

            ArrayList<Robot> blueAlliance = new ArrayList<Robot>();
            blueAlliance.add(robot1);
            blueAlliance.add(robot2);
            blueAlliance.add(new Robot(shootLowAuto, shootLowTele, autoAccuracy, teleAccuracy, secondsPerCycleAuto, secondsPerCycleTele, hangPoints, hangTime));
      
            Game game = new Game(blueAlliance, redAlliance);
            totalRPs += game.getBlueAllianceRPs();
            totalPoints += game.getBlueAlliancePoints();
            if (game.winner() == "Blue Alliance") {
                totalWins++;
            }
        }

        System.out.print("SHOOT HIGH TELE SUMMARY: ");
        System.out.print("TOTAL WINS: " + totalWins + " / " + Constants.NUM_GAMES);
        System.out.print(", AVERAGE RANKING POINTS: " + (totalRPs / Constants.NUM_GAMES));
        System.out.println(", AVERAGE (ALLIANCE) POINTS: " + (totalPoints / Constants.NUM_GAMES));
        input.close();

    }

    public static void runGame(){
        ArrayList<Robot> fixedBlueAlliance = new ArrayList<Robot>();
        Boolean randomizeAlliance = false;
        System.out.println("For YOUR ROBOT: ");

        Robot myRobot = new Robot();

        Scanner input = new Scanner(System.in);
        System.out.println("Randomize Alliance? - Random (0) or Specified (1)?");
        if (input.nextInt()==0) {
            randomizeAlliance = true; 
        } 

        if (!randomizeAlliance){
            fixedBlueAlliance.add(myRobot);
            System.out.println("Alliance Member #1: ");
            fixedBlueAlliance.add(new Robot());
            System.out.println("Alliance Member #2: ");
            fixedBlueAlliance.add(new Robot());
        }

        float totalRPs = 0;
        float totalPoints = 0;
        int totalWins = 0;

        for (int i = 0; i < Constants.NUM_GAMES; i++) {
            
            ArrayList<Robot> redAlliance = new ArrayList<Robot>();
            for (int x = 0; x < 3; x++) {
                redAlliance.add(Game.randomizeRobot());
            }

            ArrayList<Robot> blueAlliance = new ArrayList<Robot>();

            if (randomizeAlliance)
            {
                blueAlliance.add(myRobot);
                blueAlliance.add(Game.randomizeRobot());
                blueAlliance.add(Game.randomizeRobot());
            }
            else{
                blueAlliance = fixedBlueAlliance;
            }
            System.out.print("Game #" + (i + 1) + ": ");
            Game game = new Game(blueAlliance, redAlliance);
            totalRPs += game.getBlueAllianceRPs();
            totalPoints += game.getBlueAlliancePoints();
            System.out.print("Red Points: " + game.getRedAlliancePoints() + "; RPs: "
                    + game.getRedAllianceRPs());
            System.out.print(" | Blue Points: " + game.getBlueAlliancePoints() + "; RPs: "
                    + game.getBlueAllianceRPs());
            System.out.println(" | WINNER: " + game.winner());
            if (game.winner() == "Blue Alliance") {
                totalWins++;
            }
        }

        System.out.print("SUMMARY: ");
        System.out.print("TOTAL WINS: " + totalWins + " / " + Constants.NUM_GAMES);
        System.out.print(", AVERAGE RANKING POINTS: " + (totalRPs / Constants.NUM_GAMES));
        System.out.println(", AVERAGE (ALLIANCE) POINTS: " + (totalPoints / Constants.NUM_GAMES));
        input.close();
    }
}
