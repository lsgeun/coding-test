// https://www.acmicpc.net/problem/24416
#include <iostream>

int fib(int n);
int fibonacci(int n);

int fibCount = 0;
int fibonacciCount = 0;

int main(void) {
    int n;
    std::cin >> n;

    fib(n);
    fibonacci(n);

    std::cout << fibCount << ' ' << fibonacciCount;

    return 0;
}

int fib(int n) {
    if (n == 1 || n == 2) {
        fibCount = fibCount + 1;
        return 1; // 횟수를 측정할 코드 라인
    } else {
        return fib(n - 1) + fib(n - 2);
    }
}

int fibonacci(int n) {
    if (n == 1 || n == 2) {
        return 1;
    }
    int f[n];
    f[0] = 1;
    f[1] = 1;
    for (int i = 2; i < n; ++i) {
        fibonacciCount = fibonacciCount + 1;
        f[i] = f[i - 1] + f[i - 2]; // 횟수를 측정할 코드 라인
    }
    return f[n - 1];
}