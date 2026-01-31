import java.util.Scanner;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1991
public class Problem1991트리순회 {
    public static int n;
    public static int[][] nodeChildren;
    public static final int L = 0, R = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();

        nodeChildren = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int parentNode = st.nextToken().charAt(0) - 'A';
            int leftChildNode = st.nextToken().charAt(0) - 'A';
            int rightChildNode = st.nextToken().charAt(0) - 'A';

            nodeChildren[parentNode][L] = leftChildNode;
            nodeChildren[parentNode][R] = rightChildNode;
        }

        preOrderTraversal(0);
        System.out.println();

        inOrderTraversal(0);
        System.out.println();

        postOrderTraversal(0);
        System.out.println();

        sc.close();
    }

    public static void preOrderTraversal(int node) {
        if (!isValid(node)) {
            return;
        }

        System.out.print((char) (node + 'A'));
        preOrderTraversal(nodeChildren[node][L]);
        preOrderTraversal(nodeChildren[node][R]);
    }

    public static void inOrderTraversal(int node) {
        if (!isValid(node)) {
            return;
        }

        inOrderTraversal(nodeChildren[node][L]);
        System.out.print((char) (node + 'A'));
        inOrderTraversal(nodeChildren[node][R]);
    }

    public static void postOrderTraversal(int node) {
        if (!isValid(node)) {
            return;
        }

        postOrderTraversal(nodeChildren[node][L]);
        postOrderTraversal(nodeChildren[node][R]);
        System.out.print((char) (node + 'A'));
    }

    public static boolean isValid(int node) {
        // 'A' ~ 'Z'라면 0 ~ 25 isValid에서 true 반환.
        // 나머지 false 반환
        // 특히 '.' == -19, false 반환.
        if (0 <= node && node <= 25) {
            return true;
        } else {
            return false;
        }
    }
}
