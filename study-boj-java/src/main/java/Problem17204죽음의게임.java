import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17204
public class Problem17204죽음의게임 {
    static int n, k;
    static int[] pointer;
    static boolean[] visited;
    static int m;

    public static void main(String[] args) throws IOException {
        setUp();

        solve();

        System.out.println(m);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pointer = new int[n];
        for (int i = 0; i < n; i++) {
            pointer[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[n];
        m = -1;
    }

    static void solve() {
        int person = 0;
        int count = 0;
        visited[person] = true;

        while (true) {
            person = pointer[person];

            if (visited[person]) {
                break;
            }

            count += 1;
            visited[person] = true;

            if (person == k) {
                m = count;
                break;
            }
        }
    }
}
