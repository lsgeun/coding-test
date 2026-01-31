// * 원래 풀이?

// https://www.acmicpc.net/problem/2573

#include <iostream>
#include <vector>

int Row, Column;
std::vector<std::vector<int>> Grid;
std::vector<std::vector<bool>> VisitGrid;

void initSystem(void);
void getInfo(void);
void initVisitGrid(void);

void solve(void);

int getGlacierPiece(void);
void visitOneGlacierPiece(int row, int column);
void resetVisitGrid(void);

void meltGlacier(void);

bool thereIsGlacier(void);

int main(void) {
    initSystem();
    solve();

    return 0;
}

void initSystem(void) {
    getInfo();
    initVisitGrid();
}
void getInfo(void) {
    std::cin >> Row >> Column;

    for (int i = 0; i < Row; i = i + 1) {
        std::vector<int> Grid_Row_i;
        for (int j = 0; j < Column; j = j + 1) {
            int GridNumber;
            std::cin >> GridNumber;
            Grid_Row_i.push_back(GridNumber);
        }
        Grid.push_back(Grid_Row_i);
    }
}
void initVisitGrid(void) {
    for (int i = 0; i < Row; i = i + 1) {
        std::vector<bool> VisitGrid_Row_i;
        for (int j = 0; j < Column; j = j + 1) {
            bool VisitGridTruthValue = false;
            VisitGrid_Row_i.push_back(VisitGridTruthValue);
        }
        VisitGrid.push_back(VisitGrid_Row_i);
    }
}

void solve(void) {
    int year = 0;

    while (thereIsGlacier()) {
        int glacierPiece = getGlacierPiece();
        if (glacierPiece >= 2) {
            break;
        }
        year = year + 1;
        meltGlacier();
    }

    if (thereIsGlacier()) {
        std::cout << year << std::endl;
    } else {
        std::cout << 0 << std::endl;
    }
}

int getGlacierPiece(void) {
    int glacierPiece = 0;
    for (int i = 1; i < Row - 1; i = i + 1) {
        for (int j = 1; j < Column - 1; j = j + 1) {
            bool isGlacier = (Grid[i][j] > 0), doesNotVisit = (VisitGrid[i][j] == false),
                 findOneGlacierPiece = (isGlacier && doesNotVisit);
            if (findOneGlacierPiece) {
                visitOneGlacierPiece(i, j);
                glacierPiece = glacierPiece + 1;
            }
        }
    }
    resetVisitGrid();
    return glacierPiece;
}
void visitOneGlacierPiece(int row, int column) {
    bool isGlacier = (Grid[row][column] > 0), doesVisit = (VisitGrid[row][column] == true),
         findVisitedGlacierPieceSquare = (isGlacier && doesVisit), isSea = (Grid[row][column] == 0);
    if (findVisitedGlacierPieceSquare || isSea) {
        return;
    }

    VisitGrid[row][column] = true;

    int rowNeighborhood[4] = {row, row, row + 1, row - 1};
    int columnNeighborhood[4]{column + 1, column - 1, column, column};

    for (int i = 0; i < 4; i = i + 1) {
        visitOneGlacierPiece(rowNeighborhood[i], columnNeighborhood[i]);
    }
}
void resetVisitGrid(void) {
    for (int i = 1; i < Row - 1; i = i + 1) {
        for (int j = 1; j < Column - 1; j = j + 1) {
            VisitGrid[i][j] = false;
        }
    }
}

void meltGlacier(void) {
    std::vector<int> seaSquareCount;
    for (int i = 1; i < Row - 1; i = i + 1) {
        for (int j = 1; j < Column - 1; j = j + 1) {
            bool isGlacier = (Grid[i][j] > 0);
            if (isGlacier) {
                int currentSeaSquareCount = 0;
                int RightOfCurrentPositionInGrid = Grid[i][j + 1], LeftOfCurrentPositionInGrid = Grid[i][j - 1],
                    ButtomOfCurrentPositionInGrid = Grid[i + 1][j], TopOfCurrentPositionInGrid = Grid[i - 1][j];

                if (RightOfCurrentPositionInGrid == 0) {
                    currentSeaSquareCount = currentSeaSquareCount + 1;
                }
                if (LeftOfCurrentPositionInGrid == 0) {
                    currentSeaSquareCount = currentSeaSquareCount + 1;
                }
                if (ButtomOfCurrentPositionInGrid == 0) {
                    currentSeaSquareCount = currentSeaSquareCount + 1;
                }
                if (TopOfCurrentPositionInGrid == 0) {
                    currentSeaSquareCount = currentSeaSquareCount + 1;
                }
                seaSquareCount.push_back(currentSeaSquareCount);
            }
        }
    }

    std::vector<int>::iterator seaSquareCountIter = seaSquareCount.begin();
    for (int i = 1; i < Row - 1; i = i + 1) {
        for (int j = 1; j < Column - 1; j = j + 1) {
            bool isGlacier = (Grid[i][j] > 0);
            if (isGlacier) {
                if (Grid[i][j] > *seaSquareCountIter) {
                    Grid[i][j] = Grid[i][j] - *seaSquareCountIter;
                } else {
                    Grid[i][j] = 0;
                }
                seaSquareCountIter = seaSquareCountIter + 1;
            }
        }
    }
}

bool thereIsGlacier(void) {
    bool thereIsGlacier = false;
    for (int i = 1; i < Row - 1; i = i + 1) {
        for (int j = 1; j < Column - 1; j = j + 1) {
            bool isGlacier = (Grid[i][j] > 0);
            if (isGlacier) {
                thereIsGlacier = true;
                break;
            }
        }
        if (thereIsGlacier) {
            break;
        }
    }

    return thereIsGlacier;
}

// * 스택

// https://www.acmicpc.net/problem/2573

// #include <iostream>
// #include <vector>
// #include <stack>

// int Row, Column;
// std::vector<std::vector<int>> Grid;
// std::vector<std::vector<bool>> VisitGrid;
// enum {ROW=0, Column};
// int Direction[4][2] = { {0,1}, {0,-1}, {1,0}, {-1,0} };

// void initSystem(void);
// void getInfo(void);
// void initVisitGrid(void);

// void solve(void);

// int getGlacierPiece(void);
// void visitOneGlacierPiece(int row, int column);
// void resetVisitGrid(void);

// void meltGlacier(void);

// bool thereIsGlacier(void);

// int main(void) {
//     initSystem();
//     solve();

//     return 0;
// }

// void initSystem(void) {
//     getInfo();
//     initVisitGrid();
// }
// void getInfo(void) {
//     std::cin >> Row >> Column;

//     for(int i = 0; i < Row; i = i + 1) {
//         std::vector<int> Grid_Row_i;
//         for(int j = 0; j < Column; j = j + 1) {
//             int GridNumber;
//             std::cin >> GridNumber;
//             Grid_Row_i.push_back(GridNumber);
//         }
//         Grid.push_back(Grid_Row_i);
//     }
// }
// void initVisitGrid(void) {
//     for(int i = 0; i < Row; i = i + 1) {
//         std::vector<bool> VisitGrid_Row_i;
//         for(int j = 0; j < Column; j = j + 1) {
//             bool VisitGridTruthValue = false;
//             VisitGrid_Row_i.push_back(VisitGridTruthValue);
//         }
//         VisitGrid.push_back(VisitGrid_Row_i);
//     }
// }

// void solve(void) {
//     int year = 0;

//     while(thereIsGlacier()) {
//         int glacierPiece = getGlacierPiece();
//         if(glacierPiece >= 2) {
//             break;
//         }
//         year = year + 1;
//         meltGlacier();
//     }

//     if(thereIsGlacier()) {
//         std::cout << year << std::endl;
//     }
//     else {
//         std::cout << 0 << std::endl;
//     }
// }

// int getGlacierPiece(void) {
//     int glacierPiece = 0;
//     for(int i = 1; i < Row-1; i = i + 1) {
//         for(int j = 1; j < Column-1; j = j + 1) {
//             bool isGlacier = (Grid[i][j] > 0), doesNotVisit = (VisitGrid[i][j] == false), findOneGlacierPiece =
//             (isGlacier && doesNotVisit); if(findOneGlacierPiece) {
//                 visitOneGlacierPiece(i, j);
//                 glacierPiece = glacierPiece + 1;
//             }
//         }
//     }
//     resetVisitGrid();
//     return glacierPiece;
// }
// void visitOneGlacierPiece(int row, int column) {
//     struct position {int row; int column;};
//     std::stack<position> positionStack;

//     position initPosition;
//     initPosition.row = row; initPosition.column = column;
//     VisitGrid[initPosition.row][initPosition.column] = true;
//     positionStack.push(initPosition);

//     while(!positionStack.empty()) {
//         position& curPosition = positionStack.top();

//         bool AllNeighborIsNotUnvisitedGlacierSquare = true;
//         for(int i = 0; i < 4; i = i + 1) {
//             position neighborPositon;
//             neighborPositon.row = curPosition.row + Direction[i][ROW], neighborPositon.column = curPosition.column +
//             Direction[i][Column]; int NeighborInGrid = Grid[neighborPositon.row][neighborPositon.column]; bool
//             NeighborInVisitGrid = VisitGrid[neighborPositon.row][neighborPositon.column];

//             bool NeighborIsGlacierSquare = (NeighborInGrid > 0), doesNotVisitNeighbor = (NeighborInVisitGrid ==
//             false), NeighborIsUnvisitedGlacierSquare = (NeighborIsGlacierSquare && doesNotVisitNeighbor);

//             if(NeighborIsUnvisitedGlacierSquare) {
//                 VisitGrid[neighborPositon.row][neighborPositon.column] = true;
//                 positionStack.push(neighborPositon);
//                 AllNeighborIsNotUnvisitedGlacierSquare = false;
//                 break;
//             }
//         }
//         if(AllNeighborIsNotUnvisitedGlacierSquare) {
//             positionStack.pop();
//         }
//     }
// }
// void resetVisitGrid(void) {
//     for(int i = 1; i < Row-1; i = i + 1) {
//         for(int j = 1; j < Column-1; j = j + 1) {
//             VisitGrid[i][j] = false;
//         }
//     }
// }

// void meltGlacier(void) {
//     std::vector<int> seaSquareCount;
//     for(int i = 1; i < Row-1; i = i + 1) {
//         for(int j = 1; j < Column-1; j = j + 1) {
//             bool isGlacier = (Grid[i][j] > 0);
//             if(isGlacier) {
//                 int currentSeaSquareCount = 0;
//                 for(int k = 0; k < 4; k = k + 1) {
//                     int NeighborInGrid = Grid[i + Direction[k][ROW]][i + Direction[k][Column]];
//                     if(NeighborInGrid == 0) {
//                         currentSeaSquareCount = currentSeaSquareCount + 1;
//                     }
//                 }
//                 seaSquareCount.push_back(currentSeaSquareCount);
//             }
//         }
//     }

//     std::vector<int>::iterator seaSquareCountIter = seaSquareCount.begin();
//     for(int i = 1; i < Row-1; i = i + 1) {
//         for(int j = 1; j < Column-1; j = j + 1) {
//             bool isGlacier = (Grid[i][j] > 0);
//             if(isGlacier) {
//                 if(Grid[i][j] > *seaSquareCountIter) {
//                     Grid[i][j] = Grid[i][j] - *seaSquareCountIter;
//                 }
//                 else {
//                     Grid[i][j] = 0;
//                 }
//                 seaSquareCountIter = seaSquareCountIter + 1;
//             }
//         }
//     }
// }

// bool thereIsGlacier(void) {
//     bool thereIsGlacier = false;
//     for(int i = 1; i < Row-1; i = i + 1) {
//         for(int j = 1; j < Column-1; j = j + 1) {
//             bool isGlacier = (Grid[i][j] > 0);
//             if(isGlacier) {
//                 thereIsGlacier = true;
//                 break;
//             }
//         }
//         if(thereIsGlacier) {
//             break;
//         }
//     }

//     return thereIsGlacier;
// }