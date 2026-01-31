import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1516
public class Problem1516게임개발 {
    static int N;
    static ArrayList<Integer>[] adjacentList;
    static int[] buildTime;
    static int[] minBuildTime;
    static int[] inDegree;

    public static void main(String[] args) {
        // 1. 입력 데이터로 초기화
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        adjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        buildTime = new int[N + 1];
        minBuildTime = new int[N + 1];
        inDegree = new int[N + 1];

        for (int building = 1; building <= N; building++) {
            int time = sc.nextInt();
            // minBuildTime의 초기값 설정. 위상정렬이 진행되면서 추후에 이 값이 정해짐.
            buildTime[building] = time;

            int requiredBuilding = sc.nextInt();
            while (requiredBuilding != -1) {
                adjacentList[requiredBuilding].add(building);
                inDegree[building]++;

                requiredBuilding = sc.nextInt();
            }
        }

        // 2. minBuildTime 구하기
        Queue<Integer> queue = new LinkedList<>();
        // 진입차수가 0인 노드를 큐에 모두 넣어줌. O(N).
        for (int building = 1; building <= N; building++) {
            if (inDegree[building] == 0) {
                // 진입차수가 0인 경우 minBuildTime을 넣어주어야 함.
                minBuildTime[building] = buildTime[building];
                queue.add(building);
            }
        }

        // 여러 connected component에서 동시에 BFS. O(V+E).
        while (!queue.isEmpty()) {
            int building = queue.poll();

            for (int nextBuilding : adjacentList[building]) {
                inDegree[nextBuilding]--;

                int oldMinBuildTime = minBuildTime[nextBuilding],
                        newMinBuildTime = minBuildTime[building] + buildTime[nextBuilding],
                        nextMinBuildingTime = Math.max(oldMinBuildTime, newMinBuildTime);
                minBuildTime[nextBuilding] = nextMinBuildingTime;

                if (inDegree[nextBuilding] == 0) {
                    queue.add(nextBuilding);
                }
            }
        }

        // 3. minBuildTime 출력
        for (int building = 1; building <= N; building++) {
            System.out.println(minBuildTime[building]);
        }
    }
}
