import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11403
public class Problem11403경로찾기 {
    static int n;
    static int[][] adjacentArray;
    static boolean[][] existsPositivePath;

    public static void main(String[] args) throws IOException {
        setUp();
        findExistsPositivePath();
        printPositivePathExistsOrNot();
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adjacentArray = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adjacentArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        existsPositivePath = new boolean[n][n];
        // 중간 노드가 아무 것도 없을 때,
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacentArray[i][j] == 1) {
                    existsPositivePath[i][j] = true;
                } else if (adjacentArray[i][j] == 0) {
                    existsPositivePath[i][j] = false;
                }
            }
        }
    }

    static void findExistsPositivePath() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (existsPositivePath[i][k] && existsPositivePath[k][j]) {
                        existsPositivePath[i][j] = true;
                    }
                }
            }
        }
    }

    static void printPositivePathExistsOrNot() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (existsPositivePath[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}
