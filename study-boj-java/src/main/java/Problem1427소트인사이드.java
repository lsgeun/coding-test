import java.util.Scanner;

// https://www.acmicpc.net/problem/1427
public class Problem1427소트인사이드 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] arr = sc.next().toCharArray();
        sc.close();
        int arrLength = arr.length;
        for (int i = 0; i < arrLength - 1; i++) {
            int maxIndex = i;
            for (int j = i; j < arrLength; j++) {
                if (arr[maxIndex] < arr[j]) {
                    maxIndex = j;
                }
            }

            char temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }

        for (int i = 0; i < arrLength; i++) {
            System.out.print(arr[i]);
        }
    }
}
