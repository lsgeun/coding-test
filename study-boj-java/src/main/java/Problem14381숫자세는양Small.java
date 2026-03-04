import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14381
public class Problem14381숫자세는양Small {
    static int t, n;
    static boolean[] digits;
    static int falseCount; // digits의 false 개수

    public static void main(String[] args) {
        setUp();

        solve();
    }

    static void setUp() {
        digits = new boolean[10];
        resetDigits();
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        for (int i = 1; i <= t; i++) {
            n = sc.nextInt();

            if (n == 0) {
                System.out.println("Case #" + i + ": INSOMNIA");
            } else {
                resetDigits();

                int j = 1; // 배수
                int num = n;
                while (true) {
                    if (falseCount == 0) {
                        System.out.println("Case #" + i + ": " + num);
                        break;
                    }

                    num = n * j;
                    checkDigits(num);

                    j += 1;
                }
            }
        }
    }

    static void checkDigits(int num) {
        int remainder;

        while (true) {
            if (num == 0) {
                break;
            }

            remainder = num % 10;
            num = num / 10;

            if (!digits[remainder]) {
                falseCount -= 1;
                digits[remainder] = true;
            }
        }
    }

    static void resetDigits() {
        Arrays.fill(digits, false);
        falseCount = 10;
    }
}
