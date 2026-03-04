import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/13699
public class Problem13699점화식 {
    static int n;
    static long[] dp;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(dp[n]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp = new long[n + 1];
        Arrays.fill(dp, 0L);
    }

    static void solve() {
        Arrays.fill(dp, 0L);

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // j는 0 ~ i - 1까지 순회
            for (int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
    }
}
