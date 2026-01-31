import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15684
public class Problem15684사다리조작 {
    static int n, m, h;
    static boolean[][] ladder;
    static int minLadderCount;

    public static void main(String[] args) throws IOException {
        setUp();

        findMinLadderCount(1, 1, 0);

        if (0 <= minLadderCount && minLadderCount <= 3) {
            System.out.println(minLadderCount);
        } else {
            System.out.println(-1);
        }
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[h + 1][n - 1 + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = true;
        }

        minLadderCount = Integer.MAX_VALUE;
    }

    static void findMinLadderCount(int row, int column, int ladderCount) {
        // minLadderCount보다 더 작은 ladderCount에 대해서만 관심있음.
        if (ladderCount >= minLadderCount) {
            return;
        }
        // ladderCount <= minLadderCount - 1

        if (canGoStraightRespectively()) {
            // ladderCount가 minLadderCount보다 더 작음.
            minLadderCount = ladderCount;
            return;
        }

        // 성공해봐야 최소 minLadderCount이니까 더 알아볼 필요 없음.
        if (ladderCount == minLadderCount - 1) {
            return;
        }
        // ladderCount <= minLadderCount - 2

        // 성공해도 최소 4이니까 더 알아볼 필요 없음.
        // Integer.MAX_VALUE 포함한 4 이상은 실패와 동일함.
        if (ladderCount >= 3) {
            return;
        }

        for (int i = row; i <= h; i++) {
            // 경우의 수가 중복되지 않도록 row 줄에서는 column에서 시작해야 함
            int startColumn = (i == row) ? column : 1;
            for (int j = startColumn; j <= n - 1; j++) {
                // 현재 점선은 항상 존재
                // 현재 점선 줄에 사다리에 있을 때,
                if (ladder[i][j]) {
                    continue;
                }
                // 왼쪽 점선 줄이 있고, 사다리가 있을 때,
                if (1 <= j - 1 && ladder[i][j - 1]) {
                    continue;
                }
                // 오른쪽 점선 줄이 있고, 사다리가 있을 때,
                if (j + 1 <= n - 1 && ladder[i][j + 1]) {
                    continue;
                }

                ladder[i][j] = true;
                findMinLadderCount(i, j + 2, ladderCount + 1);
                ladder[i][j] = false;
            }
        }

    }

    static boolean canGoStraightRespectively() {
        boolean canGoStraightRespectively = true;
        for (int column = 1; column <= n; column++) {
            int curColumn = column;
            for (int row = 1; row <= h; row++) {
                int leftColumn = curColumn - 1;
                int rightColumn = curColumn + 1;
                // 참고로, 알고리즘에 의해 왼쪽, 오른쪽 모두에 사다리가 있을 수 없음
                // 현재 열에서 왼쪽 점선 줄이 있고, 사다리가 있다면, 왼쪽 줄로 이동
                if (1 <= leftColumn && ladder[row][leftColumn]) {
                    curColumn = leftColumn;
                // 현재 열에서 오른쪽 점선 줄이 있고, 사다리가 있다면, 오른쪽 줄로 이동
                } else if (curColumn <= n - 1 && ladder[row][curColumn]) {
                    curColumn = rightColumn;
                }
                // 좌우에 아무런 사다리가 없다면 다음 줄로 넘어감.
            }

            if (column != curColumn) {
                canGoStraightRespectively = false;
                break;
            }
        }

        return canGoStraightRespectively;
    }
}
