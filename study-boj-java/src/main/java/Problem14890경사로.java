import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14890
public class Problem14890경사로 {
    public static int n, l;
    public static int[][] roadHeightRow;
    public static int[][] roadHeightColumn;
    public static int roadCount;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        findRoadCount();
        System.out.println(roadCount);
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        roadHeightRow = new int[n][n];
        roadHeightColumn = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int height = Integer.parseInt(st.nextToken());
                roadHeightRow[i][j] = height;
                roadHeightColumn[j][i] = height;
            }
        }

        roadCount = 0;
    }

    public static void findRoadCount() {
        for (int i = 0; i < n; i++) {
            if (canBeRoad(roadHeightRow[i])) {
                roadCount += 1;
            }
            if (canBeRoad(roadHeightColumn[i])) {
                roadCount += 1;
            }
        }
    }

    public static boolean canBeRoad(int[] roadHeight) {
        boolean[] ramp = new boolean[n];
        boolean canBeRoad = true;

        for (int i = 0; i < roadHeight.length - 1; i++) {
            int heightDiff = roadHeight[i] - roadHeight[i + 1];

            if (Math.abs(heightDiff) >= 2) {
                canBeRoad = false;
                break;
            }

            if (heightDiff == 1) { // 앞 > 뒤
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n) { // 범위 벗어나면
                        canBeRoad = false;
                        break;
                    }

                    if (roadHeight[j] != roadHeight[i + 1]) {
                        canBeRoad = false;
                        break;
                    }

                    if (ramp[j]) {
                        canBeRoad = false;
                        break;
                    }

                    ramp[j] = true;
                }
            }

            if (heightDiff == -1) { // 앞 < 뒤
                for (int j = i; j >= i - l + 1; j--) {
                    if (j < 0) { // 범위 벗어나면
                        canBeRoad = false;
                        break;
                    }

                    if (roadHeight[j] != roadHeight[i]) {
                        canBeRoad = false;
                        break;
                    }

                    if (ramp[j]) {
                        canBeRoad = false;
                        break;
                    }

                    ramp[j] = true;
                }
            }

            if (!canBeRoad) {
                break;
            }
        }

        return canBeRoad;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.StringTokenizer;
//
// // https://www.acmicpc.net/problem/14890
// public class Problem14890경사로 {
//     public static int n, l;
//     public static int[][] map;
//     public static int roadCount;
//
//     public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     public static StringTokenizer st;
//
//     public static void main(String[] args) throws IOException {
//         setUp();
//         findRoadCountRow();
//         findRoadCountColumn();
//         System.out.println(roadCount);
//     }
//
//     public static void setUp() throws IOException {
//         st = new StringTokenizer(br.readLine());
//
//         n = Integer.parseInt(st.nextToken());
//         l = Integer.parseInt(st.nextToken());
//
//         map = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             st = new StringTokenizer(br.readLine());
//             for (int j = 0; j < n; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }
//
//         roadCount = 0;
//     }
//
//     public static void findRoadCountRow() {
//         for (int i = 0; i < n; i++) {
//             ArrayList<int[]> floatRoad = new ArrayList<>();
//             final int HEIGHT = 0, HEIGHT_LENGTH = 1;
//
//             int curHeight = map[i][0];
//             int curHeightLength = 0;
//             int roadBlockIndex = 0;
//
//             while (0 <= roadBlockIndex && roadBlockIndex < n) {
//                 if (curHeight == map[i][roadBlockIndex]) {
//                     curHeightLength += 1;
//                 } else { // curHeight != map[i][roadBlockIndex]
//                     floatRoad.add(new int[]{curHeight, curHeightLength});
//
//                     curHeight = map[i][roadBlockIndex];
//                     curHeightLength = 1;
//                 }
//
//                 roadBlockIndex += 1;
//             }
//
//             floatRoad.add(new int[]{curHeight, curHeightLength});
//
//             if (floatRoad.size() == 1) {
//                 roadCount += 1;
//             } else { // floatRoad.size() != 1, 특히 >= 2
//                 boolean canBeRoad = true;
//                 for (int j = 0; j < floatRoad.size() - 1; j++) {
//                     if (Math.abs(floatRoad.get(j)[HEIGHT] - floatRoad.get(j + 1)[HEIGHT]) >= 2) {
//                         canBeRoad = false;
//                         break;
//                     }
//
//                     int lowHeightIndex;
//                     if (floatRoad.get(j)[HEIGHT] < floatRoad.get(j + 1)[HEIGHT]) {
//                         lowHeightIndex = j;
//                     } else { // floatRoad.get(j)[HEIGHT] >= floatRoad.get(j + 1)[HEIGHT], 특히 >
//                         lowHeightIndex = j + 1;
//                     }
//
//                     if (floatRoad.get(lowHeightIndex)[HEIGHT_LENGTH] < l) {
//                         canBeRoad = false;
//                         break;
//                     } else {
//                         // 경사로 길이만큼 뺌.
//                         floatRoad.get(lowHeightIndex)[HEIGHT_LENGTH] -= l;
//                     }
//                 }
//
//                 if (canBeRoad) {
//                     roadCount += 1;
//                 }
//             }
//         }
//     }
//
//     public static void findRoadCountColumn() {
//         for (int i = 0; i < n; i++) {
//             ArrayList<int[]> floatRoad = new ArrayList<>();
//             final int HEIGHT = 0, HEIGHT_LENGTH = 1;
//
//             int curHeight = map[0][i];
//             int curHeightLength = 0;
//             int roadBlockIndex = 0;
//
//             while (0 <= roadBlockIndex && roadBlockIndex < n) {
//                 if (curHeight == map[roadBlockIndex][i]) {
//                     curHeightLength += 1;
//                 } else { // curHeight != map[i][roadBlockIndex]
//                     floatRoad.add(new int[]{curHeight, curHeightLength});
//
//                     curHeight = map[roadBlockIndex][i];
//                     curHeightLength = 1;
//                 }
//
//                 roadBlockIndex += 1;
//             }
//
//             floatRoad.add(new int[]{curHeight, curHeightLength});
//
//             if (floatRoad.size() == 1) {
//                 roadCount += 1;
//             } else { // floatRoad.size() != 1, 특히 >= 2
//                 boolean canBeRoad = true;
//                 for (int j = 0; j < floatRoad.size() - 1; j++) {
//                     if (Math.abs(floatRoad.get(j)[HEIGHT] - floatRoad.get(j + 1)[HEIGHT]) >= 2) {
//                         canBeRoad = false;
//                         break;
//                     }
//
//                     int lowHeightIndex;
//                     if (floatRoad.get(j)[HEIGHT] < floatRoad.get(j + 1)[HEIGHT]) {
//                         lowHeightIndex = j;
//                     } else { // floatRoad.get(j)[HEIGHT] >= floatRoad.get(j + 1)[HEIGHT], 특히 >
//                         lowHeightIndex = j + 1;
//                     }
//
//                     if (floatRoad.get(lowHeightIndex)[HEIGHT_LENGTH] < l) {
//                         canBeRoad = false;
//                         break;
//                     } else {
//                         // 경사로 길이만큼 뺌.
//                         floatRoad.get(lowHeightIndex)[HEIGHT_LENGTH] -= l;
//                     }
//                 }
//
//                 if (canBeRoad) {
//                     roadCount += 1;
//                 }
//             }
//         }
//     }
// }
