import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14503
public class Problem14503로봇청소기 {
    public static int n, m;
    public static int[] robot;
    public static final int R = 0, C = 1, D = 2;
    public static final int[][] DIRECTION = {
            {-1, 0},
            {0 , 1},
            {1, 0},
            {0, -1},
    };
    public static int[][] map; // 0 = 빈칸, 1 = 벽
    public static int[][] cleanlinessMap; // 0 = 청소 x 빈칸, 1 = 벽, 2 = 청소 o 빈칸
    public static int countOfCleanedBlank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        m = sc.nextInt();

        robot = new int[3];
        robot[R] = sc.nextInt();
        robot[C] = sc.nextInt();
        robot[D] = sc.nextInt();

        map = new int[n][m];
        cleanlinessMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int mapNode = sc.nextInt();
                map[i][j] = mapNode;
                cleanlinessMap[i][j] = mapNode;
            }
        }
        countOfCleanedBlank = 0;

        findCountOfCleanedBlank();

        System.out.println(countOfCleanedBlank);
    }

    public static void findCountOfCleanedBlank() {
        while (true) {
            if (cleanlinessMap[robot[R]][robot[C]] == 0) {
                cleanlinessMap[robot[R]][robot[C]] = 2;
                countOfCleanedBlank++;
            }

            if (!existsUncleanedNeighborBlank()) {
                if (canMoveBackward()) {
                    moveBackward();
                    continue;
                } else {
                    break;
                }
            } else {
                // 시계 반대 방향으로 돌며 청소하지 않은 빈칸으로 로봇 돌리기
                turnCounterClockWise();
                int[] frontNode = new int[]{robot[R] + DIRECTION[robot[D]][R], robot[C] + DIRECTION[robot[D]][C]};
                while (true) {
                    if (!isInMap(frontNode[R], frontNode[C])) {
                        continue;
                    }

                     if (cleanlinessMap[frontNode[R]][frontNode[C]] == 0) {
                         break;
                     }

                    turnCounterClockWise();
                    frontNode = new int[]{robot[R] + DIRECTION[robot[D]][R], robot[C] + DIRECTION[robot[D]][C]};
                }

                moveForward();
            }
        }
    }

    public static boolean existsUncleanedNeighborBlank() {
        boolean existsUncleanedNeighborBlank = false;
        for (int dir = 0; dir <= 3; dir++) {
            int robotR = robot[R] + DIRECTION[dir][R];
            int robotC = robot[C] + DIRECTION[dir][C];

            if (!isInMap(robotR, robotC)) {
                continue;
            }

            if (cleanlinessMap[robotR][robotC] == 0) {
                existsUncleanedNeighborBlank = true;
                break;
            }
        }

        return existsUncleanedNeighborBlank;
    }

    public static void turnCounterClockWise() {
        robot[D] = (robot[D] + 3) % 4;
    }

    public static boolean canMoveForward() {
        int robotR = robot[R] + DIRECTION[robot[D]][R];
        int robotC = robot[C] + DIRECTION[robot[D]][C];

        if (!isInMap(robotR, robotC)) {
            return false;
        }

        if (map[robotR][robotC] == 1) {
            return false;
        }

        return true;
    }

    public static void moveForward() {
        robot[R] = robot[R] + DIRECTION[robot[D]][R];
        robot[C] = robot[C] + DIRECTION[robot[D]][C];
    }

    public static boolean canMoveBackward() {
        int robotR = robot[R] - DIRECTION[robot[D]][R];
        int robotC = robot[C] - DIRECTION[robot[D]][C];

        if (!isInMap(robotR, robotC)) {
            return false;
        }

        if (map[robotR][robotC] == 1) {
            return false;
        }

        return true;
    }

    public static void moveBackward() {
        robot[R] = robot[R] - DIRECTION[robot[D]][R];
        robot[C] = robot[C] - DIRECTION[robot[D]][C];
    }

    public static boolean isInMap(int robotR, int robotC) {
        if (0 <= robotR && robotR < n &&
                0 <= robotC && robotC < m) {
            return true;
        }

        return false;
    }
}
