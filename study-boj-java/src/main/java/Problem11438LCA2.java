import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11438
public class Problem11438LCA2 {
    public static int n, m;
    public static ArrayList<Integer>[] adjacentList;
    public static boolean[] visited;
    public static int[] depth;
    public static int[][] parent;
    public static int maxHeight;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        adjacentList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            adjacentList[nodeA].add(nodeB);
            adjacentList[nodeB].add(nodeA);
        }

        maxHeight = (int) Math.floor(Math.log(n) / Math.log(2)); // log(n-1) 대신 lon(n)을 쓴 것은 부동소수점 에러 방지.
        parent = new int[n + 1][maxHeight + 1 + 1]; // 1을 더 더한 것은 개발자 실수 방지.
        visited = new boolean[n + 1];
        depth = new int[n + 1];
        findParent();

        findTestCaseLCAAndPrint();

        br.close();
    }

    public static void findParent() {
        // parent가 모두 0이라고 가정.
        // 알고리즘이 끝나도, 0(노드 없음), 1(루트 노드)에 대한 parent[0 or 1][k] == 0임.
        Queue<Integer> queue = new LinkedList<>();
        parent[1][0] = 0;
        depth[1] = 0;
        visited[1] = true;
        queue.offer(1);

        // parent[i][0] 초기화
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            int curDepth = depth[curNode];

            for (int nextNode : adjacentList[curNode]) {
                if (visited[nextNode]) {
                    continue;
                }

                int nextDepth = curDepth + 1;
                parent[nextNode][0] = curNode;
                depth[nextNode] = nextDepth;
                visited[nextNode] = true;
                queue.offer(nextNode);
            }
        }

        // dp, 부모를 따라 옮겨가는 이미지로 이해하면 좀 쉬움.
        // parent[i][1 ~ maxHeight( == floor(log2(n-1)))] 초기화.
        for (int height = 1; height <= maxHeight; height++) {
            for (int node = 1; node <= n; node++) {
                parent[node][height] = parent[parent[node][height - 1]][height - 1];
            }
        }
    }

    public static void findTestCaseLCAAndPrint() throws IOException {
        m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int lca = 1;

            st = new StringTokenizer(br.readLine());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            // nodeA depth를 더 깊거나 동일한 깊이로 설정.
            if (depth[nodeA] < depth[nodeB]) {
                int temp = nodeA;
                nodeA = nodeB;
                nodeB = temp;
            }

            // depth 맞추기
            for (int height = maxHeight; height >= 0; height--) {
                if (Math.pow(2, height) <= depth[nodeA] - depth[nodeB]) {
                    nodeA = parent[nodeA][height];
                }
            }

            if (nodeA == nodeB) {
                lca = nodeA;
                sb.append(lca).append('\n');
                continue;
            }

            // lca 구하기, nodeA != nodeB
            for (int height = maxHeight; height >= 0; height--) {
                if (parent[nodeA][height] != parent[nodeB][height]) {
                    nodeA = parent[nodeA][height];
                    nodeB = parent[nodeB][height];
                }
            }

            lca = parent[nodeA][0];
            sb.append(lca).append('\n');
        }

        System.out.println(sb);
    }
}
