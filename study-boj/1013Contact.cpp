// https://www.acmicpc.net/problem/1013
#include <algorithm>
#include <iostream>
#include <string>

bool interpreteInput(void);
void printResult(bool isInRe);
bool isOne(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast);
bool isZero(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast);
bool isInValidArrange(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast);

#define nextES(curES) curES = curES + 1
#define OneOrBreak(isInRe, curES, ESFirst, ESLast)                                                                     \
    isInRe = isOne(curES, ESFirst, ESLast);                                                                            \
    if (isInRe == false) {                                                                                             \
        break;                                                                                                         \
    }
#define OneOrContinue(isInRe, curES, ESFirst, ESLast)                                                                  \
    if (!isOne(curES, ESFirst, ESLast)) {                                                                              \
        continue;                                                                                                      \
    }
#define ZeroOrBreak(isInRe, curES, ESFirst, ESLast)                                                                    \
    isInRe = isZero(curES, ESFirst, ESLast);                                                                           \
    if (isInRe == false) {                                                                                             \
        break;                                                                                                         \
    }
#define InValidArrangeOrBreak(isInRe, curES, ESFirst, ESLast)                                                          \
    isInRe = isInValidArrange(curES, ESFirst, ESLast);                                                                 \
    if (isInRe == false) {                                                                                             \
        break;                                                                                                         \
    }

int main(void) {
    int TCCount = 0;
    std::cin >> TCCount;

    for (int TCIndex = 0; TCIndex < TCCount; TCIndex = TCIndex + 1) {
        bool isInRe = false;
        isInRe = interpreteInput();
        printResult(isInRe);
    }
    return 0;
}

bool interpreteInput(void) {
    std::string ES;
    std::cin >> ES;

    bool isInRe = true;
    std::string::iterator ESBegin = ES.begin(), ESEnd = ES.end(), ESFirst = ES.begin(), ESLast = ESEnd - 1;
    for (std::string::iterator curES = ESBegin; curES < ESEnd;) {
        if (*curES == '0') {
            nextES(curES);
            OneOrBreak(isInRe, curES, ESFirst, ESLast);
            nextES(curES);
        } else {
            nextES(curES);
            ZeroOrBreak(isInRe, curES, ESFirst, ESLast);
            nextES(curES);

            ZeroOrBreak(isInRe, curES, ESFirst, ESLast);
            nextES(curES);
            InValidArrangeOrBreak(isInRe, curES, ESFirst, ESLast);
            while (*curES == '0') {
                nextES(curES);
                isInRe = isInValidArrange(curES, ESFirst, ESLast);
                if (isInRe == false) {
                    break;
                }
            }
            if (isInRe == false) {
                break;
            }

            OneOrBreak(isInRe, curES, ESFirst, ESLast);
            nextES(curES);
            OneOrContinue(isInRe, curES, ESFirst, ESLast);
            while (*curES == '1') {
                nextES(curES);
                OneOrBreak(isInRe, curES, ESFirst, ESLast);
            }
        }
    }

    return isInRe;
}

void printResult(bool isInRe) {
    if (isInRe == true) {
        std::cout << "YES" << std::endl;
    } else {
        std::cout << "NO" << std::endl;
    }
}

bool isOne(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast) {
    bool isOne = true;

    isOne = isInValidArrange(curES, ESFirst, ESLast);
    if (isOne == false) {
        return isOne;
    }

    if (*curES == '0') {
        isOne = false;
        return isOne;
    }

    return isOne;
}

bool isZero(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast) {
    bool isZero = true;

    isZero = isInValidArrange(curES, ESFirst, ESLast);
    if (isZero == false) {
        return isZero;
    }

    if (*curES == '1') {
        isZero = false;
        return isZero;
    }

    return isZero;
}

bool isInValidArrange(std::string::iterator curES, std::string::iterator ESFirst, std::string::iterator ESLast) {
    bool isInValidArrange = true;

    if (!(ESFirst <= curES && curES <= ESLast)) {
        isInValidArrange = false;
    }

    return isInValidArrange;
}