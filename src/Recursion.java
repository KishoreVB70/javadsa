import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        char board[][] = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        sudokuSolver(board);
    }


    // Backtracking problems
    // 1) N queens
    static  List<List<List<Integer>>> nQueens(int n) {
        List<List<List<Integer>>> returnList = new ArrayList<>();
        List<List<Integer>> processed = new ArrayList<>();
        char[][] charMan = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                charMan[i][j] = 'X';
            }
        }

        List<Integer> current = new ArrayList<>(2);
        current.add(0);
        current.add(0);

        boolean[][] board = new boolean[n][n];
        helperNQueens(board, current, processed, returnList, 0, charMan);
        return  returnList;

    }
    static void helperNQueens(boolean[][] board, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList, int queens, char[][] charMan) {
        int row = current.get(0);
        int column = current.get(1);
        int n = board.length;

        // Base condition
        if (queens == n) {
            returnList.add(processed);
            for (int i = 0; i < n; i++) {
                System.out.println(charMan[i]);
            }
            System.out.println(processed);
            return;
        }

        // Return condition -> queen is less than row
        if (queens < row) {
            return;
        }

        List<Integer> newCurrent = new ArrayList<>(current);
        List<List<Integer>> newProcessed = new ArrayList<>(processed);

        // If queen can't be placed
        if (!helperFindIfCanPlaceQueenNQueens(board, row, column, n) ) {
            // Move on to the next position
            if (column < n -1) {
                newCurrent.set(1, column+1);
            } else if (row < n -1){
                newCurrent.set(0, row+1);
                newCurrent.set(1, 0);
            } else {
                // If we have reached the last row
                return;
            }
            helperNQueens(board, newCurrent, newProcessed, returnList, queens, charMan);
            return;
        }

        // If the queen can be placed -> true, or else stays as false
        board[row][column] = true;
        charMan[row][column] = 'Q';

        // New position
        newProcessed.add(current);
        newCurrent.set(0, row +1);
        newCurrent.set(1, 0);
        helperNQueens(board, newCurrent, newProcessed, returnList, queens + 1, charMan);

        // Back tracking step
        board[row][column] = false;
        charMan[row][column] = 'X';


        // Only if there is space in the row, move on to the next position
        if (column < n -1) {
            List<Integer> newestNewCurrent = new ArrayList<>(current);
            List<List<Integer>> newestNewProcessed = new ArrayList<>(processed);
            newestNewCurrent.set(1, column+1);
            helperNQueens(board, newestNewCurrent, newestNewProcessed, returnList, queens, charMan);
        }
        // Else -> return
    }
    static boolean helperFindIfCanPlaceQueenNQueens(boolean[][] board, int row, int column, int n) {
        int tempRow = row - 1;
        // Straight up
        while (tempRow >= 0) {
            if (board[tempRow][column]) {
                return  false;
            }
            tempRow--;
        }

        // Left up diagonal
        tempRow = row - 1;
        int tempColumn = column - 1;
        while (tempRow >= 0 && tempColumn >= 0) {
            if (board[tempRow][tempColumn]) {
                return  false;
            }
            tempRow--;
            tempColumn--;
        }

        // Right up diagonal
        tempRow = row - 1;
        tempColumn = column + 1;
        while (tempRow >= 0 && tempColumn < n) {
            if (board[tempRow][tempColumn]) {
                return  false;
            }
            tempRow--;
            tempColumn++;
        }

        return  true;

    }

    // 2) N Knights
    static List<List<List<Integer>>> nKnights(int n) {
        List<List<List<Integer>>> returnList = new ArrayList<>();
        List<List<Integer>> processed = new ArrayList<>();
        char[][] charMan = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                charMan[i][j] = 'O';
            }
        }

        List<Integer> current = new ArrayList<>(2);
        current.add(0);
        current.add(0);
        boolean[][] board = new boolean[n][n];
        helperNKnights(board, current, processed, returnList, 0, charMan);
        return  returnList;
    }
    static void helperNKnights(boolean[][] board, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList, int knights, char[][] charMan) {
        int row = current.get(0);
        int column = current.get(1);
        int n = board.length;

        // Base condition
        if (knights == n) {
            returnList.add(processed);
            for (int i = 0; i < n; i++) {
                System.out.println(charMan[i]);
            }
            System.out.println(processed);
            return;
        }

        List<Integer> newCurrent = new ArrayList<>(current);
        List<List<Integer>> newProcessed = new ArrayList<>(processed);

        // If queen can't be placed
        if (!helperFindIfCanPlaceNKnights(board, row, column, n) ) {
            // Move on to the next position
            if (column < n -1) {
                newCurrent.set(1, column+1);
            } else if (row < n -1){
                newCurrent.set(0, row+1);
                newCurrent.set(1, 0);
            } else {
                // If we have reached the last row
                return;
            }
            helperNKnights(board, newCurrent, newProcessed, returnList, knights, charMan);
            return;
        }

        // If the queen can be placed -> true, or else stays as false
        board[row][column] = true;
        charMan[row][column] = 'K';
        newProcessed.add(current);


        // New position
        if (column < n -1) {
            newCurrent.set(1, column+1);
            helperNKnights(board, newCurrent, newProcessed, returnList, knights+1, charMan);
        } else if ( row < n-1 ) {
            newCurrent.set(0, row+1);
            newCurrent.set(1, 0);
            helperNKnights(board, newCurrent, newProcessed, returnList, knights +1, charMan);
        } else if (column == n-1 & row == n-1) {
            if (knights + 1 == n) {
                returnList.add(newProcessed);
                for (int i = 0; i < n; i++) {
                    System.out.println(charMan[i]);
                }
                System.out.println(newProcessed);
                return;
            }
        }

        // Back tracking step
        board[row][column] = false;
        charMan[row][column] = 'O';

        List<Integer> newestNewCurrent = new ArrayList<>(current);
        List<List<Integer>> newestNewProcessed = new ArrayList<>(processed);

        // Only if there is space in the row, move on to the next position
        if (column < n -1) {
            newestNewCurrent.set(1, column+1);
            helperNKnights(board, newestNewCurrent, newestNewProcessed, returnList, knights, charMan);
        } else if ( row < n-1 ) {
            newestNewCurrent.set(0, row+1);
            newestNewCurrent.set(1, 0);
            helperNKnights(board, newestNewCurrent, newestNewProcessed, returnList, knights, charMan);
        }
        // Else -> return
    }
    static boolean helperFindIfCanPlaceNKnights(boolean[][] board, int row, int column, int n) {

        // Top,Top right and left
        int tempRow = row - 2;
        if (tempRow >= 0) {
            if ( (column - 1 >= 0 && board[tempRow][column-1]) || (column + 1 < n && board[tempRow][column+1])) {
                return false;
            }
        }

        tempRow = row -1;
        if (tempRow >= 0) {
            // Left, Left, Top
            int tempColumn = column - 2;
            if (tempColumn >= 0 && board[tempRow][tempColumn]) {
                return false;
            }

            // Right, Right, top
            tempColumn = column + 2;
            if (tempColumn < n && board[tempRow][tempColumn]) {
                    return false;
            }
        }

        return  true;

    }

    // 3) Sudoku solver
    static void sudokuSolver(char[][] board) {
        int row = 0;
        int column = 0;
        sudokuSolver(board, row, column);
    }

    static void sudokuSolver(char[][] board, int row, int column) {
        // Base condition -> when you exceeded the sudoku, it means you've solved it
        if (row == board.length) {
            for (char[] chars : board) {
                System.out.println(chars);
            }
            return;
        }

        // If it is already assigned, move ahead
        if (! (board[row][column] == '.')) {
            if (column < board.length -1) {
                sudokuSolver(board, row, column + 1);
            } else if (row < board.length -1 ) {
                sudokuSolver(board, row + 1, 0);
            }
            // If not both condition, then it means, it the last element and return bro
            return;
        }

        // If it is assigned, try every number
        for (int i = 1; i < 9; i++) {
            if (helperFindIfNumberCanBePlaced(board, i, row, column)) {
                board[row][column] = (char)  ( (char) i + '0');

                // Check the next element
                if (column < board.length -1) {
                    sudokuSolver(board, row, column + 1);
                } else {
                    sudokuSolver(board, row + 1, 0);
                }
                // If it returns, move back to normal
                board[row][column] = '.';
            }
        }

        // If nothing works out, go back for the previous to change it's combination
    }

    static char helperFindAppropriateSudokuChar(char[][] board, int row, int column) {
        int i = 0;
        while (i < 9) {
            i++;
            boolean nextNum = false;

            // Check for all the elements in the row
            for (int tempRow = 0; tempRow < 9  ; tempRow++) {
                if (board[tempRow][column] == i + '0') {
                    nextNum = true;
                    break;
                }
            }
            if (nextNum) {
                continue;
            }

            // Check for all the elements in the column
            for (int tempColumn = 0; tempColumn < 9  ; tempColumn++) {
                if (board[row][tempColumn] == i + '0') {
                    nextNum = true;
                    break;
                }
            }
            if (nextNum) {
                continue;
            }

            // Check for all the element in the grid

            // Condition -> if the row or column is getting perfectly divided by 3, then it is the starting column or row
            int startOfGridRow = (row - row % 3 );
            int startOfGridColumn = (column - column % 3);

            for (int j = startOfGridRow; j <= startOfGridRow + 2; j++) {
                for (int k = startOfGridColumn; k <= startOfGridColumn + 2; k++) {
                    if (board[j][k] == i) {
                        nextNum = true;
                        break;
                    }
                }
                if (nextNum) {
                    break;
                }
            }

            if (!nextNum) {
                return (char) ((char) i + '0');
            }
        }

        return 'x';

    }
    static boolean helperFindIfNumberCanBePlaced(char[][] board, int i, int row, int column) {
        // Check for all the elements in the row
        for (int tempRow = 0; tempRow < 9  ; tempRow++) {
            if (board[tempRow][column] == i + '0') {
                return  false;
            }
        }

        // Check for all the elements in the column
        for (int tempColumn = 0; tempColumn < 9  ; tempColumn++) {
            if (board[row][tempColumn] == i + '0') {
                return false;
            }
        }
        // Check for all the element in the grid

        // Condition -> if the row or column is getting perfectly divided by 3, then it is the starting column or row
        int startOfGridRow = (row - row % 3 );
        int startOfGridColumn = (column - column % 3);

        for (int j = startOfGridRow; j <= startOfGridRow + 2; j++) {
            for (int k = startOfGridColumn; k <= startOfGridColumn + 2; k++) {
                if (board[j][k] == i) {
                    return false;
                }
            }
        }

        return true;
    }

    // Maze problems (intro to back tracking)

    // 1) Finding the number of unique paths
    static int findNumberOfUniquePathsBacktracking(int[] goal) {
        int[] start = {0,0};

        return helperFindNumberOfUniquePathsBacktracking(goal, start);
    }
    static int helperFindNumberOfUniquePathsBacktracking(int[] goal, int[] current) {
        int count = 0;

        // Base condition
        if (current[0] == goal[0] || current[1] == goal[1]) {
            return 1;
        }

        // Either add it in the left -> index 0
        int[] temp = new int[2];
        temp[0] = current[0] + 1;
        temp[1] = current[1];
        count += helperFindNumberOfUniquePathsBacktracking(goal, temp);

        // Or add it in the right -> index 1
        current[1] = current[1] + 1;

        // Using the same input array
        count += helperFindNumberOfUniquePathsBacktracking(goal, current);
        return count;
    }

    // 2) Returning all the paths as int array
    static List<List<List<Integer>>> findAllTheUniquePathsOfMaze(int[] goal) {
        List<List<List<Integer>>> returnList = new ArrayList<>();
        List<List<Integer>> processed = new ArrayList<>();
        List<Integer> current  = new ArrayList<>();
        int[] obstacle = {1,1};
        current.add(0);
        current.add(0);
        helperFindAllTheUniquePathsOfMazeObstacle(goal, obstacle, current, processed, returnList);
        return  returnList;
    }
    static void helperFindAllTheUniquePathsOfMaze(int[] goal, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList) {
        List<List<Integer>> newProcessed = new ArrayList<>(processed);
        newProcessed.add(current);

        if (current.get(0) == goal[0] && current.get(1) == goal[1]) {
            returnList.add(newProcessed);
            return;
        }

        // Add to the left
        if (current.get(0) < goal[0]) {
            // Create new List
            List<Integer> temp = new ArrayList<>(current);
            temp.set(0, current.getFirst() + 1);
            helperFindAllTheUniquePathsOfMaze(goal, temp, newProcessed, returnList);
        }

        // Add to the right
        if (current.get(1) < goal[1]) {
            List<Integer> temp2 = new ArrayList<>(current);
            temp2.set(1, current.get(1) + 1);
            helperFindAllTheUniquePathsOfMaze(goal, temp2, newProcessed, returnList);
        }

    }

    // 3) Return the path as string directions
    static List<String> findAllUniquePathsOfMazeString(int[] goal) {
        List<String> resultList = new ArrayList<>();
        int[] current = {0,0};
        int[] obstacle = {1,1};
        helperFindTheNumberOfPathWithObstacles(goal, obstacle, current, "", resultList);
        return resultList;
    }
    static void helperFindAllUniquePathsOfMazeString(int[] goal, int[] current, String processed, List<String> returnList) {
        if (Arrays.equals(goal,current)) {
            returnList.add(processed);
            return;
        }

        if (current[0] < goal[0]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1];
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "R", returnList);
        }

        if (current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0];
            newCurrent[1] = current[1] + 1;
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "D", returnList);
        }
    }
    // 4) Return the path as string -> Include diagonal
    static void helperFindAllUniquePathsOfMazeStringDiagonal(int[] goal, int[] current, String processed, List<String> returnList) {
        if (Arrays.equals(goal, current)) {
            returnList.add(processed);
            return;
        }

        // Diagonal
        if (current[0] <  goal[0] &&  current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1] +1;
            helperFindAllUniquePathsOfMazeStringDiagonal(goal, newCurrent, processed + "S", returnList);
        }

        // Right
        if (current[0] < goal[0]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1];
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "R", returnList);
        }

        // Down
        if (current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0];
            newCurrent[1] = current[1] + 1;
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "D", returnList);
        }
    }

    // 5) With obstacles -> string
    static void helperFindTheNumberOfPathWithObstacles(int[] goal, int[] obstacle, int[] current, String processed, List<String> returnList ) {
        // If we have reached the obstacle, return
        if (Arrays.equals(current, obstacle)) {
            return;
        }
        if (Arrays.equals(current, goal)) {
            returnList.add(processed);
            return;
        }

        // Diagonal
        if (current[0] <  goal[0] &&  current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1] +1;
            helperFindAllUniquePathsOfMazeStringDiagonal(goal, newCurrent, processed + "S", returnList);
        }

        // Right
        if (current[0] < goal[0]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1];
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "R", returnList);
        }

        // Down
        if (current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0];
            newCurrent[1] = current[1] + 1;
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "D", returnList);
        }

    }

    // 5) With obstacles -> List of int
    static void helperFindAllTheUniquePathsOfMazeObstacle(int[] goal,int[] obstacle, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList) {
        // If in the path of obstacle, return back
        if (current.get(0) == obstacle[0] && current.get(1) == obstacle[1]) {
            return;
        }

        List<List<Integer>> newProcessed = new ArrayList<>(processed);
        newProcessed.add(current);

        if (current.get(0) == goal[0] && current.get(1) == goal[1]) {
            returnList.add(newProcessed);
            return;
        }

        // Add to the left
        if (current.get(0) < goal[0]) {
            // Create new List
            List<Integer> temp = new ArrayList<>(current);
            temp.set(0, current.getFirst() + 1);
            helperFindAllTheUniquePathsOfMazeObstacle(goal, obstacle, temp, newProcessed, returnList);
        }

        // Add to the right
        if (current.get(1) < goal[1]) {
            List<Integer> temp2 = new ArrayList<>(current);
            temp2.set(1, current.get(1) + 1);
            helperFindAllTheUniquePathsOfMazeObstacle(goal, obstacle, temp2, newProcessed, returnList);
        }

    }

    // 6) Boolean array for simulating obstacles
    static List<String> findAllTheUniquePathOfMazeObstacleBooleanIn(boolean[][] maze, int[] goal) {
        List<String> returnList = new ArrayList<>();
        int[] current = {0,0};
        int[][] matrix = {{0,0,0},{0,0,0}, {0,0,0}};
        helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, current,maze, "", returnList, 0, matrix);
        return returnList;
    }
    static void helperFindAllTheUniquePathOfMazeObstacleBooleanIn(int[] goal, int[] current,boolean[][] maze, String processed,  List<String> returnList) {
        // Base condition
        if (Arrays.equals(goal, current)) {
            returnList.add(processed);
            return;
        }
        int index0 = current[0];
        int index1 = current[1];

        // If bostacle
        if (!maze[index1][index0]) {
            return;
        }

        if (index0 < goal[0] &&  index1 < goal[1]) {
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1 + 1;
            helperFindAllTheUniquePathOfMazeObstacleBooleanIn(goal, temp1, maze, processed + "D", returnList);
        }

        if (index0 < goal[0]) {
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1;
            helperFindAllTheUniquePathOfMazeObstacleBooleanIn(goal, temp1, maze, processed + "R", returnList);
        }

        if (index1 < goal[1]) {
            int[] temp1 = new int[2];
            temp1[0] = index0;
            temp1[1] = index1 + 1;
            helperFindAllTheUniquePathOfMazeObstacleBooleanIn(goal, temp1, maze, processed + "B", returnList);
        }



    }

    // 7)BACKTRACKING  -> Any direction instead of just down and right
    static void helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn (int[] goal, int[] current,boolean[][] maze, String processed,  List<String> returnList) {
        // Base condition
        if (Arrays.equals(goal, current)) {
            returnList.add(processed);
            return;
        }
        int index0 = current[0];
        int index1 = current[1];

        // If obstacle
        if (!maze[index0][index1]) {
            return;
        }

        // Diagonal
        if (index0 < goal[0] &&  index1 < goal[1]) {
            maze[index0][index1] = false;
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1 + 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn(goal, temp1, maze, processed + "D", returnList);
            maze[index0][index1] = true;
        }

        // Up
        if (index1 > 0) {
            maze[index0][index1] = false;
            int[] temp1 = new int[2];
            temp1[0] = index0;
            temp1[1] = index1 - 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn(goal, temp1, maze, processed + "U", returnList);
            maze[index0][index1] = true;
        }

        // Left
        if (index0 > 0) {
            maze[index0][index1] = false;
            int[] temp1 = new int[2];
            temp1[0] = index0 - 1;
            temp1[1] = index1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn(goal, temp1, maze, processed + "L", returnList);
            maze[index0][index1] = true;
        }

        // Right
        if (index0 < goal[0]) {
            maze[index0][index1] = false;
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn(goal, temp1, maze, processed + "R", returnList);
            maze[index0][index1] = true;
        }

        // Bottom
        if (index1 < goal[1]) {
            maze[index0][index1] = false;
            int[] temp1 = new int[2];
            temp1[0] = index0;
            temp1[1] = index1 + 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanIn(goal, temp1, maze, processed + "B", returnList);
            maze[index0][index1] = true;
        }


    }

    // 8) Printing the matrix along with the path
    static void helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(int[] goal, int[] current,boolean[][] maze, String processed,  List<String> returnList, int currentStep, int[][] matrix) {
        int index0 = current[0];
        int index1 = current[1];

        // If obstacle
        if (!maze[index0][index1]) {
            return;
        }

        matrix[index0][index1] = currentStep;

        // Base condition
        if (Arrays.equals(goal, current)) {
            for (int[] i: matrix) {
                System.out.println(Arrays.toString(i));
            }
            System.out.println(processed);
            System.out.println("-----");
            returnList.add(processed);
            // Reset it to 0
            matrix[index0][index1] = 0;
            return;
        }

        // Backtracking step
        maze[index0][index1] = false;

        // Diagonal
        if (index0 < goal[0] &&  index1 < goal[1]) {
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1 + 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, temp1, maze, processed + "D",  returnList, currentStep +1, matrix);
        }

        // Up
        if (index1 > 0) {
            int[] temp1 = new int[2];
            temp1[0] = index0;
            temp1[1] = index1 - 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, temp1, maze, processed + "L", returnList,currentStep +1, matrix);
        }

        // Left
        if (index0 > 0) {
            int[] temp1 = new int[2];
            temp1[0] = index0 - 1;
            temp1[1] = index1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, temp1, maze, processed + "U", returnList,currentStep +1, matrix);
        }

        // Right
        if (index0 < goal[0]) {
            int[] temp1 = new int[2];
            temp1[0] = index0 + 1;
            temp1[1] = index1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, temp1, maze, processed + "B", returnList, currentStep +1, matrix);
        }

        // Bottom
        if (index1 < goal[1]) {
            int[] temp1 = new int[2];
            temp1[0] = index0;
            temp1[1] = index1 + 1;
            helperFindAllThePathOfMazeAnyDirectionObstacleBooleanInPrintMatrix(goal, temp1, maze, processed + "R", returnList, currentStep +1, matrix);
        }

        // Backtracking
        maze[index0][index1] = true;
        // Resetting the index to 0
        matrix[index0][index1] = 0;
    }


    // Subset problems

    // Microsoft Dice problem
    static List<List<Integer>> diceCombinationToGetTheValue(int digit) {
        List<List<Integer>> returnList = new ArrayList<>();

        if (digit <= 1 || digit > 12) {
            return returnList;
        }

        List<Integer> processed = new ArrayList<>();
        processed.add(0);

        helperDiceCombinationToGetTheValueRecursionStreamLine(digit, 1, processed, returnList, true, false);
        return returnList;
    }
    static void helperDiceCombinationToGetTheValueRecursionStreamLine(int digit, int currentValue, List<Integer> processed, List<List<Integer>> returnList, boolean isFirst, boolean ignore ) {
        // Base condition
        if (currentValue > 6) {
            if (processed.size() > 1 && processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
            }
            return;
        }

        // Another base condition
        if (processed.size() > 1) {
            if (processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
            }
            return;
        }

        // Function body
        ArrayList<Integer> temp1 = new ArrayList<>(processed);
        if (processed.get(0) == 0) {
            temp1.set(0,currentValue);
        }
        else {
            temp1.add(currentValue);
        }

        if (isFirst) {
            // Ignore it
            helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, processed, returnList, false, true);
        } else {
            currentValue++;
            // Ignore it
            helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, processed, returnList, true, false);
        }

        // If ignore, don't do the addition step
        if (ignore) {
            return;
        }

        // Add step
        helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, temp1, returnList, false, false);
    }
    static void helperDiceCombinationToGetTheValueRecursion(int digit, int currentValue,List<Integer> processed, List<List<Integer>> returnList,boolean isFirst, boolean ignore ) {
        // Base condition
        if (currentValue > 6) {
            if (processed.size() > 1 && processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
                return;
            }
            return;
        }

        // Another base condition
        if (processed.size() > 1) {
            if (processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
                return;
            } else {
                return;
            }
        }

        // Function body
        if (isFirst) {
            if (processed.get(0) == 0) {
                // Add it
                ArrayList<Integer> temp1 = new ArrayList<>(processed);
                temp1.set(0,currentValue);
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue, temp1, returnList, false, false);

                // Ignore it
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue, processed, returnList, false, true);
                return;
            }


            // Ignore it
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue, processed, returnList, false, true);

            // Add it
            ArrayList<Integer> temp1 = new ArrayList<>(processed);
            temp1.add(currentValue);
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue, temp1, returnList, false, false);
        } else {
            if (processed.get(0) == 0) {
                // Ignore it
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, processed, returnList, true, false);

                if (ignore) {
                    return;
                }

                // Add it
                ArrayList<Integer> temp1 = new ArrayList<>(processed);
                temp1.set(0,currentValue);
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, temp1, returnList, true, false);
                return;
            }

            // Ignore it
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, processed, returnList, true, false);

            if (ignore) {
                return;
            }

            // Add it
            ArrayList<Integer> temp1 = new ArrayList<>(processed);
            temp1.add(currentValue);
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, temp1, returnList, true, false);
        }


    }
    static void helperDiceCombinationToGetTheValueIteration(int digit, List<List<Integer>> returnList) {
        int[] dieFaces = {1,2,3,4,5,6};

        int start = 0;
        int end = 6;

        for (int i = start; i < end; i++) {
            for (int j = i; j < end; j++) {

                // Making the process efficient
                if (dieFaces[i] + dieFaces[j] > digit) {
                    break;
                }

                // Checking if equal and adding
                if (dieFaces[i] + dieFaces[j] == digit) {
                    List<Integer> tempList = new ArrayList<>(2);
                    tempList.add(dieFaces[i]);
                    tempList.add(dieFaces[j]);
                    returnList.add(tempList);
                }
            }
        }
    }

    // Google problem
    static List<String> letterCombinations(String digits) {
        List<String> returnList = new ArrayList<>();
        String[] letters = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        if (digits.isEmpty()) {
            return returnList;
        }

        helperLetterCombinations(digits, "", letters,  returnList);
        return returnList;
    }
    static void helperLetterCombinations(String digits, String processed, String[] letters, List<String> returnList) {
        // Base condition
        if (digits.isEmpty()) {
            returnList.add(processed);
            return;
        }

        int currentDigit = digits.charAt(0) - '0';

        for (int i = 0; i < letters[currentDigit].length(); i++) {
            String currentString = letters[currentDigit];
            helperLetterCombinations(
                    digits.substring(1),
                    processed + currentString.charAt(i),
                    letters, returnList
            );
        }

    }

    // I] COMBINATION PROBLEMS
    // 1) Find all combinations -> Array
    static List<List<Integer>> findAllCombinationsArray(int[] original) {
        List<List<Integer>> returnList = new ArrayList<>(original.length*2);

        // Adding empty list
        List<Integer> emptyList = new ArrayList<Integer>();

        helperFindAllCombinationsArrayDuplicate(original, emptyList, returnList, 0, false);
        return returnList;
    }
    static void helperFindAllCombinationsArray(int[] original, List<Integer> processed, int currentIndex, List<List<Integer>> returnList) {
        // Base condition
        if (currentIndex == original.length) {
            returnList.add(processed);
            return;
        }

        //Add it
        List<Integer> argList = new ArrayList<>(processed);
        argList.add(original[currentIndex]);
        helperFindAllCombinationsArray(original, argList, currentIndex+1, returnList);


        // Ignore it
        List<Integer> argList2 = new ArrayList<>(processed);
        helperFindAllCombinationsArray(original, argList2, currentIndex+1, returnList);
    }
    // 2) Find all combinations -> String
    static List<String> findAllCombinationsString(String original) {
        List<String> returnList = new ArrayList<>(original.length() * 2);
        helperFindAllCombinationsString(original, "", returnList);
        helperFindAllCombinationsStringDuplicate(original, "" , returnList, false);
        return returnList;
    }
    static void helperFindAllCombinationsString(String original, String processed, List<String> returnList) {
        // Base condition
        if (original.isEmpty()) {
            returnList.add(processed);
            return;
        }
        // Ignore
        helperFindAllCombinationsString(original.substring(1), processed, returnList);

        // Add
        helperFindAllCombinationsString(original.substring(1), processed + original.charAt(0), returnList);
    }
    // 3) Find all combination with duplicates -> String
    static void helperFindAllCombinationsStringDuplicate(String original, String processed, List<String> returnList, boolean duplicate) {
        // Base condition
        if (original.isEmpty()) {
            returnList.add(processed);
            return;
        }

        // If it is a duplicate, don't do the addition step
        if (duplicate) {
            // Ignore
            helperFindAllCombinationsStringDuplicate(original.substring(1), processed, returnList, false);
            return;
        }


        // If duplicate, add true to only ignore step
        helperFindAllCombinationsStringDuplicate(
                original.substring(1),
                processed, returnList,
                original.length() > 1 && original.charAt(0) == original.charAt(1)
        );
        // Add
        helperFindAllCombinationsStringDuplicate(original.substring(1), processed + original.charAt(0), returnList, false);
    }
    // 4) Find all combination with duplicates -> Array
    static void helperFindAllCombinationsArrayDuplicate(int[] original, List<Integer> processed, List<List<Integer>> returnList, int currentIndex, boolean duplicate) {
        // Base condition
        if (currentIndex == original.length) {
            returnList.add(processed);
            return;
        }

        List<Integer> temp1 = new ArrayList<>(processed);
        boolean nextDuplicate = currentIndex < original.length - 1 && original[currentIndex] == original[currentIndex+1];

        if (duplicate) {
            // Skip adding phase -> only ignore phase
            helperFindAllCombinationsArrayDuplicate(original, temp1, returnList, currentIndex+1, nextDuplicate);
            return;
        }

        // Ignore
        helperFindAllCombinationsArrayDuplicate(original, temp1, returnList, currentIndex+1, nextDuplicate);

        // Add
        List<Integer> temp2 = new ArrayList<>(processed);
        temp2.add(original[currentIndex]);
        helperFindAllCombinationsArrayDuplicate(original, temp2, returnList, currentIndex+1, false);

    }
    // 5) Find all combination -> String -> iteration
    static List<String> findAllCombinationsStringIteration(String original) {
        String processed = "";
        List<String> returnList = new ArrayList<>();
        returnList.add(" ");
        for (int i = 0; i < original.length(); i++) {
            for(int j = 0; j < processed.length(); j++) {
                // Add it
                processed = processed + original.charAt(i);
                // Ignore it
            }
            returnList.add(processed);
        }
        return returnList;
    }
    // 6) Find all combination -> Array -> iteration
    // 7) Find all combination with duplicates -> String -> iteration
    // 8) FInd all combination with duplicates -> array -> iteration


    // II] Permutation problems
    // 1) Find all permutations -> String

    // 2) Find all permutations -> Array
    static List<List<Integer>> findAllPermutations(int[] original) {
        List<List<Integer>> returnList = new ArrayList<>(original.length * 2);
        helperFindAllPermutationsArrayNoReturn(original, new ArrayList<>(), 0, returnList);
        return returnList;
    }
    static List<List<Integer>> helperFindAllPermutationsArrayReturn(int[] original, List<Integer> processed, int current) {
        List<List<Integer>> returnList = new ArrayList<>();

        // Base condition
        if (current == original.length) {
            returnList.add(processed);
            return returnList;
        }

        for (int i = 0; i <= processed.size(); i++) {
            int sizeOfThatArray = processed.size();

            // Start
            List<Integer> argList = new ArrayList<>((processed.subList(0, i)));

            // Middle
            argList.add(original[current]);

            //End
            argList.addAll(processed.subList(i,sizeOfThatArray));

            // Call next
            returnList.addAll(helperFindAllPermutationsArrayReturn(original, argList, current+1 ));
        }
        return returnList;


    }
    static void helperFindAllPermutationsArrayNoReturn(int[] original, List<Integer> processed, int current, List<List<Integer>> returnList) {
        // Base condition
        if (current == original.length) {
            returnList.add(processed);
            return;
        }

        for (int i = 0; i <= processed.size(); i++) {
            int sizeOfThatArray = processed.size();

            // Start
            List<Integer> argList = new ArrayList<>((processed.subList(0, i)));

            // Middle
            argList.add(original[current]);

            //End
            argList.addAll(processed.subList(i,sizeOfThatArray));

            // Call next
            helperFindAllPermutationsArrayNoReturn(original, argList, current+1, returnList);
        }
    }
    static void findAllPermutationsString(String st){
        helperFindAllPermutationsString(st.substring(1), st.substring(0,1));
    }
    static void helperFindAllPermutationsString(String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            System.out.println(processed);
            return;
        }

        for (int i = 0; i <= processed.length(); i++) {
            String firstHalf = processed.substring(0, i);
            String secondHalf = processed.substring(i);

            helperFindAllPermutationsString(
                    original.substring(1),
                    firstHalf + original.charAt(0) + secondHalf);
        }
    }

    // Combinations
    static List<List<Integer>> allSubsetsIterationWithDuplicateElements(int[] original) {
        List<List<Integer>> processed = new ArrayList<>();
        processed.add(new ArrayList<>());

        int start = 0;
        for (int i = 0; i < original.length; i++) {

            int sizeOfProcessed = processed.size();
            // If it is not the first turn, create new array with each combination
            for (int j = start; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(original[i]);
                processed.add(temp);
            }

            if (i < original.length - 1 && original[i] == original[i+1]) {
                start = processed.size() - sizeOfProcessed;
            } else {
                start = 0;
            }

        }

        return processed;
    }
    static List<List<Integer>> allSubsetsIterationWithDuplicateElementsCheating(int[] original) {
        List<List<Integer>> processed = new ArrayList<>();
        processed.add(new ArrayList<>());


        for(int i: original) {
            // Initial processed size
            int sizeOfProcessed = processed.size();

            // If it is not the first turn, create new array with each combination
            for (int j = 0; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(i);
                if (processed.contains(temp)) {
                    continue;
                }
                processed.add(temp);
            }
        }

        return processed;
    }
    static List<List<Integer>>  allSubsetsIteration(int[] original) {
        List<List<Integer>> processed = new ArrayList<>(original.length * 2);
        // Add empty list
        processed.add(new ArrayList<>());

        for(int i: original) {
            // Initial processed size
            int sizeOfProcessed = processed.size();

            // If it is not the first turn, create new array with each combination
            for (int j = 0; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(i);
                processed.add(temp);
            }
        }

        return processed;
    }
    static List<String> allSubsetsOfString(String original) {
        List<String> stList = new ArrayList<>(original.length() * 2);
        helperPrintAllSubsetsAlongWithAscii(stList, original, "");
        return stList;
    }
    static void helperPrintAllSubsetsAlongWithAscii(List<String> stList, String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            if (processed.isEmpty()) {
                return;
            }
            stList.add(processed);
            return;
        }
        // Add
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), processed + original.charAt(0));

        // Add the ascii value
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), (processed +  (int)original.charAt(0) ) );

        // Ignore
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), processed);
    }
    static void helperPrintAllSubsetsOfString(List<String> stList, String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            if (processed.isEmpty()) {
                return;
            }
            stList.add(processed);
            return;
        }
        // Add
        helperPrintAllSubsetsOfString(stList, original.substring(1), processed + original.charAt(0));

        // Ignore
        helperPrintAllSubsetsOfString(stList, original.substring(1), processed);
    }


    // Basic strings
    static String removeTargetStringInString(String original, String target) {
        // Base condition
        if (original.isEmpty()) {
            return "";
        }

        // Obtain the next word
        int blankIndex = original.indexOf(' ');
        // Base condition
        if (blankIndex == -1) {
            if (original.equals(target) ) {
                return "";
            }
            return original;

        }

        String currentString = original.substring(0, blankIndex);
        if (currentString.equals(target) ) {
            return removeTargetStringInString(original.substring(blankIndex+1), target);
        } else {
            return currentString + " " + removeTargetStringInString(original.substring(blankIndex+1), target);
        }



    }
    static String removeTargetCharInStringReturn(String original , char target) {
        // Base condition
        if (original.isEmpty()) {
            return "";
        }

        if (original.charAt(0) == target) {
            return removeTargetCharInStringReturn(original.substring(1) , target);
        } else {
            return original.charAt(0) + removeTargetCharInStringReturn(original.substring(1) , target);
        }
    }
    static void removeTargetElementInStringPrint(String original, char target) {
        String processed = "";

        helperRemoveTargetElementInStringPrint(original, processed, target);

    }
    static void helperRemoveTargetElementInStringPrint(String original, String processed, char target) {
        if (original.isEmpty()) {
            System.out.println(processed);
            return;
        }
        if (original.charAt(0) != target) {
            processed += original.charAt(0);
        }
        helperRemoveTargetElementInStringPrint(original.substring(1), processed, target);
    }

    // Selection sort
    static void selectionSort(int[] nums) {
        //helperSelectionSort(nums, nums.length -1);
        helperSelectionSortIntegrated(nums, 1, nums.length -1, 0);
    }
    static void helperSelectionSortIntegrated(int[] nums, int n, int end, int largest) {
        // Base condition for outer
        if (end <= 0) {
            return;
        }

        // Base condition for inner
        if (n > end) {
            // Swap
            helperSwap(nums, largest, end);
            helperSelectionSortIntegrated(nums, 1, --end, 0);
            return;
        }

        if (nums[n] > nums[largest]) {
            largest = n;
            helperSelectionSortIntegrated(nums, ++n, end, largest);
        } else {
            helperSelectionSortIntegrated(nums, ++n, end, largest);
        }
    }
    static void helperSelectionSort(int[] nums, int end) {
        // Base condition
        if (end <= 0) {
            return;
        }

        // 1 -> find the largest element
        int largestElement = helperFindLargest(nums,1, end, 0);
        // 2 -> sort it in right place
        helperSwap(nums, largestElement, end);

        // Iteration
        helperSelectionSort(nums, --end);
    }
    static int helperFindLargest(int[] nums, int n, int end, int largest) {
        if (n > end) {
            return largest;
        }

        if (nums[n] > nums[largest]) {
            largest = n;
            return helperFindLargest(nums, ++n, end, largest);
        } else {
            return helperFindLargest(nums, ++n, end, largest);
        }
    }


    // Bubble sort
    static void bubbleSort(int[] nums) {
        helperBubbleSort(nums, 0, 0, nums.length - 1);
    }
    static void helperBubbleSort(int[] nums, int n, int swaps, int end) {
        // Base condition for one row
        if (n >= end) {
            if (swaps > 1) {
                helperBubbleSort(nums, 0, 0, --end);
                return;
            } else {
                return;
            }
        }

        // Swap if smaller, if not smaller, move on to the next num
        if (nums[n] > nums[n+1]) {
            helperSwap(nums, n, n+1);
            helperBubbleSort(nums, ++n, ++swaps, end);
        } else {
            helperBubbleSort(nums, ++n, swaps, end);
        }
    }
    static  void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }


    // Pattern problems
    static void printStars(int n) {
        helperPrintStarsReversePure(n, 1);
    }
    static void helperPrintStars(int row, int column) {
        if (row == 0) {
            return;
        }
        if (column == row) {
            System.out.println();
            helperPrintStars(--row, 1);
        } else {
            helperPrintStars(row, ++column);
        }
    }
    static void helperPrintStarsReversePure(int row, int column) {
        if ( row == 0) {
            return;
        }
        if (column <= row) {
            helperPrintStarsReversePure(row, ++column);
            System.out.print("*");
        } else {
            helperPrintStarsReversePure(--row, 1);
            System.out.println();
        }
    }
    static void helperPrintStarsReverse(int row, int column, int n) {
        if (row == n+1) {
            return;
        }
        if (row < column) {
            System.out.println();
            helperPrintStarsReverse(++row, 1, n);
        } else {
            System.out.print("*");
            helperPrintStarsReverse(row, ++column, n);

        }

    }
    static void printStarsPartial(int n) {
        if (n == 0) {
            return;
        }
        printStars(n-1);
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }


    // Array problems
    static int FindInRotatedArrayWithoutPivot(int[] nums, int target) {
        int end = nums.length -1 ;
        // Check if no pivot -> normal binary search
        if (nums[0] < nums[end/2] && nums[end/2] < nums[end]) {
            return binarySearchViaRecursion(nums,0,end,target);
        }

        // If there is a pivot, then this
        return helperFindInRotatedArrayWithoutPivot(nums, target, 0, nums.length-1);

    }
    static int helperFindInRotatedArrayWithoutPivot(int[] nums, int target, int start, int end) {
        // base condition
        if (start > end) {
            return -1;
        }
        int m = (start + end) /2 ;

        // check if middle is the target
        if (nums[m] == target) {
            return m;
        }

        // Left side is sorted
        if (nums[start] < nums[m]) {
            // Target is within m till end
            if (target < nums[m] && target < nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
            }

            // Target is within m till end
            if (target < nums[m] && target > nums[start]) {
                return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
            }

            // If target is the start
            return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
        }

        // Check if the start is larger -> this means
        // everything to the right is sorted
        // the pivot is to the left
        if (nums[start] > nums[m]) {

            // If the target is greater than middle and the start,
            // it's in the left
            if (target > nums[m] && target > nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
            }

            // If the target is larger than the middle but smaller than start
            // It's in the right
            if (target > nums[m] && target < nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
            }

            // If the target is larger than start and larger than middle
            // It's in the left
            if (target > nums[m] && target > nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);

            }
            // Element is smaller than middle and start
            if (target < nums[start] && target < nums[m]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);

            }
        }



        // Pivot in in the right


        // Check if pivot is in the right

        // Check if target lies in the left of middle
        if (nums[0] >= nums[m] && target > nums[0]) {
            return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
        }
        if (nums[0] <= nums[m] && target < nums[m]) {
            return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
        }

        // Check if target lies in the right of middle
        if (nums[0] <= nums[m] && target > nums[m]) {
            return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
        }

        return -1;



    }
    static int findInRotatedArray(int[] nums, int target) {
        // 1) find pivot
        int pivot = findPivotInRotateArray(nums);

        // 2) Check if pivot is the target
        if (nums[pivot] == target) {
            return pivot;
        }

        // 3) If non-rotated array
        if (pivot == nums.length - 1) {
            return binarySearchViaRecursion(nums, 0 , pivot, target);
        }

        // 4) If a target out of bounds is given
        if (target > nums[pivot] || target < nums[pivot+1]) {
            return -1;
        }

        // 5) If rotated array
        return target < nums[0]
                ?binarySearchViaRecursion(nums, pivot+1, nums.length-1, target)
                :binarySearchViaRecursion(nums, 0, pivot -1, target);
    }
    static int findPivotInRotateArray(int[] nums) {
        int end = nums.length-1;
        // If no pivot
        if (nums[0] < nums[end/2] && nums[end/2] < nums[end]) {
            return end;
        }

        return helperFindPivotInRotateArray(nums, 0, end);

    }
    static int helperFindPivotInRotateArray(int[] nums, int start, int end) {

        // Base condition
        if (start >= end) {
            return start;
        }

        int m = (start + end) / 2;

        // If pivot is found
        if (nums[m] > nums[m+1]) {
            return m;
        } else if (nums[m-1] > nums[m]) {
            return  m-1;
        }

        // If no pivot
        if (nums[m] < nums[start]) {
            return helperFindPivotInRotateArray(nums, start, m-2);
        } else {
            return helperFindPivotInRotateArray(nums, m+1, end);
        }
    }
    static List<Integer> linearSearchMultipleIndex(int[] nums, int num) {
        List<Integer> list = new ArrayList<>(1);
        helperLinearSearchMultipleIndex(nums, num, 0, list);
        return list;
    }
    static void helperLinearSearchMultipleIndex(int[] nums, int num, int n, List<Integer> list) {
        if (nums[n] == num) list.add(n);

        if (n == nums.length - 1) {
            return;
        }
        helperLinearSearchMultipleIndex(nums, num, n+1, list);
    }
    static boolean linearSearchBool(int[] nums, int num) {
        return helperLinearSearchBool(nums, num, 0);
    }
    static boolean helperLinearSearchBool(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num);
        }

        // Either this num must be num or the next should be n
        return nums[n] == num || helperLinearSearchBool(nums, num, n+1);

    }
    static int linearSearchIndex(int[] nums, int num) {
        return helperLinearSearchIndex(nums, num, 0);
    }
    static int helperLinearSearchIndex(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num)?n:-1;
        }

        // If it is the num, return the index, if not, return the next result
        return (nums[n] == num)?n:helperLinearSearchIndex(nums, num, n+1);
    }
    static boolean findIfArrayIsSorted(int[] n) {
        return helperFindIfArrayIsSortedLinear(n, 0);
    }
    static  boolean helperFindIfArrayIsSortedLinear(int[] arr, int n) {
        // Base condition
        if (n == arr.length - 1) {
            return true;
        }
        if (arr[n] <= arr[n+1]) {
            return helperFindIfArrayIsSortedLinear(arr, n+1);
        }
        return false;
    }
    // Flopped problem
    static boolean helperFindIfArrayIsSortedBinary(int[] n, int start, int end) {
        int m = (start + end) / 2;
        // Base Condition
        if (start >= end) {
            return true;
        }

        if (n[start] <= n[m] && n[m] <= n[end] ) {
            return helperFindIfArrayIsSortedBinary(n,start+1, end-1 );
        }
        else {
            return false;
        }
    }


    // Basic easy questions
    static int numberOfStepsToReduceTo0(int n) {
        // Base condition
        if (n == 0) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + numberOfStepsToReduceTo0(n/2);
        }
        else return 1+ numberOfStepsToReduceTo0(n-1);
    }
    static int numberOfZeros(int number) {
        // Base condition
        if (number < 10) {
            return number == 0?1:0;
        }

        if (number % 10 == 0) {
            return 1 + numberOfZeros(number/10);
        }
        return numberOfZeros(number/10);
    }
    static boolean palindromeNumber(int number) {
        return  (number == reverseANumber(number));
    }
    static int reverseANumber(int number) {
        // Base condition
        if (number < 10) {
            return number;
        }

        // Finding the position of the number
        int digits =  (int)Math.log10(number);
        int position = (int) Math.pow(10, digits);

        // Obtaining the last number
        int lastDigit = number % 10;
        int newNumber = lastDigit * position;

        // Returning
        return (newNumber + reverseANumber(number/10));
    }
    static int productOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) * productOfDigitsOfNum(n/10);
    }
    static int sumOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) + sumOfDigitsOfNum(n/10);
    }
    static int factorialMan(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialMan(n-1);
    }
    static int binarySearchViaRecursion(int[] nums, int start, int end, int target) {
        // Base condition -> target is not in the array
        if (start > end) {
            return -1;
        }

        int n = ( start + end ) / 2;
        int currentValue = nums[n];

        if (currentValue == target) {
            // Base condition -> target is found
            return n;
        }
        else if (currentValue > target) {
            return binarySearchViaRecursion(nums, start, n-1, target);
        }
        else  {
            return binarySearchViaRecursion(nums, n+1, end, target);
        }
    }
    static int fibonacciNumber(int n) {
        if (n == 1) {
            return 1;
        }

        if (n <= 0) {
            return n;
        }
        return (fibonacciNumber(n-1) + fibonacciNumber(n-2));
    }
    static void printNumbers(int n) {
        if (n== 0) {
            return;
        }
        printNumbers(n-1);
        System.out.println(n);
    }
    static  void printHelloWorld(int n) {
        if (n == 1) {
            System.out.println("Hello world");
            return;
        }
        System.out.println("Hello world");
        printHelloWorld(n-1);
    }
}