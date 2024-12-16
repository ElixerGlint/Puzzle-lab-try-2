
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws Exception {
        boolean playagain = true;
        while (playagain) {

            int dimension = 0;
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome!");
            while (true) {
                System.out.println("What dimension would you like to make one side of the square");
                dimension = input.nextInt();

                if (dimension > 1 && dimension < 6) {
                    break;
                }
                System.out.println("It must be between 2 and 5 (inclusive)");
            }

            PuzzleSquare abc = new PuzzleSquare(dimension);

            abc.randomize(100000);
            abc.setMoves(0);

            while (!abc.checkIfSolved()) {
                //System.out.print("\033[H\033[2J");
                System.out.println(abc);
                int dir = input.nextInt();
                if (!abc.makeMove(dir)) {
                    System.out.println("Invalid move made: you moved out of bounds");
                    Thread.sleep(1000);
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.println(abc);
            System.out.println("You won!");
            System.out.println("It took you " + abc.getMoves() + " moves!");

            System.out.println("Would you like to play again? Say either true or false");
            playagain = input.nextBoolean();
        }

        

    }
}