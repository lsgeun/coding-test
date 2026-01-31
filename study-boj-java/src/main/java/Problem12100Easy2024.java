import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/12100
public class Problem12100Easy2024 {
    public static int[][] board;
    public static int n;
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();

        board = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int num = sc.nextInt();
                board[i][j] = num;
                if (num != 0) {
                    max = Math.max(max, num);
                }
            }
        }

        moveFourDirection(5);

        System.out.println(max);
    }

    public static void moveFourDirection(int count) {
        if (count == 0) {
            return;
        }

        int[][] copyBoard = deepCopyBoard(board);

        for (Direction dir : Direction.values()) {
            // 움직여서 값이 합쳐질 때마다 최대값을 갱신함.
            move(dir);
            moveFourDirection(count - 1);
            revertBoard(copyBoard);
        }
    }

    public static int[][] deepCopyBoard(int[][] board) {
        int[][] copyBoard = new int[board.length][];

        for (int i = 0; i < board.length; i++) {
            copyBoard[i] = board[i].clone();
        }

        return copyBoard;
    }

    public static void revertBoard(int[][] copyBoard) {
        for (int i = 0; i < board.length; i++) {
            board[i] = copyBoard[i].clone();
        }
    }

    public static void move(Direction dir) {
        switch (dir) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }

    private static void moveLeft() {
        for (int row = 1; row <= n; row++) {
            // 같은 숫자를 방향쪽으로 합치기
            for (int i = 1; i <= n; i++) {
                if (board[row][i] == 0) {
                    continue;
                }

                for (int j = i + 1; j <= n; j++) {
                    if (board[row][j] == 0) {
                        continue;
                    }

                    if (board[row][i] == board[row][j]) {
                        board[row][i] *= 2;
                        board[row][j] = 0;

                        max = Math.max(max, board[row][i]);
                        // i = j;와 i = j - 1;는 없어도 되지만
                        // 속도로 조금 더 빠르게 해줌.
                        i = j; // 두번째 값 다음부터
                    } else {
                        i = j - 1; // 두번째 값부터
                    }

                    break;
                }
            }
            // 숫자를 방향쪽으로 몰아넣기
            int insertIndex = 1;
            for (int numIndex = 1; numIndex <= n; numIndex++) {
                if (board[row][numIndex] == 0) {
                    continue;
                }

                if (insertIndex != numIndex) {
                    board[row][insertIndex] = board[row][numIndex];
                    board[row][numIndex] = 0;
                }
                insertIndex++;
            }
        }
    }

    private static void moveRight() {
        for (int row = 1; row <= n; row++) {
            // 같은 숫자를 방향쪽으로 합치기
            for (int i = n; i >= 1; i--) {
                if (board[row][i] == 0) {
                    continue;
                }

                for (int j = i - 1; j >= 1; j--) {
                    if (board[row][j] == 0) {
                        continue;
                    }

                    if (board[row][i] == board[row][j]) {
                        board[row][i] *= 2;
                        board[row][j] = 0;

                        max = Math.max(max, board[row][i]);
                        // i = j;와 i = j - 1;는 없어도 되지만
                        // 속도로 조금 더 빠르게 해줌.
                        i = j; // 두번째 값 다음부터
                    } else {
                        i = j + 1; // 두번째 값부터
                    }

                    break;
                }
            }
            // 숫자를 방향쪽으로 몰아넣기
            int insertIndex = n;
            for (int numIndex = n; numIndex >= 1; numIndex--) {
                if (board[row][numIndex] == 0) {
                    continue;
                }

                if (insertIndex != numIndex) {
                    board[row][insertIndex] = board[row][numIndex];
                    board[row][numIndex] = 0;
                }
                insertIndex--;
            }
        }
    }

    private static void moveUp() {
        for (int column = 1; column <= n; column++) {
            // 같은 숫자를 방향쪽으로 합치기
            for (int i = 1; i <= n; i++) {
                if (board[i][column] == 0) {
                    continue;
                }

                for (int j = i + 1; j <= n; j++) {
                    if (board[j][column] == 0) {
                        continue;
                    }

                    if (board[i][column] == board[j][column]) {
                        board[i][column] *= 2;
                        board[j][column] = 0;

                        max = Math.max(max, board[i][column]);
                        // i = j;와 i = j - 1;는 없어도 되지만
                        // 속도로 조금 더 빠르게 해줌.
                        i = j; // 두번째 값 다음부터
                    } else {
                        i = j - 1; // 두번째 값부터
                    }

                    break;
                }
            }
            // 숫자를 방향쪽으로 몰아넣기
            int insertIndex = 1;
            for (int numIndex = 1; numIndex <= n; numIndex++) {
                if (board[numIndex][column] == 0) {
                    continue;
                }

                if (insertIndex != numIndex) {
                    board[insertIndex][column] = board[numIndex][column];
                    board[numIndex][column] = 0;
                }
                insertIndex++;
            }
        }
    }

    private static void moveDown() {
        for (int column = 1; column <= n; column++) {
            // 같은 숫자를 방향쪽으로 합치기
            for (int i = n; i >= 1; i--) {
                if (board[i][column] == 0) {
                    continue;
                }

                for (int j = i - 1; j >= 1; j--) {
                    if (board[j][column] == 0) {
                        continue;
                    }

                    if (board[i][column] == board[j][column]) {
                        board[i][column] *= 2;
                        board[j][column] = 0;

                        max = Math.max(max, board[i][column]);
                        // i = j;와 i = j - 1;는 없어도 되지만
                        // 속도로 조금 더 빠르게 해줌.
                        i = j; // 두번째 값 다음부터
                    } else {
                        i = j + 1; // 두번째 값부터
                    }

                    break;
                }
            }
            // 숫자를 방향쪽으로 몰아넣기
            int insertIndex = n;
            for (int numIndex = n; numIndex >= 1; numIndex--) {
                if (board[numIndex][column] == 0) {
                    continue;
                }

                if (insertIndex != numIndex) {
                    board[insertIndex][column] = board[numIndex][column];
                    board[numIndex][column] = 0;
                }
                insertIndex--;
            }
        }
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
