import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1865
public class Problem1865웜홀 {
    static int tc;
    static int n, m, w;
    static int[] minTime;
    static final int INF = Integer.MAX_VALUE;
    static int[][] edgeList;
    static final int START = 0, END = 1, TIME = 2;

    static BufferedReader br;


    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    static void setUp() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException {
        StringTokenizer st;
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edgeList = new int[2 * m + w + n][3];

            int j = 0;
            // 양방향 도로 추가
            while (j < m) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edgeList[2 * j][START] = start;
                edgeList[2 * j][END] = end;
                edgeList[2 * j][TIME] = time;
                edgeList[2 * j + 1][START] = end;
                edgeList[2 * j + 1][END] = start;
                edgeList[2 * j + 1][TIME] = time;

                j++;
            }

            // 웜홀 추가
            int startIndex = 2 * j;
            int k = 0;
            while (k < w) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edgeList[startIndex + k][START] = start;
                edgeList[startIndex + k][END] = end;
                edgeList[startIndex + k][TIME] = -time;

                k++;
            }

            // 가상 노드 도로 추가
            startIndex = 2 * j + k;
            for (int l = 0; l < n; l++) {
                int node = l + 1;
                edgeList[startIndex + l][START] = 0;
                edgeList[startIndex + l][END] = node;
                edgeList[startIndex + l][TIME] = 0;
            }

            if (existsNegativeCycle()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    static boolean existsNegativeCycle() {
        boolean existsNegativeCycle = false;
        // 가상 노드 0 추가
        minTime = new int [n + 1];
        // 0개 이하의 엣지를 가진 경로의 최단 거리 초기화
        Arrays.fill(minTime, INF);
        // 가상 노드를 시작점으로
        minTime[0] = 0;

        // 가상 노드 포함한 전체 노드의 수 == n + 1
        for (int i = 0; i < n + 1 - 1; i++) {
            for (int[] edge : edgeList) {
                if (minTime[edge[START]] == INF) {
                    continue;
                }

                // minTime 최대값은 5000만, 최소값 -200만
                if (minTime[edge[END]] > minTime[edge[START]] + edge[TIME]) {
                    minTime[edge[END]] = minTime[edge[START]] + edge[TIME];
                }
            }
        }

        for (int[] edge : edgeList) {
            if (minTime[edge[START]] == INF) {
                continue;
            }

            // minTime 최대값은 5000만
            if (minTime[edge[END]] > minTime[edge[START]] + edge[TIME]) {
                existsNegativeCycle = true;
                break;
            }
        }

        return existsNegativeCycle;
    }
}
