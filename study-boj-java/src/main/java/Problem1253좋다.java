import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1253
public class Problem1253좋다 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(arr);

        int count = 0, left, right;
        for (int K = 0; K < N; K++) {
            left = 0;
            right = N - 1;
            while (left < right) {
                if (arr[left] + arr[right] == arr[K]) {
                    if (left != K && right != K) {
                        count++;
                        break;
                    } else if (left == K) {
                        left++;
                    } else if (right == K) {
                        right--;
                    }
                } else if (arr[left] + arr[right] < arr[K]) {
                    left++;
                } else { // arr[left] + arr[right] > arr[K]
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
