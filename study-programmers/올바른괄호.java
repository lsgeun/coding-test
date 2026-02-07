import java.io.*;
import java.util.*;

class 올바른괄호 {
    static char[] stack;
    static int stackSize;

    boolean solution(String s) {
        setUp();

        boolean answer = true;

        int length = s.length();
        int index = 0;
        while (true) {
            if (index == length) {
                break;
            } // 0 <= index < length

            char ch = s.charAt(index);

            if (ch == '(') {
                push(ch);
            } else if (ch == ')') {
                if (isEmpty()) {
                    answer = false;
                    break;
                }
                // 사실 아래 코드는 필요 없긴 함.
                // 다른 괄호('{', '[' 등)이 올 때 필요한 코드
                char top = pop();
                if (top != '(') {
                    answer = false;
                }
            }

            index += 1;
        }

        if (stackSize != 0) {
            answer = false;
        }

        return answer;
    }

    static void push(char ch) {
        // 문자열의 길이가 100_000 이하이므로 에러가 발생하지 않음
        stack[stackSize] = ch;
        stackSize += 1;
    }

    static char pop() {
        // pop() 하기 전에 stackSize >= 1임이 보장됨
        char top = stack[stackSize - 1];
        stackSize -= 1;
        return top;
    }

    static boolean isEmpty() {
        return stackSize == 0 ? true : false;
    }

    static void setUp() {
        stack = new char[100_000];
        stackSize = 0;
    }
}