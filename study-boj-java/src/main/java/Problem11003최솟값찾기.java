import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11003
public class Problem11003최솟값찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Node11003> deque = new LinkedList<>();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        deque.addLast(new Node11003(arr[0], 0));
        System.out.print(deque.peekFirst().value);
        System.out.print(" ");
        for (int i = 1; i < N; i++) {
            deque.peekLast();
            if (deque.peekFirst().index < i - L + 1) {
                deque.removeFirst();
            }

            while (deque.peekLast() != null && deque.peekLast().value > arr[i]) {
                deque.removeLast();
            }
            deque.addLast(new Node11003(arr[i], i));

            bw.write(deque.peekFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    static class Node11003 {
        public int value;
        public int index;

        Node11003(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
