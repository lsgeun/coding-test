import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1934
public class Problem1934최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int A = Integer.parseInt(st.nextToken());
            final int B = Integer.parseInt(st.nextToken());

            int dividend = A, divider = B;
            int gcd;
            while (true) {
                int remainder = dividend % divider;

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

            int lcm = A * B / gcd;
            System.out.println(lcm);
        }

        br.close();
    }
}
