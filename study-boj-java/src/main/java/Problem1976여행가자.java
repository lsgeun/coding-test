import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1976
public class Problem1976여행가자 {
    public static int n, m;
    public static int[] parent;
    public static int[] rank;
    public static int[] plan;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setup();
        solve();
    }

    public static void setup() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        rank = new int[n + 1];
        Arrays.fill(rank, 0);

        for (int nodeA = 1; nodeA <= n; nodeA++) {
            st = new StringTokenizer(br.readLine());
            for (int nodeB = 1; nodeB <= n; nodeB++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 0) {
                    continue;
                } // 이후부터 connected == 1

                if (nodeA >= nodeB) {
                    continue;
                } // 이후부터 nodeA < nodeB

                union(nodeA, nodeB);
            }
        }

        plan = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
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
        } else { // rank[setNodeA] == rank[setNodeB]
            parent[setNodeB] = setNodeA;
            rank[setNodeA]++;
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

    public static void solve() {
        int setNodePlan0 = find(plan[0]);

        boolean canTravel = true;
        for (int i = 1; i < m; i++) {
            int setNodePlanI = find(plan[i]);
            if (setNodePlanI != setNodePlan0) {
                canTravel = false;
            }
        }

        if (canTravel) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayDeque;
//
// // https://www.acmicpc.net/problem/1976
// public class Problem1976여행가자 {
//     static int N, M;
//     static int[] nodeIndex;
//     static int[] cityOfTravelPlan;
//
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         M = Integer.parseInt(br.readLine());
//
//         // 1. 입력 데이터인 인접행렬로부터 union 연산 진행
//         nodeIndex = new int[N + 1];
//         for (int i = 0; i < N + 1; i++) {
//             nodeIndex[i] = i;
//         }
//         String inputLine;
//         for (int cityRow = 1; cityRow <= N - 1; cityRow++) {
//             inputLine = br.readLine();
//
//             for (int cityColumn = cityRow + 1; cityColumn <= N; cityColumn++) {
//                 int cityColumnOffset = 2 * (cityColumn - 1);
//                 int isConnected = Integer.parseInt(inputLine.substring(cityColumnOffset, cityColumnOffset + 1));
//                 if (isConnected == 0) {
//                     continue;
//                 }
//
//                 union(cityRow, cityColumn); // isConnected == 1
//             }
//         }
//         br.readLine(); // 중복되므로 인접 행렬 마지막 행 버림.
//
//         // 2. 입력 데이터인 여행 계획으로부터 find 연산 진행
//         cityOfTravelPlan = new int[M + 1];
//         inputLine = br.readLine();
//         int startIndex = 0, endIndex = inputLine.indexOf(' ');
//         for (int i = 1; i <= M; i++) {
//             if (endIndex != -1) {
//                 cityOfTravelPlan[i] = Integer.parseInt(inputLine.substring(startIndex, endIndex));
//             } else {
//                 cityOfTravelPlan[i] = Integer.parseInt(inputLine.substring(startIndex));
//             }
//             startIndex = endIndex + 1;
//             endIndex = inputLine.indexOf(' ', startIndex);
//         }
//         boolean canTravel = true;
//         for (int i = 1; i <= M - 1; i++) {
//             boolean isConnected = (find(cityOfTravelPlan[i]) == find(cityOfTravelPlan[i + 1]));
//             if (!isConnected) {
//                 canTravel = false;
//                 break;
//             }
//         }
//
//         // 3. 여행 가능 여부에 따라 출력하기
//         if (canTravel) {
//             System.out.println("YES");
//         } else {
//             System.out.println("NO");
//         }
//     }
//
//     public static void union(int cityX, int cityY) {
//         // 경로 단축. 경로 단축 전에는 반복적으로 같은 경로를 많은 횟수로 거쳐감.
//         int representativeNodeOfSetX = find(cityX);
//         int representativeNodeOfSetY = find(cityY);
//
//         if (representativeNodeOfSetX != representativeNodeOfSetY) {
//             // 생각보다 같은 집합 간에 union하는 연산이 많으므로 if문으로 어느 정도 최적화가 됨.
//             nodeIndex[representativeNodeOfSetY] = representativeNodeOfSetX;
//         }
//     }
//
//     public static int find(int city) {
//         ArrayDeque<Integer> stack = new ArrayDeque<>();
//
//         while (nodeIndex[city] != city) {
//             stack.add(city);
//             city = nodeIndex[city];
//         }
//
//         int representativeNodeOfSetCity = city;
//         while (!stack.isEmpty()) {
//             city = stack.pollLast();
//             nodeIndex[city] = representativeNodeOfSetCity;
//         }
//
//         return representativeNodeOfSetCity;
//     }
// }
