import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2170
public class Problem2170선긋기 {
    static int n;
    static Line[] lines;
    static int length;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(length);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        lines = new Line[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Line line = new Line(start, end);

            lines[i] = line;
        }

        Arrays.sort(lines);

        length = 0;
    }

    static void solve() {
        Line line = lines[0];
        int start = line.start;
        int end = line.end;

        for (int i = 1; i < n; i++) {
            line = lines[i];

            if (line.start <= end) {
                end = Math.max(line.end, end);
            } else { // end > line.start
                length += (end - start);

                start = line.start;
                end = line.end;
            }
        }
        // for문이 끝나면 길이를 계산하지 않은 줄이 항상 1개 남음
        // 그래서, 마지막에 길이를 계산해야 함
        length += (end - start);
    }

    static class Line implements Comparable<Line> {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line l) {
            if (this.start < l.start) {
                return -1;
            } else if (this.start > l.start){
                return 1;
            } else {
                return 0;
            }
        }
    }
}
