import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24060
public class Problem24060알고리즘수업병합정렬1 {
    public static int n, k;
    public static int[] seq;
    public static int[] temp;
    public static int saveCount;
    public static int numKthSave;

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
        temp = new int[n];

        saveCount = 0;
        numKthSave = -1;
        mergeSort(0, seq.length - 1);

        System.out.println(numKthSave);

        br.close();
    }

    public static void mergeSort(int left, int right) {
        // 오류가 나는 상황(left > right)과 정렬이 이미 된 상황(left == right)
        if (left >= right) {
            return;
        }

        int middle = ((right - left) / 2) + left;

        // 아래 if문 두 개는 mergeSort 호출을 줄이기 위함.
        if (left < middle) {
            mergeSort(left, middle);
        }
        if (middle + 1 < right) {
            mergeSort(middle + 1, right);
        }
        merge(left, middle, right);
    }

    public static void merge(int left, int middle, int right) {
        int leftArrayIndex = left;
        int rightArrayIndex = middle + 1;
        int tempIndex = left;

        while (leftArrayIndex <= middle && rightArrayIndex <= right) {
            // 오른쪽이 아닌 왼쪽을 넣으면, 오름차순일 때, 같은 값일 때 순서가 꼬이지 않음.
            // 내림차순은 왼쪽 대신 오른쪽
            if (seq[leftArrayIndex] <= seq[rightArrayIndex]) {
                saveCount++;
                temp[tempIndex] = seq[leftArrayIndex];
                leftArrayIndex++;
            } else { // seq[leftArrayIndex] > seq[rightArrayIndex]
                saveCount++;
                temp[tempIndex] = seq[rightArrayIndex];
                rightArrayIndex++;
            }

            if (saveCount == k) {
                numKthSave = temp[tempIndex];
            }

            tempIndex++;
        }

        if (rightArrayIndex == right + 1) {
            while (leftArrayIndex <= middle) {
                saveCount++;
                temp[tempIndex] = seq[leftArrayIndex];
                leftArrayIndex++;

                if (saveCount == k) {
                    numKthSave = temp[tempIndex];
                }
                tempIndex++;
            }
        }
        if (leftArrayIndex == middle + 1) {
            while (rightArrayIndex <= right) {
                saveCount++;
                temp[tempIndex] = seq[rightArrayIndex];
                rightArrayIndex++;

                if (saveCount == k) {
                    numKthSave = temp[tempIndex];
                }
                tempIndex++;
            }
        }


        for (int i = left; i <= right; i++) {
            seq[i] = temp[i];
        }
    }
}
