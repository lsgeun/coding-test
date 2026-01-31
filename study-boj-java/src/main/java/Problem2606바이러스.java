import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2606
public class Problem2606바이러스 {
    public static ArrayList<ArrayList<Integer>> adjacentList;
    public static int computerCount;
    public static int computerPairCount;
    public static int virusComputerCount;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        computerCount = sc.nextInt();
        computerPairCount = sc.nextInt();

        adjacentList = new ArrayList<>();
        for (int i = 0; i < computerCount + 1; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for (int i = 0; i < computerPairCount; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();

            adjacentList.get(first).add(second);
            adjacentList.get(second).add(first);
        }
        sc.close();

        visited = new boolean[computerCount + 1];
        virusComputerCount = 0;
        findVirusComputerCount();

        System.out.println(virusComputerCount);
    }

    public static void findVirusComputerCount() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int curComputer = queue.poll();

            for (int nextComputer: adjacentList.get(curComputer)) {
                if (!visited[nextComputer]) {
                    visited[nextComputer] = true;
                    virusComputerCount++;
                    queue.add(nextComputer);
                }
            }
        }
    }
}
