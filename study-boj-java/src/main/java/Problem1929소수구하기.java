import java.util.Scanner;

// https://www.acmicpc.net/problem/1929
public class Problem1929소수구하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt(), N = sc.nextInt();
        sc.close();
        int[] A = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (A[i] == 0) {
                continue;
            }
            for (int j = 2 * i; j <= N; j = j + i) {
                A[j] = 0;
            }
        }

        for (int i = M; i <= N; i++) {
            if (A[i] != 0) {
                System.out.println(A[i]);
            }
        }
    }
}
