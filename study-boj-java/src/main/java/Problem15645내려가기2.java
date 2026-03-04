import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem15645내려가기2 {
    static int n;
    static int[][] lines;
    static int[][][] dp;
    static int min, max;
    static final int MIN = 0, MAX = 1;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(max + " " + min);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        lines = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num0 =  Integer.parseInt(st.nextToken());
            int num1 =  Integer.parseInt(st.nextToken());
            int num2 =  Integer.parseInt(st.nextToken());

            lines[i][0] = num0;
            lines[i][1] = num1;
            lines[i][2] = num2;
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        dp = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][MIN] = Integer.MAX_VALUE;
                dp[i][j][MAX] = Integer.MIN_VALUE;
            }
        }
    }

    static void solve() {
        for (int i = 0; i < 3; i++) {
            dp[0][i][MIN] = lines[0][i];
            dp[0][i][MAX] = lines[0][i];
        }

        for (int line = 1; line <= n - 1; line++) {
            // dp[line][0]의 MIN, MAX 초기화
            dp[line][0][MIN] = Math.min(
                    dp[line][0][MIN],
                    dp[line - 1][0][MIN] + lines[line][0]);
            dp[line][0][MIN] = Math.min(
                    dp[line][0][MIN],
                    dp[line - 1][1][MIN] + lines[line][0]);

            dp[line][0][MAX] = Math.max(
                    dp[line][0][MAX],
                    dp[line - 1][0][MAX] + lines[line][0]);
            dp[line][0][MAX] = Math.max(
                    dp[line][0][MAX],
                    dp[line - 1][1][MAX] + lines[line][0]);

            // dp[line][1]의 MIN, MAX 초기화
            dp[line][1][MIN] = Math.min(
                    dp[line][1][MIN],
                    dp[line - 1][0][MIN] + lines[line][1]);
            dp[line][1][MIN] = Math.min(
                    dp[line][1][MIN],
                    dp[line - 1][1][MIN] + lines[line][1]);
            dp[line][1][MIN] = Math.min(
                    dp[line][1][MIN],
                    dp[line - 1][2][MIN] + lines[line][1]);

            dp[line][1][MAX] = Math.max(
                    dp[line][1][MAX],
                    dp[line - 1][0][MAX] + lines[line][1]);
            dp[line][1][MAX] = Math.max(
                    dp[line][1][MAX],
                    dp[line - 1][1][MAX] + lines[line][1]);
            dp[line][1][MAX] = Math.max(
                    dp[line][1][MAX],
                    dp[line - 1][2][MAX] + lines[line][1]);

            // dp[line][2]의 MIN, MAX 초기화
            dp[line][2][MIN] = Math.min(
                    dp[line][2][MIN],
                    dp[line - 1][1][MIN] + lines[line][2]);
            dp[line][2][MIN] = Math.min(
                    dp[line][2][MIN],
                    dp[line - 1][2][MIN] + lines[line][2]);

            dp[line][2][MAX] = Math.max(
                    dp[line][2][MAX],
                    dp[line - 1][1][MAX] + lines[line][2]);
            dp[line][2][MAX] = Math.max(
                    dp[line][2][MAX],
                    dp[line - 1][2][MAX] + lines[line][2]);
        }

        // 최대, 최소 구하기
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[n - 1][i][MIN]);
            max = Math.max(max, dp[n - 1][i][MAX]);
        }
    }
}
