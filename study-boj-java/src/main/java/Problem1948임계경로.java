import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1948
public class Problem1948임계경로 {
    static ArrayList<EdgeProblem55>[] adjacentList;
    static int[] inDegree;
    static int[] maxTime;
    static ArrayList<EdgeProblem55>[] reverseAdjacentList;
    static int roadWithOutBreakCount;
    static boolean[] visited;
    static int startCity, endCity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjacentList = new ArrayList[N + 1];
        reverseAdjacentList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjacentList[i] = new ArrayList<>();
            reverseAdjacentList[i] = new ArrayList<>();
        }
        inDegree = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startCity = Integer.parseInt(st.nextToken()), endCity = java.lang.Integer.parseInt(st.nextToken()), time = Integer.parseInt(st.nextToken());
            EdgeProblem55 edge = new EdgeProblem55(endCity, time);
            EdgeProblem55 reverseEdgeProblem55 = new EdgeProblem55(startCity, time);

            adjacentList[startCity].add(edge);
            reverseAdjacentList[endCity].add(reverseEdgeProblem55);

            inDegree[endCity]++;
        }

        maxTime = new int[N + 1];
        visited = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());

        findMaxTime();
        findRoadWithOutBreakCount();

        System.out.println(maxTime[endCity]);
        System.out.println(roadWithOutBreakCount);
    }

    static public void findMaxTime() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startCity);

        while (!queue.isEmpty()) {
            int city = queue.poll();

            // 가정에 의해, 도착 도시가 아니라면 진출 차수 >= 1
            // 도착 도시가 아니라면 for문 항상 동작
            for (EdgeProblem55 nextEdgeProblem55 : adjacentList[city]) {
                int nextCity = nextEdgeProblem55.city;
                int time = nextEdgeProblem55.time;

                int oldMaxTime = maxTime[nextCity];
                int newMaxTime = maxTime[city] + time;
                maxTime[nextCity] = Math.max(oldMaxTime, newMaxTime);

                inDegree[nextCity]--;

                if (inDegree[nextCity] == 0) {
                    queue.add(nextCity);
                }
            }
        }
    }

    private static void findRoadWithOutBreakCount() {
        Queue<Integer> queue = new LinkedList<>();
        visited[endCity] = true;
        queue.add(endCity);

        while (!queue.isEmpty()) {
            int city = queue.poll();

            // 가정에 의해, 출발 도시가 아니라면 진출 차수 >= 1
            // 출발 도시가 아니라면 for문 항상 동작
            for (EdgeProblem55 nextEdgeProblem55 : reverseAdjacentList[city]) {
                int nextCity = nextEdgeProblem55.city;
                int time = nextEdgeProblem55.time;

                if (maxTime[city] == maxTime[nextCity] + time) {
                    roadWithOutBreakCount++;

                    if (visited[nextCity] == false) {
                        visited[nextCity] = true;
                        queue.add(nextCity);
                    }
                }
            }
        }
    }
}

class EdgeProblem55 {
    int city;
    int time;

    public EdgeProblem55(int city, int time) {
        this.city = city;
        this.time = time;
    }
}
