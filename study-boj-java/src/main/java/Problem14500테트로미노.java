import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14500
public class Problem14500테트로미노 {
    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited;
    public static int maxSum;
    public static final int[][] FOUR_DIRECTION = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        findMaxSum();
        System.out.println(maxSum);
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];

        maxSum = Integer.MIN_VALUE;
    }

    public static void findMaxSum() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                findTetrominoWithoutFive(i, j, 1, map[i][j]);
                visited[i][j] = false;

                findTetrominoFive(i, j);
            }
        }
    }

    public static void findTetrominoWithoutFive(int x, int y, int count, int sum) {
        if (count == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int[] direction : FOUR_DIRECTION) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];

            if (!isInMap(nextX, nextY)) {
                continue;
            }

            if (visited[nextX][nextY]) {
                continue;
            }

            visited[nextX][nextY] = true;
            findTetrominoWithoutFive(nextX, nextY, count + 1, sum + map[nextX][nextY]);
            visited[nextX][nextY] = false;
        }
    }

    public static void findTetrominoFive(int x, int y) {
        if (0 <= x - 1 && x + 1 <= n - 1 && 0 <= y - 1) {
            maxSum = Math.max(maxSum, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y - 1]);
        }
        if (0 <= x - 1 && x + 1 <= n - 1 && y + 1 <= m - 1) {
            maxSum = Math.max(maxSum, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y + 1]);
        }
        if (0 <= x - 1 && 0 <= y - 1 && y + 1 <= m - 1) {
            maxSum = Math.max(maxSum, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
        }
        if (x + 1 <= n - 1 && 0 <= y - 1 && y + 1 <= m - 1) {
            maxSum = Math.max(maxSum, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1]);
        }
    }

    public static boolean isInMap(int x, int y) {
        if (!(0 <= x && x <= n - 1) ||
                !(0 <= y && y <= m - 1)) {
            return false;
        }

        return true;
    }
}
