import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17143
public class Problem17143낚시왕 {
    public static int r, c, m;
    public static Shark[][] map;

    public static final int[][] fourDirection = {
            {0, 0},
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };
    public static final int N = 1, S = 2, E = 3, W = 4;
    public static final int X = 0, Y = 1;

    public static int totalFishedSharkSize;

    public static void main(String[] args) throws IOException {
        setUp();
        fishSharks();
        System.out.println(totalFishedSharkSize);
    }

    static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new Shark[r + 1][c + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sharkR = Integer.parseInt(st.nextToken());
            int sharkC = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            // 방향과 위치가 동일해지는 주기(2 * (r - 1) or 2 * (c - 1))를 모두 없앰
            // 상어 이동 최적화, 이걸하지 않으면 최악의 경우 10억 번 연산되어 시간 초과
            if (direction == N || direction == S) {
                speed %= 2 * (r - 1);
            } else if (direction == E || direction == W) {
                speed %= 2 * (c - 1);
            }

            map[sharkR][sharkC] = new Shark(size, speed, direction);
        }

        totalFishedSharkSize = 0;
    }

    static void fishSharks() {
        // 1칸씩 움직임
        for (int fishingKingColumn = 1; fishingKingColumn <= c; fishingKingColumn++) {
            fishFirstSharkInColumnOf(fishingKingColumn);
            moveSharksAndEatSharks();
        }
    }

    static void fishFirstSharkInColumnOf(int column) {
        for (int i = 1; i <= r; i++) {
            if (existsSharkInSomeMap(i, column, map)) {
                Shark shark = map[i][column];
                totalFishedSharkSize += shark.size;
                map[i][column] = null;
                break;
            }
        }
    }

    static void moveSharksAndEatSharks() {
        // DEBUG
        // System.out.println("moveSharksAndEatSharks()");
        Shark[][] mapAfterMoveAndEat = new Shark[r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (!existsSharkInSomeMap(i, j, map)) {
                    continue;
                }

                Shark shark = map[i][j];
                int sharkX = i;
                int sharkY = j;

                for (int k = 0; k < shark.speed; k++) {
                    sharkX += fourDirection[shark.direction][X];
                    sharkY += fourDirection[shark.direction][Y];

                    if (!isInMap(sharkX, sharkY)) {
                        shark.direction = reverseDirection(shark.direction);
                        sharkX += 2 * fourDirection[shark.direction][X];
                        sharkY += 2 * fourDirection[shark.direction][Y];
                    }
                } // 여기부터, (i, j)는 출발점, (sharkX, sharkY)는 도착점

                // DEBUG
                // printMap 4개는 디버깅 용도, 더 최적화할 수 있을 거 같아서 남겨둠.
                // System.out.println("출발점: (" + i + ", " + j + "), " + "도착점: (" + sharkX + ", " + sharkY + ")");
                // printMap();

                if (existsSharkInSomeMap(sharkX, sharkY, mapAfterMoveAndEat)) {
                    Shark anotherShark = mapAfterMoveAndEat[sharkX][sharkY];
                    if (anotherShark.size > shark.size) {
                        // shark는 잡아먹힐 상어임
                    } else { // anotherShark.size < shark.size
                        mapAfterMoveAndEat[sharkX][sharkY] = shark;
                    }
                } else {
                    mapAfterMoveAndEat[sharkX][sharkY] = shark;
                }
            }
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] = mapAfterMoveAndEat[i][j];
            }
        }
    }

    static int reverseDirection(int direction) {
        if (direction == N) {
            direction = S;
        } else if (direction == S) {
            direction = N;
        } else if (direction == E) {
            direction = W;
        } else if (direction == W) {
            direction = E;
        } else {
            direction = 0;
        }

        return direction;
    }

    static boolean isInMap(int x, int y) {
        return (1 <= x && x <= r) && (1 <= y && y <= c);
    }

    static boolean existsSharkInSomeMap(int x, int y, Shark[][] someMap) {
        return someMap[x][y] != null;
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= r; i++){
            for (int j = 1; j <= c; j++){
                if (map[i][j] == null) {
                    sb.append(0);
                    continue;
                }
                sb.append(map[i][j].size);
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.print(sb);
    }

    static class Shark {
        public int size;
        public int speed;
        public int direction;

        public Shark(int size, int speed, int direction) {
            this.size = size;
            this.speed = speed;
            this.direction = direction;
        }
    }
}
