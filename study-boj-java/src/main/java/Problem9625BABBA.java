import java.io.*;
import java.util.*;

public class Problem9625BABBA {
    static int k;
    static int[][] count;
    static final int A = 0, B = 1;

    public static void main(String[] args) {
        setUp();

        solve();

        System.out.println(count[k][A] + " " + count[k][B]);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();

        count = new int[45 + 1][2];
    }

    static void solve() {
        count[0][A] = 1;
        count[0][B] = 0;

        for (int i = 1; i <= 45; i++) {
            count[i][A] = count[i - 1][B];
            count[i][B] = count[i - 1][A] + count[i - 1][B];
            // 위 식으로부터 아래 식을 도출할 수 있음
            // count[i][A] = count[i - 1][A] + count[i - 2][A];
            // count[i][B] = count[i - 1][B] + count[i - 2][B];
        }
    }
}
