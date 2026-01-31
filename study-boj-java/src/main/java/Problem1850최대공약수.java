import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1850
public class Problem1850최대공약수 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong(), B = sc.nextLong();
        sc.close();

        long dividend = A, divider = B;
        long gcd;
        while (true) {
            long remainder = dividend % divider;

            if (remainder == 0) {
                gcd = divider;
                break;
            }
            if (divider % remainder == 0) {
                gcd = remainder;
                break;
            }

            dividend = divider;
            divider = remainder;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < gcd; i++) {
            bw.write("1");
        }
        bw.flush();
        bw.close();
    }
}
