import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9663
public class Problem9663NQueen {
    public static int n;
    public static int[] rowIndexOfColumn;
    public static int count = 0;
    private static boolean showSteps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        sc.close();
        rowIndexOfColumn = new int[n + 1];

        showSteps = false;
        nQueen(1);

        System.out.println(count);

    }

    public static void nQueen(int columnIndex) {
        if (columnIndex == n + 1) {
            count++;
            return;
        }

        for (int rowIndex = 1; rowIndex <= n; rowIndex++) {
            if (canDrop(rowIndex, columnIndex)) {
                rowIndexOfColumn[columnIndex] = rowIndex;
                if (showSteps) {
                    printRowIndexOfColumn();
                }

                nQueen(columnIndex + 1);

                rowIndexOfColumn[columnIndex] = 0;
                if (showSteps) {
                    printRowIndexOfColumn();
                }
            }

        }
    }

    private static void printRowIndexOfColumn() {
        for (int i = 0; i < n + 1; i++) {
            System.out.print(rowIndexOfColumn[i] + " ");
        }
        System.out.println();
    }

    public static boolean canDrop(int curRowIndex, int curColumnIndex) {
        for (int columnIndex = 1; columnIndex < curColumnIndex; columnIndex++) {
            if (rowIndexOfColumn[columnIndex] == 0) {
                continue;
            }

            boolean isSameRow = rowIndexOfColumn[columnIndex] == curRowIndex;
            if (isSameRow) {
                return false;
            }

            int diffY = rowIndexOfColumn[columnIndex] - curRowIndex;
            int diffX = columnIndex - curColumnIndex;
            // IMPORTANT 나누기 오차 때문에, x 차이와 y 차이의 절대값을 비교하는 것이 가장 정확함.
            boolean isDiagonal = Math.abs(diffX) == Math.abs(diffY);
            if (isDiagonal) {
                return false;
            }
        }

        return true;
    }


}
