import java.util.Scanner;

// https://www.acmicpc.net/problem/1806
public class Problem1806부분합 {
    static int n, s;
    static int[] arr;
    static int minLength;

    public static void main(String[] args) {
        setUp();
        findMinLength();

        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // s가 소수일 수도 있으므로, 천장 함수 사용
        s = (int) Math.ceil(sc.nextDouble());

        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        minLength = Integer.MAX_VALUE;
    }

    static void findMinLength() {
        int start = 0;
        int end = 0;
        int sum = 0;

        while (end < n) {
            // sum < s
            sum += arr[end];
            end++;

            while (sum >= s) {
                minLength = Math.min(minLength, end - start);
                sum -= arr[start];
                start++;
            }
        }
    }
}
