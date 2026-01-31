#include <stdio.h>

#define MOD 1000000000

int main() {

    int dp[200 + 1][200 + 1] = {
        0,
    };
    int n, k, i, j, l;

    scanf("%d %d", &n, &k);

    for (i = 0; i <= n; i++) {
        dp[0][i] = 0;
        dp[1][i] = 1;
    }

    for (i = 2; i <= k; i++) {
        for (j = 0; j <= n; j++) {
            for (l = 0; l <= j; l++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j - l]) % MOD;
            }
        }
    }

    printf("%d\n", dp[k][n]);
    return 0;
}
