#include <iostream>

int n;
int count[10000 + 1] = {
    0,
};

int main(int argc, char const *argv[]) {
    // 속도 높이기
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    std::cin >> n;
    for (int i = 0; i < n; i++) {
        int num;
        std::cin >> num;

        count[num]++;
    }

    for (int i = 1; i <= 10000; i++) {
        for (int j = 0; j < count[i]; j++) {
            std::cout << i << '\n';
        }
    }

    return 0;
}