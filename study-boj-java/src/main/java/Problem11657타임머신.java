import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11657
public class Problem11657타임머신 {
    static final int MAX = Integer.MAX_VALUE;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static long[] minDistance;
    static EdgeProblem59[] edges;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new EdgeProblem59[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new EdgeProblem59(start, end, time);
        }

        minDistance = new long[N + 1];
        Arrays.fill(minDistance, MAX);

        // 벨만-포드 알고리즘
        minDistance[1] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                EdgeProblem59 edge = edges[j];
                if (minDistance[edge.start] == MAX) {
                    continue;
                }

                if (minDistance[edge.end] > minDistance[edge.start] + edge.time) {
                    minDistance[edge.end] = minDistance[edge.start] + edge.time;
                }
            }
        }

        // 음의 사이클 존재 여부 파악
        boolean minusCycle = false;
        for (int i = 0; i < M; i++) {
            EdgeProblem59 edge = edges[i];
            if (minDistance[edge.start] == MAX) {
                continue;
            }

            if (minDistance[edge.end] > minDistance[edge.start] + edge.time) {
                minusCycle = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (minusCycle) {
            sb.append("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (minDistance[i] == MAX) {
                    sb.append("-1").append("\n");
                } else {
                    sb.append(minDistance[i]).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

class EdgeProblem59 {
    int start, end, time;

    public EdgeProblem59(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}