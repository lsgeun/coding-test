import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2110
public class Problem2110공유기설치 {
    public static int n, c;
    public static final ArrayList<Integer> arr = new ArrayList<>();
    public static int maxDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);

        maxDistance = 0;
        findMaxDistance();

        System.out.println(maxDistance);

        br.close();
    }

    public static void findMaxDistance() {
        // arr은 오름차순 정렬되었다고 가정.
        // 거리의 최소 1, 최대 arr[0] - arr[size - 1]
        int left = 1;
        int right = arr.get(arr.size() - 1) - arr.get(0);

        while (left <= right) {
            // left + right가 항상 int 범위 내라서 오버플로우 안 남.
            int middle = (left + right) / 2;

            int routerMaxCountWithGreaterEqualMiddle = getMaxRouterCountWithGreaterEqual(middle);

            if (routerMaxCountWithGreaterEqualMiddle < c) {
                right = middle - 1;
            } else { // routerCount >= c
                left = middle + 1;
                maxDistance = middle;
            }
        }
    }

    public static int getMaxRouterCountWithGreaterEqual(int distance) {
        int maxRouterCountWithGreaterEqualDistance = 1; // arr[0]에서 시작해야 항상 최대값을 구할 수 있음.

        int curRouter = 0;
        for (int nextHouse = 1; nextHouse < arr.size(); nextHouse++) {
            if (arr.get(nextHouse) - arr.get(curRouter) >= distance) {
                maxRouterCountWithGreaterEqualDistance++;

                curRouter = nextHouse;
            }
        }

        return maxRouterCountWithGreaterEqualDistance;
    }
}
