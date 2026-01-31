import java.util.*;

// https://www.acmicpc.net/problem/1260
public class Problem1260DFS와BFS {
    static int N, M, start;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) {
        // 그래프 정보
        // N 노드 개수, M 엣지 개수, start 시작 노드
        // arr 인접 리스트, visited 방문 배열
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        start = sc.nextInt();

        // 빈 인접 리스트 생성
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        // 인접 리스트 채우기
        for (int i = 0; i < M; i++) {
            int first = sc.nextInt(), end = sc.nextInt();
            arr[first].add(end);
            arr[end].add(first);
        }
        sc.close();

        // 인접 리스트 엣지 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }

        // DFS, BFS 실행
        visited = new boolean[N + 1];
        DFS(start);
        System.out.println();
        visited = new boolean[N + 1];
        BFS(start);
    }

    public static void DFS(int vertex) {
        System.out.print(vertex + " ");
        visited[vertex] = true;
        for (int next_vertex : arr[vertex]) {
            if (!visited[next_vertex]) {
                DFS(next_vertex);
            }
        }
    }

    public static void BFS(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        visited[vertex] = true;
        queue.add(vertex);

        while (!queue.isEmpty()) {
            int current_vertex = queue.poll();
            System.out.print(current_vertex + " ");
            for (int next_vertex : arr[current_vertex]) {
                if (!visited[next_vertex]) {
                    visited[next_vertex] = true;
                    queue.add(next_vertex);
                }
            }
        }
    }
}
