import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1033
public class Problem1033칵테일 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // ratioInfoLists의 정보를 가지고 ratioList를 만드는 것
        ArrayList<ArrayList<ArrayList<Integer>>> ratioInfoLists = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<ArrayList<Integer>> ratioInfoList = new ArrayList<>();
            ratioInfoLists.add(ratioInfoList);
        }

        for (int i = 0; i < N - 1; i++) {
            ArrayList<Integer> ratioInfoA = new ArrayList<>();
            ArrayList<Integer> ratioInfoB = new ArrayList<>();
            int a = sc.nextInt(), b = sc.nextInt(), p = sc.nextInt(), q = sc.nextInt();
            ratioInfoA.add(b);
            ratioInfoA.add(p);
            ratioInfoA.add(q);
            ratioInfoB.add(a);
            ratioInfoB.add(p);
            ratioInfoB.add(q);

            ArrayList<ArrayList<Integer>> ratioInfoAList = ratioInfoLists.get(a);
            ArrayList<ArrayList<Integer>> ratioInfoBList = ratioInfoLists.get(b);
            ratioInfoAList.add(ratioInfoA);
            ratioInfoBList.add(ratioInfoB);
        }

        ArrayList<ArrayList<Integer>> ratioList = new ArrayList<>();
        ArrayList<Integer> ratio0 = new ArrayList<>();
        ratio0.add(1);
        ratio0.add(1);
        ratioList.add(0, ratio0);
        for (int i = 1; i < N; i++) {
            ArrayList<Integer> ratio = new ArrayList<>();
            ratioList.add(i, ratio);
        }

        ArrayList<ArrayList<Integer>> ratioInfo0List = ratioInfoLists.get(0);
        for (int i = 0; i < ratioInfo0List.size(); i++) {
            ArrayList<Integer> ratioInfo0 = ratioInfo0List.get(i);
            int element = ratioInfo0.get(0), p = ratioInfo0.get(1), q = ratioInfo0.get(2);
            ArrayList<Integer> ratio = ratioList.get(element);
            ratio.add(element);
            ratio.add(q);
            ratio.add(p);
        }

        for (int i = 1; i < N; i++) {
            ArrayList<ArrayList<Integer>> ratioInfoList = ratioInfoLists.get(i);
            for (int j = 0; j < ratioInfoList.size(); j++) {
                ArrayList<Integer> ratioInfo = ratioInfoList.get(i);
                int element = ratioInfo.get(0), p = ratioInfo.get(1), q = ratioInfo.get(2);
//                if (element)
            }
        }
    }

    static public int lcm(int a, int b) {
        int lcm;

        int dividend = a, divider = b;
        int gcd;
        while (true) {
            int remainder = dividend % divider;

            if (remainder == 0) {
                gcd = divider;
                break;
            }
            if (divider % remainder == 0) {
                gcd = remainder;
                break;
            }

            dividend = divider;
            divider = remainder;
        }

        lcm = a * b / gcd;

        return lcm;
    }
}
