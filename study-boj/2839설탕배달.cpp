// https://www.acmicpc.net/problem/2839
#include <iostream>

int minEnvelopOfSugerWeight[5000 - 2];
int N;

int findMinEnvelopOfSugerNkg(void);
bool sugerWeightHasMinEnvelop(int sugerWeight);

int main(void) {
    for (int i = 0; i < 5000 - 2; i = i + 1) {
        minEnvelopOfSugerWeight[i] = 0;
    }
    std::cin >> N;

    int minEnvelopOfSugerNkg = findMinEnvelopOfSugerNkg();
    if (minEnvelopOfSugerNkg == 0) {
        std::cout << -1;
    } else {
        std::cout << minEnvelopOfSugerNkg;
    }

    return 0;
}

int findMinEnvelopOfSugerNkg(void) {
    minEnvelopOfSugerWeight[3 - 3] = 1;
    minEnvelopOfSugerWeight[5 - 3] = 1;

    for (int curSugerWeight = 6; curSugerWeight <= N; curSugerWeight = curSugerWeight + 1) {
        if (!sugerWeightHasMinEnvelop(curSugerWeight)) {
            continue;
        }

        int &minEnvelopOfCurSugerWeight = minEnvelopOfSugerWeight[curSugerWeight - 3];
        int &minEnvelopOfCurSugerWeightMinus3 = minEnvelopOfSugerWeight[curSugerWeight - 3 - 3];
        if (minEnvelopOfCurSugerWeightMinus3 > 0) {
            minEnvelopOfCurSugerWeight = minEnvelopOfCurSugerWeightMinus3 + 1;
        }
        int &minEnvelopOfCurSugerWeightMinus5 = minEnvelopOfSugerWeight[curSugerWeight - 5 - 3];
        if (minEnvelopOfCurSugerWeightMinus5 > 0) {
            if (minEnvelopOfCurSugerWeight > 0) {
                if (minEnvelopOfCurSugerWeightMinus5 + 1 < minEnvelopOfCurSugerWeight) {
                    minEnvelopOfCurSugerWeight = minEnvelopOfCurSugerWeightMinus5 + 1;
                }
            } else {
                minEnvelopOfCurSugerWeight = minEnvelopOfCurSugerWeightMinus5 + 1;
            }
        }
    }

    int minEnvelopOfSugerNkg = minEnvelopOfSugerWeight[N - 3];
    return minEnvelopOfSugerNkg;
}

bool sugerWeightHasMinEnvelop(int sugerWeight) {
    int sugerWeightMod10 = sugerWeight % 10;
    if (sugerWeightMod10 == 0) {
        if (sugerWeight == 0) {
            return false;
        } else {
            return true;
        }
    }
    if (sugerWeightMod10 == 5) {
        return true;
    }

    if (sugerWeightMod10 == 1 || sugerWeightMod10 == 6) {
        if (sugerWeight - 6 >= 0) {
            return true;
        }
    }
    if (sugerWeightMod10 == 2 || sugerWeightMod10 == 7) {
        if (sugerWeight - 12 >= 0) {
            return true;
        }
    }
    if (sugerWeightMod10 == 3 || sugerWeightMod10 == 8) {
        if (sugerWeight - 3 >= 0) {
            return true;
        }
    }
    if (sugerWeightMod10 == 4 || sugerWeightMod10 == 9) {
        if (sugerWeight - 9 >= 0) {
            return true;
        }
    }

    return false;
}