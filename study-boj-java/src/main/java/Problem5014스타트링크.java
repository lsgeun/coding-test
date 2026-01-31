import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/5014
public class Problem5014스타트링크 {
    public static int f, s, g, u, d;
    public static int[] minButtonCount = new int[1000000 + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        f = sc.nextInt();
        s = sc.nextInt();
        g = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();
        sc.close();

        Arrays.fill(minButtonCount, -1);

        findMinButtonCount();

        if (minButtonCount[g] == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(minButtonCount[g]);
        }
    }

    public static void findMinButtonCount() {
        Queue<Integer> queue = new LinkedList<>();
        minButtonCount[s] = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            int curX = queue.poll();
            if (curX == g) {
                break;
            }

            if (isInBuilding(curX + u) && minButtonCount[curX + u] == -1) {
                minButtonCount[curX + u] = minButtonCount[curX] + 1;
                queue.add(curX + u);
            }
            if (isInBuilding(curX - d) && minButtonCount[curX - d] == -1) {
                minButtonCount[curX - d] = minButtonCount[curX] + 1;
                queue.add(curX - d);
            }
        }
    }

    public static boolean isInBuilding(int x) {
        if (1 <= x && x <= f) {
            return true;
        } else {
            return false;
        }
    }
}
