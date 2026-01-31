import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11004
public class Problem11004k번째수 {
    public static int n, k;
    public static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        seq = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        quicksort(0, seq.length - 1);

        System.out.println(seq[k - 1]);

        br.close();
    }

    public static void quicksort(int left, int right) {
        while (left <= right) {
            if (right - left + 1 <= 10) {
                insertSort(left, right);
                break;
            }

            int middle = left + ((right - left) / 2);
            // 아래 코드 실행 후 피벗이 middle에 위치함.
            int pivotAtMiddle = middleOf3(left, middle, right);

            swap(left, middle); // partition3Way를 적용하기 위해 left, middle 자리 바꿈.
            int[] equalLR = partition3Way(pivotAtMiddle, left, right);

            int equalLeft = equalLR[0];
            int equalRight = equalLR[1];

            if (left == equalLeft && right == equalRight) {
                break;
            } else if (left == equalLeft && equalRight < right) {
                left = equalRight + 1;
                continue;
            } else if (left < equalLeft && right == equalRight) {
                right = equalLeft - 1;
                continue;
            }

            if (equalLeft - left < right - equalRight) {
                quicksort(left, equalLeft - 1);
                left = equalRight + 1;
            } else { // equalLeft - left >= right - equalRight
                quicksort(equalRight + 1, right);
                right = equalLeft - 1;
            }
        }
    }

    public static void insertSort(int left, int right) {
        // left <= right 이면 예외를 발생시키지 않고 모두 처리 가능
        for (int i = left + 1; i <= right; i++) {
            int value = seq[i];

            int j;
            for (j = i - 1; j >= left; j--) {
                if (seq[j] > value) {
                    seq[j + 1] = seq[j];
                } else {
                    break;
                }
            }
            // seq[j == 0] > value이면 j == -1.
            // 논리적 일관성 때문에 아래 코드를 for loop 밖으로 뺌.
            seq[j + 1] = value;
        }
    }

    public static int middleOf3(int left, int middle, int right) {
        if (left > middle) {
            swap(left, middle);
        } // left <= middle
        if (left > right) {
            swap(left, right);
        } // left <= right
        if (middle > right) {
            swap(middle, right);
        } // middle <= right
        // left <= middle <= right

        return seq[middle];
    }

    public static int[] partition3Way(int pivot, int left, int right) {
        // pivot이 left에 있다고 가정.
        // quicksort 조건에 의해 seq 길이가 10 초과임.
        int lowInsertIndex = left;
        int highInsertIndex = right;
        int searchIndex = left;

        while (searchIndex <= highInsertIndex) {
            if (seq[searchIndex] < pivot) {
                swap(lowInsertIndex, searchIndex);
                lowInsertIndex++;
                searchIndex++;
            } else if (seq[searchIndex] > pivot) {
                swap(searchIndex, highInsertIndex);
                highInsertIndex--;
            } else { // seq[searchIndex] == pivot
                searchIndex++;
            }
        }

        return new int[]{lowInsertIndex, highInsertIndex}; // [equalLeft, equalRight]
    }

    public static void swap(int a, int b) {
        int temp = seq[a];
        seq[a] = seq[b];
        seq[b] = temp;
    }
}

// 틀림, 정렬하는 데에서 오류가 있음.
// 같은 값을 정렬하는 것이면 lo, hi 모두 인덱스 범위를 벗어남
// https://www.acmicpc.net/problem/11004
// public class Problem11004k번째수 {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//
//         int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
//         int[] arr = new int[N];
//         st = new StringTokenizer(br.readLine());
//         br.close();
//         for (int i = 0; i < N; i++) {
//             arr[i] = Integer.parseInt(st.nextToken());
//         }
//
//         quicksort(arr, 0, N - 1, K - 1);
//
//         System.out.print(arr[K - 1]);
//     }
//
//     public static void quicksort(int[] arr, int first, int last, int K) {
//         if (!(first < last)) {
//             return;
//         }
//         int pivotIndex = partition(arr, first, last);
//         if (pivotIndex == K) {
//             return;
//         } else if (K < pivotIndex) {
//             quicksort(arr, first, pivotIndex - 1, K);
//         } else { // pivotIndex < K
//             quicksort(arr, pivotIndex + 1, last, K);
//         }
//     }
//
//     public static int partition(int[] arr, int first, int last) {
//         int pivotIndex = (first + last) / 2;
//         int pivot = arr[pivotIndex];
//         swap(arr, first, pivotIndex);
//         pivotIndex = first;
//
//         int lo = first, hi = last;
//         while (lo < hi) {
//             while (pivot < arr[hi]) {
//                 hi--;
//             }
//             while (lo < hi && pivot >= arr[lo]) {
//                 lo++;
//             }
//             if (lo < hi) {
//                 swap(arr, lo, hi);
//             }
//         }
//
//         arr[pivotIndex] = arr[lo];
//         arr[lo] = pivot;
//
//         return lo;
//     }
//
//     public static void swap(int[] arr, int i, int j) {
//         int temp = arr[i];
//         arr[i] = arr[j];
//         arr[j] = temp;
//     }
// }
