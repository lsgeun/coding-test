import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14502
public class Problem14502연구소 {
    public static int n, m;
    public static int[][] map;
    public static ArrayList<int[]> blanks;
    public static ArrayList<int[]> viruses;
    public static int maxSafeZoneArea = Integer.MIN_VALUE;
    public static int[][] fourDirection = {
            // 상, 하, 좌, 우
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},};

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n + 1][m + 1];
        blanks = new ArrayList<>();
        viruses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                }
            }
        }
        sc.close();

        findMaxSafeZoneArea(0, 0);

        System.out.println(maxSafeZoneArea);
    }

    public static void findMaxSafeZoneArea(int blankIndex, int wallCount) {
        if (wallCount == 3) {
            findSafeZoneAreaAndCalculateMax();
            return;
        }

        int[] blank;
        int blankX, blankY;
        for (int i = blankIndex; i < blanks.size(); i++) {
            blank = blanks.get(i);
            blankX = blank[0];
            blankY = blank[1];

            map[blankX][blankY] = 1;
            findMaxSafeZoneArea(i + 1, wallCount + 1);
            map[blankX][blankY] = 0;
        }
    }

    public static void findSafeZoneAreaAndCalculateMax() {
        int[][] copyMap = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            copyMap[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : viruses) {
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            int[] virus = queue.poll();

            for (int[] direction : fourDirection) {
                int mapStateX = virus[0] + direction[0];
                int mapStateY = virus[1] + direction[1];
                if (1 <= mapStateX && mapStateX <= n &&
                        1 <= mapStateY && mapStateY <= m &&
                        copyMap[mapStateX][mapStateY] == 0) {
                    copyMap[mapStateX][mapStateY] = 2;
                    queue.add(new int[]{mapStateX, mapStateY});
                }
            }
        }

        int safeZoneArea = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (copyMap[i][j] == 0) {
                    safeZoneArea++;
                }
            }
        }

        maxSafeZoneArea = Math.max(maxSafeZoneArea, safeZoneArea);
    }
}
