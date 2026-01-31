import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/24416
public class Problem24416알고리즘수업피보나치수1 {
    static int n;
    static int recursionCount;
    static int dpCount;

    public static void main(String[] args) throws IOException {
        setUp();
        fibonacciRecursion(n);
        fibonacciDP(n);
        System.out.println(recursionCount + " " + dpCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        recursionCount = 0;
        dpCount = 0;
    }

    static int fibonacciRecursion(int num) {
        if (num == 1 || num == 2) {
            recursionCount += 1;
            return 1;
        }

        return fibonacciRecursion(num - 2) + fibonacciRecursion(num - 1);
    }

    static int fibonacciDP(int num) {
        int[] fibonacci = new int[40 + 1];
        fibonacci[1] = 1;
        fibonacci[2] = 1;

        for (int i = 3; i <= num; i++) {
            dpCount += 1;
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
        }

        return fibonacci[num];
    }
}
