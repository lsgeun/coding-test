import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/9656
public class Problem9656돌게임2 {
    static int n;
    // dp[i]는 i개의 돌을 가지고 처음 시작하는 사람(상근)의 승패 여부
    static int[] dp;
    static final int UNDEFINED = 0, LOSE = 1, WIN = 2;

    public static void main(String[] args) {
        setUp();

        calculateDP();

        if (dp[n] == LOSE) {
            System.out.println("CY");
        } else { // dp[n] == WIN
            System.out.println("SK");
        }
    }

    static void setUp() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp = new int[n + 1];
        Arrays.fill(dp, UNDEFINED);
    }

    static void calculateDP() {
        // 1. 반복문을 이용한 방식
        dp[1] = LOSE;
        if (n >= 2) {
            dp[2] = WIN;
        }
        if (n >= 3) {
            dp[3] = LOSE;
        }

        for (int i = 4; i <= n; i++) {
            // dp[i - 1] == LOSE <=> dp[i - 3] == LOSE
            // dp[i - 1] == WIN <=> dp[i - 3] == WIN
            // 하지만, dp[i - 1], dp[i - 3]이 UNDEFINED일 수도 있음
            if (dp[i - 1] == LOSE || dp[i - 3] == LOSE) {
                dp[i] = WIN;
            }
            if (dp[i - 1] == WIN || dp[i - 3] == WIN) {
                dp[i] = LOSE;
            }
        }

        // 2. 큐를 이용한 방식, 인덱스 범위 에러가 있음
        // dp[1] = LOSE;
        // dp[3] = LOSE;
        //
        // Queue<Integer> queue = new LinkedList<>();
        // queue.offer(1);
        // queue.offer(3);
        //
        // while (true) {
        //     // queue에는 항상 승패가 정해진 숫자만 들어감
        //     int num = queue.poll();
        //
        //     if (dp[num + 1] != UNDEFINED) {
        //         // 큐에 한 번 넣어진 경우 아무것도 하지 않음
        //     } else {
        //         dp[num + 1] = (dp[num] == LOSE) ? WIN : LOSE;
        //         queue.offer(num + 1);
        //     }
        //
        //     // 종료할 때, n보다 작은 수에 대해서 승패 여부가 안 정해졌을 수도 있음.
        //     if (dp[n] != UNDEFINED) {
        //         break;
        //     }
        //
        //     if (dp[num + 3] != UNDEFINED) {
        //         // 큐에 한 번 넣어진 경우 아무것도 하지 않음
        //     } else {
        //         dp[num + 3] = (dp[num] == LOSE) ? WIN : LOSE;
        //         queue.offer(num + 3);
        //     }
        //
        //     // 여기서 종료하면 인덱스 초과 에러 남.
        //     // n + 1에서 n이 초기화되었는데, n + 3은 n + 2를 초기화하는 것이기 때문.
        // }
    }
}
