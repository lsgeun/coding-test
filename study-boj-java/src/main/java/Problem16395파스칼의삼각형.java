import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/16395
public class Problem16395파스칼의삼각형 {
    static int n, k;
    static int[][] dp;
    public static void main(String[] args) {
        setUp();

        solve();

        // 문제는 1행(열)부터, dp는 0행(열)부터 시작하므로 n - 1, r - 1로 해야 제대로 된 정답이 됨
        System.out.println(dp[n - 1][k - 1]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], 0);
        }
    }

    static void solve() {
        dp[0][0] = 1;
        dp[1][1] = 1;
        dp[1][0] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = i; j >= 0; j--) {
                if ((j == i) || (j == 0)){
                    dp[i][j] = 1;
                } else { // 1 <= j <= i - 1
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
    }
}
