import java.util.Scanner;

// https://www.acmicpc.net/problem/2018
public class Problem2018수들의합5 {
    static int n;
    static int count;

    public static void main(String[] args) {
        setUp();
        calculateCount();
        System.out.println(count);
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
    }

    static void calculateCount() {
        int sum = 0;
        int start = 1;
        int end = 1;

        while (true) {
            if (sum < n) {
                if (end == n + 1) {
                    break;
                }

                sum += end;
                end++;
            } else if (sum == n) {
                count++;
                sum -= start;
                start++;
            } else { // sum > n
                sum -= start;
                start++;
            }
        }
    }
}

// import java.util.Scanner;
//
// // https://www.acmicpc.net/problem/2018
// public class Problem2018수들의합5 {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//
//         int N = sc.nextInt();
//         sc.close();
//         long left = 1, right = 1, sum = 1, count = 0;
//
//         while (right <= N) {
//             if (sum == N) {
//                 count++;
//                 right++;
//                 sum = right * (right + 1) / 2 - (left - 1) * (left) / 2;
//             } else if (sum < N) {
//                 right++;
//                 sum = right * (right + 1) / 2 - (left - 1) * (left) / 2;
//             } else { // sum > N
//                 left++;
//                 sum = right * (right + 1) / 2 - (left - 1) * (left) / 2;
//             }
//         }
//
//         System.out.println(count);
//     }
// }

// 책 풀이

// import java.util.Scanner;
//
// public class Baekjoon_2018 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int N = sc.nextInt();
//        sc.close();
//        int start=1, end=1, sum=1, count=0;
//
//        while (end <= N) {
//            if (sum == N) {
//                count++;
//                end++;
//                sum += end;
//            }
//            else if (sum < N) {
//                end++;
//                sum += end;
//            }
//            else { // sum > N
//                sum -= start;
//                start++;
//            }
//        }
//
//        System.out.println(count);
//    }
//}