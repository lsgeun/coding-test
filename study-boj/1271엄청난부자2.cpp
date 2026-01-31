// 엄청난 부자2

// try 2
#include <cstring>
#include <iostream>

using namespace std;

bool is_not_smaller(char n[1001 + 1], char m[1001 + 1], int first_n, int first_m, int count_m, int mode);

int main(int argc, char *argv[]) {
    char n[1001 + 1]; // 조교가 가진 돈
    char m[1001 + 1]; // 생명체의 수
    char tmp_char[1001 + 1];
    int tmp_int;              // 스왑을 위한 임시 저장소
    char quotient[1001 + 1];  // 생명체 하나에게 돌아가는 돈의 양
    char remainder[1001 + 1]; // 1원씩 분배할 수 없는 남는 돈
    int start_n, current_n, end_n, count_n;
    int start_m, current_m, end_m, count_m;
    int end_quo;
    int between_current_end_n;
    int subtraction_count;
    // mode == 1, n[start_n] >  m[start_m]
    // mode == 0, n[start_n] <= m[start_m]
    int mode;
    // method 1
    // 생명체 하나에게 돌아가는 돈의 양, 1원씩 분배할 수 없는 남는 돈을 차례대로 입력 받음
    // if ( argc == 3 )
    // {
    //     int i;
    //     for(i=0; argv[1][i] != '\0'; ++i) { n[i] = argv[1][i]; } n[i] = '\0';
    //     for(i=0; argv[2][i] != '\0'; ++i) { m[i] = argv[2][i]; } m[i] = '\0';
    // }
    // else
    // {
    //     return 0;
    // }

    // method 2
    cin >> n >> m;

    // end_quo를 구함
    end_quo = 1001;
    // quotient 모두 '0' 으로 초기화.
    for (int i = 0; i < end_quo; ++i) {
        quotient[i] = '0';
    }
    // 맨 마지막은 널 문자로 채움. 나중에 이동 될 거임.
    quotient[end_quo] = '\0';
    // start, end와 count를 구함.
    start_n = 0, start_m = 0;
    for (int i = 0;; ++i) {
        if (n[i] == '\0') {
            end_n = i;
            count_n = i;
            break;
        }
    }
    for (int i = 0;; ++i) {
        if (m[i] == '\0') {
            end_m = i;
            count_m = i;
            break;
        }
    }
    // 최소 while의 연산을 한 번은 하는데, n이 나머지일 때까지 진행. (1인 경우가 아닐 때)
    // n[start_n] == m[start_m] && count_n == count_m인 경우는 예외임. .... 1
    // n < m 인 경우 n과 m을 바꾸어 n > m 이 되도록 하기, TODO.
    if (count_n <= count_m) {
        if (count_n == count_m) {
            if (is_not_smaller(n, m, start_n, start_m, count_m, 0)) {
                /*nope*/
            } else {
                /*tmp_char = n*/ for (int i = 0; i <= end_n; ++i) {
                    tmp_char[i] = n[i];
                }
                /*n = m*/ for (int i = 0; i <= end_m; ++i) {
                    n[i] = m[i];
                }
                /*m = tmp_char*/ for (int i = 0; i <= end_n; ++i) {
                    m[i] = tmp_char[i];
                }
                tmp_int = end_n;
                end_n = end_m;
                end_m = tmp_int;
                tmp_int = count_n;
                count_n = count_m;
                count_m = tmp_int;
            }
        } else {
            /*tmp_char = n*/ for (int i = 0; i <= end_n; ++i) {
                tmp_char[i] = n[i];
            }
            /*n = m*/ for (int i = 0; i <= end_m; ++i) {
                n[i] = m[i];
            }
            /*m = tmp_char*/ for (int i = 0; i <= end_n; ++i) {
                m[i] = tmp_char[i];
            }
            tmp_int = end_n;
            end_n = end_m;
            end_m = tmp_int;
            tmp_int = count_n;
            count_n = count_m;
            count_m = tmp_int;
        }
    }
    while (!((n[start_n] <= m[start_m] && count_n <= count_m) || (n[start_n] > m[start_m] && count_n < count_m)))
    // 탈출조건. 위의 조건은 n이 나머지인지 확인하는 조건임.(1인 경우가 아닐 때)
    // 가장 높은 자리 수 끼리 비교해서 작거나 같은 경우 개수가 같거나 작아야 하고
    //                          큰 경우 개수가 더 작아야 한다.
    {
        // 가장 높은 자리 수 끼리 비교하여 경우에 따라 'current'의 위치를 조정함.
        // 'current_n'의 위치를 조정함.
        // 1. n[start_n] > m[start_m] 탈출조건에 의해 count_n >= count_m이 보장됨.
        if (n[start_n] > m[start_m]) {
            current_n = start_n + count_m - 1;
            mode = 1;
        }
        // 2. n[start_n] <= m[start_m], 탈출조건에 의해 count_n > count_m이 보장됨.
        else {
            current_n = start_n + count_m;
            mode = 0;
        }
        // 'current_m'의 위치를 조정함.
        current_m = (end_m - 1);
        // current부터 end까지 거리를 구함.
        between_current_end_n = end_n - current_n - 1;
        // 뺀 횟수를 0으로 초기화
        subtraction_count = 0;
        // n이 m에 의해 빼진 횟수 만큼 quotient의 수 증가(ex) 1000번 빼졌다면 quotient는 1000*10^between_current_end_n)
        // 최소 한 번은 함
        while (is_not_smaller(n, m, start_n, start_m, count_m, mode)) {
            // m으로 n을 빼기
            for (int loop_count = 0; loop_count < count_m; ++loop_count, --current_n, --current_m) {
                int subtraction = n[current_n] - m[current_m];
                if (subtraction < 0) {
                    // not_zero을 찾음.
                    int not_zero;
                    for (not_zero = current_n - 1; !(n[not_zero] != '0' || not_zero < start_n); --not_zero) {
                    }
                    // not_zero 1 감소
                    n[not_zero] -= 1;
                    // not_zero에서 current_n 사이의 값을 모두 '0'->'9'로 변경
                    int added_index;
                    for (added_index = 1; not_zero + added_index < current_n; ++added_index) {
                        n[not_zero + added_index] = '9';
                    }
                    // subtraction + 10 + '0'을 current_n에 넣음.
                    n[current_n] = (subtraction + 10) + '0';
                } else // subtraction >= 0
                {
                    // subtraction + '0'을 current_n에 넣음.
                    n[current_n] = subtraction + '0';
                }
            }
            // mode == 1 일 경우 current_n 위치 변경
            if (mode == 1) {
                current_n = start_n + count_m - 1;
            }
            // mode == 09 일 경우 current_n 위치 변경
            else {
                current_n = start_n + count_m;
            }
            // current_m 위치 변경
            current_m = (end_m - 1);
            // 빼기 횟수 1 증가
            ++subtraction_count;
        }

        // quotient에 subtraction_count과 between_current_to_end를 이용해서 몫을 최신화.
        int added;
        // end_quo는 널 문자가 있는 곳.
        // max(between_current_end_n) = 1000 이므로
        // end_quo - (between_current_end_n+1) >= 0
        // end_quo - (between_current_end_n+2) >= -1
        if (end_quo - (between_current_end_n + 2) >= 0) {
            added = 10 * (quotient[end_quo - (between_current_end_n + 2)] - '0') +
                    (quotient[end_quo - (between_current_end_n + 1)] - '0') + subtraction_count;
        } else // 이 경우는 n = 10^1000, m = 1일 경우만 됨. 따라서 예외적으로 이 경우만 처리한다면 약간 속도가 높아짐.
        {
            added = (quotient[end_quo - (between_current_end_n + 1)] - '0') + subtraction_count;
        }
        // max(subtraction_count), 두 자리 수 최대값 = 99 이므로, max(added) = max(subtraction_count) + 두 자리 수
        // 최대값 = 198
        if (100 <= added && added <= 198) {
            // 가장 극단적인 경우인 n = 10^1000 - 1, m = 1 이더라도
            // end_quo - (between_current_end_n+3) >= 0. 따라서, 배열 범위를 벗어나지 않음.
            for (int added_index = 0; !(end_quo - 1 - added_index < 0); --added_index) {
                if ((quotient[end_quo - 1 - added_index] - '0') + 1 < 10) {
                    quotient[end_quo - 1 - added_index] = (quotient[end_quo - 1 - added_index] - '0') + 1 + '0';
                    break;
                } else // max(일의 자리 + 1) = 10이므로 (quotient[end_quo-1 -added_index)] - '0') + 1 == 10
                {
                    quotient[end_quo - 1 - added_index] = '0';
                }
            }
            // quotient[end_quo - (between_current_end_n+3)] = 1; // = added/100, 백의 자리
            quotient[end_quo - (between_current_end_n + 2)] = ((added / 10) % 10) + '0'; // 십의 자리
            quotient[end_quo - (between_current_end_n + 1)] = (added % 10) + '0';        // 일의 자리
        } else if (10 <= added && added <= 99) {
            quotient[end_quo - (between_current_end_n + 2)] = (added / 10) + '0';
            quotient[end_quo - (between_current_end_n + 1)] = (added % 10) + '0'; // 일의 자리
        }
        // 1 <= added&&added <= 9 // min(subtraction_count) = 1, 두 자리 수 최소값 = 00(즉, 0), min(added) =
        // min(subtraction_count) + 두 자리 수 최소값 = 1
        else {
            quotient[end_quo - (between_current_end_n + 1)] = (added % 10) + '0';
        }
        // start_n를 '0'이 아닌 곳 까지 이동. '0'이 아닌 숫자이거나 '\0'(널문자)까지 이동
        while (!(n[start_n] != '0')) {
            ++start_n;
        }
        // count_n 최신화.
        count_n = end_n - start_n;
    }
    // 예외처리, 루프를 빠져 나온 뒤에도 n > m 인 경우, 이 경우 밖에 없음. 근데 n < m 일 수도 있으므로 비교함. 만약 n >
    // m 이라면 예외처리를 통해 n < m으로 바꿔줌. 한 번만 빼기 연산이 되므로 quotient에 1이 더해지고 n이 m보다 작아짐.
    if (n[start_n] == m[start_m] && count_n == count_m) {
        if (is_not_smaller(n, m, start_n, start_m, count_m, 0)) {
            current_n = start_n + count_m - 1;
            // m으로 n을 빼기
            for (int loop_count = 0; loop_count < count_m; ++loop_count, --current_n, --current_m) {
                int subtraction = n[current_n] - m[current_m];
                if (subtraction < 0) {
                    // not_zero을 찾음.
                    int not_zero;
                    for (not_zero = current_n - 1; !(n[not_zero] != '0') && not_zero >= start_n; --not_zero)
                        // not_zero 1 감소
                        n[not_zero] -= 1;
                    // not_zero에서 current_n 사이의 값을 모두 '0'->'9'로 변경
                    int added_index;
                    for (added_index = 1; not_zero + added_index < current_n; ++added_index) {
                        n[not_zero + added_index] = '9';
                    }
                    // subtraction + 10 + '0'을 current_n에 넣음.
                    n[current_n] = (subtraction + 10) + '0';
                } else // subtraction >= 0
                {
                    // subtraction + '0'을 current_n에 넣음.
                    n[current_n] = subtraction + '0';
                }
            }
            // quotient 1 증가.
            for (int added_index = 0; end_quo - 1 - added_index >= 0; --added_index) {
                if ((quotient[end_quo - 1 - added_index] - '0') + 1 < 10) {
                    quotient[end_quo - 1 - added_index] = (quotient[end_quo - 1 - added_index] - '0') + 1 + '0';
                    break;
                } else // max(일의 자리 + 1) = 10이므로 (quotient[1001-1 -added_index)] - '0') + 1 == 10
                {
                    quotient[end_quo - 1 - added_index] = '0';
                }
            }
            // start_n를 '0'이 아닌 곳 까지 이동. '0'이 아닌 숫자이거나 '\0'(널문자)까지 이동
            while (!(n[start_n] != '0')) {
                ++start_n;
            }
            // count_n 최신화.
            count_n = end_n - start_n;
        }
    }
    // quotient의 위치를 맨 앞으로 당김.
    int quotient_front_empty_count;
    // quotient의 앞 쪽에 '0'의 갯수를 찾음
    // n = 10^1000, m = 10^1000일 경우 quotient = 1이므로 quotient는 항상 1 이상임.
    for (quotient_front_empty_count = 0;
         !(quotient[quotient_front_empty_count] != '0') && quotient_front_empty_count < end_quo;
         ++quotient_front_empty_count) {
    }
    // 탈출하면 quotient_front_empty_count는 '0'의 개수 + '0'아닌 숫자이거나 '\0'(널 문자) 까지 포함한 갯수이므로
    // quotient_front_empty_count 에 1을 빼야함.
    --quotient_front_empty_count;
    // '0' 개수만큼 앞으로 당김. '\0' 포함
    for (int i = 0; quotient_front_empty_count + 1 + i < end_quo + 1 /*count_quo*/; ++i) {
        quotient[i] = quotient[quotient_front_empty_count + 1 + i];
    }
    // n에 남아있는 것이 나머지이므로 n의 나머지들을 remainder로 옮김.
    for (int i = 0, index_n = start_n; index_n <= end_n; ++i, ++index_n) {
        remainder[i] = n[index_n];
    }
    // 나머지가 0 이라면 start_n == end_n 이라서 remainder[0] == '\0'이므로 아무것도 출력을 안 함. 따라서 이 경우
    // '\0'->'0'으로 변경
    if (remainder[0] == '\0') {
        remainder[0] = '0';
        remainder[1] = '\0';
    }
    // 최종 n, m 출력.
    // cout << n << "\n" << m << endl;
    // 정답 출력.
    cout << quotient << "\n" << remainder;

    return 0;
}

// 1. n < m 일 경우 판단하기 위해서 쓰임.
// 2. 빼는 동안 큰지 비교하는 것.
// 그냥 두 수를 비교하는 거면, 탈출 조건에 의해, 앞의 count_m개에 대해, 항상 n > m 가 보장됨.
bool is_not_smaller(char n[1001 + 1], char m[1001 + 1], int first_n, int first_m, int count_m, int mode) {
    bool ret = true;
    int added_index;
    if (mode == 0) // n[first_n] <= m[first_m]
    {
        if (n[first_n] > '0') {
            // ret == true;
        } else // n[first_n] == '0'
        {
            // ret == true 인 경우는 n >= m인 경우
            // ret == false인 경우는 n <  m인 경우
            for (added_index = 0; added_index < count_m; ++added_index) {
                if (n[first_n + 1 + added_index] == m[first_m + added_index]) {
                    // pass, ret == true;
                } else if (n[first_n + 1 + added_index] < m[first_m + added_index]) {
                    ret = false;
                    break;
                } else // ( n[first_n+mode+added_index] > m[first_m+added_index] )
                {
                    break; // ret == true;
                }
            }
        }
    } else // (mode == 1), n[first_n] > m[first_m]
    {
        // ret == true 인 경우는 n >= m인 경우
        // ret == false인 경우는 n <  m인 경우
        for (added_index = 0; added_index < count_m; ++added_index) {
            if (n[first_n + added_index] == m[first_m + added_index]) {
                // pass, ret == true;
            } else if (n[first_n + added_index] < m[first_m + added_index]) {
                ret = false;
                break;
            } else // ( n[first_n+mode+added_index] > m[first_m+added_index] )
            {
                break; // ret == true;
            }
        }
    }
    return ret;
}

// try 1

// #include <iostream>

// using namespace std;

// int main(int argc, char *argv[]) {
//     long long moneyAmount, lifeAmount;
//     cin >> moneyAmount >> lifeAmount;
//     cout << moneyAmount / lifeAmount << "\n" << moneyAmount % lifeAmount << endl;
//     return 0;
// }