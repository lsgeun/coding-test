import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15685
public class Problem15685드래곤커브 {
    static int n;
    static int[][] dragonCurveInfo;
    static final int Y = 0, X = 1, D = 2, G = 3;

    static boolean[][] map;
    static int validGridBlockCount;
    static final int E = 0, N = 1, W = 2, S = 3;
    static final int[][] fourDirection = {
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 0}
    };

    public static void main(String[] args) throws IOException {
        setUp();
        paintDragonCurve();
        calculateValidGridBlockCount();
        System.out.println(validGridBlockCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        dragonCurveInfo = new int[n][4];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurveInfo[i][Y] = y;
            dragonCurveInfo[i][X] = x;
            dragonCurveInfo[i][D] = d;
            dragonCurveInfo[i][G] = g;
        }

        map = new boolean[100 + 1][100 + 1];
        validGridBlockCount = 0;
    }

    static void paintDragonCurve() {
        for (int i = 0; i < n; i++) {
            int y = dragonCurveInfo[i][Y];
            int x = dragonCurveInfo[i][X];
            int d = dragonCurveInfo[i][D];
            int g = dragonCurveInfo[i][G];

            ArrayList<Integer> dragonCurveDirection = new ArrayList<>();
            dragonCurveDirection.add(d);

            for (int generation = 1; generation <= g; generation++) {
                for (int directionIndex = dragonCurveDirection.size() - 1; directionIndex >= 0; directionIndex--) {
                    int direction = dragonCurveDirection.get(directionIndex);
                    direction = rotateClockWise(direction);
                    direction = rotate180Degree(direction);

                    dragonCurveDirection.add(direction);
                }
            }

            map[y][x] = true;

            for (int direction : dragonCurveDirection) {
                y += fourDirection[direction][Y];
                x += fourDirection[direction][X];

                map[y][x] = true;
            }
        }
    }

    static int rotateClockWise(int direction) {
        direction = (direction - 1 + 4) % 4;
        return direction;
    }

    static int rotate180Degree(int direction) {
        direction = (direction + 2) % 4;
        return direction;
    }

    static void calculateValidGridBlockCount() {
        for (int y = 1; y <= 100; y++) {
            for (int x = 1; x <= 100; x++) {
                if (isValidGridBlock(y, x)) {
                    validGridBlockCount += 1;
                }
            }
        }
    }

    static boolean isValidGridBlock(int y, int x) {
        return map[y - 1][x - 1] && map[y - 1][x] && map[y][x - 1] && map[y][x];
    }
}
