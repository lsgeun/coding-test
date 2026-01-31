import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5639
public class Problem5639이진검색트리 {
    public static Node5639 root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int key = Integer.parseInt(line);
        root = new Node5639(key);

        while (true) {
            line = br.readLine();

            if (line == null || line.equals("")) {
                break;
            }

            key = Integer.parseInt(line);
            root.addNode(key);
        }

        postOrderTraversal(root);
    }

    public static void postOrderTraversal(Node5639 node) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.key);
    }

    public static class Node5639 {
        int key;
        Node5639 left;
        Node5639 right;

        public Node5639(int key) {
            this.key = key;
        }

        public void addNode(int key) {
            if (key < this.key) {
                if (left == null) {
                    left = new Node5639(key);
                } else {
                    left.addNode(key);
                }
            } else { // key > this.key
                if (right == null) {
                    right = new Node5639(key);
                } else {
                    right.addNode(key);
                }
            }
        }
    }
}
