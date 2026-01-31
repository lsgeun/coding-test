import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2023
public class Problem2023신기한소수 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();

        int[] oneDigitPrime = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            DFS(oneDigitPrime[i], 1);
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int prevNum, int preDigitCount) throws IOException {
        if (!(isPrime(prevNum))) {
            return;
        }
        // isPrime(prevNum) == true
        if (preDigitCount == N) {
            bw.write(prevNum + "\n");
            return;
        }
        // preDigitCount < N

        int[] units = {1, 3, 7, 9}; // units는 '일의 자리'라는 의미
        for (int i = 0; i < 4; i++) {
            int curNum = prevNum * 10 + units[i];
            int curDigitCount = preDigitCount + 1;

            if (isPrime(curNum)) {
                if (curDigitCount < N) {
                    DFS(curNum, curDigitCount);
                } else { // curDigitCount == N
                    bw.write(curNum + "\n");
                }
            }
        }
    }

    public static boolean isPrime(int num) {
        boolean isPrime = true;
        int halfNum = num / 2;
        for (int i = 2; i <= halfNum; i++) {
            if (num % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }
}
