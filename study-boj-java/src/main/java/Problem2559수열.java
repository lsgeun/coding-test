import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2559
public class Problem2559수열 {
    public static int n, k;
    public static int[] seq;
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        k = sc.nextInt();

        seq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seq[i] = sc.nextInt();
        }

        findMax();

        System.out.println(max);
    }

    public static void findMax() {
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += seq[i];
        }
        max = Math.max(max, sum);

        int left = 1, right = k;
        while (left <= n - k) {
            sum -= seq[left];
            left++;
            right++;
            sum += seq[right];
            max = Math.max(max, sum);
        }
    }
}
