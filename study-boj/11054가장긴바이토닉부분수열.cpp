#include <iostream>

using namespace std;

class Bitonic {
  private:
    // 4000Byte, 약 4KB, 각 원소마다 0 ~ SequenceLength-1까지 인덱스를 1:1로 붙힘.
    int Sequence[1000] =
        {
            0,
    },
        SequenceLength = 0;
    // LIS의 ith 원소는 0부터 i까지의 원소들로 만들 수 있는 LIS임.
    // LDS의 ith 원소는 i부터 SequenceLength까지의 원소들로 만들 수 있는 LDS임.
    int LISElementLength[1000] =
        {
            0,
    },
        LDSElementLength[1000] = {
            0,
    }; // LIS 최장 증가 부분 수열, LDS 최장 김소 부분 수열
  public:
    Bitonic(void) {}              // 생성자
    void getSequenceLength(void); // 수열의 길이 입력 받음
    void getSequence(void);       // 수열의 길이만큼 입력을 받음, 1 <= Ai <= 1000(이건 검사 안 함)임.
    // void showSequence(void); // 수열을 보여줌
    void getLISElementLengthWithDp(void); // LISElementLength를 구함
    void getLDSElementLengthWithDp(void); // LDSElementLength를 구함
    void getAndshowBitonicLength(void);   // BitonicLength를 구함
};
int main(void) {
    Bitonic bitonic;
    bitonic.getSequenceLength();
    bitonic.getSequence();
    bitonic.getLISElementLengthWithDp();
    bitonic.getLDSElementLengthWithDp();
    bitonic.getAndshowBitonicLength();
    // bitonic.showSequence();
    return 0;
}

void Bitonic::getSequenceLength(void) { cin >> this->SequenceLength; }
void Bitonic::getSequence(void) {
    for (int i = 0; i < this->SequenceLength; ++i) {
        cin >> this->Sequence[i];
    }
}
// void Bitonic::showSequence(void) { for(int i = 0; i < this->SequenceLength; ++i) { cout << this->Sequence[i] << ' ';
// } }
void Bitonic::getLISElementLengthWithDp(void) {
    int start = 0, end = this->SequenceLength - 1;
    // ith 원소마다 0부터 i까지의 적어도 길이가 1인 LIS가 존재함.
    // 그 LIS은 i번째 원소만 있는 subsequence임.
    for (int i = start; i <= end; ++i) {
        this->LISElementLength[i] = 1;
    }
    for (int i = start; i <= end; ++i) {
        for (int j = start; j < i; ++j) {
            if (this->Sequence[i] > this->Sequence[j] && this->LISElementLength[j] + 1 > this->LISElementLength[i]) {
                this->LISElementLength[i] = this->LISElementLength[j] + 1;
            }
        }
    }
}
void Bitonic::getLDSElementLengthWithDp(void) {
    int start = 0, end = this->SequenceLength - 1;
    // ith 원소마다 i부터 SequenceLength까지의 적어도 길이가 1인 LDS가 존재함.
    // 그 LDS은 i번째 원소만 있는 subsequence임.
    for (int i = start; i <= end; ++i) {
        this->LDSElementLength[i] = 1;
    }
    for (int i = end; i >= start; --i) {
        for (int j = end; i < j; --j) {
            if (this->Sequence[i] > this->Sequence[j] && this->LDSElementLength[j] + 1 > this->LDSElementLength[i]) {
                this->LDSElementLength[i] = this->LDSElementLength[j] + 1;
            }
        }
    }
}
void Bitonic::getAndshowBitonicLength(void) {
    int start = 0, end = this->SequenceLength - 1;
    int BitonicLength = 0;
    for (int i = start; i <= end; ++i) {
        if (BitonicLength < this->LISElementLength[i] + this->LDSElementLength[i] - 1) {
            BitonicLength = this->LISElementLength[i] + this->LDSElementLength[i] - 1;
        }
    }
    cout << BitonicLength;
}
