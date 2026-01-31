import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16234
public class Problem16234인구이동 {
    static int n, l, r;
    static int[][] population;
    static boolean[][] visited;
    static ArrayList<ArrayList<Node16234>> unions;
    static int days;

    static final int X = 0, Y = 1;
    static final int[][] fourDirection = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    public static void main(String[] args) throws IOException {
        setUp();
        movePopulation();
        System.out.println(days);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        population = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        unions = new ArrayList<>();

        days = 0;
    }

    static void movePopulation() {
        while (true) {
            unions = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        continue;
                    }

                    ArrayList<Node16234> union = findUnion(i, j);

                    if (union.size() == 1) {
                        continue;
                    }

                    unions.add(union);
                }
            }

            if (unions.isEmpty()) {
                break;
            }

            for (ArrayList<Node16234> union : unions) {
                int totalSum = union.stream()
                        .mapToInt(node -> node.population)
                        .sum();
                int totalCount = union.size();
                int calculatedPopulation = totalSum / totalCount;

                for (Node16234 node : union) {
                    population[node.x][node.y] = calculatedPopulation;
                }
            }

            days += 1;
        }
    }

    static ArrayList<Node16234> findUnion(int x, int y) {
        ArrayList<Node16234> union = new ArrayList<>();
        Queue<Node16234> queue = new LinkedList<>();

        Node16234 node = new Node16234(x, y, population[x][y]);
        visited[node.x][node.y] = true;
        queue.add(node);

        while (!queue.isEmpty()) {
            Node16234 curNode = queue.poll();

            union.add(curNode);

            for (int[] direction : fourDirection) {
                int nextX = curNode.x + direction[X];
                int nextY = curNode.y + direction[Y];

                if (!isInMap(nextX, nextY)) {
                    continue;
                }
                if (visited[nextX][nextY]) {
                    continue;
                }

                Node16234 nextNode = new Node16234(nextX, nextY, population[nextX][nextY]);

                int diff = Math.abs(curNode.population - nextNode.population);
                boolean isConnected = (l <= diff && diff <= r);
                if (!isConnected) {
                    continue;
                }

                visited[nextNode.x][nextNode.y] = true;
                queue.add(nextNode);
            }
        }

        return union;
    }

    static boolean isInMap(int x, int y) {
        return (0 <= x && x <= n - 1) && (0 <= y && y <= n - 1);
    }
}

class Node16234 {
    public int x;
    public int y;
    public int population;

    public Node16234(int x, int y, int population) {
        this.x = x;
        this.y = y;
        this.population = population;
    }
}
