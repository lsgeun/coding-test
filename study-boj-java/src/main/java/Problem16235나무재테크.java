import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem16235나무재테크 {
    static int n, m, k;
    static int[][] nourishment;
    static int[][] nourishmentToAdd;
    static LinkedList<Tree>[][] treeList;
    static long liveTreeCount;
    static final int[][] eightDirection = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
    };
    static final int X = 0, Y = 1;

    public static void main(String[] args) throws IOException {
        setUp();

        for (int year = 1; year <= k; year++) {
            oneYearPass();
        }

        calculateLiveTreeCount();
        System.out.println(liveTreeCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nourishment = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nourishment[i][j] = 5;
            }
        }

        nourishmentToAdd = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                nourishmentToAdd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        treeList = new LinkedList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                treeList[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            treeList[x][y].add(new Tree(age));
        }

        liveTreeCount = 0L;
    }

    static void oneYearPass() {
        // spring
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Tree tree : treeList[i][j]) {
                    boolean die = (tree.age > nourishment[i][j]);
                    if (die) {
                        tree.die = true;
                    } else { // die == false
                        nourishment[i][j] -= tree.age;
                        tree.age += 1;
                    }
                }
            }
        }

        // summer
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                while (true) {
                    // 나무 배열이 비어있다면 조사 종료
                    if (treeList[i][j].isEmpty()) {
                        break;
                    }

                    Tree tree = treeList[i][j].getLast();
                    if (tree.canDie()) {
                        nourishment[i][j] += tree.getNourishment();
                        treeList[i][j].removeLast();
                    } else { // 더 찾아봐야 나이가 어린 나무이므로 종료
                        break;
                    }
                }
            }
        }

        // autumn
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Tree tree : treeList[i][j]) {
                    if (tree.canBreed()) {
                        breed(i, j);
                    }
                }
            }
        }

        // winter
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nourishment[i][j] += nourishmentToAdd[i][j];
            }
        }
    }

    static void breed(int x, int y) {
        for (int[] direction : eightDirection) {
            int curX = x + direction[X];
            int curY = y + direction[Y];

            if (!isInMap(curX, curY)) {
                continue;
            }

            treeList[curX][curY].addFirst(new Tree(1));
        }
    }

    static boolean isInMap(int x, int y) {
        return (1 <= x && x <= n) && (1 <= y && y <= n);
    }

    static void calculateLiveTreeCount() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                liveTreeCount += treeList[i][j].size();
            }
        }
    }
}

class Tree {
    public int age;
    public boolean die = false;

    public Tree(int age) {
        this.age = age;
    }

    boolean canDie() {
        return this.die;
    }

    int getNourishment() {
        return this.age / 2;
    }

    boolean canBreed() {
        return this.age % 5 == 0;
    }
}
