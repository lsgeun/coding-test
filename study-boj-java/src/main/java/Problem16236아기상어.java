import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16236
public class Problem16236아기상어 {
    static int n;
    static Shark babyShark;
    static int[][] map;
    static boolean[][] visited;
    static int seconds;
    static final int BLANK = 0, BABY_SHARK = 9;
    static final int MIN_FISH_SIZE = 1, MAX_FISH_SIZE = 6;
    static final int X = 0, Y = 1;
    static final int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public static void main(String[] args) throws IOException {
        setUp();
        eatAllEdibleFish();
        System.out.println(seconds);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        babyShark = new Shark(2, 0);

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == BABY_SHARK) {
                    babyShark.x = i;
                    babyShark.y = j;
                }
            }
        }

        visited = new boolean[n][n];

        seconds = 0;
    }

    static void eatAllEdibleFish() {
        PriorityQueue<EdibleFish> edibleFishes = findEdibleFishes();

        while (!edibleFishes.isEmpty()) {
            EdibleFish edibleFishMinDistance = edibleFishes.poll();

            // 이동
            map[babyShark.x][babyShark.y] = BLANK;
            map[edibleFishMinDistance.x][edibleFishMinDistance.y] = BABY_SHARK;
            babyShark.x = edibleFishMinDistance.x;
            babyShark.y = edibleFishMinDistance.y;

            seconds += edibleFishMinDistance.distance;

            // 먹기
            babyShark.eatFish();

            resetVisited();
            edibleFishes = findEdibleFishes();
        }
    }

    static void resetVisited() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static PriorityQueue<EdibleFish> findEdibleFishes() {
        PriorityQueue<EdibleFish> edibleFishes = new PriorityQueue<>((fish1, fish2) -> {
            if (fish1.x != fish2.x) {
                return Integer.compare(fish1.x, fish2.x);
            } else { // fish1.x != fish2.x
                return Integer.compare(fish1.y, fish2.y);
            }
        });

        Queue<Node16236> queue = new ArrayDeque<>();
        visited[babyShark.x][babyShark.y] = true;
        queue.add(new Node16236(babyShark.x, babyShark.y, 0));

        int minDistance = 399;
        while (!queue.isEmpty()) {
            Node16236 curNode = queue.poll();
            // 0 <= 노드 distance <= 399 이므로 처음에는 항상 통과됨.
            // minDistance가 한 번 초기화된 이후로는 그 값보다 큰 경우가 나오자마자 바로 종료
            // bfs에서는 minDistance보다 큰 노드는 탐색하지 않겠다는 것. 다른 노드는 모두 탐색, 약간의 최적화
            if (curNode.distance > minDistance) {
                break;
            }

            if (babyShark.canEat(map[curNode.x][curNode.y])) {
                edibleFishes.add(new EdibleFish(curNode.x, curNode.y, curNode.distance));
                minDistance = curNode.distance;
                // 현재 노드 거리보다 더 긴 거리를 가진 노드를 만들지 않기, 약간의 최적화
                continue;
            }

            for (int[] direction : fourDirection) {
                int nextNodeX = curNode.x + direction[X];
                int nextNodeY = curNode.y + direction[Y];

                // 맵 안 인지 확인
                if (!isInMap(nextNodeX, nextNodeY)) {
                    continue;
                }

                // 엣지가 있는지 확인
                if (!babyShark.canMove(map[nextNodeX][nextNodeY])) {
                    continue;
                }

                // 방문했는지 확인
                if (visited[nextNodeX][nextNodeY]) {
                    continue;
                }

                Node16236 nextNode = new Node16236(nextNodeX, nextNodeY, curNode.distance + 1);
                visited[nextNode.x][nextNode.y] = true;
                queue.add(nextNode);
            }

        }

        return edibleFishes;
    }

    static boolean isInMap(int x, int y) {
        return (0 <= x && x <= n - 1) && (0 <= y && y <= n - 1);
    }

    static class Shark extends Node16236 {
        int size;
        int eatenFishCount;

        Shark(int size, int eatenFishCount) {
            super();
            this.size = size;
            this.eatenFishCount = eatenFishCount;
        }

        void eatFish() {
            this.eatenFishCount += 1;
            if (this.eatenFishCount == size) {
                this.eatenFishCount = 0;
                this.size += 1;
            }
        }

        boolean canEat(int num) {
            if (num == BLANK || num == BABY_SHARK) {
                return false;
            }

            // 크기가 7이면 전부 먹을 수 있음, 약간의 최적화
            if (this.size >= MAX_FISH_SIZE + 1) {
                return true;
            }
            int fishSize = num;
            return MIN_FISH_SIZE <= fishSize && fishSize < this.size;
        }

        boolean canMove(int num) {
            // 아기 상어로 갈 일은 없긴 함
            // 그럴 일은 없지만, 혹시라도 간다면 루프가 생겨서 최소 거리가 아니게 됨.
            if (num == BABY_SHARK) {
                return false;
            }
            if (num == BLANK) {
                return true;
            }
            int fishSize = num;
            return MIN_FISH_SIZE <= fishSize && fishSize <= this.size;
        }
    }

    static class EdibleFish extends Node16236 {
        EdibleFish(int x, int y, int distance) {
            super(x, y, distance);
        }
    }

    static class Node16236 {
        int x;
        int y;
        int distance;

        public Node16236() {
        }

        public Node16236(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}