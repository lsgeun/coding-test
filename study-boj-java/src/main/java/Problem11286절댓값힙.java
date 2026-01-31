import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/11286
public class Problem11286절댓값힙 {
    static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int absO1 = Math.abs(o1), absO2 = Math.abs(o2);
            if (absO1 == absO2) {
                if (o1 > o2) {
                    return 1;
                } else { // o1 <= o2
                    return -1;
                }
            } else {
                if (absO1 > absO2) {
                    return 1;
                } else { // absO1 <= absO2
                    return -1;
                }
            }
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
        int num;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            if (num != 0) {
                priorityQueue.add(num);
            } else { // num == 0
                if (priorityQueue.isEmpty()) {
                    bw.append(0 + "\n");
                } else {
                    bw.append(priorityQueue.poll() + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
