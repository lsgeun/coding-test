import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13023
public class Problem13023ABCDE {
    static ArrayList<Integer>[] adjacentArr;
    static boolean[] visited;
    static boolean subGraphExist;
    static int depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        adjacentArr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjacentArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
            adjacentArr[start].add(end);
            adjacentArr[end].add(start);
        }
        br.close();
        visited = new boolean[N];
        subGraphExist = false;
        depth = 1;
        for (int i = 0; i < N; i++) {
            if (subGraphExist) {
                break;
            }
            visited[i] = true;
            DFS(i);
            visited[i] = false;
        }
        depth -= 1;

        if (subGraphExist) {
            bw.write('1');
        } else {
            bw.write('0');
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int node) {
        for (int adjacentNode : adjacentArr[node]) {
            // 이전에 호출 재귀함수를 종료하기 위한 코드
            if (depth == 5) {
                subGraphExist = true;
                break;
            }

            if (!visited[adjacentNode]) {
                depth += 1;
                // 가지치기를 하기 위한 코드
                // 마지막 재귀함수를 호출하지 않음
                if (depth == 5) {
                    subGraphExist = true;
                    break;
                }

                visited[adjacentNode] = true;
                DFS(adjacentNode);
                visited[adjacentNode] = false;
                depth -= 1;
            }
        }
    }
}
