// https://www.acmicpc.net/problem/15649
#include <iostream>

int N, M;
int candidateNumbers[8];
int selectedNumbersIndex[8];

void printPermutationInDictionaryOrder(int selectedCount);
bool findIndexInSelectedNumbersIndex(int index, int selectedNumbersIndexLength);

int main(void) {
    std::cin >> N >> M;

    for (int i = 0; i < N; i = i + 1) {
        int numberFromOneToN = i + 1;
        candidateNumbers[i] = numberFromOneToN;
    }
    for (int i = 0; i < N; i = i + 1) {
        selectedNumbersIndex[i] = -1;
    }
    printPermutationInDictionaryOrder(0);

    return 0;
}

void printPermutationInDictionaryOrder(int selectedCount) {
    if (selectedCount == M) {
        for (int i = 0; i < M; i = i + 1) {
            std::cout << candidateNumbers[selectedNumbersIndex[i]];
            if (i != M - 1) {
                std::cout << ' ';
            } else {
                std::cout << '\n';
            }
        }
        return;
    }

    for (int candidateNumberIndex = 0; candidateNumberIndex < N; candidateNumberIndex = candidateNumberIndex + 1) {
        int selectedNumbersIndexLength = selectedCount;
        if (findIndexInSelectedNumbersIndex(candidateNumberIndex, selectedNumbersIndexLength)) {
            continue;
        }

        int indexToBeInsertedInSelectedNumbersIndex = selectedCount;
        selectedNumbersIndex[indexToBeInsertedInSelectedNumbersIndex] = candidateNumberIndex;

        printPermutationInDictionaryOrder(selectedCount + 1);

        selectedNumbersIndex[indexToBeInsertedInSelectedNumbersIndex] = -1;
    }
}

bool findIndexInSelectedNumbersIndex(int index, int selectedNumbersIndexLength) {
    int doesFindIndex = false;
    for (int i = 0; i < selectedNumbersIndexLength; i = i + 1) {
        if (selectedNumbersIndex[i] == index) {
            doesFindIndex = true;
            break;
        }
    }
    return doesFindIndex;
}

// #include <iostream>
// #include <vector>

// int N, M;

// void printPermutationInDictionaryOrder(std::vector<int> candidateNumbers, std::vector<int> selectedNumbers);

// int main(void)
// {
//     std::cin >> N >> M;

//     std::vector<int> candidateNumbers;
//     for (int i = 1; i <= N; i = i + 1) {
//         candidateNumbers.push_back(i);
//     }
//     std::vector<int> selectedNumbers;
//     printPermutationInDictionaryOrder(candidateNumbers, selectedNumbers);

//     return 0;
// }

// void printPermutationInDictionaryOrder(std::vector<int> candidateNumbers, std::vector<int> selectedNumbers) {
//     if (selectedNumbers.size() == M) {
//         for (int i = 0; i < M; i = i + 1) {
//             std::cout << selectedNumbers[i];
//             if (i != M-1) {
//                 std::cout << ' ';
//             }
//             else {
//                 std::cout << '\n';
//             }
//         }
//         return;
//     }

//     for(std::vector<int>::iterator chosenNumberIter = candidateNumbers.begin(); chosenNumberIter <
//     candidateNumbers.end(); chosenNumberIter = chosenNumberIter + 1) {
//         int chosenNumber = *chosenNumberIter;
//         selectedNumbers.push_back(chosenNumber);
//         candidateNumbers.erase(chosenNumberIter, chosenNumberIter + 1);

//         printPermutationInDictionaryOrder(candidateNumbers, selectedNumbers);

//         selectedNumbers.pop_back();
//         candidateNumbers.insert(chosenNumberIter, chosenNumber);
//     }

// }
