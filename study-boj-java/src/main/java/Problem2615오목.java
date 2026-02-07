import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2615
public class Problem2615오목 {
    static int[][] map;
    static final int X = 0, Y = 1;
    static final int BLACK = 1, WHITE = 2, BLANK = 0;
    static final int BLACK_WIN = 1, WHITE_WIN = 2, NOT_DETERMINED = 0;

    static int[][] eightDirection = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
    };

    static int result;
    static int mostLeftX, mostLeftY;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(result);
        if (result != NOT_DETERMINED) {
            System.out.println(mostLeftX + " " + mostLeftY);
        }
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[19 + 1][19 + 1];
        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = NOT_DETERMINED;
        mostLeftX = -1;
        mostLeftY = -1;
    }

    static void solve() {
        boolean canWin = false;
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                if (map[i][j] == BLANK) {
                    continue;
                }

                canWin = canWin(i, j, map[i][j]);
                if (canWin) {
                    break;
                }
            }

            if (canWin) {
                break;
            }
        }
    }

    static boolean canWin(int x, int y, int type) {
        boolean canWin = false;
        for (int[] dir : eightDirection) {
            int[] oppositeDir = new int[]{-dir[X], -dir[Y]};

            int[] firstEnd = new int[]{x + oppositeDir[X], y + oppositeDir[Y]};

            // 다른 노드에서 탐색할 거라면 스킵
            if (isInMap(firstEnd)) {
                if(map[firstEnd[X]][firstEnd[Y]] == type) {
                    continue;
                }
            } // firstEnd는 type과 다른 타입이거나, 맵 밖임

            // 돌의 나열이 이기는 수인지 확인하고,
            // 이긴다면 결과와 맨 왼쪽 노드를 저장한 후, 탐색 종료
            PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> {
                if (n1[Y] != n2[Y]) {
                    return n1[Y] - n2[Y];
                } else { // n1[Y] == n2[Y]
                    return n1[X] - n2[X];
                }
            });

            int[] curNode = new int[]{x, y};
            pq.add(curNode);

            while (true) {
                curNode = new int[]{curNode[X] + dir[X], curNode[Y] + dir[Y]};

                // pq 개수가 5개면 종료
                if (pq.size() == 5) {
                    break;
                }
                // 끝까지 도달하면 종료
                if (!isInMap(curNode)) {
                    break;
                }
                // type과 다른 타입의 노드(다른 돌, 빈칸)에 도달하면 종료
                if (map[curNode[X]][curNode[Y]] != type) {
                    break;
                }

                // 여기서부터, curNode의 type과 같은 타입임
                pq.add(curNode);
            }

            int[] lastEnd = curNode;
            if (pq.size() == 5) {
                int[] mostLeft;

                if (!isInMap(lastEnd)) {
                    canWin = true;
                    result = (type == BLACK) ? BLACK_WIN : WHITE_WIN;
                    mostLeft = pq.poll();
                    mostLeftX = mostLeft[X];
                    mostLeftY = mostLeft[Y];
                } else { // isInMap(lastEnd)
                    if (map[lastEnd[X]][lastEnd[Y]] != type) {
                        canWin = true;
                        mostLeft = pq.poll();
                        result = (type == BLACK) ? BLACK_WIN : WHITE_WIN;
                        mostLeftX = mostLeft[X];
                        mostLeftY = mostLeft[Y];
                    } else { // map[lastEnd[X]][lastEnd[Y]] == type
                        // 5개보다 더 많은 type의 돌이 있다면,
                        // 연속적으로 이어진 길이가 6개 이상이므로,
                        // 이길 수 없음
                    }
                }
            }

            if (canWin) {
                break;
            }
        }

        return canWin;
    }

    static boolean isInMap(int[] node) {
        if ((1 <= node[X] && node[X] <= 19) &&
                (1 <= node[Y] && node[Y] <= 19)) {
            return true;
        } else {
            return false;
        }
    }
}
