import java.io.*;
import java.util.*;

public class Problem1932정수삼각형 {
    static int n;
    static int[] triangle;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(max);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        triangle = new int[n * (n + 1) / 2];
        int index = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                triangle[index] = Integer.parseInt(st.nextToken());
                index += 1;
            }
        }

        dp = new int[n * (n + 1) / 2];
        Arrays.fill(dp, Integer.MIN_VALUE);

        max = Integer.MIN_VALUE;
    }

    static void solve() {
        dp[0] = triangle[0];

        for (int floor = 2; floor <= n; floor++) {
            for (int order = 1; order <= floor; order++) {
                int index = getIndex(floor, order);
                int firstOrder = 1;
                int lastOrder = floor;

                // 이전 층 왼쪽 dp와 현재 층 order 번째 triangle의 합으로 최대값 갱신
                if (order >= firstOrder + 1) {
                    int previousFloorLeftIndex = getIndex(floor - 1, order - 1);
                    dp[index] = Math.max(dp[index], dp[previousFloorLeftIndex] + triangle[index]);
                }
                // 이전 층 오른쪽 dp와 현재 층 order 번째 triangle의 합으로 최대값 갱신
                if (order <= lastOrder - 1) {
                    int previousFloorLeftIndex = getIndex(floor - 1, order);
                    dp[index] = Math.max(dp[index], dp[previousFloorLeftIndex] + triangle[index]);
                }
            }
        }

        // dp의 마지막 층(n)에서 max 값을 구함
        for (int order = 1; order <= n; order++) {
            int floor = n;
            int index = getIndex(floor, order);
            max = Math.max(max, dp[index]);
        }
    }

    static int getIndex(int floor, int order) {
        return ((floor - 1) * (floor - 1 + 1) / 2) + (order - 1);
    }
}
