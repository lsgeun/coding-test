import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13458
public class Problem13458시험감독 {
    public static int n;
    public static int[] a;
    public static int b, c;
    public static long minCount;

    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        setUp();
        findMinCount();
        System.out.println(minCount);
    }

    public static void setUp() throws IOException{
        n = Integer.parseInt(br.readLine());

        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        minCount = 0L;
    }

    public static void findMinCount() {
        for (int i = 0; i < n; i++) {
            minCount += 1L;
            if (a[i] >= b) {
                minCount += (long) Math.ceil((double) (a[i] - b) / c);
            }
        }
    }
}
