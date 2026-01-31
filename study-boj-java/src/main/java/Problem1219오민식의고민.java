// https://www.acmicpc.net/problem/1219

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem1219오민식의고민 {
    static final long MIN = Long.MIN_VALUE;
    static final long MAX = Long.MAX_VALUE;
    static int N, S, E, M;
    static int earnings[];
    static long maxCost[];
    static EdgeProblem60[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 엣지의 가중치에 교통 수단의 교통비를 뺌.
        edges = new EdgeProblem60[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), toll = Integer.parseInt(st.nextToken());

            EdgeProblem60 edge = new EdgeProblem60(start, end, -toll); // 교통비로 돈을 잃는 거니까 -toll
            edges[i] = edge;
        }

        // 엣지의 가중치에 그 도시를 방문했을 때 생기는 수익을 더함.
        earnings = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int earning = Integer.parseInt(st.nextToken());
            earnings[i] = earning;
        }
        for (int i = 0; i < M; i++) {
            int end = edges[i].end;
            edges[i].cost += earnings[end];
        }

        maxCost = new long[N];
        Arrays.fill(maxCost, MIN);

        // 벨만 포드, 최장 거리(최단 거리 x)
        maxCost[S] = earnings[S];
        for (int i = 0; i < N - 1 + 100; i++) {
            for (int j = 0; j < M; j++) {
                EdgeProblem60 edge = edges[j];
                if (maxCost[edge.start] == MIN) {
                    continue;
                } else if (maxCost[edge.start] == MAX) {
                    maxCost[edge.end] = MAX;
                } else if (maxCost[edge.end] < maxCost[edge.start] + edge.cost) {
                    if (i >= N - 1) {
                        maxCost[edge.end] = MAX;
                    } else {
                        maxCost[edge.end] = maxCost[edge.start] + edge.cost;
                    }
                }
            }
        }

        if (maxCost[E] == MIN) {
            // 몇 가지 더 고려해야 할 거 같음
            System.out.println("gg");
        } else if (maxCost[E] == MAX) {
            System.out.println("Gee");
        } else {
            System.out.println(maxCost[E]);
        }
    }
}

class EdgeProblem60 {
    int start;
    int end;
    int cost;

    public EdgeProblem60(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}