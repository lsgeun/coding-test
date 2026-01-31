import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14891
public class Problem14891톱니바퀴 {
    public static int[][] gears;
    public static final int TWELVE = 0, THREE = 2, NINE = 6;
    public static final int N = 0, S = 1;
    public static final int CLOCKWISE = 1, COUNTER_CLOCKWISE = -1, NONE = 0;

    public static int k;
    public static int[][] moveCommands;
    public static final int GEAR_INDEX = 0, DIRECTION = 1;

    public static int score;

    public static void main(String[] args) throws IOException {
        setUp();
        findScore();
        System.out.println(score);
    }

    public static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears = new int[4 + 1][8];
        for (int i = 1; i <= 4; i++) {
            String gear = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = gear.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        moveCommands = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int gearIndex = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            moveCommands[i][GEAR_INDEX] = gearIndex;
            moveCommands[i][DIRECTION] = direction;
        }

        score = 0;
    }

    public static void findScore() {
        for (int[] moveCommand : moveCommands) {
            int gearIndex = moveCommand[GEAR_INDEX];
            int direction = moveCommand[DIRECTION];

            int[] directions = new int[4 + 1];
            Arrays.fill(directions, NONE);

            directions[gearIndex] = direction;

            for (int i = gearIndex + 1; i <= 4; i++) {
                if (gears[i - 1][THREE] == gears[i][NINE]) {
                    break;
                } else { // gears[i - 1][THREE] != gears[i][NINE]
                    direction = changeDirection(direction);
                    directions[i] = direction;
                }
            }

            direction = moveCommand[DIRECTION];
            for (int i = gearIndex - 1; i >= 1; i--) {
                if (gears[i][THREE] == gears[i + 1][NINE]) {
                    break;
                }
                else { // gears[i][THREE] != gears[i + 1][NINE]
                    direction = changeDirection(direction);
                    directions[i] = direction;
                }
            }

            for (int i = 1; i <= 4; i++) {
                if (directions[i] == NONE) {
                    continue;
                }

                rotate(i, directions[i]);
            }
        }

        for (int i = 1; i <= 4; i++) {
            if (gears[i][TWELVE] == N) {
                score += 0;
            } else if (gears[i][TWELVE] == S) {
                score += (int) Math.pow(2, i - 1);
            }
        }
    }

    public static void rotate(int gearIndex, int direction) {
        if (direction == CLOCKWISE) {
            int twelveValue = gears[gearIndex][TWELVE];
            int gearBlock = TWELVE;
            for (int i = 0; i < 7; i++) {
                gears[gearIndex][gearBlock] = gears[gearIndex][(gearBlock - 1 + 8) % 8];
                gearBlock = (gearBlock - 1 + 8) % 8;
            }

            gears[gearIndex][gearBlock] = twelveValue;
        } else if (direction == COUNTER_CLOCKWISE) {
            int twelveValue = gears[gearIndex][TWELVE];
            int gearBlock = TWELVE;
            for (int i = 0; i < 7; i++) {
                gears[gearIndex][gearBlock] = gears[gearIndex][(gearBlock + 1 + 8) % 8];
                gearBlock = (gearBlock + 1 + 8) % 8;
            }

            gears[gearIndex][gearBlock] = twelveValue;
        }
    }

    public static int changeDirection(int direction) {
        if (direction == CLOCKWISE) {
            direction = COUNTER_CLOCKWISE;
        } else if (direction == COUNTER_CLOCKWISE) {
            direction = CLOCKWISE;
        }
        return direction;
    }
}
