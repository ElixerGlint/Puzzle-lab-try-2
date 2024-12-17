


public class PuzzleSquare {
    private int[][] square; //private variables
    private int moves;
    private int zeroRow;
    private int zeroCol;

    public PuzzleSquare(int dim) { //constructor
        moves = 0;
        zeroCol = dim - 1;
        zeroRow = dim - 1;
        square = new int[dim][dim];
        int counter = 1;
        for (int i = 0; i < dim; i++) { //rows
            for (int j = 0; j < dim; j++) { //collums
                square[i][j] = counter;
                counter++;
            }
        }
        square[square.length - 1][square[0].length - 1] = 0; //setting the final space to 0
    }

    public PuzzleSquare() { //auto constructs 3x3
        this(3);
    }

    public boolean guessMove() throws Exception { //this is the guess move

        if(square.length == 3 && !checkIfSolved()) {

            //up
            if(zeroRow > 0 && zeroRow-1 < findperfectrow(square[zeroRow - 1][zeroCol])) { //up, without going out of bounds and still being a good move
                System.out.println("Going up!");
                return makeMove(0);
            }
            //down
            else if(zeroRow < 2 && zeroRow + 1 > findperfectrow(square[zeroRow+1][zeroCol])) {//down, without going out of bounds and still being a good move
                System.out.println("Going down!");
                return makeMove(2);
            }
            //right
            else if(zeroCol > 0 && zeroCol-1 < findperfectcol(square[zeroRow][zeroCol-1])) {//right, without going out of bounds and still being a good move
                System.out.println("Going right!");
                return(makeMove(3));
            }
            //left
            else if (zeroCol < 2 && zeroCol > 0) {//left, without going out of bounds and still being a good move
                System.out.println("Going left");
                return(makeMove(1));
            }
        }
        if(square.length != 3)  { //if its not 3x3, tells the players that it is not available
            System.out.println("The board is not 3x3, therefore you cannot guess for the move");
            Thread.sleep(1000);
            return true;
        }
    

        System.out.println("No good move to make!"); //worst case
        Thread.sleep(1000);
        return true;
    }

    public int findperfectcol(int target) { //finds the collum of the number target, in the solved order
        PuzzleSquare asd = new PuzzleSquare(3);
        int[][] perfectsquare = asd.getSquare();

        for(int i = 0; i < perfectsquare.length; i++) {
            for(int j = 0; j < perfectsquare.length; j++) {
                if(perfectsquare[i][j] == target) {
                    return j;
                }
                
            }
        }
        return -100; //will never return -100, all values imputted will be in the array
    }

    public int findperfectrow(int target) { //finds the row of the number target, in the solved order
        PuzzleSquare asd = new PuzzleSquare(3);
        int[][] perfectsquare = asd.getSquare();

        for(int i = 0; i < perfectsquare.length; i++) {
            for(int j = 0; j < perfectsquare.length; j++) {
                if(perfectsquare[i][j] == target) {
                    return i;
                }
                
            }
        }
        return -100;
    }


    public boolean makeMove(int dir) throws Exception { //0 is move Up 1 is move Left 2 is move Down 3 is Move Right
        //move up
        if (dir == 0 && zeroRow - 1 >= 0) { //makes sure its not out of bounds
            int temp = square[zeroRow - 1][zeroCol]; //moves it
            square[zeroRow - 1][zeroCol] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroRow -= 1;
            moves++;
            return true;
        }

        //move left
        if (dir == 1 && zeroCol - 1 >= 0) { //makes sure its not out of bounds
            int temp = square[zeroRow][zeroCol - 1]; //moves it
            square[zeroRow][zeroCol - 1] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroCol -= 1;
            moves++;
            return true;
        }

        //move down
        if (dir == 2 && zeroRow + 1 <= square.length - 1) { //makes sure its not out of bounds
            int temp = square[zeroRow + 1][zeroCol]; //moves it
            square[zeroRow + 1][zeroCol] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroRow += 1;
            moves++;
            return true;
        }

        //move right
        if (dir == 3 && zeroCol + 1 <= square.length - 1) { //makes sure its not out of bounds
            int temp = square[zeroRow][zeroCol + 1]; //moves it
            square[zeroRow][zeroCol + 1] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroCol += 1;
            moves++;
            return true;
        }

        if (dir == 4) {
            guessMove(); //moves it
            return true;
        }

        if (dir == 0 || dir == 1 || dir == 2 || dir == 3 || dir == 4) { //error proofing (out of bounds)
            return false;
        } else {
            System.out.println("Thats not a number you can use!"); //the user inputted a number other than the core 5
            Thread.sleep(1000);
            return true;
        }
    }

    public void randomize(int r) throws Exception { //r is how randomized it is
        //fill the square with random integers from 1 to n 2 -1 (so 1-8 for above).
        //Then starting making r random moves. Don’t just put them randomly across the board because
        //then it’s possible for it not to be solvable.

        int counter = 1;
        for (int i = 0; i < square.length; i++) { //rows
            for (int j = 0; j < square.length; j++) { //collums
                square[i][j] = counter;
                counter++;
            }
        } 
        //fills it and sets the final square to 0
        square[square.length - 1][square[0].length - 1] = 0;

        int x = 0;
        this.makeMove(0);
        this.makeMove(0);
        for (int i = 0; i < r; i++) { //r is the amount of moves made
            x = (int) (Math.random() * 4); 
            makeMove(x);//randomly moves it
        }
        moves = 0; //resets the moves, which go up every time makemove() is used
    }

    public boolean fillSquare(int[] arr) { //fills the square with a 2d array
        if (arr == null) {
            return false;
        }
        if (arr.length != Math.pow(square.length, 2)) { //checks if it can be equal legnths
            return false;
        }
        int counter = 0;

        for (int i = 0; i < square.length; i++) { //fills it up
            for (int j = 0; j < square[0].length; j++) {
                square[i][j] = arr[counter];
                counter++;
            }
        }
        return true;
    }

    public boolean checkIfSolved() { //checks if it is solved
        int counter = 1;
        if (square[square.length - 1][square[0].length - 1] != 0) { //if the final number is not 0, it is not solved
            return false;
        }
        for (int i = 0; i < square.length; i++) { //goes through number by number to see if it is correct
            for (int j = 0; j < square[i].length; j++) {
                if (counter == Math.pow(square.length, 2)) {
                    return true;
                }
                if (square[i][j] != counter) {
                    return false;
                }
                counter++;
            }
        }
        return true; //returns true if it is all the same
    }

    @Override
    public String toString() { //makes the tostring pretty
        String output = "[";

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                if (square[i][j] < 10) {
                    output += " " + square[i][j];
                } else {
                    output += square[i][j];
                }

                if (j != square.length - 1) {
                    output += " ";
                }
            }
            if (i != square.length - 1) {
                output += "]" + '\n' + "[";
            }
        }
        output += "]";

        return output;
    }

    public int[][] getSquare() { //getters and setters
        return square;
    }

    public int getMoves() {
        return moves;
    }

    public int getZeroRow() {
        return zeroRow;
    }

    public int getZeroCol() {
        return zeroCol;
    }

    public void setSquare(int[][] square) {
        this.square = square;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setZeroRow(int zeroRow) {
        this.zeroRow = zeroRow;
    }

    public void setZeroCol(int zeroCol) {
        this.zeroCol = zeroCol;
    }

}

//:)