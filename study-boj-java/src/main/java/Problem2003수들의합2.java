import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2003
public class Problem2003수들의합2 {
    static int n, m;
    static int[] arr;
    static int count;

    public static void main(String[] args) throws IOException {
        setUp();
        calculateCount();
        System.out.println(count);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;
    }

    static void calculateCount() {
        int sum = 0;
        int start = 0;
        int end = 0;

        while (true) {
            if (sum < m) {
                if (end == n) {
                    break;
                }

                sum += arr[end];
                end++;
            } else if (sum == m) {
                count++;
                sum -= arr[start];
                start++;
            } else { // sum > m
                sum -= arr[start];
                start++;
            }
        }
    }
}

// import java.io.InputStreamReader;
// import java.util.Scanner;
//
// // https://www.acmicpc.net/problem/2003
// public class Problem2003수들의합2 {
//     public static int n, m;
//     public static int[] arr;
//     public static int count = 0;
//     public static int left, right, sumLToR;
//
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(new InputStreamReader(System.in));
//         n = sc.nextInt();
//         m = sc.nextInt();
//
//         arr = new int[n + 1];
//         for (int i = 1; i <= n; i++) {
//             arr[i] = sc.nextInt();
//         }
//
//         findCount();
//
//         System.out.println(count);
//     }
//
//     public static void findCount() {
//         left = 1;
//         right = 1;
//         sumLToR = arr[left];
//         while (!(right >= arr.length)) {
//             if (sumLToR == m) {
//                 count++;
//                 moveLeft();
//                 if (left > right) {
//                     moveRight();
//                 }
//             } else if (sumLToR < m) {
//                 moveRight();
//             } else if (sumLToR > m) {
//                 moveLeft();
//                 if (left > right) {
//                     moveRight();
//                 }
//             }
//         }
//     }
//
//     public static void moveRight() {
//         right++;
//         if (1 <= right && right < arr.length) {
//             sumLToR += arr[right];
//         }
//     }
//     public static void moveLeft() {
//         sumLToR -= arr[left];
//         left++;
//     }
// }
