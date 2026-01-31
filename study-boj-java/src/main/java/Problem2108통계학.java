import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/2108
public class Problem2108통계학 {
    public static int n;
    public static final int THRESHOLD = 4000;
    public static final int[] countingArray = new int[THRESHOLD * 2 + 1];
    public static final int SHIFT_COUNT = THRESHOLD;
    public static final ArrayList<Integer> presentValues = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            countingArray[Integer.parseInt(br.readLine()) + SHIFT_COUNT]++;
        }

        for (int value = 0; value < countingArray.length; value++) {
            if (countingArray[value] >= 1) {
                presentValues.add(value);
            }
        }

        int arithmeticMean = getArithmeticMean();
        int median = getMedian();
        int mode = getMode();
        int interval = getInterval();

        System.out.println(arithmeticMean);
        System.out.println(median);
        System.out.println(mode);
        System.out.println(interval);

        br.close();
    }

    public static int getArithmeticMean() {
        int arithmeticMean = 0;
        for (int i = 0; i < presentValues.size(); i++) {
            arithmeticMean += countingArray[presentValues.get(i)] * (presentValues.get(i) - SHIFT_COUNT);
        }
        arithmeticMean = (int) Math.round((double) arithmeticMean / n);

        return arithmeticMean;
    }

    public static int getMedian() {
        int median = presentValues.get(0) - SHIFT_COUNT;

        int count = 0;
        for (int i = 0; i < presentValues.size(); i++) {
            count += countingArray[presentValues.get(i)];
            if (count >= (n + 1) / 2) {
                median = presentValues.get(i) - SHIFT_COUNT;
                break;
            }
        }

        return median;
    }

    public static int getMode() {
        int mode = presentValues.get(0) - SHIFT_COUNT;

        int maxFrequency = 0;
        ArrayList<Integer> maxFrequencyValues = new ArrayList<>();
        for (int i = 0; i < presentValues.size(); i++) {
            if (countingArray[presentValues.get(i)] > maxFrequency) {
                maxFrequencyValues.clear();
                maxFrequencyValues.add(presentValues.get(i));
                maxFrequency = countingArray[presentValues.get(i)];
            } else if (countingArray[presentValues.get(i)] == maxFrequency) {
                maxFrequencyValues.add(presentValues.get(i));
                maxFrequency = countingArray[presentValues.get(i)];
            } else {
                // 작다면 아무것도 안 함.
            }
        }

        if (maxFrequencyValues.size() == 1) {
            mode = maxFrequencyValues.get(0) - SHIFT_COUNT;
        } else if (maxFrequencyValues.size() >= 2) { //
            mode = maxFrequencyValues.get(1) - SHIFT_COUNT;
        } else {
            mode = -1 - SHIFT_COUNT; // 에러 상태임.
        }

        return mode;
    }

    public static int getInterval() {
        int interval = presentValues.get(presentValues.size() - 1) - presentValues.get(0);

        return interval;
    }
}
