import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13144
public class Problem13144ListOfUniqueNumbers {
    public static int n;
    public static int[] seq;
    public static int[] count = new int[100000 + 1];
    public static long numberOfCases = 0;
    public static int left, right, previousRight;
    public static int length = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        seq = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        findNumberOfCases();

        System.out.println(numberOfCases);
    }

    public static void findNumberOfCases() {
        left = 1;
        right = 1;
        previousRight = -1;
        count[seq[left]]++;
        length = 1;
        boolean canCalculate = true;
        boolean hasDuplicatedCases = false;
        int duplicatedLength = 0;
        while (!(right >= seq.length)) {
            if (count[seq[right]] >= 2) {
                if (canCalculate) {
                    canCalculate = false;

                    hasDuplicatedCases = previousRight != -1;
                    duplicatedLength = previousRight - left;

                    previousRight = right;

                    numberOfCases += calculateNumberOfCases(length - 1);
                    if (hasDuplicatedCases) {
                        numberOfCases -= calculateNumberOfCases(duplicatedLength);
                    }
                }

                if (left == right) {
                    moveRightBackward();
                }
                moveLeftBackward();
            } else { // 0 <= count[seq[right]] <= 1
                canCalculate = true;
                moveRightBackward();
            }
        }

        hasDuplicatedCases = (previousRight != -1);
        duplicatedLength = previousRight - left;

        numberOfCases += calculateNumberOfCases(length); // right >= seq.length 이면 length 증가 안 됨
        if (hasDuplicatedCases) {
            numberOfCases -= calculateNumberOfCases(duplicatedLength);
        }
    }

    public static void moveLeftBackward() {
        if (1 <= left && left <= seq.length - 1) {
            count[seq[left]]--;
            length--;
        }
        left++;
    }

    public static void moveRightBackward() {
        right++;
        if (1 <= right && right <= seq.length - 1) {
            count[seq[right]]++;
            length++;
        }
    }

    public static long calculateNumberOfCases(int length) {
        return (long) length * (length + 1) / 2;
    }
}
