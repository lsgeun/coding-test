// https://www.acmicpc.net/problem/2206
#include <iostream>
#include <queue>
#include <string>
#include <vector>

int Row, Column;

std::vector<std::vector<int>> Grid;
std::vector<std::vector<std::vector<bool>>> VisitGrid;
int EWSN[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
enum { ROW = 0, Column, ROAD = 0, WALL, BROKED_NONE = 0, BROKED_ONE_WALL };

struct GridSquareInfo {
    int row;
    int column;
    bool hasBrokenWall;
    int pathLength;
};

void initSystem(void);
void getInfo(void);
void resetVisitGrid(void);

void solve(void);
int getMinimumPathLength(void);

int main(void) {
    initSystem();
    solve();

    return 0;
}

void initSystem(void) {
    getInfo();
    resetVisitGrid();
}
void getInfo(void) {
    std::cin >> Row >> Column;

    std::cin.ignore();
    for (int i = 0; i < Row; i = i + 1) {
        std::vector<int> GridRow;
        std::string GridRowString;
        getline(std::cin, GridRowString);
        for (int j = 0; j < Column; j = j + 1) {
            int GridRowNumber = GridRowString[j] - '0';
            GridRow.push_back(GridRowNumber);
        }
        Grid.push_back(GridRow);
    }
}
void resetVisitGrid(void) {
    for (int i = 0; i < Row; i = i + 1) {
        std::vector<std::vector<bool>> VisitGrid_Row_i;
        for (int j = 0; j < Column; j = j + 1) {
            std::vector<bool> VisitGrid_ij;
            for (int k = 0; k < 2; k = k + 1) {
                VisitGrid_ij.push_back(false);
            }
            VisitGrid_Row_i.push_back(VisitGrid_ij);
        }
        VisitGrid.push_back(VisitGrid_Row_i);
    }
}

void solve(void) {
    int minimumPathLength = getMinimumPathLength();

    std::cout << minimumPathLength;
}
int getMinimumPathLength(void) {
    std::queue<GridSquareInfo> queueGridSquareInfo;

    GridSquareInfo curGridSquareInfo;
    curGridSquareInfo.row = 0, curGridSquareInfo.column = 0, curGridSquareInfo.hasBrokenWall = false,
    curGridSquareInfo.pathLength = 1;

    VisitGrid[curGridSquareInfo.row][curGridSquareInfo.column][BROKED_NONE] = true,
    VisitGrid[curGridSquareInfo.row][curGridSquareInfo.column][BROKED_ONE_WALL] = true;
    queueGridSquareInfo.push(curGridSquareInfo);

    int getMinimumPathLength = -1;
    while (!queueGridSquareInfo.empty()) {
        curGridSquareInfo = queueGridSquareInfo.front();
        queueGridSquareInfo.pop();

        bool findDestination = (curGridSquareInfo.row == Row - 1) && (curGridSquareInfo.column == Column - 1);
        if (findDestination) {
            getMinimumPathLength = curGridSquareInfo.pathLength;
            break;
        }

        for (int i = 0; i < 4; i = i + 1) {
            GridSquareInfo nextGridSquareInfo = curGridSquareInfo;

            nextGridSquareInfo.row = curGridSquareInfo.row + EWSN[i][ROW];
            nextGridSquareInfo.column = curGridSquareInfo.column + EWSN[i][Column];
            if (!((0 <= nextGridSquareInfo.row && nextGridSquareInfo.row <= Row - 1) &&
                  (0 <= nextGridSquareInfo.column && nextGridSquareInfo.column <= Column - 1))) {
                continue;
            }

            int nextGridSquare = Grid[nextGridSquareInfo.row][nextGridSquareInfo.column];
            if (nextGridSquare == WALL && nextGridSquareInfo.hasBrokenWall) {
                continue;
            }

            bool VisitGridSquareInBrokedNone =
                VisitGrid[nextGridSquareInfo.row][nextGridSquareInfo.column][BROKED_NONE];
            bool VisitGridSquareInBrokedOneWall =
                VisitGrid[nextGridSquareInfo.row][nextGridSquareInfo.column][BROKED_ONE_WALL];

            if ((!nextGridSquareInfo.hasBrokenWall && VisitGridSquareInBrokedNone) ||
                (nextGridSquareInfo.hasBrokenWall && VisitGridSquareInBrokedOneWall)) {
                continue;
            }

            if (nextGridSquare == ROAD && !nextGridSquareInfo.hasBrokenWall) {
                VisitGrid[nextGridSquareInfo.row][nextGridSquareInfo.column][BROKED_NONE] = true;
            }
            if (nextGridSquare == ROAD && nextGridSquareInfo.hasBrokenWall) {
                VisitGrid[nextGridSquareInfo.row][nextGridSquareInfo.column][BROKED_ONE_WALL] = true;
            }
            if (nextGridSquare == WALL && !nextGridSquareInfo.hasBrokenWall) {
                nextGridSquareInfo.hasBrokenWall = true;
            }
            nextGridSquareInfo.pathLength = nextGridSquareInfo.pathLength + 1;
            queueGridSquareInfo.push(nextGridSquareInfo);
        }
    }

    return getMinimumPathLength;
}