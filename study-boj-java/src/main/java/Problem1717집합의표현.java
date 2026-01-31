import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1717
public class Problem1717집합의표현 {
    public static int n, m;
    public static int[] parent;
    public static int[] rank;
    public static int[] size;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        initField();
        solve();

        br.close();
    }

    public static void initField() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int node = 0; node <= n; node++) {
            parent[node] = node;
        }

        size = new int[n + 1];
        Arrays.fill(size, 1);

        rank = new int[n + 1];
        Arrays.fill(rank, 0);
    }

    public static void solve() throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            if (operation == 0) {
                unionSize(nodeA, nodeB);
            } else if (operation == 1) {
                int setNodeA = find(nodeA);
                int setNodeB = find(nodeB);

                if (setNodeA == setNodeB) {
                    sb.append("YES").append('\n');
                } else {
                    sb.append("NO").append('\n');
                }
            }
        }

        System.out.println(sb);
    }

    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        Stack<Integer> stack = new Stack<>();
        while (parent[node] != node) {
            stack.add(node);
            node = parent[node];
        }

        // 경로 단축
        int setNode = node;
        while (!stack.isEmpty()) {
            int curElement = stack.pop();
            parent[curElement] = setNode;
        }

        return setNode;
    }

    public static void unionRank(int nodeA, int nodeB) {
        int setNodeA = find(nodeA);
        int setNodeB = find(nodeB);

        if (setNodeA == setNodeB) {
            return;
        }

        if (rank[setNodeA] > rank[setNodeB]) {
            parent[setNodeB] = parent[setNodeA];
        } else if (rank[setNodeA] < rank[setNodeB]) {
            parent[setNodeA] = parent[setNodeB];
        } else {
            parent[setNodeB] = setNodeA;
            rank[setNodeA]++;
        }
    }

    public static void unionSize(int nodeA, int nodeB) {
        int setNodeA = find(nodeA);
        int setNodeB = find(nodeB);

        if (setNodeA == setNodeB) {
            return;
        }

        if (size[setNodeA] > size[setNodeB]) {
            parent[setNodeB] = setNodeA;
            size[setNodeA] += size[setNodeB];
        } else if (size[setNodeA] < size[setNodeB]) {
            parent[setNodeA] = setNodeB;
            size[setNodeB] += size[setNodeA];
        } else {
            parent[setNodeB] = setNodeA;
            size[setNodeA] += size[setNodeB];
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayDeque;
//
// // https://www.acmicpc.net/problem/1717
// public class Problem1717집합의표현 {
//     static int N, M;
//     // 대표 노드는 i번째 노드의 인덱스가 i인 경우. 즉, nodeIndex[i] = i
//     static int[] nodeIndex;
//
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         String inputLine = br.readLine();
//         // 속도를 높이기 위해, 문자열의 indexOf()와 substring() 사용
//         int blankIndex = inputLine.indexOf(' ');
//         N = Integer.parseInt(inputLine.substring(0, blankIndex));
//         M = Integer.parseInt(inputLine.substring(blankIndex + 1));
//
//         nodeIndex = new int[N + 1];
//         for (int i = 0; i < N + 1; i++) {
//             nodeIndex[i] = i;
//         }
//
//         int firstBlankIndex, secondBlankIndex;
//         for (int i = 0; i < M; i++) {
//             inputLine = br.readLine();
//             // 속도를 높이기 위해, 문자열의 indexOf()와 substring() 사용
//             firstBlankIndex = inputLine.indexOf(' ');
//             secondBlankIndex = inputLine.indexOf(' ', firstBlankIndex + 1);
//             int operator = Integer.parseInt(inputLine.substring(0, firstBlankIndex)), a = Integer.parseInt(inputLine.substring(firstBlankIndex + 1, secondBlankIndex)), b = Integer.parseInt(inputLine.substring(secondBlankIndex + 1));
//
//             if (operator == 0) {
//                 if (a == b) {
//                     // 같은 노드 간에 합집합 연산( union(a,a) )하지 않음.
//                     continue;
//                 }
//
//                 union(a, b);
//             }
//             if (operator == 1) {
//                 if (a == b) {
//                     find(a); // 경로 단축만 진행
//
//                     System.out.println("YES");
//                     continue;
//                 }
//
//                 int representativeNodeOfSetA = find(a), representativeNodeOfSetB = find(b);
//
//                 boolean isSameSet = (representativeNodeOfSetA == representativeNodeOfSetB);
//                 if (isSameSet == true) {
//                     System.out.println("YES");
//                 } else {
//                     System.out.println("NO");
//                 }
//             }
//         }
//     }
//
//     public static void union(int a, int b) {
//         // 1. a, b 노드의 대표 노드 찾기
//         int representativeNodeOfSetA;
//         int representativeNodeOfSetB;
//
//         if (nodeIndex[a] == a) {
//             representativeNodeOfSetA = a;
//         } else {
//             int nodeOfSetA = nodeIndex[a];
//             // 현재 노드가 대표 노드가 아니라면 인덱스에 지정된 노드로 넘어감
//             while (nodeIndex[nodeOfSetA] != nodeOfSetA) {
//                 nodeOfSetA = nodeIndex[nodeOfSetA];
//             }
//             // while문을 벗어나면 nodeOfSetA가 대표 노드임
//             representativeNodeOfSetA = nodeOfSetA;
//         }
//
//         if (nodeIndex[b] == b) {
//             representativeNodeOfSetB = b;
//         } else {
//             int nodeOfSetB = nodeIndex[b];
//             // 현재 노드가 대표 노드가 아니라면 인덱스에 지정된 노드로 넘어감
//             while (nodeIndex[nodeOfSetB] != nodeOfSetB) {
//                 nodeOfSetB = nodeIndex[nodeOfSetB];
//             }
//             // while문을 벗어나면 nodeOfSetB가 대표 노드임
//             representativeNodeOfSetB = nodeOfSetB;
//         }
//
//         // 2. b의 대표 노드의 인덱스를 a의 대표 노드로 변경.
//         nodeIndex[representativeNodeOfSetB] = representativeNodeOfSetA;
//     }
//
//     private static int find(int x) {
//         ArrayDeque<Integer> stack = new ArrayDeque<>();
//
//         // 1. 현재 노드가 대표 노드가 아니라면 스택에 넣은 후 인덱스를 따라 대표 노드로 이동
//         while (nodeIndex[x] != x) {
//             stack.add(x);
//             x = nodeIndex[x];
//         }
//         int representativeNodeOfSetX = x;
//
//         // 2. x 집합의 노드의 인덱스를 모두 대표 인덱스로 바꿈. ** 경로 단축 **
//         int nodeOfSetX;
//         while (!stack.isEmpty()) {
//             nodeOfSetX = stack.pollLast();
//             nodeIndex[nodeOfSetX] = representativeNodeOfSetX;
//         }
//
//         return representativeNodeOfSetX;
//     }
// }
