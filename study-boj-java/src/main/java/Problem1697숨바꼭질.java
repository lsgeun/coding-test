import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1697
public class Problem1697숨바꼭질 {
    public static int n, k; // n: 수빈, k: 동생
    public static boolean[] visited = new boolean[100000 + 1];
    public static int minTime;
    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        k = sc.nextInt();
        sc.close();

        findMinTime();

        System.out.println(minTime);
    }

    public static void findMinTime() {
        Queue<int[]> queue = new LinkedList<>();
        visited[n] = true;
        queue.add(new int[]{n, 0});

        while (true) {
            int[] curNode = queue.poll();
            int curNodeX = curNode[0];
            int curNodeT = curNode[1];

            if (curNodeX == k) {
                minTime = curNodeT;
                break;
            }

            // nextNode 추가
            if (isInLine(curNodeX-1) &&
                    !visited[curNodeX-1]) {
                visited[curNodeX-1] = true;
                queue.add(new int[]{curNodeX-1, curNodeT + 1});
            }
            if (isInLine(curNodeX+1) &&
                    !visited[curNodeX+1]) {
                visited[curNodeX+1] = true;
                queue.add(new int[]{curNodeX+1, curNodeT + 1});
            }
            if (isInLine(2*curNodeX) &&
                    !visited[2*curNodeX]) {
                visited[2*curNodeX] = true;
                queue.add(new int[]{2*curNodeX, curNodeT + 1});
            }
        }
    }

    public static boolean isInLine(int x) {
        if (0 <= x && x <= 100000) {
            return true;
        } else {
            return false;
        }
    }
}
