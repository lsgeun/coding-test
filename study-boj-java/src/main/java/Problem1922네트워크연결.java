import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1922
public class Problem1922네트워크연결 {
    public static int n, m;
    public static int[] parent;
    public static int[] rank;
    public static int[][] edgeList;
    public static int minMSTWeight;

    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static final int NODE_A = 0, NODE_B = 1, WEIGHT_C = 2;

    public static void main(String[] args) throws IOException {
        setUp();
        findMinMSTWeight();
        System.out.println(minMSTWeight);
    }

    public static void setUp() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int node = 1; node <= n; node++) {
            parent[node] = node;
        }

        rank = new int[n + 1];
        Arrays.fill(rank, 0);

        edgeList = new int[m][3];
        for (int edgeIndex = 0; edgeIndex < m; edgeIndex++) {
            st = new StringTokenizer(br.readLine());
            edgeList[edgeIndex][NODE_A] = Integer.parseInt(st.nextToken());
            edgeList[edgeIndex][NODE_B] = Integer.parseInt(st.nextToken());
            edgeList[edgeIndex][WEIGHT_C] = Integer.parseInt(st.nextToken());
        }

        minMSTWeight = 0;

        br.close();
    }

    public static int find(int node) {
        Stack<Integer> stack = new Stack<>();

        while (node != parent[node]) {
            stack.add(node);
            node = parent[node];
        }

        int setNode = node;

        while (!stack.isEmpty()) {
            int curNode = stack.pop();
            parent[curNode] = setNode;
        }

        return setNode;
    }

    public static void union(int nodeA, int nodeB) {
        int setNodeA = find(nodeA);
        int setNodeB = find(nodeB);

        if (rank[setNodeA] > rank[setNodeB]) {
            parent[setNodeB] = setNodeA;
        } else if (rank[setNodeA] < rank[setNodeB]) {
            parent[setNodeA] = setNodeB;
        } else { // rank[setNodeA] == rank[setNodeB]
            parent[setNodeB] = setNodeA;
            rank[setNodeA]++;
        }
    }

    public static void findMinMSTWeight() {
        Arrays.sort(edgeList, Comparator.comparingInt(edge -> edge[WEIGHT_C]));

        int edgeCount = 0;
        for (int edgeIndex = 0; edgeIndex < m; edgeIndex++) {
            if (find(edgeList[edgeIndex][NODE_A]) == find(edgeList[edgeIndex][NODE_B])) {
                continue;
            }

            union(edgeList[edgeIndex][NODE_A], edgeList[edgeIndex][NODE_B]);
            edgeCount++;
            minMSTWeight += edgeList[edgeIndex][WEIGHT_C];

            if (edgeCount == n - 1) {
                break;
            }
        }
    }
}
