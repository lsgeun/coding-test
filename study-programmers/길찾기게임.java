import java.io.*;
import java.util.*;

class 길찾기게임 {
    static Node[] nodes;
    static Node bst;

    static int[][] result;

    public int[][] solution(int[][] nodeinfo) {
        setUp(nodeinfo);

        solve();

        return result;
    }

    static void setUp(int[][] nodeinfo) {
        nodes = new Node[nodeinfo.length];

        for (int i = 0; i < nodeinfo.length; i++) {
            int id = i + 1;
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];

            nodes[i] = new Node(id, x, y);
        }

        Arrays.sort(nodes);

        bst = null;

        result = new int[2][nodeinfo.length];
    }

    public static void solve() {
        Node root = nodes[0];
        bst = root;

        for (int i = 1; i < nodes.length; i++) {
            bst.insertNode(nodes[i]);
        }

        result[0] = bst.preorder();
        result[1] = bst.postorder();
    }

    public static class Node implements Comparable<Node> {
        public static int[] travelsal;
        public static int travelsalIndex;

        public int id;
        public int x;
        public int y;
        // 해당 노드를 트리의 루트 노트라고 할 때, 그 트리의 크기 = size
        public int size;
        public Node leftNode;
        public Node rightNode;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.size = 1;
            this.leftNode = null;
            this.rightNode = null;
        }

        static void resetTravelsal(int treeSize) {
            travelsal = new int[treeSize];
            travelsalIndex = 0;
        }

        public void insertNode(Node node) {
            // 트리 크기 1 늘림
            this.size += 1;

            // 가정에 의해 this.x == node.x 인 것은 없음
            if (node.x < this.x) {
                if (leftNode == null) {
                    leftNode = node;
                } else { // leftNode != null
                    leftNode.insertNode(node);
                }
            } else { // node.x > this.x
                if (rightNode == null) {
                    rightNode = node;
                } else { // rightNode != null
                    rightNode.insertNode(node);
                }
            }
        }

        public int[] preorder() {
            resetTravelsal(this.size);

            _preorder(this);

            return travelsal;
        }

        public void _preorder(Node node) {
            travelsal[travelsalIndex] = node.id;
            travelsalIndex += 1;

            if (node.leftNode != null) {
                _preorder(node.leftNode);
            }
            if (node.rightNode != null) {
                _preorder(node.rightNode);
            }
        }

        public int[] postorder() {
            resetTravelsal(this.size);

            _postorder(this);

            return travelsal;
        }

        public void _postorder(Node node) {

            if (node.leftNode != null) {
                _postorder(node.leftNode);
            }
            if (node.rightNode != null) {
                _postorder(node.rightNode);
            }

            travelsal[travelsalIndex] = node.id;
            travelsalIndex += 1;
        }

        @Override
        public int compareTo(Node n) {
            if (this.y != n.y) {
                return n.y - this.y;
            } else { // this.y == n.y
                return this.x - n.x;
            }
        }
    }
}