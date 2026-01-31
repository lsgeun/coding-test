import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11053
public class Problem11053가장긴증가하는부분수열 {
    static int n;
    static int[] arr;

    static int[] lengthLISEndsWith;
    static int lengthLIS;

    public static void main(String[] args) throws IOException {
        setUp();
        calculateLengthLIS();
        System.out.println(lengthLIS);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lengthLISEndsWith = new int[n];
        lengthLIS = 1;
    }

    static void calculateLengthLIS() {
        // 명백하게 0 인덱스로 끝나는 부분 배열의 LIS는 1.
        lengthLISEndsWith[0] = 1;

        for (int i = 1; i < n; i++) {
            // 자기 자신만을 부분 배열로 가지는 경우가 반드시 있으므로 LIS는 적어도 1임
            lengthLISEndsWith[i] = 1;

            // 원소 j들은 모두 j로 끝나는 LIS 길이를 구한 상태
            // 그러니까 j로 끝나는 부분 수열을 모두 고려해서 LIS를 구한 상태
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    lengthLISEndsWith[i] = Math.max(lengthLISEndsWith[i], lengthLISEndsWith[j] + 1);
                }
            }
        }

        // lengthLISEndsWith[i] 중 최대값을 lengthLIS로 할당
        for (int i = 0; i < n; i++) {
            lengthLIS = Math.max(lengthLIS, lengthLISEndsWith[i]);
        }
    }
}
