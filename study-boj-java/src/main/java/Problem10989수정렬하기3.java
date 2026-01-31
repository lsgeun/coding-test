import java.io.*;

// 스스로 다시 한 번 더 풀어봄
// https://www.acmicpc.net/problem/10989
public class Problem10989수정렬하기3 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        setUp();
        radixSort();
        printArray();
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }

    static void radixSort() {
        int[] sortedArr = new int[n + 1];
        int[] insertIndex;
        int digitOrder = 1;
        int digit;

        while (true) {
            // insertIndex를 누적 개수로 초기화
            // 삽입할 때마다 1 감소시켜서 삽입 위치 변경
            insertIndex = new int[10];
            for (int i = 1; i <= n; i++) {
                digit = getDigitInArr(i, digitOrder);
                insertIndex[digit]++;
            }
            for (int i = 1; i <= 9; i++) {
                insertIndex[i] += insertIndex[i - 1];
            }

            // 자리수 0의 누적 개수가 자리수 9의 누적 개수와 같다는 건
            // 1 ~ 9에 개수가 없었다는 것.
            // 더 이상 정렬할 것이 없다는 것. 정렬 종료.
            if (insertIndex[0] == insertIndex[9]) {
                break;
            }

            // 높은 것부터 차례대로 삽입할 때, 정렬이 일어나는 것.
            for (int i = n; i >= 1; i--) {
                digit = getDigitInArr(i, digitOrder);
                sortedArr[insertIndex[digit]] = arr[i];
                insertIndex[digit]--;
            }
            for (int i = 1; i <= n; i++) {
                arr[i] = sortedArr[i];
            }

            digitOrder++;
        }
    }

    static int getDigitInArr(int index, int digitOrder) {
        int digit = arr[index];
        for (int j = 1; j <= digitOrder - 1; j++) {
            digit /= 10;
        }
        digit %= 10;

        return digit;
    }

    static void printArray() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= n; i++) {
            bw.write(arr[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}

// import java.io.*;
//
// // 책 풀이
// // https://www.acmicpc.net/problem/10989
// public class Problem10989수정렬하기3 {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//         int N = Integer.parseInt(br.readLine());
//         int[] arr = new int[N];
//         for (int i = 0; i < N; i++) {
//             arr[i] = Integer.parseInt(br.readLine());
//         }
//         br.close();
//
//         int[] temp = new int[N];
//
//         int pow10 = 1;
//         for (int k = 0; k < 5; k++) {
//             int[] radixCountSum = new int[10];
//             for (int i = 0; i < N; i++) {
//                 radixCountSum[arr[i] / pow10 % 10]++; // arr 원소에 해당하는 radix의 개수 증가
//             }
//             for (int i = 1; i < 10; i++) {
//                 radixCountSum[i] += radixCountSum[i - 1];
//             }
//
//             // 0 ~ 9 순서로 기수를 가진다고 가정하면 오름차순 정렬
//             // 9 ~ 0 순서로 기수를 가진다고 가정하면 내림차순 정렬
//             for (int i = N - 1; i >= 0; i--) {
//                 // 앞에서부터 넣으면 구현이 좀 어려움. 뒤에서부터 넣으면 구현이 쉬움.
//                 temp[radixCountSum[arr[i] / pow10 % 10] - 1] = arr[i];
//                 radixCountSum[arr[i] / pow10 % 10]--;
//             }
//
//             for (int i = 0; i < N; i++) {
//                 arr[i] = temp[i];
//             }
//             pow10 *= 10;
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

// import java.io.*;
// import java.util.LinkedList;
// import java.util.Queue;
//
// // 내가 작성한 코드. 메모리 초과
// // https://www.acmicpc.net/problem/10989
// public class Baekjoon_10989 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(br.readLine());
//        int[] arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//        br.close();
//
//        Queue<Integer>[] queues = new LinkedList[10];
//        for (int i = 0; i < 10; i++) {
//            queues[i] = new LinkedList<>();
//        }
//
//        for (int k = 0; k < 5; k++) {
//            for (int i = 0; i < N; i++) {
//                queues[(arr[i] / power(10, k))%10].offer(arr[i]);
//            }
//
//            int i = 0;
//            for (int j = 0; j < 10; j++) {
//                while(!queues[j].isEmpty()) {
//                    arr[i] = queues[j].remove();
//                    i++;
//                }
//            }
//        }
//
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        for (int i = 0; i < N; i++) {
//            bw.write(arr[i] + "\n");
//        }
//        bw.flush();
//        bw.close();
//    }
//    public static int power(int n, int k) {
//        int pow = 1;
//        for (int i = 1; i <= k; i++) {
//            pow *= n;
//        }
//
//        return pow;
//    }
//}
