import java.util.Scanner;

// https://www.acmicpc.net/problem/2750
public class Problem2750수정렬하기 {
    public static int n;
    public static int[] seq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = sc.nextInt();
        }

        bubbleSort();

        for (int i = 0; i < n; i++) {
            System.out.println(seq[i]);
        }

        sc.close();
    }

    public static void bubbleSort() {
        int temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (seq[i] > seq[j]) {
                    temp = seq[i];
                    seq[i] = seq[j];
                    seq[j] = temp;
                }
            }
        }
    }
}

// import java.io.*;
//
// // 전에 푼 문제
// // https://www.acmicpc.net/problem/2750
// public class Problem2750수정렬하기 {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         int N = Integer.parseInt(br.readLine());
//
//         int[] arr = new int[N];
//         for (int i = 0; i < N; i++) {
//             arr[i] = Integer.parseInt(br.readLine());
//         }
//         br.close();
//
//         for (int i = 0; i < N - 1; i++) {
//             for (int j = 0; j < N - 1 - i; j++) {
//                 if (arr[j] > arr[j + 1]) {
//                     int temp = arr[j];
//                     arr[j] = arr[j + 1];
//                     arr[j + 1] = temp;
//                 }
//             }
//         }
//
//         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//         for (int i = 0; i < N; i++) {
//             bw.write(arr[i] + "\n");
//         }
//         bw.flush();
//         bw.close();
//     }
// }
