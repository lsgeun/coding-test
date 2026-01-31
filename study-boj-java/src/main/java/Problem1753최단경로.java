import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
public class Problem1753최단경로 {
    static int V, E, S;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<EdgeProblem56>[] adjacentList;
    static int[] minDistance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());

        adjacentList = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());

            adjacentList[start].add(new EdgeProblem56(end, weight));
        }
        minDistance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            minDistance[i] = INF;
        }
        visited = new boolean[V + 1];

        dijkstra();

        for (int node = 1; node <= V; node++) {
            if (minDistance[node] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(minDistance[node]);
            }
        }

    }

    public static void dijkstra() {
        // ** BFS 동작이랑 완전 다름 **
        PriorityQueue<EdgeProblem56> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new EdgeProblem56(S, 0));
        minDistance[S] = 0;

        while (!priorityQueue.isEmpty()) {
            EdgeProblem56 curEdge = priorityQueue.poll();
            int curNode = curEdge.node;
            if (visited[curNode]) {
                continue;
            }

            visited[curNode] = true;

            for (EdgeProblem56 nextEdge : adjacentList[curNode]) {
                int nextNode = nextEdge.node;
                int nextWeight = nextEdge.weight;

                if (minDistance[curNode] + nextWeight < minDistance[nextNode]) {
                    // 여기서 최소힙에 넣는 Edge는 현재 출발노드에서 최소 거리가 정해지지 않은 노드까지의 경로의 거리를 넣은 것.
                    // (노드, 엣지의 가중치)가 아닌 (노드, 경로의 거리)를 넣은 것
                    // 이들 중 경로의 거리가 가장 작은 노드까지의 거리를 최종적으로 최소의 거리로 정해질 것이기 때문.

                    // 현재 최소거리보다 더 작아질 때 최소힙에 Edge를 추가하는데
                    // 이는 최소 거리가 무한인 노드를 추가하지 않게 함. 추가한다면 오버플로우로 음의 거리가 나올 수도 있음.
                    minDistance[nextNode] = minDistance[curNode] + nextWeight;
                    priorityQueue.add(new EdgeProblem56(nextNode, minDistance[nextNode]));
                }
            }
        }

    }
}

class EdgeProblem56 implements Comparable<EdgeProblem56> {
    int node;
    int weight;

    public EdgeProblem56(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeProblem56 e) {
        if (this.weight > e.weight) {
            return 1;
        } else {
            return -1;
        }
    }
}