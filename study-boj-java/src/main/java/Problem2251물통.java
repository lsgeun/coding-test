import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2251
public class Problem2251물통 {
    static final int A = 0, B = 1, C = 2;
    static int[] Sender = {A, A, B, B, C, C};
    static int[] Receiver = {B, C, A, C, A, B};
    static boolean visited[][];
    static boolean answerWaterAmountC[];
    static int totalWaterAmount[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        totalWaterAmount = new int[3];
        totalWaterAmount[A] = sc.nextInt();
        totalWaterAmount[B] = sc.nextInt();
        totalWaterAmount[C] = sc.nextInt();

        // A, B가 확정되면 C도 확정됨. 따라서, A, B만으로 A, B, C 상태를 표현할 수 있음.
        // 0 <= A, B <= 200이므로 boolean[201][201]을 사용
        visited = new boolean[200 + 1][200 + 1];
        // 0 <= C <= 200
        answerWaterAmountC = new boolean[200 + 1];

        BFS();

        for (int i = 0; i < answerWaterAmountC.length; i++) {
            if (answerWaterAmountC[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS() {
        Queue<AB> queue = new LinkedList<>();
        queue.add(new AB(0, 0));
        visited[0][0] = true;
        answerWaterAmountC[totalWaterAmount[C]] = true;

        while (!queue.isEmpty()) {
            AB curWaterAmountAB = queue.poll();

            for (int i = 0; i < 6; i++) {
                int[] nextWaterAmount = {curWaterAmountAB.A, curWaterAmountAB.B, totalWaterAmount[C] - curWaterAmountAB.A - curWaterAmountAB.B};

                // 주는 컵에서 받는 컵으로 물 옮기기
                nextWaterAmount[Receiver[i]] += nextWaterAmount[Sender[i]];
                nextWaterAmount[Sender[i]] = 0;
                if (nextWaterAmount[Receiver[i]] > totalWaterAmount[Receiver[i]]) {
                    nextWaterAmount[Sender[i]] = nextWaterAmount[Receiver[i]] - totalWaterAmount[Receiver[i]];
                    nextWaterAmount[Receiver[i]] = totalWaterAmount[Receiver[i]];
                }

                // 이전에 있었던 A, B, C 물 상태라면 큐에 추가하지 않음.
                // 큐에 추가하면 BFS가 끝나지 않음. 또, 이미 answerWaterAmountC에 최신화되었으므로 할 필요도 없음.
                if (!visited[nextWaterAmount[A]][nextWaterAmount[B]]) {
                    visited[nextWaterAmount[A]][nextWaterAmount[B]] = true;
                    queue.add(new AB(nextWaterAmount[A], nextWaterAmount[B]));

                    if (nextWaterAmount[A] == 0) {
                        answerWaterAmountC[nextWaterAmount[C]] = true;
                    }
                }
            }
        }
    }
}

class AB {
    int A;
    int B;

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}