import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2468
public class Problem2468안전영역 {
    public static int n;
    public static int[][] map;
    public static int[][] safeAreaTypes;
    public static int maxSafeAreaCount;
    public static final int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };
    public static final int X = 0, Y = 1;
    public static int waterLevel;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        maxSafeAreaCount = 1; // 비가 안 내리는 경우

        findMaxSafeAreaCount();

        System.out.println(maxSafeAreaCount);
    }

    public static void findMaxSafeAreaCount() {
        for (waterLevel = 1; waterLevel <= 100; waterLevel++) {
            safeAreaTypes = new int[n][n];
            int safeAreaType = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (didVisit(i, j)) {
                        continue;
                    }
                    if (isInWater(i, j)) {
                        continue;
                    }

                    int[] startArea = new int[]{i ,j};
                    findSafeArea(safeAreaType, startArea);
                    safeAreaType++;
                }
            }

            int safeAreaCount = safeAreaType - 1;
            maxSafeAreaCount = Math.max(maxSafeAreaCount, safeAreaCount);
        }
    }

    public static void findSafeArea(int safeAreaType, int[] startArea) {
        Queue<int[]> queue = new LinkedList<>();
        safeAreaTypes[startArea[X]][startArea[Y]] = safeAreaType;
        queue.add(startArea);

        while (!queue.isEmpty()) {
            int[] curArea = queue.poll();

            for (int[] dir : fourDirection) {
                int[] nextArea = new int[]{curArea[X] + dir[X], curArea[Y] + dir[Y]};

                if (!isInMap(nextArea)) {
                    continue;
                }
                if (didVisit(nextArea)) {
                    continue;
                }
                if (isInWater(nextArea)) {
                    continue;
                }

                safeAreaTypes[nextArea[X]][nextArea[Y]] = safeAreaType;
                queue.add(nextArea);
            }
        }
    }

    public static boolean isInMap(int[] area) {
        if (0 <= area[X] && area[X] < n &&
                0 <= area[Y] && area[Y] < n) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean didVisit(int[] area) {
        return safeAreaTypes[area[X]][area[Y]] != 0;
    }

    public static boolean didVisit(int areaX, int areaY) {
        return safeAreaTypes[areaX][areaY] != 0;
    }

    public static boolean isInWater(int[] area) {
        return map[area[X]][area[Y]] <= waterLevel;
    }

    public static boolean isInWater(int areaX, int areaY) {
        return map[areaX][areaY] <= waterLevel;
    }
}
