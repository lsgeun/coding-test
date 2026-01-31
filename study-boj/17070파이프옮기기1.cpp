#include <iostream>

using namespace std;

int N;
int map[17][17];
int dp[17][17][3];

int main(void) {
    enum { right = 0, slash, down };
    // 입력
    cin >> N;
    for (int i = 1; i <= N; i = i + 1) {
        for (int j = 1; j <= N; j = j + 1) {
            cin >> map[i][j];
        }
    }
    // 초기값 설정
    for (int i = 2; i <= N; i = i + 1) {
        if (map[1][i] == 1) {
            break;
        }

        dp[1][i][right] = 1;
    }

    for (int i = 2; i <= N; i = i + 1) {
        for (int j = 3; j <= N; j = j + 1) {
            for (int direct = right; direct <= down; direct = direct + 1) {
                if (direct == right && map[i][j] == 0) {
                    dp[i][j][direct] = dp[i][j - 1][right] + dp[i][j - 1][slash];
                } else if (direct == slash && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    dp[i][j][direct] = dp[i - 1][j - 1][right] + dp[i - 1][j - 1][slash] + dp[i - 1][j - 1][down];
                } else if (direct == down && map[i][j] == 0) {
                    dp[i][j][direct] = dp[i - 1][j][slash] + dp[i - 1][j][down];
                }
            }
        }
    }

    cout << dp[N][N][right] + dp[N][N][slash] + dp[N][N][down];

    return 0;
}
