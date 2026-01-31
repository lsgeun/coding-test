import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2178
public class Problem2178미로탐색 {
    static int[] x = {1, -1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[101][101];
        for (int i = 1; i <= N; i++) {
            String maze_row = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = maze_row.charAt(j - 1) - '0';
            }
        }
        br.close();

        visited = new boolean[101][101];
        BFS(1, 1);

        System.out.print(arr[N][M]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList();
        visited[i][j] = true;
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] current_node = queue.poll();

            for (int k = 0; k < 4; k++) {
                int[] next_node = {current_node[0] + y[k], current_node[1] + x[k]};

                // 다음 노드가 미로 범위를 벗어난다면 무시
                if ((next_node[0] < 1) || (next_node[1] < 1)) {
                    continue;
                }
                if ((next_node[0] > 100) || (next_node[1] > 100)) {
                    continue;
                }
                // 다음 노드가 벽이라면 무시
                if (arr[next_node[0]][next_node[1]] == 0) {
                    continue;
                }

                // 다음 노드를 방문 했다면 무시
                if (!visited[next_node[0]][next_node[1]]) {
                    // 다음 노드의 최단 거리 기록
                    arr[next_node[0]][next_node[1]] = arr[current_node[0]][current_node[1]] + 1;

                    visited[next_node[0]][next_node[1]] = true;
                    queue.add(next_node);
                }
            }
        }

    }
}
