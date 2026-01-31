import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2573

public class Problem2573빙산 {
    public static int n, m;
    public static int[][] map;
    public static ArrayList<int[]> glacierCells;
    public static ArrayList<Integer> neighborSeaCellCount;
    public static int time;
    public static int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        glacierCells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int cell = Integer.parseInt(st.nextToken());
                map[i][j] = cell;

                if (cell != 0) {
                    // 일종의 setGlacierCells() 역할
                    glacierCells.add(new int[]{i, j});
                }
            }
        }

        setNeighborSeaCellCount();

        time = 0;
        int glacierCount = calculateGlacierCount();
        while (true) {
            if (glacierCount == 0 || glacierCount >= 2) {
                break;
            } // 아래 코드는 glacierCount == 1인 상황

            meltGlacier();
            setGlacierCells(); // 녹은 빙하 제거
            setNeighborSeaCellCount(); // 안 녹은 빙하의 주변 바다셀 개수 계산
            glacierCount = calculateGlacierCount();
            time++;
        }

        if (glacierCount == 0) {
            System.out.println(0);
        } else { // glacierCount >= 2
            System.out.println(time);
        }
    }

    public static void setNeighborSeaCellCount() {
        neighborSeaCellCount = new ArrayList<>();

        for (int i = 0; i < glacierCells.size(); i++) {
            int seaCellCount = 0;
            for (int[] dir : fourDirection) {
                int neighborCellX = glacierCells.get(i)[0] + dir[0];
                int neighborCellY = glacierCells.get(i)[1] + dir[1];

                if (!isInMap(neighborCellX, neighborCellY)) {
                    continue;
                }

                if (map[neighborCellX][neighborCellY] == 0) {
                    seaCellCount++;
                }
            }

            neighborSeaCellCount.add(seaCellCount);
        }
    }

    public static void setGlacierCells() {
        ArrayList<int[]> newGlacierCells = new ArrayList<>();
        for (int[] glacierCell : glacierCells) {
            int glacierCellX = glacierCell[0];
            int glacierCellY = glacierCell[1];
            if (map[glacierCellX][glacierCellY] == 0) {
                continue;
            }

            newGlacierCells.add(new int[]{glacierCellX, glacierCellY});
        }

        glacierCells = newGlacierCells;
    }

    public static void meltGlacier() {
        for (int i = 0; i < glacierCells.size(); i++) {
            int glacierCellX = glacierCells.get(i)[0];
            int glacierCellY = glacierCells.get(i)[1];
            if (map[glacierCellX][glacierCellY] > neighborSeaCellCount.get(i)) {
                map[glacierCellX][glacierCellY] -= neighborSeaCellCount.get(i);
            } else {
                map[glacierCellX][glacierCellY] = 0;
            }
        }
    }

    public static int calculateGlacierCount() {
        int[][] glacierTypes = new int[n][m];

        int glacierType = 1;
        for (int i = 0; i < glacierCells.size(); i++) {
            int glacierCellX = glacierCells.get(i)[0];
            int glacierCellY = glacierCells.get(i)[1];

            if (map[glacierCellX][glacierCellY] > 0 && glacierTypes[glacierCellX][glacierCellY] == 0) {
                Queue<int[]> queue = new LinkedList<>();
                glacierTypes[glacierCellX][glacierCellY] = glacierType;
                queue.add(new int[]{glacierCellX, glacierCellY});

                while (!queue.isEmpty()) {
                    int[] glacierCell = queue.poll();

                    for (int[] dir : fourDirection) {
                        int neighborCellX = glacierCell[0] + dir[0];
                        int neighborCellY = glacierCell[1] + dir[1];

                        if (!isInMap(neighborCellX, neighborCellY)) {
                            continue;
                        }

                        // 해당 셀이 빙하셀일 때
                        if (map[neighborCellX][neighborCellY] > 0) {
                            // 빙하 종류가 할당되지 않았을 때
                            if (glacierTypes[neighborCellX][neighborCellY] == 0) {
                                glacierTypes[neighborCellX][neighborCellY] = glacierType;
                                queue.add(new int[]{neighborCellX, neighborCellY});
                            }
                        }
                    }
                }

                glacierType++;
            }
        }
        int glacierCount = glacierType - 1;

        return glacierCount;
    }

    public static boolean isInMap(int x, int y) {
        if ((0 <= x && x < n) && (0 <= y && y < m)) {
            return true;
        }
         return false;
    }
}
