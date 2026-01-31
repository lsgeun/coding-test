import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2531
public class Problem2531회전초밥 {
    public static int n, d, k, c;
    public static int[] seq;
    public static int typeCountMax = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        d = sc.nextInt();
        k = sc.nextInt();
        c = sc.nextInt();

        seq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seq[i] = sc.nextInt();
        }

        findMax();

        System.out.println(typeCountMax);
    }

    public static void findMax() {
        int[] count = new int[d + 1];
        int typeCount = 1; // c 초밥으로 인해 최소 1로 고정.
        for (int i = 1; i <= k; i++) {
            count[seq[i]]++;

            if (seq[i] == c) {
                continue;
            }

            if (count[seq[i]] == 1) {
                typeCount++;
            }
        }
        typeCountMax = Math.max(typeCountMax, typeCount);

        int first = 1;
        int last = first + k - 1;
        while (first <= n - 1) {
            if (count[seq[first]] == 1) {
                count[seq[first]]--;
                if (seq[first] != c) {
                    typeCount--;
                }
            } else if (count[seq[first]] >= 2) {
                count[seq[first]]--;
            }
            first = (first + 1) % (n + 1);
            first = first == 0 ? 1 : first;

            last = (last + 1) % (n + 1);
            last = last == 0 ? 1 : last;
            if (count[seq[last]] == 0) {
                count[seq[last]]++;
                if (seq[last] != c) {
                    typeCount++;
                }
            } else if (count[seq[last]] >= 1) {
                count[seq[last]]++;
            }

            typeCountMax = Math.max(typeCountMax, typeCount);
        }
    }
}
