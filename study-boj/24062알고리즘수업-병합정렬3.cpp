#include <iostream>
#include <stdlib.h>

// 브루트포스 시간 복잡도 = O(N * Nlog(N)) <= O(500000 * 500000 * log(500000)) = O(약 4,7328,9214,2331)
// A와 B 비교 단순화 시간 복잡도 = O(log(N) * Nlog(N)) <=  O(log(500000) * 500000 * log(500000)) = O(약 1,7984,9896)
// 브루트포스가 1이라고 할 때, 단순화된 알고리즘은 0.000002임. 100만 분의 38임.

int N;
int array_A[500000];
int array_B[500000];
int tmp[500000];

bool cmp[500000];
int false_count = 0;
bool A_equals_B = false;

void check_A_equals_B(int ti_start, int ti_end);
void merge(int first, int middle, int last);
void merge_sort(int first, int last);

int main(void) {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    std::cin >> N;                      // N 초기화
    for (int i = 0; i < N; i = i + 1) { // A 초기화
        std::cin >> array_A[i];
    }
    for (int i = 0; i < N; i = i + 1) { // B 초기화
        std::cin >> array_B[i];
    }
    for (int i = 0; i < N; i = i + 1) { // cmp 초기화
        cmp[i] = (array_A[i] == array_B[i]);
        if (array_A[i] != array_B[i]) {
            false_count = false_count + 1;
        }
    }

    merge_sort(0, N - 1);

    if (!A_equals_B) {
        std::cout << int(A_equals_B);
    }

    return 0;
}

void check_A_equals_B(int ti_start, int ti_end) {
    // for (int i = 0; i < ti_start; i = i + 1) {
    //     if (array_A[i] != array_B[i]) {
    //         A_equals_B = false;
    //         return;
    //     }
    // }
    for (int i = ti_start; i < ti_end; i = i + 1) {
        bool next_cmp_i = (tmp[i] == array_B[i]);
        if (next_cmp_i != cmp[i]) {
            if (next_cmp_i == false) {
                false_count = false_count + 1;
            } else {
                false_count = false_count - 1;
            }
        }

        cmp[i] = next_cmp_i;
    }

    if (false_count == 0) {
        A_equals_B = true;
    }
    // for (int i = ti_end; i < N; i = i + 1) {
    //     if (array_A[i] != array_B[i]) {
    //         A_equals_B = false;
    //         return;
    //     }
    // }
}

void merge(int first, int middle, int last) {
    int li = first, ri = middle + 1, ti = first;

    while (li <= middle && ri <= last) {
        if (array_A[li] < array_A[ri]) {
            tmp[ti] = array_A[li];
            ti = ti + 1;
            li = li + 1;
        } else {
            tmp[ti] = array_A[ri];
            ti = ti + 1;
            ri = ri + 1;
        }

        check_A_equals_B(first, ti);
    }

    while (li <= middle) {
        tmp[ti] = array_A[li];
        ti = ti + 1;
        li = li + 1;

        check_A_equals_B(first, ti);
    }

    while (ri <= last) {
        tmp[ti] = array_A[ri];
        ti = ti + 1;
        ri = ri + 1;

        check_A_equals_B(first, ti);
    }

    for (int i = first; i <= last; i = i + 1) {
        array_A[i] = tmp[i];
    }

    if (A_equals_B) {
        std::cout << int(A_equals_B);
        exit(0);
    }
}
void merge_sort(int first, int last) {
    if (first >= last) {
        return;
    }

    int middle = (first + last) / 2;
    merge_sort(first, middle);
    merge_sort(middle + 1, last);
    merge(first, middle, last);
}
