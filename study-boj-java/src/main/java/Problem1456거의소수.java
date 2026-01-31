import java.util.Scanner;

// https://www.acmicpc.net/problem/1456
public class Problem1456거의소수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final long MIN = sc.nextLong(), MAX = sc.nextLong();
        sc.close();
        long[] A = new long[10000000 + 1];

        for (int i = 2; i <= A.length - 1; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(A.length - 1); i++) {
            if (A[i] == 0) {
                continue;
            }
            for (int j = 2 * i; j <= A.length - 1; j += i) {
                A[j] = 0;
            }
        }

        int count = 0;
        for (int i = 2; i <= A.length - 1; i++) {
            if (A[i] == 0) {
                continue;
            }

            double maxDividedByAiPower = (double) MAX / (double) A[i], minDividedByAiPower = (double) MIN / (double) A[i];
            while ((double) A[i] <= maxDividedByAiPower) {
                if (minDividedByAiPower <= (double) A[i]) {
                    count++;
                }

                maxDividedByAiPower /= (double) A[i];
                minDividedByAiPower /= (double) A[i];
            }
        }
        System.out.println(count);
    }
}
