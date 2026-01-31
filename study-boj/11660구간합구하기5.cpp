#include <iostream>
#include <vector>

std::vector<std::vector<int>> table;
std::vector<std::vector<int>> sum_table;

int main(void) {
    // *알고리즘은 잘 풀었지만 아래 코드 3줄을 추가하지 않아서 시간 초과가 뜸
    std::ios_base::sync_wit3h_stdio(false);
    std::cout.tie(NULL);
    std::cin.tie(NULL);

    int N, M;
    std::cin >> N >> M;
    // table 초기화
    for (int i = 0; i < N; i = i + 1) {
        std::vector<int> table_row;
        for (int j = 0; j < N; j = j + 1) {
            int table_number;
            std::cin >> table_number;
            table_row.push_back(table_number);
        }
        table.push_back(table_row);
    }
    // sub_table을 원소가 모두 0인 NxN 벡터 이차 행렬로 만듦.
    for (int i = 0; i < N; i = i + 1) {
        sum_table.push_back(std::vector<int>(N, 0));
    }
    // sub_table의 0 번째 행, 0 번째 열을 만듦.
    sum_table[0][0] = table[0][0];
    for (int col = 1; col < N; col = col + 1) {
        sum_table[0][col] = sum_table[0][col - 1] + table[0][col];
    }
    for (int row = 1; row < N; row = row + 1) {
        sum_table[row][0] = sum_table[row - 1][0] + table[row][0];
    }
    // sub_table을 모두 만듦.
    for (int row = 1; row < N; row = row + 1) {
        for (int col = 1; col < N; col = col + 1) {
            sum_table[row][col] =
                sum_table[row - 1][col] + sum_table[row][col - 1] - sum_table[row - 1][col - 1] + table[row][col];
        }
    }
    // testcase마다 알맞은 정답 출력
    for (int testcase = 0; testcase < M; testcase = testcase + 1) {
        int x1, y1, x2, y2;
        std::cin >> x1 >> y1 >> x2 >> y2;
        // 벡터 이차 행렬의 인덱스로 맞추기 위해 각 값에 1을 뺌.
        x1 -= 1;
        y1 -= 1;
        x2 -= 1;
        y2 -= 1;

        int sum_of_x1y1_to_x2y2;
        if (x1 == 0 && y1 == 0) {
            sum_of_x1y1_to_x2y2 = sum_table[x2][y2];
        } else if (x1 > 0 && y1 == 0) {
            sum_of_x1y1_to_x2y2 = sum_table[x2][y2] - sum_table[x1 - 1][y2];
        } else if (x1 == 0 && y1 > 0) {
            sum_of_x1y1_to_x2y2 = sum_table[x2][y2] - sum_table[x2][y1 - 1];
        } else {
            sum_of_x1y1_to_x2y2 =
                sum_table[x2][y2] - sum_table[x2][y1 - 1] - sum_table[x1 - 1][y2] + sum_table[x1 - 1][y1 - 1];
        }

        std::cout << sum_of_x1y1_to_x2y2 << '\n';
    }

    return 0;
}
