import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11724
public class Problem11724연결요소의개수 {
    static ArrayList<Integer>[] adjacentArr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        adjacentArr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjacentArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) { // 방향 없는 그래프라 양방향으로 인접리스트를 저장
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
            adjacentArr[start].add(end);
            adjacentArr[end].add(start);
        }
        br.close();

        int connectedComponentCount = 0;
        for (int node = 1; node <= N; node++) {
            if (visited[node] == false) {
                connectedComponentCount++;
                DFS(node);
            }
        }

        System.out.print(connectedComponentCount);
    }

    public static void DFS(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int adjacentNode : adjacentArr[node]) {
            if (visited[adjacentNode] == false) {
                DFS(adjacentNode);
            }
        }
    }
}
