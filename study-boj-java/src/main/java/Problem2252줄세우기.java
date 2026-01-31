import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/2252
public class Problem2252줄세우기 {
    // N 학생수, M 키 비교 횟수
    static int N, M;
    static ArrayList<Integer>[] adjacentList;
    static int[] inDegree;
    static ArrayList<Integer> topologicalSort;

    public static void main(String[] args) throws IOException {
        initData();
        findTopologicalSort();
        showTopologicalSort();
    }

    private static void showTopologicalSort() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(topologicalSort.get(i)).append(" ");
        }
        System.out.print(sb);
    }

    private static void findTopologicalSort() {
        // 이 알고리즘은 사이클이 없는 방향그래프에서 connected component가 1개 이상이라도 동작함.

        // 1. 방문하지 않은 진입 차수가 0인 노드 찾아서 큐에 추가하기
        // 최초로 방문하지 않은 진입 차수가 0인 노드를 찾는 것은 부르트포스 탐색을 해야 함.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 2. 큐에서 노드 하나를 뺀 후, 위상 정렬 배열에 append 후 이 노드와 연결된 inDegree을 1 감소
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalSort.add(node);
            // 연결된 노드의 inDegree는 항상 1보다 크거나 같음
            // 연결된 노드가 없다면 그냥 위상 정렬 배열에 append만 함.
            for (int nextNode : adjacentList[node]) {
                inDegree[nextNode]--;
                // 최초 이후부터는 inDegree가 1 감소된 이후
                // 큐에서 뺀 노드와 연결된 노드들 중에서 진입 차수가 0인 노드가 나옴.
                if (inDegree[nextNode] == 0) {
                    queue.add(nextNode);
                }
            }
        }
    }

    private static void initData() throws IOException {
        // 1. 입력 데이터로 N 학생수, M 키 비교 횟수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = br.readLine();
        int blankIndex = inputLine.indexOf(' ');
        N = Integer.parseInt(inputLine.substring(0, blankIndex));
        M = Integer.parseInt(inputLine.substring(blankIndex + 1));

        // 2. 입력 데이터로 인접리스트, 진입차수 초기화
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];
        int A, B;
        for (int i = 0; i < M; i++) {
            inputLine = br.readLine();
            blankIndex = inputLine.indexOf(' ');
            A = Integer.parseInt(inputLine.substring(0, blankIndex));
            B = Integer.parseInt(inputLine.substring(blankIndex + 1));

            adjacentList[A].add(B);
            inDegree[B]++;
        }

        // 3. 위상 정렬 배열 초기화
        topologicalSort = new ArrayList<>();
    }
}
