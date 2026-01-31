import java.util.Scanner;

// https://www.acmicpc.net/problem/21568
public class Problem21568AxByC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
        sc.close();

        int gcd = gcd(a, b);
        if (c % gcd != 0) {
            System.out.println(-1);
        } else {
            int k = c / gcd;
            int[] xy = execute(a, b);
            System.out.println(xy[0] * k + " " + xy[1] * k);
        }
    }

    public static int gcd(int a, int b) {
        int dividend = a, divider = b;
        int gcd;
        while (true) {
            int remainder = dividend % divider;

            if (remainder == 0) {
                gcd = divider;
                break;
            }

            dividend = divider;
            divider = remainder;
        }

        // 나머지가 음수가 될 수 있으므로 절대값 반환
        return Math.abs(gcd);
    }

    public static int[] execute(int a, int b) {
        int[] xy = new int[2];

        if (b == 0) {
            xy[0] = 1;
            xy[1] = 0;

            return xy;
        }

        int q = a / b;
        int[] pxpy = execute(b, a % b);
        xy[0] = pxpy[1];
        xy[1] = pxpy[0] - pxpy[1] * q;

        return xy;
    }
}

