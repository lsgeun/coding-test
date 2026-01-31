import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2751
public class Problem2751수정렬하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        mergesort(arr, 0, arr.length - 1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(arr[i]) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void mergesort(int[] arr, int first, int last) {
        if (!(first < last)) {
            return;
        }

        int middle = (first + last) / 2;
        // 재귀 호출을 줄이기 위해 if문 사용
        if (first < middle) {
            mergesort(arr, first, middle);
        }
        if (middle + 1 < last) {
            mergesort(arr, middle + 1, last);
        }

        int tempLength = last - first + 1;
        int[] temp = new int[tempLength];
        int leftIndex = first, rightIndex = middle + 1;

        int insertIndex = 0;
        while (leftIndex <= middle && rightIndex <= last) {
            if (arr[leftIndex] < arr[rightIndex]) {
                temp[insertIndex] = arr[leftIndex];
                insertIndex++;
                leftIndex++;
            } else {
                temp[insertIndex] = arr[rightIndex];
                insertIndex++;
                rightIndex++;
            }
        }
//      while문을 나오면 leftIndex <= middle, right == last+1
//                    leftIndex == middle+1, right <= last 이다.
        while (leftIndex <= middle) { // right == last+1 고정
            temp[insertIndex] = arr[leftIndex];
            insertIndex++;
            leftIndex++;
        }
        while (rightIndex <= last) { // leftIndex == middle+1 고정
            temp[insertIndex] = arr[rightIndex];
            insertIndex++;
            rightIndex++;
        }

        for (int i = 0; i < tempLength; i++) {
            arr[first + i] = temp[i];
        }
    }
}
