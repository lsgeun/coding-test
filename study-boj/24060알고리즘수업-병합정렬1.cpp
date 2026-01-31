#include <iostream>

int N, K;
int array[500000];
int tmp[500000];
int cnt = 0;
int result = -1;

void merge(int *array, int p, int q, int r);
void merge_sort(int *array, int p, int r);

int main(void) {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    std::cin >> N >> K;

    for (int i = 0; i < N; i = i + 1) {
        std::cin >> array[i];
    }

    merge_sort(array, 0, N - 1);

    std::cout << result;

    return 0;
}

void merge(int *array, int p, int q, int r) {
    int left_idx = p, right_idx = q + 1, tmp_idx = p;

    while (left_idx <= q && right_idx <= r) {
        if (array[left_idx] < array[right_idx]) {
            cnt += 1;
            tmp[tmp_idx] = array[left_idx];
            tmp_idx += 1;
            left_idx += 1;
        } else {
            cnt += 1;
            tmp[tmp_idx] = array[right_idx];
            tmp_idx += 1;
            right_idx += 1;
        }

        if (cnt == K) {
            result = tmp[tmp_idx - 1];
        }
    }

    while (left_idx <= q) {
        cnt += 1;
        tmp[tmp_idx] = array[left_idx];
        tmp_idx += 1;
        left_idx += 1;

        if (cnt == K) {
            result = tmp[tmp_idx - 1];
        }
    }

    while (right_idx <= r) {
        cnt += 1;
        tmp[tmp_idx] = array[right_idx];
        tmp_idx += 1;
        right_idx += 1;

        if (cnt == K) {
            result = tmp[tmp_idx - 1];
        }
    }

    for (int i = p; i <= r; i = i + 1) {
        array[i] = tmp[i];
    }
}
void merge_sort(int *array, int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;
        merge_sort(array, p, q);
        merge_sort(array, q + 1, r);
        merge(array, p, q, r);
    }
}
