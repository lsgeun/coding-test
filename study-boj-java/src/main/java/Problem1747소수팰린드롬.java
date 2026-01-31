import java.util.Scanner;

// https://www.acmicpc.net/problem/1747
public class Problem1747소수팰린드롬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        int[] A = new int[10000000 + 1];

        for (int i = 2; i <= A.length - 1; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(A.length - 1); i++) {
            for (int j = 2 * i; j <= A.length - 1; j += i) {
                A[j] = 0;
            }
        }

        for (int i = N; i <= A.length - 1; i++) {
            if (A[i] == 0) {
                continue;
            }

            if (isPalindrome(A[i])) {
                System.out.println(A[i]);
                break;
            }
        }
    }

    public static boolean isPalindrome(int number) {
        // int를 char[]로 바꾸는 부분이 생소
        char[] temp = String.valueOf(number).toCharArray();

        int first = 0, last = temp.length - 1;
        while (first < last) {
            if (temp[first] != temp[last]) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }
}
