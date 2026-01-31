// https://www.acmicpc.net/problem/1182
#include <iostream>

int N, S;
int seq[20];
int subseqWhoseTotalSumIsSCount = 0;

void countSubseqWhoseTotalSumIsS(int sebseqTotalSum, int pos);

int main(void) {
    std::cin >> N >> S;

    for (int i = 0; i < N; i = i + 1) {
        std::cin >> seq[i];
    }

    countSubseqWhoseTotalSumIsS(0, 0);
    if (S == 0) {
        subseqWhoseTotalSumIsSCount = subseqWhoseTotalSumIsSCount - 1;
    }

    std::cout << subseqWhoseTotalSumIsSCount;

    return 0;
}

void countSubseqWhoseTotalSumIsS(int sebseqTotalSum, int pos) {
    if (pos == N) {
        if (sebseqTotalSum == S) {
            subseqWhoseTotalSumIsSCount = subseqWhoseTotalSumIsSCount + 1;
        }
        return;
    }

    countSubseqWhoseTotalSumIsS(sebseqTotalSum, pos + 1);
    countSubseqWhoseTotalSumIsS(sebseqTotalSum + seq[pos], pos + 1);
}
