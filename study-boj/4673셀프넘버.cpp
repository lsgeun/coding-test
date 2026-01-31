// 문제 https://www.acmicpc.net/problem/4673
// 풀이
#include <algorithm>
#include <iostream>
#include <vector>

int d(int n) {
    // 1 <= n <= 10000
    int dn = n;
    while (n >= 1) {
        dn += n % 10;
        n = n / 10;
    }
    return dn;
}

int main(void) {
    std::vector<int> selfNumber;
    // selfNumber 찾기
    int n, m;
    for (n = 1; n <= 10000; ++n) {
        for (m = 1; m < n; ++m) {
            if (d(m) == n) {
                break;
            }
        }
        if (m == n) {
            selfNumber.push_back(n);
        }
    }
    // selfNumber vector 출력
    std::vector<int>::iterator iter_i;
    for (iter_i = selfNumber.begin(); iter_i != selfNumber.end(); ++iter_i) {
        std::cout << *iter_i;
        if (iter_i + 1 != selfNumber.end()) {
            std::cout << std::endl;
        }
    }
    return 0;
}