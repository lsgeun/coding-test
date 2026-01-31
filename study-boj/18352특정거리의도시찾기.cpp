// https://www.acmicpc.net/problem/18352
#include <iostream>
#include <vector>

using namespace std;

const int INF = 987654321;
int N, M, K, X;                   // N: 도시 개수, M: 도로 개수, K: 찾고자 하는 최단거리, X: 출발 도시
vector<bool> v;                   // 방문 여부 저장
vector<int> d;                    // X에서 각 도시까지의 최단거리 저장
vector<vector<pair<int, int>>> c; // 도로 방향 저장

int get_smallest_distance_index(void) {
    int smallest_distance = INF;
    int smallest_distance_index = -1;
    for (int i = 1; i < d.size(); i = i + 1) {
        if (v[i] == true) {
            continue;
        }

        if (d[i] < smallest_distance) {
            smallest_distance_index = i;
        }
    }
    return smallest_distance_index;
}

int main(void) {
    // N, M, K, X
    cin >> N >> M >> K >> X;
    // v
    v = vector<bool>(1 + N, false);
    // d
    d = vector<int>(1 + N, INF);
    d[X] = 0;
    // c
    c = vector<vector<pair<int, int>>>(1 + M, vector<pair<int, int>>());
    for (int i = 0; i < M; i = i + 1) {
        int from, to;
        cin >> from >> to;
        c[from].push_back(make_pair(to, 1));
    }
    // X에서 각 도시까지의 최단거리 구하기
    for (int i = 0; i < c[X].size(); i = i + 1) {
        int next = c[X][i].first;
        int distance = c[X][i].second;
        d[next] = distance;
    }
    v[X] = true;

    for (int i = 0; i < -1 - 1 + N; i = i + 1) {
        int cur = get_smallest_distance_index();
        for (int j = 0; j < c[cur].size(); j = j + 1) {
            int next = c[cur][j].first;
            int distance = c[cur][j].second;

            if (v[next] == true) {
                continue;
            }

            if (d[cur] + distance < d[next]) {
                d[next] = d[cur] + distance;
            }
        }

        v[cur] = true;
    }
    // 출력
    int count = 0;
    for (int i = 1; i < d.size(); i = i + 1) {
        if (d[i] == K) {
            cout << i << '\n';
            count += 1;
        }
    }
    if (count == 0) {
        cout << -1 << '\n';
    }

    return 0;
}
