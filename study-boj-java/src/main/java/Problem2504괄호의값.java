import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/2504
public class Problem2504괄호의값 {
    static char[] string;
    static int num;
    static boolean isCorrectString;

    public static void main(String[] args) throws IOException {
        setUp();
        calculateNum();
        System.out.println(num);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();

        // string 처음과 마지막은 더미 값, 그 사이값이 입력받은 문자열
        // getStringNum의 논리를 그대로 이용하기 위해서 더미 값을 넣음
        string = new char[inputString.length() + 2];
        for (int i = 0; i < inputString.length(); i++) {
            string[i + 1] = inputString.charAt(i);
        }

        num = 0;

        isCorrectString = true;

        br.close();
    }

    static void calculateNum() {
        num = getStringNum(0, string.length - 1);

        if (!isCorrectString) {
            num = 0;
        }
    }

    static int getStringNum(int leftEnd, int rightEnd) {
        ArrayList<int[]> subStrings = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        int start = leftEnd + 1;
        for (int i = leftEnd + 1; i <= rightEnd - 1; i++) {
            char c = string[i];
            if (c == '(' || c == '[') {
                stack.add(c);
            } else { // c == ')' || c == ']'
                if (stack.isEmpty()) {
                    isCorrectString = false;
                }
                else if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    isCorrectString = false;
                }
            }

            if (!isCorrectString) {
                break;
            }

            if (stack.isEmpty()) {
                subStrings.add(new int[]{start, i});
                start = i + 1;
            }
        }

        // 맨 처음 호출할 때, 문자열 전체를 살펴보며, 문자열이 올바른 괄호열인지 알 수 있음
        // 따라서, 호출된 재귀함수를 곧바로 반환하는 최적화를 쓰지 않아도 됨
        if (!isCorrectString) {
            return -1;
        }

        int num = 0;

        for (int[] subString : subStrings) {
            int first = subString[0];
            int last = subString[1];
            int length = last - first + 1;

            if (string[first] == '(' && string[last] == ')') {
                if (length == 2) {
                    num += 2;
                } else { // length는 4보다 크거나 같은 2의 배수
                    num += 2 * getStringNum(first, last);
                }
            } else if (string[first] == '[' && string[last] == ']') {
                if (length == 2) {
                    num += 3;
                } else { // length는 4보다 크거나 같은 2의 배수
                    num += 3 * getStringNum(first, last);
                }
            }
        }

        return num;
    }
}
