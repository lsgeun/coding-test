import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2580
public class Problem2580스도쿠 {
    public static int[][] board = new int[9 + 1][9 + 1];
    public static ArrayList<int[]> blanks = new ArrayList<>();
    public static boolean found = false;
    public static boolean showSteps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                int num = sc.nextInt();
                if (num == 0) {
                    blanks.add(new int[]{i, j});
                }

                board[i][j] = num;
            }
        }
        sc.close();

        showSteps = false;
        writeBoard(0);

        printBoard();
    }

    public static void writeBoard(int blankIndex) {
        if (blankIndex == blanks.size()) {
            found = true;

            return;
        }

        int[] blank = blanks.get(blankIndex);
        int blankRow = blank[0];
        int blankColumn = blank[1];

        for (int value = 1; value <= 9; value++) {
            if (canWrite(blank, value)) {
                board[blankRow][blankColumn] = value;

                if (showSteps) {
                    printBoard();
                    System.out.println();
                }

                writeBoard(blankIndex + 1);

                if (found) {
                    break;
                }

                board[blankRow][blankColumn] = 0;

                if (showSteps) {
                    printBoard();
                    System.out.println();
                }
            }
        }
    }

    public static boolean canWrite(int[] blank, int value) {
        if (!canPutRow(blank, value) ||
                !canPutColumn(blank, value) ||
                !canPutSquare(blank, value)) {
            return false;
        }

        return true;
    }

    public static boolean canPutRow(int[] blank, int value) {
        int blankRow = blank[0];
        int blankColumn = blank[1];

        for (int column = 1; column <= 9; column++) {
            if (column == blankColumn) {
                continue;
            }

            if (value == board[blankRow][column]) {
                return false;
            }
        }

        return true;
    }

    public static boolean canPutColumn(int[] blank, int value) {
        int blankRow = blank[0];
        int blankColumn = blank[1];

        for (int row = 1; row <= 9; row++) {
            if (row == blankRow) {
                continue;
            }

            if (value == board[row][blankColumn]) {
                return false;
            }
        }

        return true;
    }

    public static boolean canPutSquare(int[] blank, int value) {
        int blankRow = blank[0];
        int blankColumn = blank[1];
        int squareRow = (blankRow - 1) / 3 + 1;
        int squareColumn = (blankColumn - 1) / 3 + 1;

        for (int squareNumRow = 3 * squareRow - 2; squareNumRow <= 3 * squareRow; squareNumRow++) {
            for (int squareNumColumn = 3 * squareColumn - 2; squareNumColumn <= 3 * squareColumn; squareNumColumn++) {
                if (squareNumRow == blankRow && squareNumColumn == blankColumn) {
                    continue;
                }

                int squareNum = board[squareNumRow][squareNumColumn];

                if (value == squareNum) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
