import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/1325
public class Problem1325효율적인해킹 {
    static int N;
    static int M;
    static ArrayList<Integer>[] adjacentList;
    static boolean[] visited;
    static int[] trustCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputLine = br.readLine();
        int blankIndex = inputLine.indexOf(' ');
        N = Integer.parseInt(inputLine.substring(0, blankIndex));
        M = Integer.parseInt(inputLine.substring(blankIndex + 1));

        // 인접 리스트 초기화
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            inputLine = br.readLine();
            blankIndex = inputLine.indexOf(' ');
            int A = Integer.parseInt(inputLine.substring(0, blankIndex)), B = Integer.parseInt(inputLine.substring(blankIndex + 1));

            adjacentList[A].add(B);
        }
        br.close();

        trustCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, trustCount[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (trustCount[i] == max) {
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int nextNode : adjacentList[curNode]) {
                if (!visited[nextNode]) {
                    trustCount[nextNode] += 1;
                    queue.add(nextNode);
                    visited[nextNode] = true;

                }
            }
        }
    }
}
