


public class PuzzleSquare {
    //ask mr moradov about which goes last, right or left
    int[][] square;
    int moves;
    int zeroRow;
    int zeroCol;

    //4 private variables?
    public PuzzleSquare(int dim) {
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
        square[square.length - 1][square[0].length - 1] = 0;

    }

    public PuzzleSquare() {
        this(3);
    }

    public boolean guessMove() throws Exception {

        if(square.length == 3 && !checkIfSolved()) {

            //up
            if(zeroRow > 0 && zeroRow-1 < findperfectrow(square[zeroRow - 1][zeroCol])) { //up
                System.out.println("Going up!");
                return makeMove(0);
            }
            //down
            else if(zeroRow < 2 && zeroRow + 1 > findperfectrow(square[zeroRow+1][zeroCol])) {
                System.out.println("Going down!");
                return makeMove(2);
            }
            //right
            else if(zeroCol > 0 && zeroCol-1 < findperfectcol(square[zeroRow][zeroCol-1])) {
                System.out.println("Going right!");
                return(makeMove(3));
            }
            //left



        }


        System.out.println("No good move to make!");
        Thread.sleep(1000);
        return true;
    }

    public int findperfectcol(int target) {
        PuzzleSquare asd = new PuzzleSquare(3);
        int[][] perfectsquare = asd.getSquare();

        for(int i = 0; i < perfectsquare.length; i++) {
            for(int j = 0; j < perfectsquare.length; j++) {
                if(perfectsquare[i][j] == target) {
                    return j;
                }
                
            }
        }
        return -100;
    }

    public int findperfectrow(int target) {
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
        if (dir == 0 && zeroRow - 1 >= 0) {
            int temp = square[zeroRow - 1][zeroCol];
            square[zeroRow - 1][zeroCol] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroRow -= 1;
            moves++;
            return true;
        }

        //move left
        if (dir == 1 && zeroCol - 1 >= 0) {
            int temp = square[zeroRow][zeroCol - 1];
            square[zeroRow][zeroCol - 1] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroCol -= 1;
            moves++;
            return true;
        }

        //move down
        if (dir == 2 && zeroRow + 1 <= square.length - 1) {
            int temp = square[zeroRow + 1][zeroCol];
            square[zeroRow + 1][zeroCol] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroRow += 1;
            moves++;
            return true;
        }

        //move right
        if (dir == 3 && zeroCol + 1 <= square.length - 1) {
            int temp = square[zeroRow][zeroCol + 1];
            square[zeroRow][zeroCol + 1] = 0;
            square[zeroRow][zeroCol] = temp;
            zeroCol += 1;
            moves++;
            return true;
        }

        if (dir == 4) {
            guessMove();
            return true;
        }

        if (dir == 0 || dir == 1 || dir == 2 || dir == 3 || dir == 4) {
            return false;
        } else {
            System.out.println("Thats not a number you can use!");
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
        square[square.length - 1][square[0].length - 1] = 0;

        int x = 0;
        this.makeMove(0);
        this.makeMove(0);
        for (int i = 0; i < r; i++) {
            x = (int) (Math.random() * 4);
            makeMove(x);
        }
        moves = 0;
    }

    public boolean fillSquare(int[] arr) {
        if (arr == null) {
            return false;
        }
        if (arr.length != Math.pow(square.length, 2)) { //checks if it can be equal
            return false;
        }
        int counter = 0;

        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                square[i][j] = arr[counter];
                counter++;
            }
        }
        return true;
    }

    public boolean checkIfSolved() {
        int counter = 1;
        if (square[square.length - 1][square[0].length - 1] != 0) {
            return false;
        }
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) { //other WAY
                if (counter == Math.pow(square.length, 2)) {
                    return true;
                }
                if (square[i][j] != counter) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }

    @Override
    public String toString() {
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

    public int[][] getSquare() {
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
