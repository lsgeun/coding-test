import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1654
public class Problem1654랜선자르기 {
    public static int k, n;
    public static int[] lanCableLength;
    public static int maxLanCableLengthWithCountN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lanCableLength = new int[k];
        maxLanCableLengthWithCountN = 0;
        for (int i = 0; i < k; i++) {
            lanCableLength[i] = Integer.parseInt(br.readLine());
        }

        findMaxLanLengthWithCountN();

        System.out.println(maxLanCableLengthWithCountN);
    }

    public static void findMaxLanLengthWithCountN() {
        int left = 1;
        int right = -1;
        for (int i = 0; i < k; i++) {
            right = Math.max(right, lanCableLength[i]);
        }

        long dividedLanCableTotalCount = 0;
        while (left <= right) {
            int middle = ((right - left) / 2) + left;

            dividedLanCableTotalCount = getDividedLanCableTotalCount(middle);

            if (dividedLanCableTotalCount < n) {
                right = middle - 1;
            } else { // dividedLanCableTotalCount >= n
                left = middle + 1;
                maxLanCableLengthWithCountN = middle;

                if (left == Integer.MIN_VALUE) { // 오버플로우라면 종료
                    break;
                }
            }
        }
    }

    public static long getDividedLanCableTotalCount(int divisorLanCableLength) {
        long dividedLanTotalCount = 0;
        for (int i = 0; i < k; i++) {
            dividedLanTotalCount += lanCableLength[i] / divisorLanCableLength;
        }

        return dividedLanTotalCount;
    }
}
