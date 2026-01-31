import java.util.Scanner;

// https://www.acmicpc.net/problem/1016
public class Problem1016제곱ㄴㄴ수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long MIN = sc.nextLong(), MAX = sc.nextLong();
        sc.close();

        boolean[] Check = new boolean[(int) (MAX - MIN + 1)];

        for (long i = 2; i <= Math.sqrt(MAX); i++) {
            long pow = i * i;
            // MIN <= pow * k를 만족하는 최소의 k = start_index
            long start_index = MIN / pow;
            if (MIN % pow != 0) {
                start_index++;
            }
            for (long j = start_index; pow * j <= MAX; j++) {
                Check[(int) ((j * pow) - MIN)] = true;
            }
        }

        int count = 0;
        for (int i = 0; i <= MAX - MIN; i++) {
            if (!Check[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
