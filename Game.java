import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    // give everything a difficulty
    // generate random robots based on difficulty distribution

    public Game() {

    }

    Scanner input = new Scanner(System.in);
    ArrayList<Robot> blueAlliance = new ArrayList<Robot>(); // Our team
    ArrayList<Robot> redAlliance = new ArrayList<Robot>(); // Opposing team

    boolean shootHigh;
    boolean shootLow;
    int cyclesPerGame;
    int hangPoints;

    Robot myRobot = new Robot();blueAlliance.add(myRobot);

    // fill opponent team
    for(
    int i = 0;i<3;i++)
    {
        redAlliance.add(randomizeRobot());
    }

    System.out.println("How many non-random alliance members?");
    int nonRandom = input.nextInt();

    for(
    int i = 0;i<nonRandom;i++)
    {
        blueAlliance.add(new Robot());
    }

    for(
    int i = 2 - nonRandom;i>0;i--)
    {
        blueAlliance.add(randomizeRobot());
    }

    public void newGame() {
        int redPoints;
        int bluePoints;
        int redRPoints;
        int blueRPoints;
        // return points and balls in auto/telly
    }

    // always randomize opponents, ask how many alliacne memebers to assign values
    // to
    public Robot randomizeRobot() {
        Random numGen = new Random();
        boolean shootHigh = false;
        boolean shootLow = false;
        int cyclesPerGame = 0;
        int hangPoints = 0;

        for (int i = 0; i < 4; i++) {

        }

        Robot robot = new Robot(shootHigh, shootLow, cyclesPerGame, hangPoints);
        return robot;
    }
}
