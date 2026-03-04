import java.io.IOException;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9095
public class Problem9095123더하기 {
    static Scanner sc;
    static int t;
    static int n;

    static int[] dp;

    public static void main(String[] args) {
        setUp();

        solve();
    }

    static void setUp() {
        sc = new Scanner(System.in);
        t = sc.nextInt();

        dp = new int[10 + 1];
    }

    static void solve() {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= 10; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
