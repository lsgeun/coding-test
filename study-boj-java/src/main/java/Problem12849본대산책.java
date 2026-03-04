import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/12849
public class Problem12849본대산책 {
    static int d;
    static int[] dp;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(dp[0]);
    }

    static void setUp() throws IOException {
        Scanner sc = new Scanner(System.in);
        d = sc.nextInt();

        dp = new int[8];
    }

    static void solve() {
        dp[0] = 1;

        for (int i = 0; i < d; i++) {
            int[] nextDP = new int[8];
            nextDP[0] = (dp[1] + dp[2]) % MOD;

            nextDP[1] = (dp[0] + dp[2]) % MOD;
            nextDP[1] = (nextDP[1] + dp[3]) % MOD;

            nextDP[2] = (dp[0] + dp[1]) % MOD;
            nextDP[2] = (nextDP[2] + dp[3]) % MOD;
            nextDP[2] = (nextDP[2] + dp[4]) % MOD;

            nextDP[3] = (dp[1] + dp[2]) % MOD;
            nextDP[3] = (nextDP[3] + dp[4]) % MOD;
            nextDP[3] = (nextDP[3] + dp[5]) % MOD;

            nextDP[4] = (dp[2] + dp[3]) % MOD;
            nextDP[4] = (nextDP[4] + dp[5]) % MOD;
            nextDP[4] = (nextDP[4] + dp[6]) % MOD;

            nextDP[5] = (dp[3] + dp[4]) % MOD;
            nextDP[5] = (nextDP[5] + dp[7]) % MOD;

            nextDP[6] = (dp[4] + dp[7]) % MOD;
            nextDP[7] = (dp[5] + dp[6]) % MOD;

            dp = nextDP;
        }
    }
}
