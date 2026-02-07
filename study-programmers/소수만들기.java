import java.io.*;
import java.util.*;

class 소수만들기 {
    // max(count) = 19600
    static int[] numsCopy;
    static ArrayList<Integer> combinationNums;
    static int n;
    static int r;

    static int totalCount;

    public int solution(int[] nums) {
        setUp(nums);

        getTotalCount(0);

        return totalCount;
    }

    static void setUp(int[] nums) {
        numsCopy = nums;
        n = numsCopy.length;
        r = 3;
        combinationNums = new ArrayList<>();

        totalCount = 0;
    }

    static void getTotalCount(int start) {
        if (combinationNums.size() == r) {
            if (isPrime(combinationNums)) {
                totalCount += 1;
            }

            return;
        }

        for (int i = start; i < n; i++) {
            combinationNums.add(numsCopy[i]);
            getTotalCount(i + 1);
            combinationNums.remove(combinationNums.size() - 1);
        }
    }

    static boolean isPrime(ArrayList<Integer> nums) {
        // nums[]에 담긴 수는 항상 자연수
        // 그러므로 num도 자연수
        int num = 0;
        for (int i = 0; i < r; i++) {
            num += nums.get(i);
        }

        boolean isPrime = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}