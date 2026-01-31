import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2188
public class Problem2188축사배정 {
    public static int n, m;
    public static ArrayList<Integer>[] houses;
    public static int[] matchedCow;
    public static boolean[] visited;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static final int NOT_FOUND = 0;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    public static void setUp() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        houses = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            houses[i] = new ArrayList<>();
        }
        for (int cow = 1; cow <= n; cow++) {
            st = new StringTokenizer(br.readLine());
            int houseCount = Integer.parseInt(st.nextToken());

            for (int i = 0; i < houseCount; i++) {
                int house = Integer.parseInt(st.nextToken());

                houses[cow].add(house);
            }
        }

        visited = new boolean[m + 1];
        Arrays.fill(visited, false);

        matchedCow = new int[m + 1];
        Arrays.fill(matchedCow, NOT_FOUND);

        br.close();
    }

    public static void solve() {
        int maxMatchingCount = 0;
        for (int cow = 1; cow <= n; cow++) {
            Arrays.fill(visited, false);

            if (canMatch(cow)) {
                maxMatchingCount++;
            }
        }

        System.out.println(maxMatchingCount);
    }

    public static boolean canMatch(int cow) {
        for (int house : houses[cow]) {
            if (visited[house]) {
                continue;
            }

            // 나중에 매칭할 위치 혹은
            // 매칭할 수 없는 위치 표시
            visited[house] = true;

            int houseCow = matchedCow[house];
            if (houseCow == NOT_FOUND) {
                matchedCow[house] = cow;
                return true;
            } // houseCow 존재, 1 <= houseCow <= m

            if (canMatch(houseCow)) {
                matchedCow[house] = cow;
                return true;
            }
        }

        return false;
    }
}
