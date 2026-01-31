import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11725
public class Problem11725트리의부모찾기 {
    public static int n;
    public static ArrayList<Integer>[] adjacentList;
    public static boolean[] visited;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adjacentList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            adjacentList[nodeA].add(nodeB);
            adjacentList[nodeB].add(nodeA);
        }

        visited = new boolean[n + 1];
        parent = new int[n + 1];

        findParentBFS();

        // Arrays.fill(visited, false);
        // Arrays.fill(parent, 0);
        // findParentDFS(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) { // 루트(1) 제외
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);

        br.close();
    }

    public static void findParentBFS() {
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(1);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int nextNode : adjacentList[curNode]) {
                if (visited[nextNode]) {
                    continue;
                }

                parent[nextNode] = curNode;
                visited[nextNode] = true;
                queue.offer(nextNode);
            }
        }
    }

    public static void findParentDFS(int curNode, int parentNode) {
        visited[curNode] = true;
        parent[curNode] = parentNode;

        for (int nextNode : adjacentList[curNode]) {
            if (visited[nextNode]) {
                continue;
            }

            findParentDFS(nextNode, curNode);
        }
    }
}
