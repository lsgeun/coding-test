import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17140
public class Problem17140이차원배열과연산 {
    static int r, c, k;
    static int[][] arr;
    static int rowCount, columnCount;
    static int minSecond;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(minSecond);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[100 + 1][100 + 1];
        for (int i = 0; i < 100 + 1; i++) {
            Arrays.fill(arr[i], 0);
        }

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rowCount = 3;
        columnCount = 3;

        minSecond = 0;
    }

    static void solve() {
        int second = 0;
        if (arr[r][c] == k) {
            minSecond = second;
            return;
        }

        for (second = 1; second <= 100; second++) {
            OneSecondPasses();

            if (arr[r][c] == k) {
                minSecond = second;
                return;
            }
        }

        // 100초가 지나도, arr[r][c] == k가 아니라면
        minSecond = -1;
    }

    static void OneSecondPasses() {
        if (rowCount >= columnCount) {
            for (int i = 1; i <= rowCount; i++) {
                int[] count = new int[100 + 1];
                for (int j = 1; j <= columnCount; j++) {
                    count[arr[i][j]] += 1;
                }

                ArrayList<Info> numCounts = new ArrayList<>();
                for (int num = 1; num <= 100; num++) {
                    // num = 0 제외
                    if (count[num] > 0) {
                        numCounts.add(new Info(num, count[num]));
                    }
                }
                Collections.sort(numCounts);

                if (numCounts.size() >= 51) {
                    numCounts = new ArrayList<>(numCounts.subList(0, 50));
                }
                for (int k = 0; k < numCounts.size(); k++) {
                    arr[i][2 * (k + 1) - 1] = numCounts.get(k).num;
                    arr[i][2 * (k + 1)] = numCounts.get(k).count;
                }
                for (int k = 2 * (numCounts.size() + 1) - 1; k <= 100; k++) {
                    arr[i][k] = 0;
                }

                int curColumnCount = 2 * numCounts.size();
                columnCount = Math.max(columnCount, curColumnCount);
            }
        } else { // rowCount < columnCount
            for (int j = 1; j <= columnCount; j++) {
                int[] count = new int[100 + 1];
                for (int i = 1; i <= rowCount; i++) {
                    count[arr[i][j]] += 1;
                }

                ArrayList<Info> numCounts = new ArrayList<>();
                for (int num = 1; num <= 100; num++) {
                    // num = 0 제외
                    if (count[num] > 0) {
                        numCounts.add(new Info(num, count[num]));
                    }
                }
                Collections.sort(numCounts);

                if (numCounts.size() >= 51) {
                    numCounts = new ArrayList<>(numCounts.subList(0, 50));
                }
                for (int k = 0; k < numCounts.size(); k++) {
                    arr[2 * (k + 1) - 1][j] = numCounts.get(k).num;
                    arr[2 * (k + 1)][j] = numCounts.get(k).count;
                }
                for (int k = 2 * (numCounts.size() + 1) - 1; k <= 100; k++) {
                    arr[k][j] = 0;
                }

                int curRowCount = 2 * numCounts.size();
                rowCount = Math.max(rowCount, curRowCount);
            }
        }
    }

    static class Info implements Comparable<Info> {
        int num;
        int count;

        public Info(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Info i) {
            if (this.count != i.count) {
                return this.count - i.count;
            } else {
                return this.num - i.num;
            }
        }
    }
}
