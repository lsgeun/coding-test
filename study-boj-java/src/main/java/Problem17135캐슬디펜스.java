import java.io.*;
import java.util.*;

public class Problem17135캐슬디펜스 {
    static int n, m, d;
    static int[][] map;
    static int BLANK = 0, ENEMY = 1, ARCHER = 2;
    static int archerRow;
    static int maxRemoveCount;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(maxRemoveCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        archerRow = n;

        map = new int[n + 1][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxRemoveCount = Integer.MIN_VALUE;
    }

    static void solve() {
        ArrayList<Node> archers = new ArrayList<>();
        attackAndRenewMaxRemoveCountInAllCases(0, archers);
    }

    static void attackAndRenewMaxRemoveCountInAllCases(int start, ArrayList<Node> archers) {
        if (archers.size() == 3) {
            attackAndRenewMaxRemoveCount(archers);
            return;
        }

        for (int i = start; i < m; i++) {
            Node archer = new Node(archerRow, i);
            archers.add(archer);

            attackAndRenewMaxRemoveCountInAllCases(i + 1, archers);

            archers.remove(archers.size() - 1);
        }
    }

    static void attackAndRenewMaxRemoveCount(ArrayList<Node> archers) {
        // 최대 제거 횟수 갱신 포함

        // 현재 맵 상태에서 적을 죽임
        // 현재 맵을 보존하기 위해서 맵을 복사함
        int[][] copyMap = new int[n + 1][m];
        for (int i = 0; i < n + 1; i++) {
            copyMap[i] = map[i].clone();
        }

        int removeCount = 0;
        // 적이 있다면, 적 제거 후, 적 제거 횟수 구하기
        while (existsEnemy(copyMap)) {
            // 제거할 적 정하기
            // pqList는 각 궁수의 제거 대상을 순서대로 배치한 pq의 리스트
            ArrayList<PriorityQueue<Node>> pqList = new ArrayList<>();
            for (Node archer : archers) {
                PriorityQueue<Node> pq = new PriorityQueue<>();
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (copyMap[j][k] == ENEMY) {
                            Node enemy = new Node(j, k);
                            enemy.length = getDistance(enemy, archer);
                            pq.add(enemy);
                        }
                    }
                }
                pqList.add(pq);
            }

            // 적 제거하기
            for (PriorityQueue<Node> pq : pqList) {
                // 거리가 가장 짧고, 가장 왼쪽에 위치한 노드
                Node node = pq.poll();
                // 그 노드의 거리가 d 이하가 아니라면 적을 제거하지 못함
                if (!(node.length <= d)) {
                    continue;
                }

                if (copyMap[node.x][node.y] == ENEMY) {
                    copyMap[node.x][node.y] = BLANK;
                    // 적 제거 횟수 구하기
                    removeCount += 1;
                }
            }

            // 적 앞으로 1칸 전진
            moveEnemyForward(copyMap);
        }

        // 최대 제거 횟수 갱신
        maxRemoveCount = Math.max(maxRemoveCount, removeCount);
    }

    static boolean existsEnemy(int[][] copyMap) {
        boolean existsEnemy = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == ENEMY) {
                    existsEnemy = true;
                     break;
                }
            }

            if (existsEnemy) {
                break;
            }
        }

        return existsEnemy;
    }

    static void moveEnemyForward(int[][] copyMap) {
        // 적 앞으로 1칸 전진
        for (int i = n - 1; i >= 1; i--) {
            copyMap[i] = copyMap[i - 1];
        }
        copyMap[0] = new int[m];
        Arrays.fill(copyMap[0], BLANK);
    }

    static int getDistance(Node nodeA, Node nodeB) {
        return Math.abs(nodeA.x - nodeB.x) + Math.abs(nodeA.y - nodeB.y);
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int length = -1;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node n) {
            // 길이가 짧은 순으로
            if (this.length != n.length) {
                return this.length - n.length;
            // 길이가 같다면, 가장 왼쪽 노드로
            } else { // this.length == n.length
                return this.y - n.y;
            }
        }
    }
}
