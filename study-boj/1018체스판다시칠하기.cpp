// https://www.acmicpc.net/problem/1018
#include <iostream>
#include <vector>

int N, M;
std::vector<std::vector<char>> BOARD;

int getMinBOARDRepainting(std::vector<std::vector<char>> BOARD);

int main(void) {
    std::cin >> N >> M;

    for (int i = 0; i < N; i = i + 1) {
        std::vector<char> BOARDRow;
        for (int j = 0; j < M; j = j + 1) {
            char BOARDij;
            std::cin >> BOARDij;
            BOARDRow.push_back(BOARDij);
        }
        BOARD.push_back(BOARDRow);
    }

    std::cout << getMinBOARDRepainting(BOARD);

    return 0;
}

int getMinBOARDRepainting(std::vector<std::vector<char>> BOARD) {
    int minRepaintingCount = 65, curRepaintingCount = 0;

    for (int TLIndexRow = 0; TLIndexRow <= N - 8; TLIndexRow = TLIndexRow + 1) {
        for (int TLIndexColumn = 0; TLIndexColumn <= M - 8; TLIndexColumn = TLIndexColumn + 1) {
            for (int CurIndexRow = TLIndexRow; CurIndexRow <= TLIndexRow + 7; CurIndexRow = CurIndexRow + 1) {
                for (int CurIndexColumn = TLIndexColumn; CurIndexColumn <= TLIndexColumn + 7;
                     CurIndexColumn = CurIndexColumn + 1) {
                    bool isBlack = (CurIndexRow % 2 == 0 && CurIndexColumn % 2 == 0) ||
                                   (CurIndexRow % 2 == 1 && CurIndexColumn % 2 == 1),
                         isWhite = (CurIndexRow % 2 == 0 && CurIndexColumn % 2 == 1) ||
                                   (CurIndexRow % 2 == 1 && CurIndexColumn % 2 == 0);
                    if (isBlack && BOARD[CurIndexRow][CurIndexColumn] == 'W') {
                        curRepaintingCount = curRepaintingCount + 1;
                    } else if (isWhite && BOARD[CurIndexRow][CurIndexColumn] == 'B') {
                        curRepaintingCount = curRepaintingCount + 1;
                    }
                }
            }
            if (curRepaintingCount < minRepaintingCount) {
                minRepaintingCount = curRepaintingCount;
            }
            curRepaintingCount = 0;

            for (int CurIndexRow = TLIndexRow; CurIndexRow <= TLIndexRow + 7; CurIndexRow = CurIndexRow + 1) {
                for (int CurIndexColumn = TLIndexColumn; CurIndexColumn <= TLIndexColumn + 7;
                     CurIndexColumn = CurIndexColumn + 1) {
                    bool isWhite = (CurIndexRow % 2 == 0 && CurIndexColumn % 2 == 0) ||
                                   (CurIndexRow % 2 == 1 && CurIndexColumn % 2 == 1),
                         isBlack = (CurIndexRow % 2 == 0 && CurIndexColumn % 2 == 1) ||
                                   (CurIndexRow % 2 == 1 && CurIndexColumn % 2 == 0);
                    if (isWhite && BOARD[CurIndexRow][CurIndexColumn] == 'B') {
                        curRepaintingCount = curRepaintingCount + 1;
                    } else if (isBlack && BOARD[CurIndexRow][CurIndexColumn] == 'W') {
                        curRepaintingCount = curRepaintingCount + 1;
                    }
                }
            }
            if (curRepaintingCount < minRepaintingCount) {
                minRepaintingCount = curRepaintingCount;
            }
            curRepaintingCount = 0;
        }
    }

    return minRepaintingCount;
}