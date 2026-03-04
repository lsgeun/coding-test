import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/30648
public class Problem30648트릭플라워 {
    static int a, b;
    static int r;
    static boolean[][] flower;
    static int time;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(time);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);

        a = sc.nextInt();
        b = sc.nextInt();
        r = sc.nextInt();

        flower = new boolean[r + 1][r + 1];
        for (int i = 0; i < r + 1; i++) {
            Arrays.fill(flower[i], false);
        }
    }

    static void solve() {
        // t는 꽃이 피는 시간
        // x, y는 꽃이 피는 위치
        int t = 0;
        int x = a;
        int y = b;
        while (true) {
            // 꽃이 중복해서 심어진다고 그 시간을 저장하고 종료
            if (flower[x][y]) {
                time = t;
                break;
            } // flower[x][y] == false

            flower[x][y] = true;

            if ((x + 1) + (y + 1) < r) {
                x = x + 1;
                y = y + 1;
            } else { // (x + 1) + (y + 1) >= r
                x = x / 2;
                y = y / 2;
            }

            t += 1;
        }
    }
}
