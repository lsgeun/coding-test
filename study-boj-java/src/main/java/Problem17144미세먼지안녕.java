import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17144
public class Problem17144미세먼지안녕 {
    static int r, c, t;
    static int[][] map;
    static final int X = 0, Y = 1;
    static final int CLEAN = 0;
    static int[][] airPurifierLocations;
    static final int UP = 0, DOWN = 1;
    static int dustAmount;

    static final int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };
    static final int N = 0, S = 1, W = 2, E = 3;
    static int[][] movesCounterClockwise;
    static int[][] movesClockwise;

    public static void main(String[] args) throws IOException {
        setUp();
        tSecPass();
        calculateDustAmount();
        System.out.println(dustAmount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        airPurifierLocations = new int[2][2];
        int airPurifierIndex = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == -1) {
                    airPurifierLocations[airPurifierIndex][X] = i;
                    airPurifierLocations[airPurifierIndex][Y] = j;
                    airPurifierIndex++;
                }
            }
        }

        movesCounterClockwise = new int[4][2];
        movesClockwise = new int[4][2];

        movesCounterClockwise[0] = new int[]{airPurifierLocations[UP][X] - 0 - 2, N};
        movesCounterClockwise[1] = new int[]{c - 1, E};
        movesCounterClockwise[2] = new int[]{airPurifierLocations[UP][X] - 0, S};
        movesCounterClockwise[3] = new int[]{c - 1 - 1, W};

        movesClockwise[0] = new int[]{r - 1 - airPurifierLocations[DOWN][X] - 2, S};
        movesClockwise[1] = new int[]{c - 1, E};
        movesClockwise[2] = new int[]{r - 1 - airPurifierLocations[DOWN][X], N};
        movesClockwise[3] = new int[]{c - 1 - 1, W};

        dustAmount = 0;
    }

    static void tSecPass() {
        for (int i = 0; i < t; i++) {
            spreadAllDust();
            moveDust();
        }
    }

    static void spreadAllDust() {
        int[][] dustDiff = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == CLEAN) {
                    continue;
                }

                if (isInAirPurifier(i,j)) {
                    continue;
                }

                int dustAmount = map[i][j] / 5;
                for (int[] direction : fourDirection) {
                    int neighborNodeX = i + direction[X];
                    int neighborNodeY = j + direction[Y];

                    if (!isInMap(neighborNodeX, neighborNodeY)) {
                        continue;
                    }

                    if (isInAirPurifier(neighborNodeX, neighborNodeY)) {
                        continue;
                    }

                    dustDiff[neighborNodeX][neighborNodeY] += dustAmount;
                    dustDiff[i][j] -= dustAmount;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += dustDiff[i][j];
            }
        }
    }

    static void moveDust() {
        // counter-clockwise
        // 먼지 움직임
        // 시계 방향으로 밀어내기 해서, 반시계 반향으로 먼지 움직이도록 구현.
        int[] insertNode = new int[]{airPurifierLocations[UP][X] + fourDirection[N][X], airPurifierLocations[UP][Y] + fourDirection[N][Y]};
        int[] curNode = new int[]{airPurifierLocations[UP][X] + 2 * fourDirection[N][X], airPurifierLocations[UP][Y] + 2 * fourDirection[N][Y]};
        map[insertNode[X]][insertNode[Y]] = map[curNode[X]][curNode[Y]];

        final int COUNT = 0, DIRECTION = 1;

        for (int[] move : movesCounterClockwise) {
            for (int i = 0; i < move[COUNT]; i++) {
                insertNode[X] = curNode[X];
                insertNode[Y] = curNode[Y];

                curNode[X] += fourDirection[move[DIRECTION]][X];
                curNode[Y] += fourDirection[move[DIRECTION]][Y];

                map[insertNode[X]][insertNode[Y]] = map[curNode[X]][curNode[Y]];
            }
        }

        // 깨끗한 공기 나옴
        map[curNode[X]][curNode[Y]] = 0;

        // clockwise
        // 먼지 움직임
         insertNode = new int[]{airPurifierLocations[DOWN][X] + fourDirection[S][X], airPurifierLocations[DOWN][Y] + fourDirection[S][Y]};
        curNode = new int[]{airPurifierLocations[DOWN][X] + 2 * fourDirection[S][X], airPurifierLocations[DOWN][Y] + 2 * fourDirection[S][Y]};

        map[insertNode[X]][insertNode[Y]] = map[curNode[X]][curNode[Y]];

        for (int[] move : movesClockwise) {
            for (int i = 0; i < move[COUNT]; i++) {
                insertNode[X] = curNode[X];
                insertNode[Y] = curNode[Y];

                curNode[X] += fourDirection[move[DIRECTION]][X];
                curNode[Y] += fourDirection[move[DIRECTION]][Y];

                map[insertNode[X]][insertNode[Y]] = map[curNode[X]][curNode[Y]];
            }
        }

        // 깨끗한 공기 나옴
        map[curNode[X]][curNode[Y]] = 0;
    }

    static boolean isInMap(int x, int y) {
        return (0 <= x && x <= r - 1) && (0 <= y && y <= c - 1);
    }

    static boolean isInAirPurifier(int x, int y) {
        boolean isInAirPurifier = false;
        for (int[] node : airPurifierLocations) {
            if (node[X] == x && node[Y] == y) {
                isInAirPurifier = true;
                break;
            }
        }
        return isInAirPurifier;
    }

    static void calculateDustAmount() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) {
                    continue;
                }

                dustAmount += map[i][j];
            }
        }
    }
}
