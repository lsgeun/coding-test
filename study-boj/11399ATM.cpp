// https://www.acmicpc.net/problem/11399
#include <iostream>
#include <numeric>
#include <vector>

int main(void) {
    int N;
    std::cin >> N;
    std::vector<int> P;
    for (int i = 0; i < N; i = i + 1) {
        int Pi;
        std::cin >> Pi;
        P.push_back(Pi);
    }

    for (int i = 0; i < N; i = i + 1) {
        for (int j = i; j < N; j = j + 1) {
            if (P[i] > P[j]) {
                int temp = P[i];
                P[i] = P[j];
                P[j] = temp;
            }
        }
    }
    std::vector<int> withdrawTime;
    for (int i = 0; i < N; i = i + 1) {
        withdrawTime.push_back(0);
    }
    for (int i = 0; i < N; i = i + 1) {
        for (int j = i; j < N; j = j + 1) {
            withdrawTime[j] += P[i];
        }
    }

    std::vector<int>::iterator withdrawTimeBegin = withdrawTime.begin(), withdrawTimeEnd = withdrawTime.end();
    std::cout << std::accumulate(withdrawTimeBegin, withdrawTimeEnd, 0);

    return 0;
}