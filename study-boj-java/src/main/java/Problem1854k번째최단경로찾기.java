import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1854
public class Problem1854k번째최단경로찾기 {
    public static void main(String[] args) throws IOException {
        int N, M, K;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer>[] distMaxHeap = new PriorityQueue[N + 1];
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        for (int i = 0; i < N + 1; i++) {
            distMaxHeap[i] = new PriorityQueue<>(K, cp);
        }

        int[][] W = new int[1001][1001];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            W[a][b] = c;
        }

        // k번째 최단 거리 구하기
        PriorityQueue<PathProblem58> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new PathProblem58(1, 0));
        distMaxHeap[1].add(0);
        while (!priorityQueue.isEmpty()) {
            PathProblem58 curPath = priorityQueue.poll();
            for (int nextCity = 1; nextCity <= N; nextCity++) {
                if (W[curPath.city][nextCity] == 0) {
                    continue;
                }

                boolean isDistMaxHeapFull = distMaxHeap[nextCity].size() >= K;
                int nextCost = curPath.cost + W[curPath.city][nextCity];
                if (!isDistMaxHeapFull) {
                    distMaxHeap[nextCity].add(nextCost);
                    priorityQueue.add(new PathProblem58(nextCity, nextCost));
                }
                if (isDistMaxHeapFull &&
                        distMaxHeap[nextCity].peek() > nextCost) {
                    distMaxHeap[nextCity].poll();
                    distMaxHeap[nextCity].add(nextCost);
                    priorityQueue.add(new PathProblem58(nextCity, nextCost));
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= N; i++) {
            if (distMaxHeap[i].size() == K) {
                bw.write(distMaxHeap[i].peek() + "\n");
            } else {
                bw.write(-1 + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}

class PathProblem58 implements Comparable<PathProblem58> {
    int city;
    int cost;

    PathProblem58(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(PathProblem58 o) {
        if (this.cost < o.cost) {
            return -1;
        } else {
            return 1;
        }
    }
}
