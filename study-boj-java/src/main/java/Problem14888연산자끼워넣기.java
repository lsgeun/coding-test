import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14888
public class Problem14888연산자끼워넣기 {
    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;
    public static int n;
    public static int[] seq;
    public static int[] operationCount = new int[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();

        seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            operationCount[i] = sc.nextInt();
        }
        sc.close();

        calculate(seq[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void calculate(int calculatedValue, int index) {
        if (index == n) {
            max = Math.max(max, calculatedValue);
            min = Math.min(min, calculatedValue);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operationCount[i] > 0) {
                operationCount[i]--;
                if (i == 0) {
                    calculate(calculatedValue + seq[index], index + 1);
                } else if (i == 1) {
                    calculate(calculatedValue - seq[index], index + 1);
                } else if (i == 2) {
                    calculate(calculatedValue * seq[index], index + 1);
                } else if (i == 3) {
                    calculate(calculatedValue / seq[index], index + 1);
                }
                operationCount[i]++;
            }
        }
    }
}
