import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1197
public class Problem1197최소스패닝트리 {
    public static int v, e;
    public static int[][] edgeList;
    public static int[] parent;
    public static int[] rank;
    public static long mstWeight;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static final int NODE_A = 0, NODE_B = 1, WEIGHT_C = 2;

    public static void main(String[] args) throws IOException {
        setUp();
        findMSTWeight();
        System.out.println(mstWeight);
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        edgeList = new int[e][3];
        for (int edgeIndex = 0; edgeIndex < e; edgeIndex++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int weightC = Integer.parseInt(st.nextToken());

            edgeList[edgeIndex][NODE_A] = nodeA;
            edgeList[edgeIndex][NODE_B] = nodeB;
            edgeList[edgeIndex][WEIGHT_C] = weightC;
        }

        parent = new int[v + 1];
        for (int node = 1; node <= v; node++) {
            parent[node] = node;
        }

        rank = new int[v + 1];
        Arrays.fill(rank, 0);

        br.close();
    }

    public static void findMSTWeight() {
        Arrays.sort(edgeList, Comparator.comparingInt(edge -> edge[WEIGHT_C]));

        int edgeCount = 0;
        for (int[] edge : edgeList) {
            int setNodeA = find(edge[NODE_A]);
            int setNodeB = find(edge[NODE_B]);

            if (setNodeA == setNodeB) {
                continue;
            }

            union(setNodeA, setNodeB);
            mstWeight += edge[WEIGHT_C];
            edgeCount++;

            if (edgeCount == v - 1) {
                break;
            }
        }
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

        if (setNodeA == setNodeB) {
            return;
        }

        if (rank[setNodeA] > rank[setNodeB]) {
            parent[setNodeB] = setNodeA;
        } else if (rank[setNodeA] < rank[setNodeB]) {
            parent[setNodeA] = setNodeB;
        } else {
            parent[setNodeB] = setNodeA;
            rank[setNodeA]++;
        }
    }
}
