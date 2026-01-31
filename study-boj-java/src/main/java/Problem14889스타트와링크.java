import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14889
public class Problem14889스타트와링크 {
    public static int min = Integer.MAX_VALUE;
    public static int n;
    public static int[][] s = new int[20 + 1][20 + 1];
    public static boolean[] visit = new boolean[20 + 1];
    public static boolean stop = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = sc.nextInt();
            }
        }
        sc.close();

        chooseAndCalculate(1, 0);

        System.out.println(min);
    }

    public static void chooseAndCalculate(int index, int count) {
        if (count == n / 2) {
            calculate();
            return;
        }

        // DELETE for 문에서 검사하기 때문에 없어도 되지만 이해를 위해 추가
        if (n < index) {
            return;
        }

        for (int i = index; i <= n; i++) {
            visit[i] = true;
            chooseAndCalculate(i + 1, count + 1);

            if (stop) {
                break;
            }

            visit[i] = false;
        }
    }

    public static void calculate() {
        int[] start = new int[n / 2];
        int startIndex = 0;
        int[] link = new int[n / 2];
        int linkIndex = 0;

        for (int i = 1; i <= n; i++) {
            if (visit[i]) {
                start[startIndex] = i;
                startIndex++;
            } else {
                link[linkIndex] = i;
                linkIndex++;
            }
        }

        int startSum = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n / 2; j++) {
                startSum += s[start[i]][start[j]];
                startSum += s[start[j]][start[i]];
            }
        }

        int linkSum = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n / 2; j++) {
                linkSum += s[link[i]][link[j]];
                linkSum += s[link[j]][link[i]];
            }
        }

        int diff = Math.abs(startSum - linkSum);

        min = Math.min(min, diff);

        if (min == 0) {
            stop = true;
        }
    }
}
