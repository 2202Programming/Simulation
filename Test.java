import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        final int NUM_GAMES = 100;
        final int NON_RANDOM = 0;

        ArrayList<Robot> blueAlliance = new ArrayList<Robot>();

        System.out.println("For YOUR ROBOT: ");

        Robot myRobot = new Robot();
        blueAlliance.add(myRobot);

        Scanner input = new Scanner(System.in);

        // System.out.println("How many non-random alliance members?");
        // int nonRandom = input.nextInt();

        for (int i = 0; i < NON_RANDOM; i++) {
            blueAlliance.add(new Robot());
        }

        float totalRPs = 0;
        float totalPoints = 0;
        int totalWins = 0;

        for (int i = 0; i < NUM_GAMES; i++) {
            System.out.println("Game #" + (i + 1) + ": ");
            Game game = new Game(blueAlliance);
            totalRPs += game.getBlueAllianceRPs();
            totalPoints += game.getBlueAlliancePoints();
            System.out.println("Red Alliance: Points: " + game.getRedAlliancePoints() + ", Ranking Points: "
                    + game.getRedAllianceRPs());
            System.out.println("Blue Alliance: Points: " + game.getBlueAlliancePoints() + ", Ranking Points: "
                    + game.getBlueAllianceRPs());
            System.out.println("WINNER: " + game.winner());
            if (game.winner() == "Blue Alliance") {
                totalWins++;
            }
        }

        System.out.println("SUMMARY: ");
        System.out.println("TOTAL WINS: " + totalWins + " / " + NUM_GAMES);
        System.out.println("AVERAGE RANKING POINTS: " + (totalRPs / NUM_GAMES));
        System.out.println("AVERAGE (ALLIANCE) POINTS: " + (totalPoints / NUM_GAMES));

        input.close();
    }
}
