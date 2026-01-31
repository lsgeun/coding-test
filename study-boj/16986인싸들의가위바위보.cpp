// https://www.acmicpc.net/problem/16986
#include <iostream>
#include <vector>

int HGC, WC;
std::vector<std::vector<int>> RuleBook;
std::vector<bool> JWHGUse;
std::vector<int> KHHGSquence, MHHGSquence;
std::vector<int> PlayerWC = {0, 0, 0};
bool JWWin = false;

void getInfo(void);
void startGame(void);
void startGame_(int player1, int player2, int KHHGpos, int MHHGpos);
bool isGameOver(void);
void printJWWin(void);

int main(void) {
    getInfo();
    startGame();
    printJWWin();

    return 0;
}

void getInfo(void) {
    std::cin >> HGC >> WC;

    for (int i = 0; i < HGC; i = i + 1) {
        std::vector<int> RuleBook_i;
        for (int j = 0; j < HGC; j = j + 1) {
            int RuleBook_ij;
            std::cin >> RuleBook_ij;
            RuleBook_i.push_back(RuleBook_ij);
        }
        RuleBook.push_back(RuleBook_i);
    }

    for (int i = 0; i < 20; i = i + 1) {
        JWHGUse.push_back(false);
    }

    for (int i = 0; i < 20; i = i + 1) {
        int KHHG_i;
        std::cin >> KHHG_i;
        KHHGSquence.push_back(KHHG_i - 1);
    }

    for (int i = 0; i < 20; i = i + 1) {
        int MHHG_i;
        std::cin >> MHHG_i;
        MHHGSquence.push_back(MHHG_i - 1);
    }
}

void startGame(void) { startGame_(0, 1, 0, 0); }

void startGame_(int player1, int player2, int KHHGpos, int MHHGpos) {
    bool gameOver = isGameOver();
    if (gameOver == true) {
        return;
    }

    if (player1 == 0 && player2 == 1) {
        for (int JWHG = 0; JWHG < HGC; JWHG = JWHG + 1) {
            if (JWHGUse[JWHG] == true) {
                continue;
            }

            int KHHG = KHHGSquence[KHHGpos];

            JWHGUse[JWHG] = true;
            if (RuleBook[JWHG][KHHG] == 2) {
                PlayerWC[0] = PlayerWC[0] + 1;
                startGame_(0, 2, KHHGpos + 1, MHHGpos);
                PlayerWC[0] = PlayerWC[0] - 1;
            } else {
                PlayerWC[1] = PlayerWC[1] + 1;
                startGame_(1, 2, KHHGpos + 1, MHHGpos);
                PlayerWC[1] = PlayerWC[1] - 1;
            }
            JWHGUse[JWHG] = false;
        }
    } else if (player1 == 0 && player2 == 2) {
        for (int JWHG = 0; JWHG < HGC; JWHG = JWHG + 1) {
            if (JWHGUse[JWHG] == true) {
                continue;
            }

            int MHHG = MHHGSquence[MHHGpos];

            JWHGUse[JWHG] = true;
            if (RuleBook[JWHG][MHHG] == 2) {
                PlayerWC[0] = PlayerWC[0] + 1;
                startGame_(0, 1, KHHGpos, MHHGpos + 1);
                PlayerWC[0] = PlayerWC[0] - 1;
            } else {
                PlayerWC[2] = PlayerWC[2] + 1;
                startGame_(1, 2, KHHGpos, MHHGpos + 1);
                PlayerWC[2] = PlayerWC[2] - 1;
            }
            JWHGUse[JWHG] = false;
        }
    } else {
        int KHHG = KHHGSquence[KHHGpos];
        int MHHG = MHHGSquence[MHHGpos];

        if (RuleBook[KHHG][MHHG] == 2) {
            PlayerWC[1] = PlayerWC[1] + 1;
            startGame_(0, 1, KHHGpos + 1, MHHGpos + 1);
            PlayerWC[1] = PlayerWC[1] - 1;
        } else {
            PlayerWC[2] = PlayerWC[2] + 1;
            startGame_(0, 2, KHHGpos + 1, MHHGpos + 1);
            PlayerWC[2] = PlayerWC[2] - 1;
        }
    }
}

bool isGameOver(void) {
    bool isGameOver = false;

    if (PlayerWC[0] == WC) {
        JWWin = true;
        isGameOver = true;
    }
    if (PlayerWC[1] == WC || PlayerWC[2] == WC) {
        isGameOver = true;
    }

    int JWHGUseCount = 0;
    for (int i = 0; i < HGC; i = i + 1) {
        if (JWHGUse[i] == true) {
            JWHGUseCount = JWHGUseCount + 1;
        }
    }
    if (JWHGUseCount == HGC) {
        isGameOver = true;
    }

    return isGameOver;
}

void printJWWin(void) {
    if (JWWin == true) {
        std::cout << 1;
    } else {
        std::cout << 0;
    }
}