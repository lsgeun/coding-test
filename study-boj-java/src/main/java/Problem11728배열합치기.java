import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11728
public class Problem11728배열합치기 {
    public static int n, m;
    public static int[] seqA, seqB;
    public static int[] mergedSeq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        seqA = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seqA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        seqB = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            seqB[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        mergedSeq = new int[n + m + 1];

        merge();

        printMergedSeq();
    }

    public static void merge() {
        int mergeIndex = 1;
        int i = 1, j = 1;
        while (i <= n && j <= m) {
            if (seqA[i] < seqB[j]) {
                mergedSeq[mergeIndex] = seqA[i];
                mergeIndex++;
                i++;
            } else if (seqA[i] > seqB[j]) {
                mergedSeq[mergeIndex] = seqB[j];
                mergeIndex++;
                j++;
            } else if (seqA[i] == seqB[j]) {
                mergedSeq[mergeIndex] = seqA[i];
                mergeIndex++;
                i++;
                mergedSeq[mergeIndex] = seqB[j];
                mergeIndex++;
                j++;
            }
        }

        while ((i <= n) && (j > m)) {
            mergedSeq[mergeIndex] = seqA[i];
            mergeIndex++;
            i++;
        }

        while ((i > n) && (j <= m)) {
            mergedSeq[mergeIndex] = seqB[j];
            mergeIndex++;
            j++;
        }
    }

    public static void printMergedSeq() {
        StringBuilder st = new StringBuilder();
        for (int i = 1; i < mergedSeq.length; i++) {
            st.append(mergedSeq[i] + " ");
        }
        System.out.println(st);
    }
}
