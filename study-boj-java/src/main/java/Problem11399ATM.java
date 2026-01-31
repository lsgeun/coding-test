import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11399
public class Problem11399ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] time = new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (time[j + 1] < time[j]) {
                    int temp = time[j];
                    time[j] = time[j + 1];
                    time[j + 1] = temp;
                }
            }
        }

        long totalTime = 0;
        int cumulTime = 0;
        for (int i = 0; i < N; i++) {
            cumulTime += time[i];
            totalTime += cumulTime;
        }

        System.out.print(totalTime);
    }
}
