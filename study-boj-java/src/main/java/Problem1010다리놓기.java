import java.io.*;
import java.util.*;

public class Problem1010다리놓기 {
    static int t;
    static int n, m;
    static long[][] dp;

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        setUp();

        calculateDp();

        solve();
    }

    static void setUp() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        dp = new long[29 + 1][29 + 1];

        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], 0L);
        }
    }

    static void calculateDp() {
        for (int i = 1; i <= 29; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= 29; i++) {
            for (int j = i; j <= 29; j++) {
                for (int k = j - 1; k >= i - 1; k--) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
    }

    static void solve() throws IOException {
        StringTokenizer st;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            System.out.println(dp[n][m]);
        }
    }
}
