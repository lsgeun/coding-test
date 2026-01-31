import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1912
public class Problem1912연속합 {
    static int n;
    static int[] array;
    static int[] maxUntil;
    static int max;

    public static void main(String[] args) throws IOException {
        setUp();
        findMax();
        System.out.println(max);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        array = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        maxUntil = new int[n];

        max = Integer.MIN_VALUE;
    }

    static void findMax() {
        maxUntil[0] = array[0];
        max = Math.max(max, maxUntil[0]);
        for (int i = 1; i < n; i++) {
            maxUntil[i] = Math.max(array[i], maxUntil[i - 1] + array[i]);
            max = Math.max(max, maxUntil[i]);
        }
    }
}
