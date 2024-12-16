


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
            System.out.println("Target of 66");
            System.out.println(findperfectrow(6));


            if(zeroRow > 0 && zeroRow-1 < findperfectrow(square[zeroRow - 1][zeroCol])) {
                makeMove(0);
            }

            /* 
            if(zeroRow > 0 && zeroRow - 1 < 2) {
                System.out.println("Made it here!");
                makeMove(0);
            }*/


            //down

            //right

            //left



        }


        return false;
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




    public boolean badguessMove() throws Exception { //returns true 100% of time
        if (square.length == 3) {
            PuzzleSquare abc = new PuzzleSquare(3);
            int[][] temp = abc.getSquare();
            int temprow;
            int tempcol;

            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    if(getZeroRow()!= 0 && temp[row][col] == square[getZeroRow()-1][getZeroCol()]) { //up
                        temprow = row;
                        if(temprow > getZeroRow()-1) {
                            return makeMove(0);
                        }
                    }
                    else if (getZeroRow()!= 2 && temp[row][col] == square[getZeroRow()+1][getZeroCol()]) { //down
                        temprow = row;
                        if(temprow<getZeroRow()+1) {
                            return makeMove(2);
                        }
                    }
                    else if(getZeroCol()!= 0 && temp[row][col] == square[getZeroRow()][getZeroCol()-1]) { //left
                        temprow = row;
                        tempcol = col;
                        if(getZeroCol()-1!=tempcol && temprow > getZeroRow()) { //right    possible error here, tmepcol in left
                            return makeMove(1);
                        }
                    }
                    else if (getZeroCol()!=2) {
                        return makeMove(3);
                    }
                }
            }
            
            System.out.println("No good moves to make");
            Thread.sleep(1000);
            return true;
        }
        System.out.println("This is not 3x3");
        Thread.sleep(1000);
        return true;
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
            boolean temp = guessMove();
            if(temp) {
                return true;
            }
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
