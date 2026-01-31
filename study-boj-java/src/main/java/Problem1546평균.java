import java.util.Scanner;

// https://www.acmicpc.net/problem/1546
public class Problem1546평균 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N;
        N = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        int max = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println((float) sum / max / N * 100);
    }
}