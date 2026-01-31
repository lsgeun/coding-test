// https://www.acmicpc.net/problem/11404
#include <iostream>
#include <string>
#include <vector>

int n;
int m;
struct busInfo {
    int startCity;
    int endCity;
    int cost;
};
std::vector<busInfo> busInfos;
int cost[100][100];
int maxCost = 1000000;

void initCostWithoutMediatedNode(void);
void getMinCost(void);
void printCost(void);

int main(void) {
    std::cin >> n;
    std::cin >> m;
    for (int i = 0; i < m; i = i + 1) {
        busInfo curBusInfo;
        std::cin >> curBusInfo.startCity;
        std::cin >> curBusInfo.endCity;
        std::cin >> curBusInfo.cost;

        busInfos.push_back(curBusInfo);
    }

    initCostWithoutMediatedNode();
    getMinCost();
    printCost();

    return 0;
}

void getMinCost(void) {}
void initCostWithoutMediatedNode(void) {
    for (int i = 0; i < n; i = i + 1) {
        for (int j = 0; j < n; j = j + 1) {
            if (i == j) {
                cost[i][j] = 0;
            } else {
                cost[i][j] = maxCost;
            }
        }
    }
    for (int i = 0; i < n; i = i + 1) {
        for (int i = 0; i < n; i = i + 1) {
        }
    }
}

void printCost(void) {
    // cost
}