import java.util.Scanner;

// https://www.acmicpc.net/problem/11689
public class Problem11689GCDnk1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();
        long result = n;

        // n의 배수를 찾는 것은 n의 제곱근까지만 해도 충분
        // 2(1 아님)부터 차례대로 수를 찾는 것이 n을 나누는 가장 작은 소수를 찾는 방법임
        for (int p = 2; p <= Math.sqrt(n); p++) {
            if (n % p == 0) {
                result = result - result / p;
            }
            // 탐색 범위를 줄이는 동작. 소인수라면 n에서 p^k을 모두 제거해도 그 범위에 있음.
            while (n % p == 0) {
                n /= p;
            }
        }

        if (n > 1) {
            // n의 지수의 밑 부분이 가장 큰 소수의 지수가 1인 경우
            // 가장 작은 소수부터 가장 큰 소수로 n을 소인수 분해했을 때
            // n = p1^a1 * p2^a2 * ... * pk^1인 경우
            // 2 이상이라면 n=1이 나와야 함.
            result = result - result / n;
        }

        System.out.println(result);
    }
}
