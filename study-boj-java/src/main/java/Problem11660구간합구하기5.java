import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11660
public class Problem11660구간합구하기5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        int[][] arr2D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr2D[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int[][] sumArr2D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sumArr2D[i][j] = arr2D[i][j] + sumArr2D[i][j - 1] + sumArr2D[i - 1][j] - sumArr2D[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X1 = Integer.parseInt(st.nextToken()), Y1 = Integer.parseInt(st.nextToken()), X2 = Integer.parseInt(st.nextToken()), Y2 = Integer.parseInt(st.nextToken());

            System.out.println(sumArr2D[X2][Y2] - sumArr2D[X2][Y1 - 1] - sumArr2D[X1 - 1][Y2] + sumArr2D[X1 - 1][Y1 - 1]);
        }
    }
}
