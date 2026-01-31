import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9205
public class Problem9205맥주마시면서걸어가기 {
    public static int[][] nodes;
    public static boolean[] visited;
    public static int t;
    public static int n;
    public static String emotion;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            nodes = new int[n+2][2];
            visited = new boolean[n+2];

            for (int j = 0; j < n+2; j++) {
                nodes[j] = new int[]{sc.nextInt(), sc.nextInt()};
            }

            findEmotion();

            System.out.println(emotion);
        }
        sc.close();
    }

    public static void findEmotion() {
        int homeIndex = 0;
        int[] home = nodes[homeIndex];
        Queue<int[]> queue = new LinkedList<>();
        visited[homeIndex] = true;
        queue.add(new int[]{home[0], home[1]});

        int festivalIndex = n+1;
        boolean canGoFestival = false;
        emotion = "sad";
        while (!queue.isEmpty()) {
            int[] curNode = queue.poll();

            for (int i = 0; i < n+2; i++) {
                if (visited[i]) {
                    continue;
                }

                if (canGo(curNode, nodes[i])) {
                    if (i == festivalIndex) {
                        canGoFestival = true;
                        emotion = "happy";
                        break;
                    }

                    visited[i] = true;
                    queue.add(new int[]{nodes[i][0], nodes[i][1]});
                }
            }

            if (canGoFestival) {
                break;
            }
        }
    }

    public static boolean canGo(int[] startNode, int[] endNode) {
        int taxiDistance = Math.abs(startNode[0] - endNode[0]) + Math.abs(startNode[1] - endNode[1]);
        if (0 <= taxiDistance && taxiDistance <= 1000) {
            return true;
        } else {
            return false;
        }
    }

}
