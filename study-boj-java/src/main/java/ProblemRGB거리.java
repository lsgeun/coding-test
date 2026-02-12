import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1149
public class ProblemRGB거리 {
    static int n;
    static int[][] cost;
    static final int R = 0, G = 1, B = 2;

    static int[][] dp;
    static int minCost;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(minCost);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int redCost = Integer.parseInt(st.nextToken());
            int greenCost = Integer.parseInt(st.nextToken());
            int blueCost = Integer.parseInt(st.nextToken());

            cost[i][R] = redCost;
            cost[i][G] = greenCost;
            cost[i][B] = blueCost;
        }

        dp = new int[n][3];
        minCost = Integer.MAX_VALUE;
    }

    static void solve() {
        dp[0][R] = cost[0][R];
        dp[0][G] = cost[0][G];
        dp[0][B] = cost[0][B];

        for (int i = 1; i < n; i++) {
            // 일단 i에 각 색으로 칠한 비용을 구하고,
            // 0 ~ i - 1까지 칠해진 비용 중 더 작은 비용을 더함
            dp[i][R] += cost[i][R];
            dp[i][R] += Math.min(dp[i - 1][G], dp[i - 1][B]);
            dp[i][G] += cost[i][G];
            dp[i][G] += Math.min(dp[i - 1][R], dp[i - 1][B]);
            dp[i][B] += cost[i][B];
            dp[i][B] += Math.min(dp[i - 1][R], dp[i - 1][G]);
        }

        minCost = Math.min(dp[n-1][R], dp[n-1][G]);
        minCost = Math.min(minCost, dp[n-1][B]);
    }
}
