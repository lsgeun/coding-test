import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem5373큐빙 {
    static int t;

    static int[][][] cube;
    static final int UP = 0, DOWN = 1, FRONT = 2, BACK = 3, LEFT = 4, RIGHT = 5;
    static final int WHITE = 0, YELLOW = 1, RED = 2, ORANGE = 3, GREEN = 4, BLUE = 5;
    static final int CLOCKWISE = 0, COUNTER_CLOCKWISE = 1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        setUp();
        solveTestCases();
    }

    static void setUp() throws IOException {
        t =  Integer.parseInt(br.readLine());

        cube = new int[6][3][3];
        initCube();
    }

    static void initCube() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(cube[UP][i], WHITE);
            Arrays.fill(cube[DOWN][i], YELLOW);
            Arrays.fill(cube[FRONT][i], RED);
            Arrays.fill(cube[BACK][i], ORANGE);
            Arrays.fill(cube[LEFT][i], GREEN);
            Arrays.fill(cube[RIGHT][i], BLUE);
        }
    }

    static void solveTestCases() throws IOException {
        StringTokenizer st;

        for (int i = 0; i < t; i++) {
            initCube();

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                String operation = st.nextToken();
                int side = getSideInt(operation.charAt(0));
                int direction = getDirectionInt(operation.charAt(1));
                rotateCube(side, direction);
            }

            printCubeUpSide();
            // printCube();
        }
    }

    static int getSideInt(char side) {
        if (side == 'U') {
            return UP;
        } else if (side == 'D') {
            return DOWN;
        } else if (side == 'F') {
            return FRONT;
        } else if (side == 'B') {
            return BACK;
        } else if (side == 'L') {
            return LEFT;
        } else if (side == 'R') {
            return RIGHT;
        } else {
            return -1;
        }
    }

    static int getDirectionInt(char direction) {
        if (direction == '+') {
            return CLOCKWISE;
        } else if (direction == '-') {
            return COUNTER_CLOCKWISE;
        } else {
            return -1;
        }
    }

    static void rotateCube(int side, int direction) {
        if (direction == CLOCKWISE) {
            rotateCubeFaceClockWise(side);
            rotateCubeSideClockWise(side);
        } else if (direction == COUNTER_CLOCKWISE) {
            for (int i = 0; i < 3; i++) {
                rotateCubeFaceClockWise(side);
                rotateCubeSideClockWise(side);
            }
        }
    }

    static void rotateCubeSideClockWise(int side) {
        int[] temp = new int[3];
        if (side == UP) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[LEFT][0][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[LEFT][0][i] = cube[FRONT][0][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[FRONT][0][i] = cube[RIGHT][0][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[RIGHT][0][i] = cube[BACK][0][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[BACK][0][i] = temp[i];
            }
        } else if (side == DOWN) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[LEFT][2][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[LEFT][2][i] = cube[BACK][2][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[BACK][2][i] = cube[RIGHT][2][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[RIGHT][2][i] = cube[FRONT][2][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[FRONT][2][i] = temp[i];
            }
        } else if (side == FRONT) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[UP][2][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[UP][2][i] = cube[LEFT][2 - i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[LEFT][2 - i][2] = cube[DOWN][0][2 - i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[DOWN][0][2 - i] = cube[RIGHT][i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[RIGHT][i][0] = temp[i];
            }
        } else if (side == BACK) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[UP][0][i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[UP][0][i] = cube[RIGHT][i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[RIGHT][i][2] = cube[DOWN][2][2 - i];
            }
            for (int i = 0; i <= 2; i++) {
                cube[DOWN][2][2 - i] = cube[LEFT][2 - i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[LEFT][2 - i][0] = temp[i];
            }
        } else if (side == LEFT) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[UP][i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[UP][i][0] = cube[BACK][2 - i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[BACK][2 - i][2] = cube[DOWN][i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[DOWN][i][0] = cube[FRONT][i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[FRONT][i][0] = temp[i];
            }
        } else if (side == RIGHT) {
            for (int i = 0; i <= 2; i++) {
                temp[i] = cube[UP][i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[UP][i][2] = cube[FRONT][i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[FRONT][i][2] = cube[DOWN][i][2];
            }
            for (int i = 0; i <= 2; i++) {
                cube[DOWN][i][2] = cube[BACK][2 - i][0];
            }
            for (int i = 0; i <= 2; i++) {
                cube[BACK][2 - i][0] = temp[i];
            }
        }
    }

    static void rotateCubeFaceClockWise(int side) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = cube[side][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = cube[side][2 - j][i];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[side][i][j] = copy[i][j];
            }
        }
    }

    static void printCubeUpSide() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cube[UP][i][j] == WHITE) {
                    sb.append('w');
                } else if (cube[UP][i][j] == YELLOW) {
                    sb.append('y');
                } else if (cube[UP][i][j] == RED) {
                    sb.append('r');
                } else if (cube[UP][i][j] == ORANGE) {
                    sb.append('o');
                } else if (cube[UP][i][j] == GREEN) {
                    sb.append('g');
                } else if (cube[UP][i][j] == BLUE) {
                    sb.append('b');
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void printCube() {
        System.out.println("BACK");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[BACK][i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("LEFT");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[LEFT][i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("UP");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[UP][i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("RIGHT");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[RIGHT][i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("FRONT");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[FRONT][i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("DOWN");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[DOWN][i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
