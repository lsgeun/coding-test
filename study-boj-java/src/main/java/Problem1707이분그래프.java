import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1707
public class Problem1707이분그래프 {
    public static int k, v, e;
    public static ArrayList<Integer>[] adjacentList;
    public static int[] status;
    public static boolean isBipartite;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static final int UNVISITED = 0, SET_A = 1, SET_B = 2;

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            setUp();
            solve();
        }
        br.close();
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adjacentList = new ArrayList[v + 1];
        for (int i = 0; i < v + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            adjacentList[nodeA].add(nodeB);
            adjacentList[nodeB].add(nodeA);
        }

        status = new int[v + 1];
        Arrays.fill(status, UNVISITED);

        isBipartite = true;
    }

    public static void solve() {
        for (int node = 1; node <= v; node++) {
            if (status[node] == SET_A ||
                status[node] == SET_B) {
                continue;
            }

            makeBipartite(node);

            if (!isBipartite) {
                break;
            }
        }

        if (isBipartite) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void makeBipartite(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        status[startNode] = SET_A;
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int nextNode : adjacentList[curNode]) {
                if (status[nextNode] == UNVISITED) {
                    if (status[curNode] == SET_A) {
                        status[nextNode] = SET_B;
                    } else {
                        status[nextNode] = SET_A;
                    }
                    queue.offer(nextNode);
                } else if (status[nextNode] == status[curNode]) {
                    isBipartite = false;
                    break;
                } else { // status[nextNode] != status[curNode]
                    // 아무것도 안함
                }
            }

            if (!isBipartite) {
                break;
            }
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
//
// // https://www.acmicpc.net/problem/1707
// public class Problem1707이분그래프 {
//     static int K;
//     static ArrayList<Integer>[] adjacentList;
//     static boolean[] visited;
//     static int[] setType;
//     static boolean isBipartite;
//
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         String inputLine;
//         int blankIndex;
//
//         inputLine = br.readLine();
//         K = Integer.parseInt(inputLine);
//
//         for (int i = 0; i < K; i++) {
//             inputLine = br.readLine();
//             blankIndex = inputLine.indexOf(' ');
//             int V = Integer.parseInt(inputLine.substring(0, blankIndex)), E = Integer.parseInt(inputLine.substring(blankIndex + 1));
//
//             adjacentList = new ArrayList[V + 1];
//             for (int j = 1; j <= V; j++) {
//                 adjacentList[j] = new ArrayList<>();
//             }
//             for (int j = 0; j < E; j++) {
//                 inputLine = br.readLine();
//                 blankIndex = inputLine.indexOf(' ');
//                 int startNode = Integer.parseInt(inputLine.substring(0, blankIndex)), endNode = Integer.parseInt(inputLine.substring(blankIndex + 1));
//                 adjacentList[startNode].add(endNode);
//                 adjacentList[endNode].add(startNode);
//             }
//             visited = new boolean[V + 1];
//             setType = new int[V + 1];
//             isBipartite = true;
//
//             for (int j = 1; j <= V; j++) {
//                 DFS(j);
//
//                 // isBipartite == false가 되어 여러 DFS가 즉시 종료되는 순간 for문도 종료됨.
//                 if (!isBipartite) {
//                     break;
//                 }
//             }
//
//             if (isBipartite) {
//                 System.out.println("YES");
//             } else {
//                 System.out.println("NO");
//             }
//         }
//     }
//
//     public static void DFS(int curNode) {
//         // 방문하지 않은 경우 nextNode는 curNode와 다른 집합에 속함.
//         // 방문한 경우 nextNode는 curNode가 다른 집합에 속할 수도, 같은 집합에 속할 수도 있음.
//         // nextNode가 같은 집합에 속할 때 isBipartite == false가 되어, 스택에 쌓여있던 여러 DFS 메서드는 그 즉시 반환됨.
//         visited[curNode] = true;
//
//         for (int nextNode : adjacentList[curNode]) {
//             if (!isBipartite) {
//                 return;
//             }
//
//             if (!visited[nextNode]) {
//                 setType[nextNode] = (setType[curNode] + 1) % 2;
//                 DFS(nextNode);
//             }
//             if (visited[nextNode]) {
//                 if (setType[curNode] == setType[nextNode]) {
//                     isBipartite = false;
//                 }
//             }
//         }
//     }
// }
