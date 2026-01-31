#include <iostream>

int N, K;
int array[500000];
int tmp[500000];
int cnt = 0;

void print_cur_array(int *array, int first, int tmp_idx);
void merge_sort(int *array, int first, int last);
void merge(int *array, int first, int mid, int last);

int main(void) {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    std::cin >> N >> K;

    for (int i = 0; i < N; i = i + 1) {
        std::cin >> array[i];
    }

    merge_sort(array, 0, N - 1);

    if (cnt < K) {
        std::cout << -1;
    }

    return 0;
}

void print_cur_array(int *array, int first, int tmp_idx) {
    for (int i = 0; i < first; i = i + 1) {
        std::cout << array[i] << ' ';
    }
    for (int i = first; i < tmp_idx; i = i + 1) {
        std::cout << tmp[i] << ' ';
    }
    for (int i = tmp_idx; i < N; i = i + 1) {
        std::cout << array[i] << ' ';
    }
}
void merge(int *array, int first, int mid, int last) {
    int left = first, right = mid + 1, tmp_idx = first;

    while (left <= mid && right <= last) {
        if (array[left] < array[right]) {
            tmp[tmp_idx] = array[left];
            tmp_idx += 1;
            left += 1;
            cnt += 1;
        } else {
            tmp[tmp_idx] = array[right];
            tmp_idx += 1;
            right += 1;
            cnt += 1;
        }

        if (cnt == K) {
            print_cur_array(array, first, tmp_idx);
        }
    }

    while (left <= mid) {
        tmp[tmp_idx] = array[left];
        tmp_idx += 1;
        left += 1;
        cnt += 1;

        if (cnt == K) {
            print_cur_array(array, first, tmp_idx);
        }
    }

    while (right <= last) {
        tmp[tmp_idx] = array[right];
        tmp_idx += 1;
        right += 1;
        cnt += 1;

        if (cnt == K) {
            print_cur_array(array, first, tmp_idx);
        }
    }

    for (int i = first; i <= last; i = i + 1) {
        array[i] = tmp[i];
    }
}
void merge_sort(int *array, int first, int last) {
    if (first >= last) {
        return;
    }

    int mid = (first + last) / 2;
    merge_sort(array, first, mid);
    merge_sort(array, mid + 1, last);
    merge(array, first, mid, last);
}
