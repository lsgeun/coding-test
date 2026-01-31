import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1052
public class Problem1052물병 {
    static int n;
    static int powerOf2Bottles;
    static int k;
    static int martBottleCount;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(martBottleCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        powerOf2Bottles = n;
        k = Integer.parseInt(st.nextToken());

        martBottleCount = 0;
    }

    static void solve() {
        // 정답이 없을 수 없음
        // 1개씩 추가되다 보면, 결국 1개의 물병 안에 다 들어감
        while (true) {
            int bottleCount = getBottleCount();
            if (bottleCount <= k) {
                break;
            }

            // 마트에서 1L 물병 하나 삼
            powerOf2Bottles += 1; // 여기서 자리수 올림이 되면서, 물병이 알아서 합쳐짐
            martBottleCount += 1;
        }
    }

    static int getBottleCount() {
        // powerOf2Bottles >= 1라 가정
        int temp = powerOf2Bottles;
        int bottleCount = 0;
        while (temp >= 1) {
            if (temp % 2 == 1) {
                bottleCount += 1;
            }

            temp = temp / 2;
        }

        return bottleCount;
    }
}
