// BFS로 최단 거리 구하기
// https://www.acmicpc.net/problem/18352
// public class Problem18352특정거리의도시찾기 {
//     public static int n, m, k, x;
//     public static ArrayList<Integer>[] graph;
//     public static int[] minDistances;
//
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//
//         n = Integer.parseInt(st.nextToken());
//         m = Integer.parseInt(st.nextToken());
//         k = Integer.parseInt(st.nextToken());
//         x = Integer.parseInt(st.nextToken());
//
//         graph = new ArrayList[n + 1];
//         for (int i = 0; i <= n; i++) {
//             graph[i] = new ArrayList<>();
//         }
//         for (int i = 0; i < m; i++) {
//             st = new StringTokenizer(br.readLine());
//             int start = Integer.parseInt(st.nextToken());
//             int end = Integer.parseInt(st.nextToken());
//
//             graph[start].add(end);
//         }
//         br.close();
//
//         minDistances = new int[n + 1];
//         Arrays.fill(minDistances, Integer.MAX_VALUE);
//
//         findMinDistances();
//
//         ArrayList<Integer> nodeDistanceK = new ArrayList<>();
//         for (int node = 1; node <= n; node++) {
//             if (minDistances[node] == k) {
//                 nodeDistanceK.add(node);
//             }
//         }
//         if (nodeDistanceK.isEmpty()) {
//             System.out.println(-1);
//         } else {
//             for (int node : nodeDistanceK) {
//                 System.out.println(node);
//             }
//         }
//     }
//
//     public static void findMinDistances() {
//         Queue<Integer> queue = new LinkedList<>();
//         minDistances[x] = 0;
//         queue.add(x);
//
//         while (!queue.isEmpty()) {
//             int curNode = queue.poll();
//
//             for (int nextNode : graph[curNode]) {
//                 if (doesVisit(nextNode)) {
//                     continue;
//                 }
//
//                 minDistances[nextNode] = minDistances[curNode] + 1;
//                 queue.add(nextNode);
//             }
//         }
//     }
//
//     public static boolean doesVisit(int node) {
//         if (minDistances[node] != Integer.MAX_VALUE) {
//             return true;
//         } else {
//             return false;
//         }
//     }
// }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 오버헤드가 있음, 오랜만에 다익스트라 구현을 해봄. 틀림.
public class Problem18352특정거리의도시찾기 {
    public static int n, m, k, x;
    public static ArrayList<int[]>[] graph;
    public static int[] minDistances;
    public static final int N = 0, D = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int[] endEdge = new int[]{end, 1};
            graph[start].add(endEdge);
        }
        br.close();

        minDistances = new int[n + 1];
        Arrays.fill(minDistances, Integer.MAX_VALUE);

        findMinDistances();

        ArrayList<Integer> nodeDistanceK = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (minDistances[i] == k) {
                nodeDistanceK.add(i);
            }
        }
        if (nodeDistanceK.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int node : nodeDistanceK) {
                System.out.println(node);
            }
        }

    }

    public static void findMinDistances() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[D]));
        int[] startDirectEdge = new int[]{x, 0};
        minDistances[startDirectEdge[N]] = startDirectEdge[D];
        pq.add(startDirectEdge);

        while (!pq.isEmpty()) {
            int[] curDirectEdge = pq.poll();

            if (minDistances[curDirectEdge[N]] < curDirectEdge[D]) {
                continue;
            }

            for (int[] nextEdge : graph[curDirectEdge[N]]) {
                int[] nextDirectEdge = new int[]{nextEdge[N], curDirectEdge[D] + nextEdge[D]};

                if (minDistances[nextDirectEdge[N]] > nextDirectEdge[D]) {
                    minDistances[nextDirectEdge[N]] = nextDirectEdge[D];
                    pq.add(nextDirectEdge);
                }
            }
        }
    }
}

// 책풀이
// public class Problem18352특정거리의도시찾기 {
//     static int N, M, K, X;
//     static ArrayList<Integer> answer;
//     static ArrayList<Integer>[] A;
//     static boolean[] visited;
//     static int[] minDistance;
//
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         K = Integer.parseInt(st.nextToken());
//         X = Integer.parseInt(st.nextToken());
//
//         A = new ArrayList[N + 1];
//         for (int i = 1; i <= N; i++) {
//             A[i] = new ArrayList<>();
//         }
//         for (int i = 0; i < M; i++) {
//             String line = br.readLine();
//             int blankIndex = line.indexOf(" ");
//             int s = Integer.parseInt(line.substring(0, blankIndex));
//             int e = Integer.parseInt(line.substring(blankIndex + 1));
//             A[s].add(e);
//         }
//         br.close();
//
//         answer = new ArrayList<>();
//         visited = new boolean[N + 1];
//         minDistance = new int[N + 1];
//
//         BFS(X);
//
//         for (int i = 1; i <= N; i++) {
//             if (minDistance[i] == K) {
//                 answer.add(i);
//             }
//         }
//         if (answer.isEmpty()) {
//             System.out.println("-1");
//         } else {
//             Collections.sort(answer);
//             for (int a : answer) {
//                 System.out.println(a);
//             }
//         }
//     }
//
//     static public void BFS(int node) {
//         Queue<Integer> queue = new LinkedList<Integer>();
//         queue.add(node);
//         visited[node] = true;
//         while (!queue.isEmpty()) {
//             int curNode = queue.poll();
//
//             for (int nextNode : A[curNode]) {
//                 if (!visited[nextNode]) {
//                     visited[nextNode] = true;
//                     minDistance[nextNode] = minDistance[curNode] + 1;
//                     queue.add(nextNode);
//                 }
//             }
//         }
//     }
// }
