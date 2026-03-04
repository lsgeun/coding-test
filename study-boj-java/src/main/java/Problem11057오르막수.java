import java.io.*;
import java.util.*;

public class Problem11057오르막수 {
    static int n;
    // int[길이][끝자리 수]
    static int[][] dp;
    static int count;
    static final int MOD = 10007;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(count);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        dp = new int[n + 1][10];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], 0);
        }

        count = 0;
    }

    static void solve() {
        // 길이가 1이면 num로 끝나는 수는 모두 1이다.
        for (int num = 0; num <= 9; num++) {
            dp[1][num] = 1;
        }

        // 길이가 2 ~ n인 dp를 구한다.
        for (int length = 1; length <= n - 1; length++) {
            for (int num = 0; num <= 9; num++) {
                for (int nextNum = num; nextNum <= 9; nextNum++) {
                    dp[length + 1][nextNum] = (dp[length][num] + dp[length + 1][nextNum]) % MOD;
                }
            }
        }

        // 길이가 n인 dp를 모두 더해 count를 구한다.
        for (int num = 0; num <= 9; num++) {
            count = (count + dp[n][num]) % MOD;
        }
    }
}
