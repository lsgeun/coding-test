import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2661
public class Problem2661좋은수열 {
    public static int n;
    public static int[] seq;
    public static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        seq = new int[n + 1];

        findMinimumGoodSequence(1);

        printSeq();
    }

    public static void findMinimumGoodSequence(int index) {
        if (index == seq.length) {
            found = true;
            return;
        }

        for (int num = 1; num <= 3; num++) {
            seq[index] = num;

            if (!isGoodSequence(index)) {
                seq[index] = 0;
                continue;
            }

            findMinimumGoodSequence(index + 1);

            if (found) {
                break;
            }

            seq[index] = 0;
        }
    }

    public static boolean isGoodSequence(int index) {
        if (index == 1) {
            return true;
        }

        for (int groupSize = 1; groupSize <= index / 2; groupSize++) {
            int firstNum = 0;
            for (int firstNumIndex = index - 2 * groupSize + 1;
                 firstNumIndex <= index - groupSize;
                 firstNumIndex++) {
                firstNum = firstNum * 10 + seq[firstNumIndex];
            }

            int secondNum = 0;
            for (int secondNumIndex = index - groupSize + 1;
                 secondNumIndex <= index;
                 secondNumIndex++) {
                secondNum = secondNum * 10 + seq[secondNumIndex];
            }

            if (firstNum == secondNum) {
                return false;
            }
        }

        return true;
    }

    public static void printSeq() {
        for (int i = 1; i <= n; i++) {
            System.out.print(seq[i]);
        }
    }
}

