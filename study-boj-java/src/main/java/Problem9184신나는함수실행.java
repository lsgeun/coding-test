import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9184
public class Problem9184신나는함수실행 {
    static StringBuilder sb;
    static int[][][] w;

    public static void main(String[] args) throws IOException {
        setUp();
        printAllWFromInputs();
    }

    static void setUp() {
        sb = new StringBuilder();
        w = new int[20 + 1][20 + 1][20 + 1];
        // w[0][][], w[][0][], w[][][0]를 1로 초기화
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        w[i][j][k] = 1;
                    }
                }
            }
        }
        // 나머지 원소 채우기
        calculateW();
    }

    static void calculateW() {
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                for (int k = 1; k <= 20; k++) {
                    if (i < j && j < k) {
                        // if 의 연산은 else로 계산된 것을 사용하므로
                        // 연산 순서는 else가 먼저임
                        w[i][j][k] += w[i][j][k - 1] + w[i][j - 1][k - 1] - w[i][j - 1][k];
                    } else {
                        w[i][j][k] += w[i - 1][j][k] + w[i - 1][j - 1][k] + w[i - 1][j][k - 1] - w[i - 1][j - 1][k - 1];
                    }
                }
            }
        }
    }

    static void printAllWFromInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;

        while (true) {
            line = br.readLine();
            if (line == null || line.equals("")) {
                break;
            }

            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            printW(a, b, c);
        }

        System.out.println(sb);
    }

    static void printW(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(1).append("\n");
        } else if (a > 20 || b > 20 || c > 20) {
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w[20][20][20]).append("\n");
        } else {
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w[a][b][c]).append("\n");
        }
    }
}
