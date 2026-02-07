import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/3425
public class Problem3425고스택 {
    static String[] command;
    static int commandSize;
    static int[] num;
    static int numSize;
    // 곱셈을 담기 위해 long으로 표현
    static long[] stack;
    static int stackSize;

    public static void main(String[] args) throws IOException {
        setUp();
        solve();
    }

    static void setUp() {
        initCommandAndNum();
        command = new String[100_000];
        num = new int[10_000];
    }

    static void initCommandAndNum() {
        commandSize = 0;
        numSize = 0;
    }

    static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            // 프로그램 명령을 받거나 "QUIT"을 받음
            line = br.readLine();

            if (line.equals("")) {
                continue;
            }
            if (line.equals("QUIT")) {
                break;
            }

            // 프로그램 명령어와 숫자 초기화
            // 명령어 초기화
            while (true) {
                command[commandSize] = line;
                commandSize += 1;

                if (line.equals("END")) {
                    break;
                }

                line = br.readLine();
            }
            // 숫자 초기화
            numSize = Integer.parseInt(br.readLine());
            for (int i = 0; i < numSize; i++) {
                num[i] = Integer.parseInt(br.readLine());
            }

            // 프로그램 실행
            for (int i = 0; i < numSize; i++) {
                stack = new long[1_000];
                stackSize = 0;

                try {
                    execute("NUM " + num[i]);
                    for (int j = 0; j < commandSize; j++) {
                        execute(command[j]);
                    }

                    if (stackSize != 1) {
                        throw new Exception ();
                    } // stackSize == 1

                    System.out.println(stack[stackSize - 1]);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }
            System.out.println();

            // 명령어, 스택 초기화
            initCommandAndNum();
        }
    }

    static void execute(String command) throws Exception {
        if (command.startsWith("NUM")) {
            int num = Integer.parseInt(command.substring(4));
            stack[stackSize] = num;
            stackSize += 1;
        } else if (command.startsWith("POP")) {
            if (stackSize == 0) {
                throw new Exception();
            }

            stackSize -= 1;
        } else if (command.startsWith("INV")) {
            if (stackSize == 0) {
                throw new Exception();
            }

            stack[stackSize - 1] = -stack[stackSize - 1];
        } else if (command.startsWith("DUP")) {
            if (stackSize == 0) {
                throw new Exception();
            }

            stack[stackSize] = stack[stackSize - 1];
            stackSize += 1;
        } else if (command.startsWith("SWP")) {
            if (stackSize <= 1) {
                throw new Exception();
            }

            long temp = stack[stackSize - 1];
            stack[stackSize - 1] = stack[stackSize - 2];
            stack[stackSize - 2] = temp;
        } else if (command.startsWith("ADD")) {
            if (stackSize <= 1) {
                throw new Exception();
            }

            stack[stackSize - 2] += stack[stackSize - 1];
            stackSize -= 1;

            if (Math.abs(stack[stackSize - 1]) > 1_000_000_000L) {
                throw new Exception();
            }
        } else if (command.startsWith("SUB")) {
            if (stackSize <= 1) {
                throw new Exception();
            }

            stack[stackSize - 2] -= stack[stackSize - 1];
            stackSize -= 1;

            if (Math.abs(stack[stackSize - 1]) > 1_000_000_000L) {
                throw new Exception();
            }
        } else if (command.startsWith("MUL")) {
            if (stackSize <= 1) {
                throw new Exception();
            }

            stack[stackSize - 2] *= stack[stackSize - 1];
            stackSize -= 1;

            if (Math.abs(stack[stackSize - 1]) > 1_000_000_000L) {
                throw new Exception();
            }
        } else if (command.startsWith("DIV")) {
            if (stackSize <= 1) {
                throw new Exception();
            }
            if (stack[stackSize - 1] == 0) {
                throw new Exception();
            }

            long sign = 1;
            if ((stack[stackSize - 2] < 0 && stack[stackSize - 1] >= 0)
                    || (stack[stackSize - 2] >= 0 && stack[stackSize - 1] < 0)) {
                sign = -1;
            }
            stack[stackSize - 2] = Math.abs(stack[stackSize - 2]) / Math.abs(stack[stackSize - 1]);
            stack[stackSize - 2] *= sign;
            stackSize -= 1;
        } else if (command.startsWith("MOD")) {
            if (stackSize <= 1) {
                throw new Exception();
            }
            if (stack[stackSize - 1] == 0) {
                throw new Exception();
            }

            long sign = 1;
            if (stack[stackSize - 2] < 0) {
                sign = -1;
            }
            stack[stackSize - 2] = Math.abs(stack[stackSize - 2]) % Math.abs(stack[stackSize - 1]);
            stack[stackSize - 2] *= sign;
            stackSize -= 1;
        }
    }
}
