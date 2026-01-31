import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1689
public class Problem1689곂치는선분 {
    static int n;
    static Point[] points;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
        System.out.println(maxCount);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        points = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken()), 1);
            Point end = new Point(Integer.parseInt(st.nextToken()), -1);

            points[2 * i] = start;
            points[2 * i + 1] = end;
        }

        Arrays.sort(points);

        maxCount = 0;
    }

    static void solve() {
        int count = 0;
        for (Point point : points) {
            count += point.value;

            if (point.value == 1) {
                maxCount = Math.max(maxCount, count);
            }
        }
    }

    static class Point implements Comparable<Point> {
        int pos;
        int value;

        public Point(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Point p) {
            if (this.pos != p.pos) {
                return this.pos - p.pos;
            } else {
                return this.value - p.value;
            }
        }
    }
}
