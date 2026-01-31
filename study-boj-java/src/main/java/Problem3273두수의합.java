import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/3273
public class Problem3273두수의합 {
    static public int n;
    static public int[] seq;
    static public int x;
    static public int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        seq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seq[i] = sc.nextInt();
        }
        x = sc.nextInt();
        sc.close();

        findPair();

        System.out.println(count);
    }

    public static void findPair() {
        int[] copySeq = seq.clone();
        Arrays.sort(copySeq);

        int left = 1;
        int right = copySeq.length - 1;

        while (left < right) {
            int sum = copySeq[left] + copySeq[right];
            if (sum == x) {
                left++;
                right--;
                count++;
            } else if (sum < x) {
                left++;
            } else if (sum > x) {
                right--;
            }
        }
    }
}
