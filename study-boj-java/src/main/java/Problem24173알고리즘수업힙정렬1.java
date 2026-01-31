import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24173
public class Problem24173알고리즘수업힙정렬1 {
    public static int n, k;
    public static int[] seq;
    public static int swapCount;
    public static int[] numKthSwap;

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

        swapCount = 0;
        numKthSwap = new int[2];
        heapSort();

        if (k <= swapCount) {
            Arrays.sort(numKthSwap);
            for (int i = 0; i < 2; i++) {
                System.out.print(numKthSwap[i] + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    public static void heapSort() {
        buildMinHeap();

        for (int length = seq.length - 1; length >= 1; length--) {
            swap(0, length);
            heapify(length, 0);
        }
    }

    public static void buildMinHeap() {
        // O(nlog(n)) = (n / 2) * log(n)
        // seq.length는 전체 배열의 길이
        int lastLeaf = seq.length - 1;
        int lastIndexOfNonLeaf = (lastLeaf - 1) / 2;
        for (int nonLeafIndex = lastIndexOfNonLeaf; nonLeafIndex >= 0; nonLeafIndex--) {
            heapify(seq.length, nonLeafIndex);
        }
    }

    public static void heapify(int length, int parentNodeIndex) {
        // O(log(n)) = log(n) - 1

        // 사실 아래 2개의 조건문을 쓰지 않아도 정상적으로 동작함.

        // 길이가 1이면 정렬할 필요 없음.
        // 길이가 1일 때 아래 조건문에서 leaf이 non leaf이 됨.
        // 길이가 2이상일 때는 정상 동작.
        if (length == 1) {
            return;
        }

        // parentNode가 LeapNode이면 안됨.
        // 마지막 non leaf는 마지막 leaf의 부모와 동일
        // length는 정렬해야 하는 배열의 길이
        int lastLeaf = length - 1;
        int lastIndexOfNonLeaf = (lastLeaf - 1) / 2;
        if (parentNodeIndex > lastIndexOfNonLeaf) {
            return;
        }

        // parent가 non leaf이므로 자식은 항상 존재함. 왼쪽은 항상 있고, 오른쪽은 없을 수 있음.
        int leftNodeIndex = parentNodeIndex * 2 + 1;
        int rightNodeIndex = parentNodeIndex * 2 + 2;
        int largestNodeIndex = parentNodeIndex;

        if (leftNodeIndex < length) {
            if (seq[leftNodeIndex] < seq[largestNodeIndex]) {
                largestNodeIndex = leftNodeIndex;
            }
        }
        if (rightNodeIndex < length) {
            if (seq[rightNodeIndex] < seq[largestNodeIndex]) {
                largestNodeIndex = rightNodeIndex;
            }
        }

        if (largestNodeIndex != parentNodeIndex) {
            swap(parentNodeIndex, largestNodeIndex);
            heapify(length, largestNodeIndex);
        }
    }

    public static void swap(int indexA, int indexB) {
        int temp = seq[indexA];
        seq[indexA] = seq[indexB];
        seq[indexB] = temp;

        swapCount++;
        if (swapCount == k) {
            numKthSwap = new int[]{seq[indexA], seq[indexB]};
        }
    }
}
