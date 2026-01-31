import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3190
public class Problem3190뱀 {
    public static int n, k;
    public static int[][] map;
    public static final int BLANK = 0, SNAKE = 1, APPLE = 2;

    public static int l;
    public static LinkedList<int[]> changeDirectionInfo;
    public static final int T = 0, C = 1; // 중복되서 X 대신 T로 바꿈
    public static final int L = 0, D = 1;

    public static Deque<int[]> snake;
    public static int headDirection;
    public static final int[][] fourDirection = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1},
    };
    public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    public static final int X = 0, Y = 1;

    public static int playTime;

    public static BufferedReader br;
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        playGame();
        System.out.println(playTime);
    }

    public static void setUp() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(map[i], BLANK);
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int appleX = Integer.parseInt(st.nextToken());
            int appleY = Integer.parseInt(st.nextToken());

            map[appleX][appleY] = APPLE;
        }

        l = Integer.parseInt(br.readLine());

        changeDirectionInfo = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            if (Objects.equals(c, "L")) {
                changeDirectionInfo.add(new int[]{x, L});
            }
            if (Objects.equals(c, "D")) {
                changeDirectionInfo.add(new int[]{x, D});
            }
        }

        snake = new LinkedList<>();
        snake.addFirst(new int[]{1, 1});
        map[1][1] = SNAKE;

        headDirection = EAST;

        playTime = 0;
    }

    public static void playGame() {
        // showMap();

        while (true) {
            playTime += 1;

            int snakeHeadX = snake.peekFirst()[X] + fourDirection[headDirection][X];
            int snakeHeadY = snake.peekFirst()[Y] + fourDirection[headDirection][Y];
            int snakeTailX = snake.peekLast()[X];
            int snakeTailY = snake.peekLast()[Y];

            if (isInWall(snakeHeadX, snakeHeadY)) {
                break;
            }
            if (isInBody(snakeHeadX, snakeHeadY)) {
                break;
            }

            snake.addFirst(new int[]{snakeHeadX, snakeHeadY});

            if (map[snakeHeadX][snakeHeadY] == APPLE) {
                map[snakeHeadX][snakeHeadY] = SNAKE;
            } else { // map[snakeHeadX][snakeHeadY] == BLANK
                map[snakeHeadX][snakeHeadY] = SNAKE;
                snake.pollLast();
                map[snakeTailX][snakeTailY] = BLANK;
            }

            if (!changeDirectionInfo.isEmpty()) {
                if (changeDirectionInfo.get(0)[T] == playTime) {
                    changeHeadDirection(changeDirectionInfo.get(0)[C]);
                    changeDirectionInfo.removeFirst();
                }
            }

            // showMap();
        }
    }

    public static boolean isInWall(int x, int y) {
        boolean isInWall = true;

        if (1 <= x && x <= n &&
                1 <= y && y <= n) {
            isInWall = false;
        }

        return isInWall;
    }

    public static boolean isInBody(int x, int y) {
        return map[x][y] == SNAKE;
    }

    public static void changeHeadDirection(int c) {
        if (c == L) {
            headDirection = (headDirection - 1 + 4) % 4;
        } else { // c == D
            headDirection = (headDirection + 1) % 4;
        }
    }

    public static void showMap() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == SNAKE) {
                    System.out.print("*");
                } else if (map[i][j] == APPLE) {
                    System.out.print("@");
                } else { // map[i][j] == BLANK
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
