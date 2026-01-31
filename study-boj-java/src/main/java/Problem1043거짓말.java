import java.util.ArrayDeque;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1043
public class Problem1043거짓말 {
    // N 사람 수, M 파티의 수, T 진실을 아는 사람 수, P 파티에 오는 사람
    static int N, M, T, P;
    static int[] nodeIndex;
    static int[] truthPerson;
    static int[] firstPersonOfParty;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        T = sc.nextInt();
        truthPerson = new int[T];
        for (int i = 0; i < T; i++) {
            truthPerson[i] = sc.nextInt();
        }

        nodeIndex = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nodeIndex[i] = i;
        }

        firstPersonOfParty = new int[M];
        for (int i = 0; i < M; i++) {
            P = sc.nextInt();
            int firstPerson = sc.nextInt();
            firstPersonOfParty[i] = firstPerson;

            // 파티에 한 명만 있는 경우 union 연산하지 않음
            for (int j = 0; j < P - 1; j++) {
                int person = sc.nextInt();
                union(firstPerson, person);
            }
        }

        int partyCount = M;
        for (int i = 0; i < M; i++) {
            // 진실을 아는 사람이 없을 경우 partyCount는 줄어들지 않음
            for (int j = 0; j < T; j++) {
                boolean doesKnowIt = (find(firstPersonOfParty[i]) == find(truthPerson[j]));
                if (doesKnowIt) {
                    partyCount--;
                    break;
                }
            }
        }

        System.out.print(partyCount);
    }

    public static void union(int personX, int personY) {
        int representativeNodeOfSetPersonX = find(personX);
        int representativeNodeOfSetPersonY = find(personY);

        if (representativeNodeOfSetPersonX != representativeNodeOfSetPersonY) {
            nodeIndex[representativeNodeOfSetPersonY] = representativeNodeOfSetPersonX;
        }
    }

    public static int find(int person) {
        int representativeNodeOfSetPerson;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while (nodeIndex[person] != person) {
            stack.add(person);
            person = nodeIndex[person];
        }

        representativeNodeOfSetPerson = person;
        while (!stack.isEmpty()) {
            person = stack.pollLast();
            nodeIndex[person] = representativeNodeOfSetPerson;
        }

        return representativeNodeOfSetPerson;
    }
}
