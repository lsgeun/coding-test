import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9507
public class Problem9507GenerationsofTribbles {
    static int t;
    static int n;
    static long[] dp;

    static Scanner sc;

    public static void main(String[] args) {
        setUp();

        solve();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();

            System.out.println(dp[n]);
        }
    }

    static void setUp() {
        sc = new Scanner(System.in);

        t = sc.nextInt();

        dp = new long[67 + 1];
        Arrays.fill(dp, 0);
    }

    static void solve() {
        dp[0] = 1L;
        dp[1] = 1L;
        dp[2] = 2L;
        dp[3] = 4L;

        for (int i = 4; i <= 67; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
        }
    }
}
