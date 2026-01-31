// https://www.acmicpc.net/problem/13460
#include <iostream>
#include <queue>
#include <string>
#include <vector>

int Height, Width;
std::vector<std::vector<char>> EmptyBoard;
int InitRedPos[2], InitBluePos[2], HolePos[2];
struct BoardState {
    int RedPos[2];
    int BluePos[2];
    int leanBoardCount;
    bool RedIsInHole;
    bool BlueIsInHole;
};

int LeaningDirection[4][2]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
enum { Row = 0, Column };

void initSystem(void);
void getInfo(void);

void solve(void);
int getMinimumRedLeanBoardCount(void);
void moveRedBallUntilStop(BoardState &boardState, int leaningDirection[2]);
void moveBlueBallUntilStop(BoardState &boardState, int leaningDirection[2]);
void leanBoard(BoardState &boardState, int leaningDirection[2]);

bool BallIsInHole(int BallPos[2]);

int main(void) {
    initSystem();
    solve();

    return 0;
}

void initSystem(void) { getInfo(); }
void getInfo(void) {
    std::cin >> Height >> Width;

    std::cin.ignore();
    for (int i = 0; i < Height; i = i + 1) {
        std::string EmptyBoardRowString;
        getline(std::cin, EmptyBoardRowString);

        std::vector<char> EmptyBoardRow;
        for (int j = 0; j < Width; j = j + 1) {
            if (EmptyBoardRowString[j] == 'R') {
                InitRedPos[Row] = i, InitRedPos[Column] = j;
                EmptyBoardRow.push_back('.');
            } else if (EmptyBoardRowString[j] == 'B') {
                InitBluePos[Row] = i, InitBluePos[Column] = j;
                EmptyBoardRow.push_back('.');
            } else if (EmptyBoardRowString[j] == 'O') {
                HolePos[Row] = i, HolePos[Column] = j;
                EmptyBoardRow.push_back(EmptyBoardRowString[j]);
            } else {
                EmptyBoardRow.push_back(EmptyBoardRowString[j]);
            }
        }
        EmptyBoard.push_back(EmptyBoardRow);
    }
}

void solve(void) {
    int minimumRedLeanBoardCount = getMinimumRedLeanBoardCount();

    std::cout << minimumRedLeanBoardCount;
}
int getMinimumRedLeanBoardCount(void) {
    std::queue<BoardState> queueBoardState;
    BoardState curBoardState;
    curBoardState.RedPos[Row] = InitRedPos[Row], curBoardState.RedPos[Column] = InitRedPos[Column];
    curBoardState.BluePos[Row] = InitBluePos[Row], curBoardState.BluePos[Column] = InitBluePos[Column];
    curBoardState.leanBoardCount = 0;
    curBoardState.RedIsInHole = false;
    curBoardState.BlueIsInHole = false;
    queueBoardState.push(curBoardState);

    int minimumRedLeanBoardCount = -1;
    while (!queueBoardState.empty()) {
        curBoardState = queueBoardState.front();
        queueBoardState.pop();

        if (curBoardState.RedIsInHole && !curBoardState.BlueIsInHole) {
            minimumRedLeanBoardCount = curBoardState.leanBoardCount;
            break;
        }

        for (int i = 0; i < 4; i = i + 1) {
            BoardState nextBoardState = curBoardState;

            leanBoard(nextBoardState, LeaningDirection[i]);

            if (!(0 <= nextBoardState.leanBoardCount && nextBoardState.leanBoardCount <= 10)) {
                continue;
            }
            if (nextBoardState.BlueIsInHole) {
                continue;
            }

            queueBoardState.push(nextBoardState);
        }
    }
    return minimumRedLeanBoardCount;
}
void leanBoard(BoardState &boardState, int leaningDirection[2]) {
    bool moveBallAlongRow = (leaningDirection[Row] == 0), moveBallAlongColumn = (leaningDirection[Column] == 0);
    if (moveBallAlongRow) {
        if (leaningDirection[Column] > 0) {
            if (boardState.RedPos[Column] <= boardState.BluePos[Column]) {
                moveBlueBallUntilStop(boardState, leaningDirection);
                moveRedBallUntilStop(boardState, leaningDirection);
            }
            if (boardState.RedPos[Column] > boardState.BluePos[Column]) {
                moveRedBallUntilStop(boardState, leaningDirection);
                moveBlueBallUntilStop(boardState, leaningDirection);
            }
        }
        if (leaningDirection[Column] < 0) {
            if (boardState.RedPos[Column] <= boardState.BluePos[Column]) {
                moveRedBallUntilStop(boardState, leaningDirection);
                moveBlueBallUntilStop(boardState, leaningDirection);
            }
            if (boardState.RedPos[Column] > boardState.BluePos[Column]) {
                moveBlueBallUntilStop(boardState, leaningDirection);
                moveRedBallUntilStop(boardState, leaningDirection);
            }
        }
    }
    if (moveBallAlongColumn) {
        if (leaningDirection[Row] > 0) {
            if (boardState.RedPos[Row] <= boardState.BluePos[Row]) {
                moveBlueBallUntilStop(boardState, leaningDirection);
                moveRedBallUntilStop(boardState, leaningDirection);
            }
            if (boardState.RedPos[Row] > boardState.BluePos[Row]) {
                moveRedBallUntilStop(boardState, leaningDirection);
                moveBlueBallUntilStop(boardState, leaningDirection);
            }
        }
        if (leaningDirection[Row] < 0) {
            if (boardState.RedPos[Row] <= boardState.BluePos[Row]) {
                moveRedBallUntilStop(boardState, leaningDirection);
                moveBlueBallUntilStop(boardState, leaningDirection);
            }
            if (boardState.RedPos[Row] > boardState.BluePos[Row]) {
                moveBlueBallUntilStop(boardState, leaningDirection);
                moveRedBallUntilStop(boardState, leaningDirection);
            }
        }
    }

    boardState.leanBoardCount = boardState.leanBoardCount + 1;
}
void moveRedBallUntilStop(BoardState &boardState, int leaningDirection[2]) {
    while (!BallIsInHole(boardState.RedPos)) {
        int nextRedPos[2];
        nextRedPos[Row] = boardState.RedPos[Row] + leaningDirection[Row];
        nextRedPos[Column] = boardState.RedPos[Column] + leaningDirection[Column];

        if (EmptyBoard[nextRedPos[Row]][nextRedPos[Column]] == '#') {
            break;
        }
        if ((nextRedPos[Row] == boardState.BluePos[Row]) && (nextRedPos[Column] == boardState.BluePos[Column]) &&
            !BallIsInHole(boardState.BluePos)) {
            break;
        }

        boardState.RedPos[Row] = nextRedPos[Row];
        boardState.RedPos[Column] = nextRedPos[Column];
    }
    if (BallIsInHole(boardState.RedPos)) {
        boardState.RedIsInHole = true;
    }
}
void moveBlueBallUntilStop(BoardState &boardState, int leaningDirection[2]) {
    while (!BallIsInHole(boardState.BluePos)) {
        int nextBluePos[2];
        nextBluePos[Row] = boardState.BluePos[Row] + leaningDirection[Row];
        nextBluePos[Column] = boardState.BluePos[Column] + leaningDirection[Column];

        if (EmptyBoard[nextBluePos[Row]][nextBluePos[Column]] == '#') {
            break;
        }
        if ((nextBluePos[Row] == boardState.RedPos[Row]) && (nextBluePos[Column] == boardState.RedPos[Column]) &&
            !BallIsInHole(boardState.RedPos)) {
            break;
        }

        boardState.BluePos[Row] = nextBluePos[Row];
        boardState.BluePos[Column] = nextBluePos[Column];
    }
    if (BallIsInHole(boardState.BluePos)) {
        boardState.BlueIsInHole = true;
    }
}
bool BallIsInHole(int BallPos[2]) { return (BallPos[Row] == HolePos[Row]) && (BallPos[Column] == HolePos[Column]); }