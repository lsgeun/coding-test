import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16194
public class Problem16194카드구매하기2 {
    static int n;
    static int[] price;

    static int[] dp;
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

        price = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        minCost = Integer.MAX_VALUE;
    }

    static void solve() {

        // ** 이걸 알면 아래 for문을 좀 쉽게 이해할 수 있음 **
        // ** 이게 좀 더 이해하기 쉬운 알고리즘 **
        // 0개를 뽑을 때 최소 비용은 0이라 가정
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // j : 1 ~ i개가 들어있는 카드 팩을 뽑을 때의 최소 비용들 중에 가장 작은 비용으로 dp[i] 초기화
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], price[j] + dp[i - j]);
            }
        }

        // dp[1] = price[1];
        // for (int i = 1; i <= n; i++) {
        //     // i장을 한 팩으로 살 때 비용으로 초기화
        //     dp[i] = price[i];
        //     // (1, i - 1), ..., (floor(i / 2), i - floor(i / 2)) 쌍의 최소 비용들 중에, 가장 작은 비용으로 dp[i] 초기화
        //     // dp[j] + dp[i - j]는
        //     // 위 2중 for문의 (price[j] + dp[i - j]), (dp[j] + price[i - j])를 포함
        //     for (int j = 1; j <= i / 2; j++) {
        //         dp[i] = Math.min(dp[i], (dp[j] + dp[i - j]));
        //     }
        // }

        minCost = dp[n];
    }
}
