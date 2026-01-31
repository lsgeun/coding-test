#include <iostream>
#include <vector>

std::vector<std::vector<int>> length_last_number;
int DIVISOR = 1000000000;

int main(void) {
    int N;
    std::cin >> N;

    // length_last_number 를 Nx10 벡터 이차 행렬로 만듦
    for (int length = 1 - 1; length <= N - 1; length = length + 1) {
        length_last_number.push_back(std::vector<int>(10, 0));
    }

    // 계단 수의 길이가 1인 경우, 1~9가 계단 수임
    for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
        int length_1 = 1 - 1;
        if (last_number == 0) {
            length_last_number[length_1][last_number] = 0;
        } else {
            length_last_number[length_1][last_number] = 1;
        }
    }

    if (N == 1) {
        // 길이가 N인 계단 수를 모두 셈
        int sum_of_length_N = 0, length_N = N - 1;
        for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
            sum_of_length_N = (sum_of_length_N + length_last_number[length_N][last_number]) % DIVISOR;
        }

        std::cout << sum_of_length_N;

        exit(0);
    }

    // 계단 수의 길이가 2인 경우
    for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
        int length_1 = 1 - 1, length_2 = 2 - 1;
        if (last_number == 0) {
            length_last_number[length_2][last_number] = length_last_number[length_1][1];
        } else if (last_number == 1) {
            length_last_number[length_2][last_number] = length_last_number[length_1][2];
        } else if (last_number == 9) {
            length_last_number[length_2][last_number] = length_last_number[length_1][8];
        } else {
            length_last_number[length_2][last_number] =
                (length_last_number[length_1][last_number - 1] + length_last_number[length_1][last_number + 1]) %
                DIVISOR;
        }
    }

    if (N == 2) {
        // 길이가 N인 계단 수를 모두 셈
        int sum_of_length_N = 0, length_N = N - 1;
        for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
            sum_of_length_N = (sum_of_length_N + length_last_number[length_N][last_number]) % DIVISOR;
        }

        std::cout << sum_of_length_N;

        exit(0);
    }

    // 계단 수의 길이가 3 이상인 경우
    for (int length = 3 - 1; length <= N - 1; length = length + 1) {
        for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
            if (last_number == 0) {
                length_last_number[length][last_number] = length_last_number[length - 1][1];
            } else if (last_number == 9) {
                length_last_number[length][last_number] = length_last_number[length - 1][8];
            } else {
                length_last_number[length][last_number] = (length_last_number[length - 1][last_number - 1] +
                                                           length_last_number[length - 1][last_number + 1]) %
                                                          DIVISOR;
            }
        }
    }

    // 길이가 N인 계단 수를 모두 셈
    int sum_of_length_N = 0, length_N = N - 1;
    for (int last_number = 0; last_number <= 9; last_number = last_number + 1) {
        sum_of_length_N = (sum_of_length_N + length_last_number[length_N][last_number]) % DIVISOR;
    }

    std::cout << sum_of_length_N;

    return 0;
}