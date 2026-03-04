import java.security.DrbgParameters;
import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14494
public class Problem14494다이나믹이뭐예요 {
    static int n, m;
    static int[][] dp;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(dp[n][m]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], 0);
        }
    }

    static void solve() {
        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // dp[0][j], dp[i][0]이 0 이라서,
                // isInMap을 적지 않아도 되지만 동작하지만
                // 맵 밖에 벗어난다는 걸 명시적으로 보여주기 위해 if 문 작성

                if (isInMap(i - 1, j)) {
                    dp[i][j] = (dp[i][j] +  dp[i - 1][j]) % MOD;
                }
                if (isInMap(i - 1, j - 1)) {
                    dp[i][j] = (dp[i][j] +  dp[i - 1][j - 1]) % MOD;
                }
                if (isInMap(i, j - 1)) {
                    dp[i][j] = (dp[i][j] +  dp[i][j - 1]) % MOD;
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        return (1 <= x && x <= n) &&
                (1 <= y && y <= m);
    }
}
