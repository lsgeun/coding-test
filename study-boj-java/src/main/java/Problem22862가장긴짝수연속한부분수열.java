import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22862
public class Problem22862가장긴짝수연속한부분수열 {
    public static int n, k;
    public static int[] seq;
    public static int maxLength = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        seq = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        findMaxLength();

        System.out.println(maxLength);
    }

    private static void findMaxLength() {
        int left = 1, right = 1;
        int length = 0;
        int deleteCount = k;

        while (true) {
            while (1 <= left && left <= n && seq[left] % 2 == 1) {
                left++;
                deleteCount++;
            }

            while (1 <= right && right <= n) {
                if (seq[right] % 2 == 1) {
                    if (deleteCount >= 1) {
                        deleteCount--;
                    } else {
                        break;
                    }
                } else { // seq[right] % 2 == 0
                    length++;
                }
                right++;
            }

            if (right == seq.length) {
                maxLength = Math.max(maxLength, length);
                break;
            }

            if (seq[right] % 2 == 0) {
                maxLength = Math.max(maxLength, length + 1);
            } else { // seq[right] % 2 == 1
                maxLength = Math.max(maxLength, length);
            }
            left++;
            length--;
        }
    }
}
