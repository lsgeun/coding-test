import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14606
public class Problem14606피자Small {
    static int n;
    static int[] dp;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(dp[n]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp = new int[n + 1];
        Arrays.fill(dp, 0);
    }

    static void solve() {
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                // i 길이의 탑을 분리할 때의 최대 즐거움을 더하고
                dp[i] += (i / 2) * (i / 2);
                // 분리된 i / 2 길이의 탑 2개를 각각 분리할 때의 최대 즐거움도 더한다.
                dp[i] += dp[i / 2];
                dp[i] += dp[i / 2];
            } else { // i % 2 == 1
                // i 길이의 탑을 분리할 때의 최대 즐거움을 더하고
                dp[i] += ((i - 1) / 2) * ((i + 1) / 2);
                // 분리된 (i - 1) / 2 길이의 탑, (i + 1) / 2 길이의 탑을 각각 분리할 때의 최대 즐거움도 더한다.
                dp[i] += dp[(i - 1) / 2];
                dp[i] += dp[(i + 1) / 2];
            }
        }
    }
}
