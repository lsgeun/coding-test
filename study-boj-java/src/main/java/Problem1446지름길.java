import java.util.*;

// https://www.acmicpc.net/submit/1446
public class Problem1446지름길 {
    public static int n, d;
    public static ArrayList<int[]>[] graph;
    public static int[] minDistances;
    public static final int N = 0, D = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        d = sc.nextInt();

        graph = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < d; i++) {
            graph[i].add(new int[]{i + 1, 1});
        }

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int distance = sc.nextInt();

            if (start > d || end > d) {
                continue;
            }

            graph[start].add(new int[]{end, distance});
        }

        minDistances = new int[d + 1];
        Arrays.fill(minDistances, Integer.MAX_VALUE);

        findMinDistance();

        System.out.println(minDistances[d]);
    }

    public static void findMinDistance() {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[D]));
        minDistances[0] = 0;
        queue.add(new int[]{0, 0});

        while(!queue.isEmpty()) {
            int[] curDirectEdge = queue.poll();

            if (minDistances[curDirectEdge[N]] < curDirectEdge[D]) {
                continue;
            }

            for (int[] nextEdge : graph[curDirectEdge[N]]) {
                int[] nextDirectEdge = new int[]{nextEdge[N], curDirectEdge[D] + nextEdge[D]};

                if (minDistances[nextDirectEdge[N]] > nextDirectEdge[D]) {
                    minDistances[nextDirectEdge[N]] = nextDirectEdge[D];
                    queue.add(nextDirectEdge);
                }
            }
        }
    }
}
