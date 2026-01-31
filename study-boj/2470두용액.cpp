// 문제 https://www.acmicpc.net/problem/2470
// 풀이
#include <algorithm>
#include <iostream>

using namespace std;

class LiquidWithAlkalineAndAcid {
  private:
    int numLiquid = 0;
    int LiquidContainer[100000] = {
        0,
    };

  public:
    LiquidWithAlkalineAndAcid() {}
    void inputNumLiquid(void);
    void inputLiquidContainer(void);
    void findLeastSumOfTwoLiquidContainerElements(void);
    void showLiquidContainer(void);
};

int main(void) {
    LiquidWithAlkalineAndAcid LAA;
    LAA.inputNumLiquid();
    LAA.inputLiquidContainer();
    LAA.findLeastSumOfTwoLiquidContainerElements();
    // LAA.showLiquidContainer();
    return 0;
}
void LiquidWithAlkalineAndAcid::inputNumLiquid(void) { cin >> this->numLiquid; }
void LiquidWithAlkalineAndAcid::inputLiquidContainer(void) {
    for (int i = 0; i < this->numLiquid; ++i) {
        cin >> LiquidContainer[i];
    }
}
void LiquidWithAlkalineAndAcid::findLeastSumOfTwoLiquidContainerElements(void) {
    int CopyLiquidContainer[100000];
    for (int i = 0; i < this->numLiquid; ++i) {
        CopyLiquidContainer[i] = LiquidContainer[i];
    }

    sort(CopyLiquidContainer, CopyLiquidContainer + this->numLiquid);

    int LeftElement = 0, RightElement = this->numLiquid - 1;
    int LeastSumLeftElement = 0, LeastSumRightElement = this->numLiquid - 1;
    int CurrentLeastSum =
        CopyLiquidContainer[LeftElement] + CopyLiquidContainer[RightElement]; // 음수일 수도 양수일 수도 있음.
    while (LeftElement < RightElement) // O(N), max(N) = 100000, sup(T(N)) = 100000 즉, 시간복잡도가 낮다고 볼 수 있음.
    {
        int CurrentSum = CopyLiquidContainer[LeftElement] + CopyLiquidContainer[RightElement];

        if (abs(CurrentLeastSum) > abs(CurrentSum)) {
            CurrentLeastSum = CurrentSum;
            LeastSumLeftElement = LeftElement;
            LeastSumRightElement = RightElement;
        }
        if (CurrentSum < 0) {
            ++LeftElement;
        } else if (CurrentSum > 0) {
            --RightElement;
        } else // CurrentSum == 0
        {
            break;
        }
    }

    cout << CopyLiquidContainer[LeastSumLeftElement] << ' ' << CopyLiquidContainer[LeastSumRightElement];
}
void LiquidWithAlkalineAndAcid::showLiquidContainer(void) {
    for (int i = 0; i < this->numLiquid || (i == 20); ++i) {
        cout << LiquidContainer[i] << ' ';
    }
}