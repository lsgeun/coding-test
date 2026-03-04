import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/25418
public class Problem25418정수a를k로만들기 {
    static int a, k;
    static int[] dp;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(dp[k]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        k = sc.nextInt();

        dp = new int[1000000 + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
    }

    static void solve() {
        dp[a] = 0;

        for (int i = a + 1; i <= k; i++) {
            if (i % 2 == 0) {
                if (i / 2 >= a) {
                    // 2 곱하기
                    dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                }
            }
            // 1 더하기
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
    }
}
