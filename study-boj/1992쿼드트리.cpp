// https://www.acmicpc.net/problem/1992
// #include <iostream>
// #include <stack>
// #include <string>
// #include <vector>

// int N;
// char Video[64][64];
// struct videoInfo {
//     int size;
//     int pos00Row;
//     int pos00Column;
// };
// static std::stack<videoInfo> videoInfoS;

// void printVideoQuadTreeCompression(void);

// int main(void) {
//     std::cin >> N;
//     for (int i = 0; i < N; i = i + 1) {
//         std::string VideoRowi;
//         std::cin >> VideoRowi;
//         for (int j = 0; j < N; j = j + 1) {
//             Video[i][j] = VideoRowi[j];
//         }
//     }

//     videoInfo originVideoInfo;
//     originVideoInfo.size = N;
//     originVideoInfo.pos00Row = 0;
//     originVideoInfo.pos00Column = 0;
//     videoInfoS.push(originVideoInfo);

//     printVideoQuadTreeCompression();

//     return 0;
// }

// void printVideoQuadTreeCompression(void) {
//     static std::vector<char> quadTreeCompression;
//     std::vector<char>::iterator QTCInsertPos = quadTreeCompression.begin();

//     while (!videoInfoS.empty()) {
//         videoInfo curVideoInfo;
//         curVideoInfo = videoInfoS.top();
//         videoInfoS.pop();

//         int oneCount = 0, zeroCount = 0;
//         for (int i = curVideoInfo.pos00Row; i < curVideoInfo.size; i = i + 1) {
//             for (int j = curVideoInfo.pos00Column; j < curVideoInfo.size; j = j + 1) {
//                 if (Video[i][j] == '1') {
//                     oneCount = oneCount + 1;
//                 }
//                 if (Video[i][j] == '0') {
//                     zeroCount = zeroCount + 1;
//                 }
//             }
//         }

//         if (oneCount == curVideoInfo.size * curVideoInfo.size) {
//             quadTreeCompression.insert(QTCInsertPos, '1');
//             QTCInsertPos = QTCInsertPos + 1;
//             continue;
//         }
//         if (zeroCount == curVideoInfo.size * curVideoInfo.size) {
//             quadTreeCompression.insert(QTCInsertPos, '0');
//             QTCInsertPos = QTCInsertPos + 1;
//             continue;
//         }

//         videoInfo videoInfoToBePush;
//         videoInfoToBePush.size = curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Row;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Column;
//         videoInfoS.push(videoInfoToBePush);
//         videoInfoToBePush.size = curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Row + curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Column;
//         videoInfoS.push(videoInfoToBePush);
//         videoInfoToBePush.size = curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Row;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Column + curVideoInfo.size / 2;
//         videoInfoS.push(videoInfoToBePush);
//         videoInfoToBePush.size = curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Row + curVideoInfo.size / 2;
//         videoInfoToBePush.pos00Row = curVideoInfo.pos00Column + curVideoInfo.size / 2;
//         videoInfoS.push(videoInfoToBePush);
//     }

//     for (int i = 0; i < quadTreeCompression.size(); i = i + 1) {
//         std::cout << quadTreeCompression[i];
//     }
// }

// https://www.acmicpc.net/problem/1992
#include <iostream>
#include <string>

int N;
char Video[64][64];

void printVideoQuadTreeCompression(int pos00Row, int pos00Column, int videoSize);

int main(void) {
    std::cin >> N;

    for (int i = 0; i < N; i = i + 1) {
        std::string VideoRowI;
        std::cin >> VideoRowI;
        for (int j = 0; j < N; j = j + 1) {
            Video[i][j] = VideoRowI[j];
        }
    }

    printVideoQuadTreeCompression(0, 0, N);

    return 0;
}

void printVideoQuadTreeCompression(int pos00Row, int pos00Column, int videoSize) {
    int zeroSize = 0, oneSize = 0;
    for (int i = pos00Row; i < pos00Row + videoSize; i = i + 1) {
        for (int j = pos00Column; j < pos00Column + videoSize; j = j + 1) {
            if (Video[i][j] == '0') {
                zeroSize = zeroSize + 1;
            }
            if (Video[i][j] == '1') {
                oneSize = oneSize + 1;
            }
        }
    }

    if (oneSize == videoSize * videoSize) {
        std::cout << '1';
        return;
    }
    if (zeroSize == videoSize * videoSize) {
        std::cout << '0';
        return;
    }

    std::cout << '(';
    printVideoQuadTreeCompression(pos00Row, pos00Column, videoSize / 2);
    printVideoQuadTreeCompression(pos00Row, pos00Column + videoSize / 2, videoSize / 2);
    printVideoQuadTreeCompression(pos00Row + videoSize / 2, pos00Column, videoSize / 2);
    printVideoQuadTreeCompression(pos00Row + videoSize / 2, pos00Column + videoSize / 2, videoSize / 2);
    std::cout << ')';
}