import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

// https://www.acmicpc.net/problem/15686
public class Problem15686치킨배달 {
    public static int n, m;
    public static ArrayList<ArrayList<Integer>> normalHouses;
    public static ArrayList<ArrayList<Integer>> chickenHouses;
    public static ArrayList<ArrayList<Integer>> chosenChickenHouses;
    public static int cityChickenDistance = 0;
    public static int min = Integer.MAX_VALUE;
    public static boolean stop = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        m = sc.nextInt();

        normalHouses = new ArrayList<>();
        chickenHouses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int num = sc.nextInt();
                if (num == 0) {
                    continue;
                }

                ArrayList<Integer> house = new ArrayList<>();
                house.add(i);
                house.add(j);
                if (num == 1) {
                    normalHouses.add(house);
                } else if (num == 2) {
                    chickenHouses.add(house);
                }
            }
        }
        chosenChickenHouses = new ArrayList<>();

        findMiniumCityChickenDistance(0);

        System.out.println(min);
    }
    public static void findMiniumCityChickenDistance(int index) {
        if (chosenChickenHouses.size() == m) {
            calculateCityChickenDistance();
            min = Math.min(min, cityChickenDistance);

            if (min == normalHouses.size()) {
                stop = true;
            }

            return;
        }

        for (int i = index; i < chickenHouses.size(); i++) {
            chosenChickenHouses.add(chickenHouses.get(i));
            findMiniumCityChickenDistance(i + 1);
            chosenChickenHouses.remove(chosenChickenHouses.size()-1);

            if (stop) {
                return;
            }
        }
    }

    public static void calculateCityChickenDistance() {
        cityChickenDistance = 0;

        for (ArrayList<Integer> normalHouse : normalHouses) {
            int chickenDistance = Integer.MAX_VALUE;
            for (ArrayList<Integer> chosenChickenHouse : chosenChickenHouses) {
                int distance = Math.abs(normalHouse.get(0) - chosenChickenHouse.get(0))
                                + Math.abs(normalHouse.get(1) - chosenChickenHouse.get(1));

                chickenDistance = Math.min(chickenDistance, distance);
            }

            cityChickenDistance += chickenDistance;
        }
    }
}
