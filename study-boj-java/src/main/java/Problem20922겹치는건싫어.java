import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20922
public class Problem20922겹치는건싫어 {
    public static int n, k;
    public static int[] seq;
    public static int[] count;
    public static int maxLength = Integer.MIN_VALUE;
    public static int left, right;
    public static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        seq = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        count = new int[100000 + 1];
        findMaxLength();

        System.out.println(maxLength);
    }

    public static void findMaxLength() {
        left = 1;
        right = 1;
        count[seq[left]]++;
        length = 1;
        while (!(right >= seq.length)) {
            if (count[seq[right]] <= k) {
                maxLength = Math.max(maxLength, length);
                moveRightBackward();
            } else { // count[seq[right]] > k
                if (left == right) { // left <= right 보장
                    moveRightBackward();
                }
                moveLeftBackward();
            }
        }
    }

    public static void moveLeftBackward() {
        // left <= right 보장될 경우 논리적으로 맞음
        if (1 <= left && left <= seq.length - 1) {
            count[seq[left]]--;
            length--;
        }
        left++;
    }

    public static void moveRightBackward() {
        // left <= right 보장될 경우 논리적으로 맞음
        right++;
        if (1 <= right && right <= seq.length - 1) {
            count[seq[right]]++;
            length++;
        }
    }
}
