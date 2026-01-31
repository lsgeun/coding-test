// dp

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14501
public class Problem14501퇴사 {
    public static int n;
    public static int[] t, p;
    public static int[] maxProfitUntil;
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        findMaxProfitUntil();
        System.out.println(maxProfitUntil[n]);
    }

    public static void setUp() throws IOException {
        n = Integer.parseInt(br.readLine());

        t = new int[n + 1];
        p = new int[n + 1];

        for (int node = 1; node <= n; node++) {
            st = new StringTokenizer(br.readLine());
            t[node] = Integer.parseInt(st.nextToken());
            p[node] = Integer.parseInt(st.nextToken());
        }

        maxProfitUntil = new int[n + 1];
        Arrays.fill(maxProfitUntil, Integer.MIN_VALUE);
    }

    public static void findMaxProfitUntil() {
        maxProfitUntil[0] = 0;

        for (int node = 1; node <= n; node++) {
            // 현재 일차의 상담을 선택하는 경우와 선택하지 않는 경우를 모두 따져서
            // 현재 일차까지의 최대값 확정.
            // 현재 일차의 상담을 선택하는 경우, t[node]가 1이면, 당일 바로 수익이 들어올 수 있음.
            maxProfitUntil[node] = Math.max(maxProfitUntil[node], maxProfitUntil[node - 1]);

            int nextNode = node + t[node] - 1;
            if (nextNode <= n) {
                maxProfitUntil[nextNode] = Math.max(maxProfitUntil[nextNode], maxProfitUntil[node - 1] + p[node]);
            }
        }
    }
}

// dfs

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
//
// // https://www.acmicpc.net/problem/14501
// public class Problem14501퇴사 {
//     public static int n;
//     public static int[] t, p;
//     public static int maxProfit;
//     public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     public static StringTokenizer st;
//
//     public static void main(String[] args) throws IOException {
//         setUp();
//         findMaxProfit();
//         System.out.println(maxProfit);
//     }
//
//     public static void setUp() throws IOException {
//         n = Integer.parseInt(br.readLine());
//
//         t = new int[n + 1];
//         p = new int[n + 1];
//
//         for (int node = 1; node <= n; node++) {
//             st = new StringTokenizer(br.readLine());
//             t[node] = Integer.parseInt(st.nextToken());
//             p[node] = Integer.parseInt(st.nextToken());
//         }
//
//         maxProfit = Integer.MIN_VALUE;
//     }
//
//     public static void findMaxProfit() {
//         _findMaxProfit(1,  0);
//     }
//
//     public static void _findMaxProfit(int node, int profit) {
//         if (node >= n + 1) {
//             maxProfit = Math.max(maxProfit, profit);
//             return;
//         }
//
//         if (node + t[node] - 1 <= n) {
//             _findMaxProfit(node + t[node], profit + p[node]);
//         }
//         _findMaxProfit(node + 1, profit);
//     }
// }
