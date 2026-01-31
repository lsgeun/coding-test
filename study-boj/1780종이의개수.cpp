// https://www.acmicpc.net/problem/1780
#include <iostream>

int PAPER[2187][2187];
int minusOnePaperCount = 0;
int zeroPaperCount = 0;
int onePaperCount = 0;

void getMinusOneZeroOnePaperCount(int firstCurPaperRowIndex, int firstCurPaperColumnIndex, int paperSize);

int main(void) {
    int N;
    std::cin >> N;

    for (int i = 0; i < N; i = i + 1) {
        for (int j = 0; j < N; j = j + 1) {
            int unitPaper;
            std::cin >> unitPaper;
            PAPER[i][j] = unitPaper;
        }
    }

    getMinusOneZeroOnePaperCount(0, 0, N);

    std::cout << minusOnePaperCount << std::endl;
    std::cout << zeroPaperCount << std::endl;
    std::cout << onePaperCount << std::endl;

    return 0;
}

void getMinusOneZeroOnePaperCount(int firstCurPaperRowIndex, int firstCurPaperColumnIndex, int paperSize) {
    int curPaperFirstNumber = PAPER[firstCurPaperRowIndex][firstCurPaperColumnIndex];

    bool EachUnitpaperIsCurPaperFirstNumber = true;
    for (int curPaperRowIndex = firstCurPaperRowIndex; curPaperRowIndex < firstCurPaperRowIndex + paperSize;
         curPaperRowIndex = curPaperRowIndex + 1) {
        for (int curPaperColumnIndex = firstCurPaperColumnIndex;
             curPaperColumnIndex < firstCurPaperColumnIndex + paperSize;
             curPaperColumnIndex = curPaperColumnIndex + 1) {
            if (PAPER[curPaperRowIndex][curPaperColumnIndex] != curPaperFirstNumber) {
                EachUnitpaperIsCurPaperFirstNumber = false;
                break;
            }
        }
        if (EachUnitpaperIsCurPaperFirstNumber == false) {
            break;
        }
    }

    if (EachUnitpaperIsCurPaperFirstNumber) {
        if (curPaperFirstNumber == -1) {
            minusOnePaperCount = minusOnePaperCount + 1;
        } else if (curPaperFirstNumber == 0) {
            zeroPaperCount = zeroPaperCount + 1;
        } else {
            onePaperCount = onePaperCount + 1;
        }

        return;
    }

    for (int i = 0; i < 3; i = i + 1) {
        for (int j = 0; j < 3; j = j + 1) {
            int firstSlicePaperRowIndex = firstCurPaperRowIndex + i * paperSize / 3;
            int firstSlicePaperColumnIndex = firstCurPaperColumnIndex + j * paperSize / 3;
            int slicePaperSize = paperSize / 3;

            getMinusOneZeroOnePaperCount(firstSlicePaperRowIndex, firstSlicePaperColumnIndex, slicePaperSize);
        }
    }
}

// #include <iostream>
// #include <vector>

// int minusOnePaperCount = 0;
// int zeroPaperCount = 0;
// int onePaperCount = 0;

// void getMinusOneZeroOnePaperCount(std::vector<std::vector<int>> PAPER, int paperSize);

// int main(void)
// {
//     int N;
//     std::cin >> N;

//     std::vector<std::vector<int>> PAPER;

//     for (int i = 0; i < N; i = i + 1) {
//         std::vector<int> PAPERRow;
//         for (int j = 0; j < N; j = j + 1) {
//             int unitPaper;
//             std::cin >> unitPaper;
//             PAPERRow.push_back(unitPaper);
//         }
//         PAPER.push_back(PAPERRow);
//     }

//     getMinusOneZeroOnePaperCount(PAPER, N);

//     std::cout << minusOnePaperCount << std::endl;
//     std::cout << zeroPaperCount << std::endl;
//     std::cout << onePaperCount << std::endl;

//     return 0;
// }

// void getMinusOneZeroOnePaperCount(std::vector<std::vector<int>> paper, int paperSize) {
//     int paper00 = paper[0][0];

//     bool EachUnitpaperIsPaper00 = true;
//     for (int i = 0; i < paperSize; i = i + 1) {
//         for (int j = 0; j < paperSize; j = j + 1) {
//             if (paper[i][j] != paper00) {
//                 EachUnitpaperIsPaper00 = false;
//                 break;
//             }
//         }
//         if (EachUnitpaperIsPaper00 == false) {
//             break;
//         }
//     }

//     if (EachUnitpaperIsPaper00) {
//         if (paper00 == -1) {
//             minusOnePaperCount = minusOnePaperCount + 1;
//         }
//         else if (paper00 == 0) {
//             zeroPaperCount = zeroPaperCount + 1;
//         }
//         else {
//             onePaperCount = onePaperCount + 1;
//         }

//         return;
//     }

//     for (int i = 0; i < 3; i = i + 1) {
//         for (int j = 0; j < 3; j = j + 1) {
//             int minSlicePaperRowIndex = i*paperSize/3, maxSlicePaperRowIndex = (paperSize-1) - (2-i)*paperSize/3;
//             int minSlicePaperColumnIndex = j*paperSize/3, maxSlicePaperColumnIndex = (paperSize-1) -
//             (2-j)*paperSize/3; int slicePaperSize = paperSize/3;

//             std::vector<std::vector<int>> slicePaper;
//             for (int slicePaperRowIndex = minSlicePaperRowIndex; slicePaperRowIndex <= maxSlicePaperRowIndex;
//             slicePaperRowIndex = slicePaperRowIndex + 1) {
//                 std::vector<int> slicePaperRow;
//                 for (int slicePaperColumnIndex = minSlicePaperColumnIndex; slicePaperColumnIndex <=
//                 maxSlicePaperColumnIndex; slicePaperColumnIndex = slicePaperColumnIndex + 1) {
//                     slicePaperRow.push_back(paper[slicePaperRowIndex][slicePaperColumnIndex]);
//                 }
//                 slicePaper.push_back(slicePaperRow);
//             }
//             getMinusOneZeroOnePaperCount(slicePaper, slicePaperSize);
//         }
//     }
// }
