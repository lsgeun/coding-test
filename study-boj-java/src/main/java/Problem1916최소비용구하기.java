import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1916
public class Problem1916최소비용구하기 {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static ArrayList<EdgeProblem57>[] adjacentList;
    static long[] minDistance;
    static boolean[] visited;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());

            adjacentList[start].add(new EdgeProblem57(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        br.close();

        minDistance = new long[N + 1];
        for (int i = 0; i < N + 1; i++) {
            minDistance[i] = INF;
        }
        visited = new boolean[N + 1];

        dijkstra();

        System.out.println(minDistance[end]);
    }

    public static void dijkstra() {
        PriorityQueue<PathProblem57> priorityQueue = new PriorityQueue<>(); // 기본은 최소힙
        priorityQueue.add(new PathProblem57(start, 0));
        minDistance[start] = 0;

        while (!priorityQueue.isEmpty()) {
            PathProblem57 curPath = priorityQueue.poll();
            int curCity = curPath.city;
            if (visited[curCity]) {
                continue;
            }

            visited[curCity] = true;

            for (EdgeProblem57 nextEdge : adjacentList[curCity]) {
                int nextCity = nextEdge.city, nextCost = nextEdge.cost;

                long oldMinDistance = minDistance[nextCity], newMinDistance = minDistance[curCity] + nextCost;
                if (oldMinDistance > newMinDistance) {
                    minDistance[nextCity] = newMinDistance;
                    priorityQueue.add(new PathProblem57(nextCity, newMinDistance));
                }
            }
        }

    }
}

class EdgeProblem57 {
    // adjacent 배열의 첫번째 배열의 요소가 출발지
    int city; // 도착지
    int cost;

    public EdgeProblem57(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }
}

class PathProblem57 implements Comparable<PathProblem57> {
    // start가 출발지
    int city; // 도착지
    long cost;

    public PathProblem57(int city, long cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(PathProblem57 p) {
        if (this.cost > p.cost) {
            return 1;
        } else if (this.cost < p.cost) {
            return -1;
        } else {
            return 0;
        }
    }
}