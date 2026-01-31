import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/2164
public class Problem2164카드2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.close();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() >= 2) {
            queue.remove();
            queue.add(queue.remove());
        }

        System.out.println(queue.peek());
    }
}
