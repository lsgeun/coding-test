import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11659
public class Problem11659구간합구하기4 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        br.close();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sumArr = new int[N + 1];
        sumArr[0] = 0;
        for (int i = 1; i < N + 1; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i - 1];
        }

        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()), j = Integer.parseInt(st.nextToken());
            System.out.println(sumArr[j] - sumArr[i - 1]);
        }
    }
}

// Scanner을 쓴 코드

// import java.util.Scanner;
//
// public class Baekjoon_11659 {
//    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//
//        int N = sc.nextInt(), M = sc.nextInt();
//
//        int[] arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = sc.nextInt();
//        }
//
//        int[] sumArr = new int[N + 1];
//        sumArr[0] = 0;
//        for (int i = 1; i < N + 1; i++) {
//            sumArr[i] = sumArr[i - 1] + arr[i - 1];
//        }
//
//        for (int k = 0; k < M; k++) {
//            int i = sc.nextInt(), j = sc.nextInt();
//            System.out.println(sumArr[j] - sumArr[i - 1]);
//        }
//    }
//}