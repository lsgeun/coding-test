import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/15651
public class Problem15651N과M3 {
    public static int N, M;
    public static ArrayList<Integer> seq = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        M = sc.nextInt();
        sc.close();

        backtracking();

        System.out.println(sb);
    }

    public static void backtracking() {
        if (seq.size() == M) {
            for (int num : seq) {
                sb.append(num + " ");
            }
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            seq.add(i);
            backtracking();
            seq.remove(seq.size() - 1);
        }
    }
}
