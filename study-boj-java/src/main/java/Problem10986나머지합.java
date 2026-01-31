// 실버 4 기준으로 작성한 코드

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
//
// https://www.acmicpc.net/problem/10986
// public class Problem005 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
//
//        int[] arr = new int[N+1];
//        st = new StringTokenizer(br.readLine());
//        br.close();
//        for (int i=1; i<=N; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        int[] sumArrModM = new int[N+1];
//        for (int i=1; i<=N; i++) {
//            sumArrModM[i] = (sumArrModM[i-1] + arr[i]) % M;
//        }
//
//        int[] modMCounter = new int[M];
//        for (int i=1; i<=N; i++) {
//            modMCounter[sumArrModM[i]]++;
//        }
//
//        long count = modMCounter[0];
//        for (int i=0; i<M; i++) {
//            count += modMCounter[i] * (modMCounter[i]-1) / 2;
//        }
//
//        System.out.println(count);
//    }
//}
