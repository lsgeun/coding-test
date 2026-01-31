import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/16472
public class Problem16472고냥이 {
    public static int maxTypeCount;
    public static int typeCount;
    public static int[] alphabetCount = new int[26];
    public static int maxLength;
    public static int length;
    public static String seq;
    public static int left, right;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        maxTypeCount = sc.nextInt();
        seq = sc.next();

        findMaxLength();

        System.out.println(maxLength);
    }

    public static void findMaxLength() {
        left = 0;
        right = 0;
        int alphabetIndex = seq.charAt(left) - 'a';
        alphabetCount[alphabetIndex] = 1;
        typeCount = 1;
        length  = 1;

        while (!(right >= seq.length())) {
            if (typeCount <= maxTypeCount) {
                maxLength = Math.max(maxLength, length);
                moveRightBackward();
            } else { // typeCount > maxTypeCount
                if (left == right) {
                    moveRightBackward();
                }
                moveLeftBackward();
            }
        }
    }

    public static void moveLeftBackward() {
        if (0 <= left && left <= seq.length() - 1) {
            int alphabetIndex = seq.charAt(left) - 'a';
            alphabetCount[alphabetIndex]--;
            if (alphabetCount[alphabetIndex] == 0) {
                typeCount--;
            }
            length--;
        }
        left++;
    }

    public static void moveRightBackward() {
        right++;
        if (0 <= right && right <= seq.length() - 1) {
            int alphabetIndex = seq.charAt(right) - 'a';
            alphabetCount[alphabetIndex]++;
            if (alphabetCount[alphabetIndex] == 1) {
                typeCount++;
            }
            length++;
        }
    }
}
