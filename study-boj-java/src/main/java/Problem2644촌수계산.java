import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2644
public class Problem2644촌수계산 {
    public static int n, m;
    public static int personA, personB;
    public static ArrayList<ArrayList<Integer>> adjacentList;
    public static int[] degreeOfKinshipForPersonA;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        personA = sc.nextInt();
        personB = sc.nextInt();
        m = sc.nextInt();

        adjacentList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacentList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int firstPerson = sc.nextInt();
            int secondPerson = sc.nextInt();

            adjacentList.get(firstPerson).add(secondPerson);
            adjacentList.get(secondPerson).add(firstPerson);
        }
        sc.close();

        degreeOfKinshipForPersonA = new int[n + 1];
        Arrays.fill(degreeOfKinshipForPersonA, -1);

        findDegreeOfKinshipForPersonA();

        System.out.println(degreeOfKinshipForPersonA[personB]);
    }

    public static void findDegreeOfKinshipForPersonA() {
        Queue<Integer> queue = new LinkedList<>();
        degreeOfKinshipForPersonA[personA] = 0;
        queue.add(personA);

        while (!queue.isEmpty()) {
            int curPerson = queue.poll();

            for (int nextPerson: adjacentList.get(curPerson)) {
                if (degreeOfKinshipForPersonA[nextPerson] == -1) {
                    degreeOfKinshipForPersonA[nextPerson] = degreeOfKinshipForPersonA[curPerson] + 1;
                    queue.add(nextPerson);
                }
            }
        }
    }
}
