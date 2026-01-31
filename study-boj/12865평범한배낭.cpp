#include <algorithm>
#include <iostream>

using namespace std;

int N, K;           // 1 <= N <= 100, 1 <= K <= 100000
int W[101], V[101]; // 1 <= W <= 100000, 1 <= V <= 1000
int dp[101][100001];

int main(void) {
    cin >> N >> K;
    for (int i = 1; i <= N; i = i + 1) {
        cin >> W[i] >> V[i];
    }

    for (int i = 1; i <= N; i = i + 1) {
        for (int j = 1; j <= K; j = j + 1) {
            if (W[i] <= j) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }

    cout << dp[N][K];

    return 0;
}
