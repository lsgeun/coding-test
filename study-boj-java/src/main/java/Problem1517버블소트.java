import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1517
public class Problem1517버블소트 {
    static long swapCount;
    static int[] arr;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        temp = new int[N];

        swapCount = 0;
        mergesort(0, N - 1);

        System.out.print(swapCount);
    }

    public static void mergesort(int first, int last) {
        if (!(first < last)) {
            return;
        }

        int middle = (first + last) / 2;
        if (first < middle) {
            mergesort(first, middle);
        }
        if (middle + 1 < last) {
            mergesort(middle + 1, last);
        }

        int insertIndex = first;
        int leftIndex = first, rightIndex = middle + 1;
        while (leftIndex <= middle && rightIndex <= last) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[insertIndex] = arr[leftIndex];
                insertIndex++;
                leftIndex++;
            } else { // arr[leftIndex] > arr[rightIndex])
                swapCount += rightIndex - insertIndex;
                temp[insertIndex] = arr[rightIndex];
                insertIndex++;
                rightIndex++;
            }
        }

        while (leftIndex <= middle) {
            temp[insertIndex] = arr[leftIndex];
            insertIndex++;
            leftIndex++;
        }
        while (rightIndex <= last) {
            temp[insertIndex] = arr[rightIndex];
            insertIndex++;
            rightIndex++;
        }

        for (int i = first; i <= last; i++) {
            arr[i] = temp[i];
        }
    }
}