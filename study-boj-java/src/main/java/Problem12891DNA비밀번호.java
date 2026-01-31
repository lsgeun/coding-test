import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12891
public class Problem12891DNA비밀번호 {
    static int[] pwRequirement = new int[4];
    static int[] pwStatus = new int[4];
    static int pwValidation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());

        char[] arr;
        arr = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < 4; i++) {
            pwRequirement[i] = Integer.parseInt(st.nextToken());
            if (pwRequirement[i] == 0) {
                pwValidation++;
            }
        }

        for (int i = 0; i < P; i++) {
            Add(arr[i]);
        }

        int count = 0;
        if (pwValidation == 4) {
            count += 1;
        }

        for (int i = P; i < S; i++) {
            Add(arr[i]);
            Remove(arr[i - P]);
            if (pwValidation == 4) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                pwStatus[0]++;
                if (pwStatus[0] == pwRequirement[0]) {
                    pwValidation++;
                }
                break;
            case 'C':
                pwStatus[1]++;
                if (pwStatus[1] == pwRequirement[1]) {
                    pwValidation++;
                }
                break;
            case 'G':
                pwStatus[2]++;
                if (pwStatus[2] == pwRequirement[2]) {
                    pwValidation++;
                }
                break;
            case 'T':
                pwStatus[3]++;
                if (pwStatus[3] == pwRequirement[3]) {
                    pwValidation++;
                }
                break;
        }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (pwStatus[0] == pwRequirement[0]) {
                    pwValidation--;
                }
                pwStatus[0]--;
                break;
            case 'C':
                if (pwStatus[1] == pwRequirement[1]) {
                    pwValidation--;
                }
                pwStatus[1]--;
                break;
            case 'G':
                if (pwStatus[2] == pwRequirement[2]) {
                    pwValidation--;
                }
                pwStatus[2]--;
                break;
            case 'T':
                if (pwStatus[3] == pwRequirement[3]) {
                    pwValidation--;
                }
                pwStatus[3]--;
                break;
        }
    }
}