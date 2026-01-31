import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499
public class Problem14499주사위굴리기 {
    public static int n, m, x, y, k;
    public static int[] cubePosition;
    public static int[] cubeStatus;
    public static int[] cubeValue;
    public static int[][] map;
    public static final int BACK = 1, LEFT = 2, UP = 3, RIGHT = 4, FRONT = 5, BOTTOM = 6;
    public static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    public static final int X = 0, Y = 1;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cubePosition = new int[]{x, y};

        cubeStatus = new int[6 + 1];
        cubeStatus[UP] = 1;
        cubeStatus[BACK] = 2;
        cubeStatus[RIGHT] = 3;
        cubeStatus[LEFT] = 4;
        cubeStatus[FRONT] = 5;
        cubeStatus[BOTTOM] = 6;

        cubeValue = new int[6 + 1];

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int direction = Integer.parseInt(st.nextToken());
            if (!canMove(direction)) {
                continue;
            }

            move(direction);

            if (map[cubePosition[X]][cubePosition[Y]] == 0) {
                map[cubePosition[X]][cubePosition[Y]] = cubeValue[cubeStatus[BOTTOM]];
            } else { // map[cubePosition[X]][cubePosition[Y]] != 0
                cubeValue[cubeStatus[BOTTOM]] = map[cubePosition[X]][cubePosition[Y]];
                map[cubePosition[X]][cubePosition[Y]] = 0;
            }

            System.out.println(cubeValue[cubeStatus[UP]]);
        }
    }

    public static void move(int direction) {
        changeCubePosition(direction);
        changeCubeStatus(direction);
    }

    public static boolean canMove(int direction) {
        boolean canMove = true;
        changeCubePosition(direction);

        if (!(0 <= cubePosition[X] && cubePosition[X] <= n - 1) ||
            !(0 <= cubePosition[Y] && cubePosition[Y] <= m - 1)) {
            canMove = false;
        }

        switch (direction) {
            case EAST:
                changeCubePosition(WEST);
                break;
            case WEST:
                changeCubePosition(EAST);
                break;
            case NORTH:
                changeCubePosition(SOUTH);
                break;
            case SOUTH:
                changeCubePosition(NORTH);
                break;
            default:
                break;
        }

        return canMove;
    }

    public static void changeCubePosition(int direction) {
        switch (direction) {
            case EAST:
                cubePosition[Y]++;
                break;
            case WEST:
                cubePosition[Y]--;
                break;
            case NORTH:
                cubePosition[X]--;
                break;
            case SOUTH:
                cubePosition[X]++;
                break;
            default:
                break;
        }
    }

    public static void changeCubeStatus(int direction) {
        int temp;
        switch (direction) {
            case EAST:
                temp = cubeStatus[UP];
                cubeStatus[UP] = cubeStatus[LEFT];
                cubeStatus[LEFT] = cubeStatus[BOTTOM];
                cubeStatus[BOTTOM] = cubeStatus[RIGHT];
                cubeStatus[RIGHT] = temp;
                break;
            case WEST:
                temp = cubeStatus[UP];
                cubeStatus[UP] = cubeStatus[RIGHT];
                cubeStatus[RIGHT] = cubeStatus[BOTTOM];
                cubeStatus[BOTTOM] = cubeStatus[LEFT];
                cubeStatus[LEFT] = temp;
                break;
            case NORTH:
                temp = cubeStatus[UP];
                cubeStatus[UP] = cubeStatus[FRONT];
                cubeStatus[FRONT] = cubeStatus[BOTTOM];
                cubeStatus[BOTTOM] = cubeStatus[BACK];
                cubeStatus[BACK] = temp;
                break;
            case SOUTH:
                temp = cubeStatus[UP];
                cubeStatus[UP] = cubeStatus[BACK];
                cubeStatus[BACK] = cubeStatus[BOTTOM];
                cubeStatus[BOTTOM] = cubeStatus[FRONT];
                cubeStatus[FRONT] = temp;
                break;
            default:
                break;
        }
    }
}
