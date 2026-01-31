import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/15650
public class Problem15650N과M2 {
    public static int N, M;
    public static ArrayList<Integer> seq = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        M = sc.nextInt();
        sc.close();

        backtracking();
    }

    public static void backtracking() {
        if (seq.size() >= 2) {
            int last = seq.get(seq.size() - 1);
            int beforeLast = seq.get(seq.size() - 2);
            if (!(beforeLast < last)) {
                return;
            }
        }

        if (seq.size() == M) {
            for (int num : seq) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            seq.add(i);
            backtracking();
            seq.remove(seq.size() - 1);
        }
    }
}
