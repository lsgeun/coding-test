import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/10800
public class Problem10800컬러볼 {
    static int n;
    static Ball[] balls;
    static int[] colorSubstraction;
    static int preSum;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        balls = new Ball[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size, 0);
        }

        colorSubstraction = new int[200_000 + 1];
        Arrays.fill(colorSubstraction, 0);
        preSum = 0;
    }

    static void solve() {
        // 공들의 크기 합 구하기
        Arrays.sort(balls, Comparator.comparingInt(b -> b.size));

        int left;
        int right = -1;
        int size;
        while (true) {
            // size 그룹을 더 찾을 수 없다면 종료
            if (right + 1 >= n) {
                break;
            }

            // size 그룹의 left, right 구하기
            left = right + 1;
            right = left;
            size = balls[left].size;
            while (true) {
                // right가 배열에서 마지막 원소이거나,
                // size 그룹에서 마지막 원소라면
                // right에 1을 더하는 것을 종료
                if (right + 1 >= n) {
                    break;
                } // left <= right < n

                if (balls[right + 1].size != size) {
                    break;
                }

                right += 1;
            }

            // colorSubstraction[], preSum을 이용해 현재 size 그룹의 sum 갱신
            for (int i = left; i <= right; i++) {
                balls[i].sum = preSum + colorSubstraction[balls[i].color];
            }

            // colorSubtraction[], preSum 갱신
            int count = (right - left + 1);
            preSum += size * count;

            for (int i = left; i <= right; i++) {
                colorSubstraction[balls[i].color] -= size;
            }
        }

        // 순서대로 잡을 수 있는 공들의 크기 합 출력
        Arrays.sort(balls, Comparator.comparingInt(b -> b.order));
        for (int i = 0; i < n; i++) {
            System.out.println(balls[i].sum);
        }
    }

    static class Ball {
        int order;
        int color;
        int size;
        // 잡을 수 있는 공들의 크기 합
        int sum;

        public Ball(int order, int color, int size, int sum) {
            this.order = order;
            this.color = color;
            this.size = size;
            this.sum = sum;
        }
    }
}
