
import java.util.Scanner; //imports

public class Driver {

    public static void main(String[] args) throws Exception {
        boolean playagain = true; //makes it so the game actually starts
        while (playagain) { //loops the game when the user wants to loop

            int dimension = 0; //initialization of the dimension of the square
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome!");
            while (true) { //error proofing the users inputs
                System.out.println("What dimension would you like to make one side of the square");
                dimension = input.nextInt();

                if (dimension > 1 && dimension < 6) { //breaking if we have a useable value
                    break;
                }
                System.out.println("It must be between 2 and 5 (inclusive)");
            }

            PuzzleSquare abc = new PuzzleSquare(dimension); //creating the puzzlesquare

            abc.randomize(100000);
            abc.setMoves(0);

            System.out.print("\033[H\033[2J"); //clears the screen
            System.out.println("All of the following moves move the zero"); //explaining the controls
            System.out.println("Enter 0 to move up");
            System.out.println("Enter 1 to move left");
            System.out.println("Enter 2 to move down");
            System.out.println("Enter 3 to move right");
            System.out.println("Enger 4 to Autoguess if it is a 3x3 board");
            System.out.println("5 seconds to read the instructions");
            Thread.sleep(5000);

            while (!abc.checkIfSolved()) { //The game looping, when the square is solved it will break
                System.out.print("\033[H\033[2J"); //clearing the screen 
                System.out.println(abc); //printing the board
                int dir = input.nextInt(); //taking the users next move
                if (!abc.makeMove(dir)) { //if no move is made (with an inputted value) that means that the move failed and therefore they moved out of bounds
                    System.out.println("Invalid move made: you moved out of bounds");
                    Thread.sleep(1000);
                }
            }
            System.out.print("\033[H\033[2J"); //clears the screen
            System.out.println(abc); //printing the screen
            System.out.println("You won!");
            System.out.println("It took you " + abc.getMoves() + " moves!"); //showing the amount of moves

            System.out.println("Would you like to play again? Say either true or false"); //determining if the user wants to play again
            playagain = input.nextBoolean();
        }

    }
}
