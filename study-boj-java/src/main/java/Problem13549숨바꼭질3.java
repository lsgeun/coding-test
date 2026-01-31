import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/13549
public class Problem13549숨바꼭질3 {
    public static int n, k;
    public static int[] minDistance;
    public static int N = 0, D = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        minDistance = new int[100000 + 1];
        Arrays.fill(minDistance, Integer.MAX_VALUE);

        findMinDistance();

        System.out.println(minDistance[k]);
    }

    public static void findMinDistance() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[D]));
        pq.offer(new int[]{n, 0});
        minDistance[n] = 0;

        while (!pq.isEmpty()) {
            int[] curDirectEdge = pq.poll();

            if (curDirectEdge[D] > minDistance[curDirectEdge[N]]) {
                continue;
            }

            int[] nextDirectEdge = new int[]{curDirectEdge[N] + 1, curDirectEdge[D] + 1};
            if (isInLine(nextDirectEdge[N])) {
                if (minDistance[nextDirectEdge[N]] > nextDirectEdge[D]) {
                    minDistance[nextDirectEdge[N]] = nextDirectEdge[D];
                    pq.offer(nextDirectEdge);
                }
            }

            nextDirectEdge = new int[]{curDirectEdge[N] - 1, curDirectEdge[D] + 1};
            if (isInLine(nextDirectEdge[N])) {
                if (minDistance[nextDirectEdge[N]] > nextDirectEdge[D]) {
                    minDistance[nextDirectEdge[N]] = nextDirectEdge[D];
                    pq.offer(nextDirectEdge);
                }
            }

            nextDirectEdge = new int[]{2 * curDirectEdge[N], curDirectEdge[D]};
            if (isInLine(nextDirectEdge[N])) {
                if (minDistance[nextDirectEdge[N]] > nextDirectEdge[D]) {
                    minDistance[nextDirectEdge[N]] = nextDirectEdge[D];
                    pq.offer(nextDirectEdge);
                }
            }
        }
    }

    public static boolean isInLine(int node) {
        if (0 <= node && node <= 100000) {
            return true;
        } else {
            return false;
        }
    }
}
