import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15683
public class Problem15683감시 {
    public static int n, m;
    public static int[][] map;
    public static final int NOT_WATCHED = 0, WALL = 6, WATCHED = 7;

    public static ArrayList<int[]> cctvs;
    public static final int X = 0, Y = 1, TYPE = 2;
    public static final int N = 0, E = 1, S = 2, W = 3;
    public static int[][] cctvDirections;

    public static int minArea = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        setUp();
        findMinArea(0);
        System.out.println(minArea);
    }

    public static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cctvs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = type;
                if (1 <= type && type <= 5) {
                    cctvs.add(new int[]{i, j, type});
                }
            }
        }

        cctvDirections = new int[][]{
                {-1},
                {N},
                {E, W},
                {N, E},
                {N, E, W},
                {N, E, S, W},
        };
    }

    public static void findMinArea(int cctvIndex) {
        if (cctvIndex == cctvs.size()) {
            minArea = Math.min(minArea, getArea());
            return;
        }

        int cctvType = cctvs.get(cctvIndex)[TYPE];
        int cctvX = cctvs.get(cctvIndex)[X];
        int cctvY = cctvs.get(cctvIndex)[Y];

        for (int i = 0; i < 4; i++) {
            ArrayList<int[]> newWatchedBlock = new ArrayList<>();

            // 새로운 감시 영역 추가.
            for (int direction : cctvDirections[cctvType]) {
                if (direction == N) {
                    for (int j = cctvX - 1; j >= 0; j--) {
                        if (map[j][cctvY] == WALL) {
                            break;
                        }

                        if (map[j][cctvY] == NOT_WATCHED) {
                            map[j][cctvY] = WATCHED;
                            newWatchedBlock.add(new int[]{j, cctvY});
                        }
                    }
                }
                if (direction == E) {
                    for (int j = cctvY + 1; j < m; j++) {
                        if (map[cctvX][j] == WALL) {
                            break;
                        }

                        if (map[cctvX][j] == NOT_WATCHED) {
                            map[cctvX][j] = WATCHED;
                            newWatchedBlock.add(new int[]{cctvX, j});
                        }
                    }
                }
                if (direction == S) {
                    for (int j = cctvX + 1; j < n; j++) {
                        if (map[j][cctvY] == WALL) {
                            break;
                        }

                        if (map[j][cctvY] == NOT_WATCHED) {
                            map[j][cctvY] = WATCHED;
                            newWatchedBlock.add(new int[]{j, cctvY});
                        }
                    }
                }
                if (direction == W) {
                    for (int j = cctvY - 1; j >= 0; j--) {
                        if (map[cctvX][j] == WALL) {
                            break;
                        }

                        if (map[cctvX][j] == NOT_WATCHED) {
                            map[cctvX][j] = WATCHED;
                            newWatchedBlock.add(new int[]{cctvX, j});
                        }
                    }
                }
            }

            findMinArea(cctvIndex + 1);

            // 새롭게 추가된 감시 영역 제외.
            for (int[] block : newWatchedBlock) {
                map[block[X]][block[Y]] = NOT_WATCHED;
            }

            // 4번 돌리면 방향은 제자리로 돌아옴. 초기화할 필요 없음.
            rotateClockWise(cctvType);
        }

    }

    public static void rotateClockWise(int cctv) {
        for (int i = 0; i < cctvDirections[cctv].length; i++) {
            cctvDirections[cctv][i] = (cctvDirections[cctv][i] + 1) % 4;
        }
    }

    public static int getArea() {
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == NOT_WATCHED) {
                    area += 1;
                }
            }
        }

        return area;
    }
}
