import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1895
public class Problem1895필터 {
    static int r, c;
    static int[][] image;
    static int[][] filtering;

    static int t;
    static int pixelCount;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(pixelCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        image = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                image[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        filtering = new int[r - 2][c - 2];

        t = Integer.parseInt(br.readLine());
        pixelCount = 0;
    }

    static void solve() {
        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < c - 2; j++) {
                int medium = getMedium(i, j);
                filtering[i][j] = medium;
                if (medium >= t) {
                    pixelCount += 1;
                }
            }
        }

    }

    static int getMedium(int x, int y) {
        int[] arr = new int[9];
        int index = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                arr[index] = image[i][j];
                index += 1;
            }
        }

        Arrays.sort(arr);
        int medium = arr[4];

        return medium;
    }
}
