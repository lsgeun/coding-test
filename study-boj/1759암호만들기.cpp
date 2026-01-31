// 문제 https://www.acmicpc.net/problem/1759
// 풀이
#include <algorithm>
#include <iostream>
#include <vector>

class Code {
  private:
    int L, C;
    std::vector<char> Characters;
    std::vector<char> code;
    int vowel_count = 0;
    int consonant_count = 0;

  public:
    void inputLC(void);
    void inputCharacters(void);
    void sortCharacters(void);
    void findCodeWith1V2C(void);
    void _findCodeWith1V2C(std::vector<char>::iterator firstSearchIndex); // 1 vowel, 2 consonant
    template <class T> void showVector(T vector);
};

int main(void) {
    Code code;
    code.inputLC();
    code.inputCharacters();
    code.sortCharacters();
    code.findCodeWith1V2C();
    return 0;
}
// L, C 입력 받기
void Code::inputLC(void) { std::cin >> this->L >> this->C; }
// Character 입력 받기
void Code::inputCharacters(void) {
    char oneCharacter;
    for (int i = 0; i < C; ++i) {
        std::cin >> oneCharacter;
        this->Characters.push_back(oneCharacter);
    }
}
// Character 정렬하기
void Code::sortCharacters(void) { std::sort(this->Characters.begin(), this->Characters.end()); }
void Code::findCodeWith1V2C(void) { Code::_findCodeWith1V2C(this->Characters.begin()); }
void Code::_findCodeWith1V2C(std::vector<char>::iterator firstSearchIndex) {
    // 크기가 L이면서 자음이 최소 2개이고 모음이 최소 1개라면 출력하고 반환되며
    // 크기가 L이지만 자음이 최소 2개가 아니거나 모음이 최소 1개가 아니라면 출력하지 않고 반환된다.
    if (this->code.size() == this->L) {
        if (this->consonant_count >= 2 && this->vowel_count >= 1) {
            for (std::vector<char>::iterator iter = this->code.begin(); iter != this->code.end(); ++iter) {
                std::cout << *iter;
                if (iter + 1 == this->code.end()) {
                    std::cout << std::endl;
                }
            }
        }
        return;
    }
    // 이 라인 아래부터 위 if 문에 의해 code의 길이가 3이하임
    // 찾아야하는 원소의 인덱스가 end라면 찾을 원소가 없다는 말이므로 더 이상 진행하지 않음.
    if (firstSearchIndex == this->Characters.end()) {
        return;
    }
    for (std::vector<char>::iterator iter = firstSearchIndex; iter != this->Characters.end(); ++iter) {
        this->code.push_back(*iter);
        if (*iter == 'a' || *iter == 'e' || *iter == 'i' || *iter == 'o' || *iter == 'u') {
            ++this->vowel_count;
        } else // 자음인 경우
        {
            ++this->consonant_count;
        }
        // Code::showVector(code);
        // std::cout << std::endl;

        Code::_findCodeWith1V2C(iter + 1);

        this->code.pop_back();
        if (*iter == 'a' || *iter == 'e' || *iter == 'i' || *iter == 'o' || *iter == 'u') {
            --this->vowel_count;
        } else // 자음인 경우
        {
            --this->consonant_count;
        }
    }
}

// vector 보여주기
template <class T> void Code::showVector(T vector) {
    // show Characters
    for (typename T::iterator iter = vector.begin(); iter < vector.end(); ++iter) // typename의 역할이 정확히 뭘까?
    {
        std::cout << *iter;
    }
}
