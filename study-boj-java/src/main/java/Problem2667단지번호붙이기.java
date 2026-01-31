import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2667
public class Problem2667단지번호붙이기 {
    public static int n;
    public static int[][] map;
    public static int[][] housingComplex;
    public static int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        sc.nextLine();

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String line = sc.nextLine();
            for (int j = 1; j <= n; j++) {
                map[i][j] = line.charAt(j-1) - '0';
            }
        }
        sc.close();

        housingComplex = new int[n + 1][n + 1];

        findHousingComplexAndPrintCount();
    }

    public static void findHousingComplexAndPrintCount() {
        int curHousingComplex = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1 && housingComplex[i][j] == 0) {
                    findHousingComplexOf(i, j, curHousingComplex);
                    curHousingComplex++;
                }
            }
        }

        int housingComplexTypeCount = curHousingComplex - 1;
        int[] housingComplexCount = new int[housingComplexTypeCount + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                housingComplexCount[housingComplex[i][j]]++;
            }
        }

        // 단지 수 출력
        System.out.println(curHousingComplex - 1);

        // 단지별 집 수 오름차순 출력
        int[] sortedHousingComplexCount = Arrays.copyOfRange(housingComplexCount, 1, housingComplexTypeCount + 1);
        Arrays.sort(sortedHousingComplexCount); // 인덱스 0은 뺌.
        for (int count : sortedHousingComplexCount) {
            System.out.println(count);
        }
    }

    public static void findHousingComplexOf(int houseX, int houseY, int curHousingComplex) {
        Queue<int[]> queue = new LinkedList<>();
        housingComplex[houseX][houseY] = curHousingComplex;
        queue.add(new int[]{houseX, houseY});

        while (!queue.isEmpty()) {
            int[] curHouse = queue.poll();

            for (int[] dir : fourDirection) {
                int nextHouseX = curHouse[0] + dir[0];
                int nextHouseY = curHouse[1] + dir[1];

                if (!(1 <= nextHouseX && nextHouseX <= n) ||
                        !(1 <= nextHouseY && nextHouseY <= n)) {
                    continue;
                }

                if (map[nextHouseX][nextHouseY] == 1 &&
                        housingComplex[nextHouseX][nextHouseY] == 0) {
                    housingComplex[nextHouseX][nextHouseY] = curHousingComplex;
                    queue.add(new int[]{nextHouseX, nextHouseY});
                }
            }
        }
    }
}
