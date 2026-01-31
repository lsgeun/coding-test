import java.util.Scanner;
import java.util.Stack;

// https://www.acmicpc.net/problem/1874
public class Problem1874스택수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] seq = new int[N];
        for (int i = 0; i < N; i++) {
            seq[i] = sc.nextInt();
        }
        sc.close();

        Stack<Integer> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        int num = 1;
        int i = 0;
        boolean enable = true;
        while (enable == true && i < N) {
            if (num <= seq[i]) {
                while (num <= seq[i]) {
                    stack.push(num);
                    sb.append("+\n");
                    num++;
                }
                i++;
                stack.pop();
                sb.append("-\n");
            } else { // num > seq[i]
                if (stack.peek() != seq[i]) {
                    System.out.println("NO");
                    enable = false;
                } else {
                    stack.pop();
                    sb.append("-\n");
                    i++;
                }
            }
        }

        if (enable) {
            System.out.println(sb.toString());
        }
    }
}
