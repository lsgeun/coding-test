import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/1940
public class Problem1940주몽 {
    public static int n, m;
    public static ArrayList<Integer> seq;
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        m = sc.nextInt();

        seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(sc.nextInt());
        }

        findCount();

        System.out.println(count);
    }

    public static void findCount() {
        ArrayList<Integer> copySeq = seq.stream()
                .collect(Collectors.toCollection(ArrayList::new));
        copySeq.sort(null);

        int left = 0;
        int right = copySeq.size()-1;
        while (left < right) {
            int sumLToR = copySeq.get(left) + copySeq.get(right);
            if (sumLToR == m) {
                count++;
                left++;
                right--;
            } else if (sumLToR > m) {
                right--;
            } else if (sumLToR < m) {
                left++;
            }
        }
    }
}
