import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

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

        System.out.println("SUMMARY: ");
        System.out.println("TOTAL WINS: " + totalWins + " / " + Constants.NUM_GAMES);
        System.out.println("AVERAGE RANKING POINTS: " + (totalRPs / Constants.NUM_GAMES));
        System.out.println("AVERAGE (ALLIANCE) POINTS: " + (totalPoints / Constants.NUM_GAMES));
        input.close();
        
    }
}
