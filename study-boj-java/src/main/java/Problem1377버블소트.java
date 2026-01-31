import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 책 풀이
// https://www.acmicpc.net/problem/1377
public class Problem1377버블소트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Data[] arr = new Data[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Data(Integer.parseInt(br.readLine()), i);
        }
        br.close();

        Arrays.sort(arr);
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            int length = arr[i].index - i;
            if (maxLength < length) {
                maxLength = length;
            }
        }

        System.out.print(maxLength + 1);
    }
}

class Data implements Comparable<Data> {
    int value;
    int index;

    public Data(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(Data o) {
        return this.value - o.value;
    }
}

// 시간복잡도가 O(n^2)인 버블 정렬을 사용한 코드
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1377
// public class Baekjoon_1337 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        int[] arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//
//        boolean changed;
//        for (int i = 0; i < N-1; i++) {
//            changed = false;
//            for (int j = 0; j < N-1-i; j++) {
//                if (arr[j] > arr[j+1]) {
//                    changed = true;
//                    int temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                }
//            }
//
//            if (changed == false) {
//                System.out.println(i+1);
//                break;
//            }
//        }
//
//        br.close();
//    }
//}
