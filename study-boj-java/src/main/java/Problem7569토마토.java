import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7569
public class Problem7569토마토 {
    public static int m, n, h;
    public static int box[][][];
    public static int minDays;
    public static int[][] sixDirection = {
            {1, 0, 0},  // 상
            {-1, 0, 0}, // 하
            {0, 0, -1}, // 좌
            {0, 0, 1},  // 우
            {0, 1, 0},  // 앞
            {0, -1, 0}, // 뒤
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        box = new int[h + 1][n + 1][m + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        minDays = -1;
        findMinDays();

        System.out.println(minDays);
    }

    public static void findMinDays() {
        int[][][] copyBox = new int[h + 1][n + 1][m + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                copyBox[i][j] = box[i][j].clone();
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        int days = 0;
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if (copyBox[i][j][k] == 1) {
                        minDays = days;
                        queue.add(new int[]{i, j, k, days});
                    }
                }
            }
        }

        // 익은 토마토(1)가 없다면
        // 토마토가 모두 익지 못하는 상황(-1)
        if (minDays == -1) {
            return;
        }

        // 익은 토마토로 주변 익지 않은 토마토 익게 하기
        while (!queue.isEmpty()) {
            int[] curTomatoInfo = queue.poll();

            for (int[] direction : sixDirection) {
                // h = 0, n = 1, m = 2, days = 3 차원
                int nextTomatoH = curTomatoInfo[0] + direction[0];
                int nextTomatoN = curTomatoInfo[1] + direction[1];
                int nextTomatoM = curTomatoInfo[2] + direction[2];
                int nextDays = curTomatoInfo[3] + 1;

                if (!(1 <= nextTomatoH && nextTomatoH <= h) ||
                        !(1 <= nextTomatoN && nextTomatoN <= n) ||
                        !(1 <= nextTomatoM && nextTomatoM <= m)) {
                    continue;
                }

                if (copyBox[nextTomatoH][nextTomatoN][nextTomatoM] == 0) {
                    copyBox[nextTomatoH][nextTomatoN][nextTomatoM] = 1;
                    minDays = nextDays;
                    queue.add(new int[]{nextTomatoH, nextTomatoN, nextTomatoM, nextDays});
                }
            }
        }

        // 익힐 수 있는 토마토를 다 익혔는데도 토마토가 남아있다면
        // 토마토가 모두 익지 못하는 상황(-1)
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
                    if (copyBox[i][j][k] == 0) {
                        minDays = -1;
                        break;
                    }
                }
            }
        }
    }
}
