import java.io.*;
import java.util.*;

class Tile3xn타일링 {
    static long[] dp;
    static long totalCount;
    final static long MOD = 1_000_000_007L;

    public long solution(int n) {
        // 홀수이면 명백히 경우의 수가 없음
        if (n % 2 == 1) {
            totalCount = 0;
            return totalCount;
        } // n은 짝수

        setUp(n);

        // dp[i]는 메모이제이션된 dp[i - 2], dp[i - 4], ..., dp[0]을 사용하여 계산
        for (int i = 4; i <= n; i += 2) {
            // 마지막 2개에서 마지막 4개로 침범하지 않는 경우
            dp[i] = (dp[i - 2] * 3) % MOD;

            // 마지막 2개에서 마지막 4개로 침범하는 경우
            // 마지막 2개에서 마지막 4개, 마지막 6개, ..., 마지막 n개까지 차례대로 침범
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = (dp[i] + dp[j] * 2) % MOD;
            }
        }

        System.out.println(Arrays.toString(dp));

        totalCount = dp[n];

        return totalCount;
    }

    static void setUp(int n) {
        dp = new long[n + 1];
        // 사실 dp[0] == 0이지만, dp[0] == 1로 해야 계산이 자연스러움
        dp[0] = 1L;
        // dp[2]는 하나씩 세어보면 알 수 있음.
        dp[2] = 3L;

        totalCount = 0;
    }
}