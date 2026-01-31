import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11723
public class Problem11723집합 {
    static int set;
    static final int UNIVERSAL_SET = (int) Math.pow(2, 20) - 1;
    static final int EMPTY_SET = 0;
    static final int NOT_FOUND = -1;
    static int m;
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    static void setUp() throws IOException {
        set = EMPTY_SET;

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

     static void solve() throws IOException{
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();

            int element;
            if (!(operation.equals("all") || operation.equals("empty"))) {
                element = Integer.parseInt(st.nextToken());
            } else {
                element = NOT_FOUND;
            }

            calculate(operation, element);
        }

        br.close();
        bw.flush();
        bw.close();
     }

     static void calculate(String operation, int element) throws IOException {
        if (operation.equals("add")) {
            set = set | (1 << element - 1);
        } else if (operation.equals("remove")) {
            set = set & ~ (1 << element - 1);
        } else if (operation.equals("check")) {
            if ((set & (1 << element - 1)) == 0) {
                bw.write("0\n");
            } else { // (set & (1 << element - 1)) != 0
                bw.write("1\n");
            }
        } else if (operation.equals("toggle")) {
            set = set ^ (1 << element - 1);
        } else if (operation.equals("all")) {
            set = UNIVERSAL_SET;
        } else if (operation.equals("empty")) {
            set = EMPTY_SET;
        }
     }
}
