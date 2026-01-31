import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11437
public class Problem11437LCA {
    public static int n, m;
    public static ArrayList<Integer>[] adjacentList;
    public static boolean[] visited;
    public static int[] parent;
    public static int[] depth;

    public static final int END = 0, ROOT = 1;
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

        visited = new boolean[n + 1];
        parent = new int[n + 1];
        parent[ROOT] = END;
        depth = new int[n + 1];
        findParent();

        findTestCaseLCAAndPrint();

        br.close();
    }

    public static void findParent() {
        Queue<Integer> queue = new LinkedList<>();
        depth[1] = 1;
        visited[1] = true;
        queue.offer(1);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            int curDepth = depth[curNode];

            for (int nextNode : adjacentList[curNode]) {
                if (visited[nextNode]) {
                    continue;
                }

                int nextDepth = curDepth + 1;
                depth[nextNode] = nextDepth;
                parent[nextNode] = curNode;
                visited[nextNode] = true;
                queue.offer(nextNode);
            }
        }
    }

    public static void findTestCaseLCAAndPrint() throws IOException {
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int lca = ROOT;

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            if (depth[nodeA] > depth[nodeB]) {
                while (true) {
                    nodeA = parent[nodeA];

                    if (depth[nodeA] == depth[nodeB]) {
                        break;
                    }
                }
            } else if (depth[nodeA] < depth[nodeB]) {
                while (true) {
                    nodeB = parent[nodeB];

                    if (depth[nodeA] == depth[nodeB]) {
                        break;
                    }
                }
            }

            while (true) {
                if (nodeA == nodeB) {
                    lca = nodeA;
                    break;
                } else { // nodeA != nodeB
                    nodeA = parent[nodeA];
                    nodeB = parent[nodeB];
                }
            }

            sb.append(lca).append("\n");
        }

        System.out.println(sb);
    }
}
