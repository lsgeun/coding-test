// https://www.acmicpc.net/problem/9461
#include <iostream>

int testCaseCount;
long long padovanSeq[100];

long long getPadovanSeqValue(int subscript);

int main(void) {
    for (int index = 0; index < 100; index = index + 1) {
        int subscript = index + 1;
        if (subscript == 1 || subscript == 2 || subscript == 3) {
            padovanSeq[index] = 1;
        } else if (subscript == 4 || subscript == 5) {
            padovanSeq[index] = 2;
        } else {
            padovanSeq[index] = 0;
        }
    }

    std::cin >> testCaseCount;

    int padovanSeqSubscripts[testCaseCount];
    for (int i = 0; i < testCaseCount; i = i + 1) {
        std::cin >> padovanSeqSubscripts[i];
    }

    for (int i = 0; i < testCaseCount; i = i + 1) {
        if (i != testCaseCount - 1) {
            std::cout << getPadovanSeqValue(padovanSeqSubscripts[i]) << '\n';
        } else {
            std::cout << getPadovanSeqValue(padovanSeqSubscripts[i]);
        }
    }

    return 0;
}

long long getPadovanSeqValue(int subscript) {
    int index = subscript - 1;
    if (padovanSeq[index] != 0) {
        return padovanSeq[index];
    }

    padovanSeq[index] = getPadovanSeqValue(subscript - 1) + getPadovanSeqValue(subscript - 5);

    return padovanSeq[index];
}