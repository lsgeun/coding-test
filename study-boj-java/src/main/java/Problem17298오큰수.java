import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17298
public class Problem17298오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] NGE = new int[N];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < N; i++) {
            if (arr[stack.peek()] >= arr[i]) {
                stack.push(i);
            } else { // arr[stack.peek()] < arr[i]
                while (!stack.empty() && arr[stack.peek()] < arr[i]) {
                    NGE[stack.pop()] = arr[i];
                }
                stack.push(i);
            }
        }
        while (!stack.empty()) {
            NGE[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(NGE[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}
