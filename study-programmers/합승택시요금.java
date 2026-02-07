// 다익스트라 3번으로 필요한 최단거리만 구함

import java.io.*;
import java.util.*;

class 합승택시요금 {
    static int nCopy, sCopy, aCopy, bCopy;

    static ArrayList<Node>[] adjacentList;

    static int[] minFaresS;
    static int[] minFaresA;
    static int[] minFaresB;

    static boolean[] visited;

    static int minTotalFare;

    static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        setUp(n, s, a, b, fares);
        int answer = 0;

        calculateMinFares(minFaresS, sCopy);
        calculateMinFares(minFaresA, aCopy);
        calculateMinFares(minFaresB, bCopy);

        calculateTotalMinFare();

        return minTotalFare;
    }

    static void setUp(int n, int s, int a, int b, int[][] fares) {
        nCopy = n;
        sCopy = s;
        aCopy = a;
        bCopy = b;

        adjacentList = new ArrayList[nCopy + 1];
        for (int i = 1; i <= n; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        for (int[] f : fares) {
            int start = f[0];
            int end = f[1];
            int fare = f[2];

            adjacentList[start].add(new Node(end, fare));
            adjacentList[end].add(new Node(start, fare));
        }

        minFaresS = new int[nCopy + 1];
        minFaresA = new int[nCopy + 1];
        minFaresB = new int[nCopy + 1];
        Arrays.fill(minFaresS, INF);
        Arrays.fill(minFaresA, INF);
        Arrays.fill(minFaresB, INF);

        visited = new boolean[nCopy + 1];

        minTotalFare = INF;
    }

    static void calculateMinFares(int[] minFares, int start) {
        resetVisited();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node node = new Node(start, 0);
        pq.offer(node);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (visited[curNode.pos]) {
                continue;
            }

            visited[curNode.pos] = true;

            minFares[curNode.pos] = curNode.fare;

            for (Node neighborNode : adjacentList[curNode.pos]) {
                if (visited[neighborNode.pos]) {
                    continue;
                }

                Node nextNode = new Node(neighborNode.pos, curNode.fare + neighborNode.fare);
                pq.offer(nextNode);
            }

        }
    }

    static void resetVisited() {
        Arrays.fill(visited, false);
    }

    static void calculateTotalMinFare() {
        minTotalFare = INF;

        for (int i = 1; i <= nCopy; i++) {
            minTotalFare = Math.min(minTotalFare,
                    minFaresS[i] + minFaresA[i] + minFaresB[i]);
        }
    }

    static class Node implements Comparable<Node> {
        public int pos;
        public int fare;

        public Node(int pos, int fare) {
            this.pos = pos;
            this.fare = fare;
        }

        @Override
        public int compareTo(Node n) {
            return this.fare - n.fare;
        }
    }
}

// 플로이드 워셜로 모든 두 노드 간의 최단 거리 구함

// import java.io.*;
// import java.util.*;

// class 합승택시요금 {
// static int nCopy;
// static int sCopy;
// static int aCopy;
// static int bCopy;

// static int[][] minFares;
// static int minTotalFare;
// static final int INF = Integer.MAX_VALUE;

// public int solution(int n, int s, int a, int b, int[][] fares) {
// setUp(n, s, a, b, fares);

// calculateMinDistance();
// calculateMinTotalFare();

// return minTotalFare;
// }

// static void setUp(int n, int s, int a, int b, int[][] fares) {
// nCopy = n;
// sCopy = s;
// aCopy = a;
// bCopy = b;

// minFares = new int[nCopy + 1][nCopy + 1];
// // 일단, 전부 길이 없도록 초기화
// for (int i = 1; i <= nCopy; i++) {
// Arrays.fill(minFares[i], INF);
// }
// // 자기 자신으로 가는 최단 거리는 0
// for (int i = 1; i <= n; i++) {
// minFares[i][i] = 0;
// }
// // 엣지가 있는 것은 초기화
// for (int[] fare : fares) {
// int start = fare[0];
// int end = fare[1];
// int _fare = fare[2];

// minFares[start][end] = _fare;
// minFares[end][start] = _fare;
// }

// minTotalFare = INF;
// }

// static void calculateMinDistance() {
// for (int k = 1; k <= nCopy; k++) {
// for (int i = 1; i <= nCopy; i++) {
// for (int j = 1; j <= nCopy; j++) {
// if (minFares[i][k] == INF) {
// continue;
// }
// if (minFares[k][j] == INF) {
// continue;
// }

// minFares[i][j] = Math.min(minFares[i][j], minFares[i][k] + minFares[k][j]);
// }
// }
// }
// }
// static void calculateMinTotalFare() {
// minTotalFare = INF;

// for (int i = 1; i <= nCopy; i++) {
// minTotalFare = Math.min(minTotalFare,
// minFares[sCopy][i] +
// minFares[i][aCopy] +
// minFares[i][bCopy]);
// }
// }
// }

// s -> a, s -> b의 최단 경로를 구하고, 공통된 부분을 뺌
// 이렇게 푸는게 아님.
// 플로이드 워셜로 모든 정점 사이의 최단 거리를 구하거나
// 다익스트라로 s, a, b에서 출발하는 최단 거리를 구한 후
// 1 <= k <= 20,
// s -> k, k -> a, k -> b 합이 최소가 되는 것을 구하면 됨
// 필요한 최단 거리를 구한 후, 20번만 계산하면 됨
// 무방향 그래프이므로 a -> k 최단 거리나 k -> a 최단 거리가 같음

// import java.io.*;
// import java.util.*;

// class 합승택시요금 {
// static int nCopy, sCopy, aCopy, bCopy;
// static ArrayList<Node>[] adjacentList;
// static boolean[] visited;

// static Node[] minFares;
// static int minTotalFare;

// public int solution(int n, int s, int a, int b, int[][] fares) {
// setUp(n, s, a, b, fares);

// calculateMinFares();
// calculateMinTotalFare();

// return minTotalFare;
// }

// static void setUp(int n, int s, int a, int b, int[][] fares) {
// nCopy = n;
// sCopy = s;
// aCopy = a;
// bCopy = b;

// adjacentList = new ArrayList[n + 1];
// for (int i = 1; i <= n; i++) {
// adjacentList[i] = new ArrayList<>();
// }

// for (int[] fare : fares) {
// int positionA = fare[0];
// int positionB = fare[1];
// int _fare = fare[2];

// adjacentList[positionA].add(new Node(positionB, _fare));
// adjacentList[positionB].add(new Node(positionA, _fare));
// }
// visited = new boolean[n + 1];

// minFares = new Node[n + 1];
// minTotalFare = 0;
// }

// static void calculateMinFares() {
// Node nodePath = new Node(sCopy, 0);
// ArrayList<Integer> path = new ArrayList<>();
// path.add(sCopy);
// nodePath.path = path;

// PriorityQueue<Node> pq = new PriorityQueue<>();
// pq.offer(nodePath);

// while (!pq.isEmpty()) {
// Node curNodePath = pq.poll();

// if (visited[curNodePath.position]) {
// continue;
// }

// visited[curNodePath.position] = true;

// minFares[curNodePath.position] = curNodePath;

// for (Node nextNode : adjacentList[curNodePath.position]) {
// if (visited[nextNode.position]) {
// continue;
// }

// Node nextNodePath = new Node(nextNode.position, nextNode.fare +
// curNodePath.fare);
// ArrayList<Integer> nextPath = new ArrayList<>(curNodePath.path);
// nextPath.add(nextNode.position);
// nextNodePath.path = nextPath;

// pq.offer(nextNodePath);
// }
// }
// }

// static void calculateMinTotalFare() {
// minTotalFare = 0;

// minTotalFare += minFares[aCopy].fare;
// minTotalFare += minFares[bCopy].fare;

// ArrayList<Integer> pathA = minFares[aCopy].path;
// ArrayList<Integer> pathB = minFares[bCopy].path;

// int endIndex = 0;
// while (true) {
// if (pathA.size() == endIndex) {
// break;
// }
// if (pathB.size() == endIndex) {
// break;
// }
// if (pathA.get(endIndex) != pathB.get(endIndex)) {
// break;
// } // pathA[endIndex] == pathB[endIndex]

// endIndex += 1;
// }

// int commonPathFare = 0;

// for (int i = 0; i < endIndex - 1; i++) {
// // pathB도 동일함
// int origin = pathA.get(i);
// int destination = pathA.get(i + 1);
// for (Node node : adjacentList[origin]) {
// if (node.position == destination) {
// commonPathFare += node.fare;
// break;
// }
// }
// }

// minTotalFare -= commonPathFare;
// }

// static class Node implements Comparable<Node> {
// int position;
// int fare;
// ArrayList<Integer> path;

// public Node(int position, int fare) {
// this.position = position;
// this.fare = fare;
// }

// @Override
// public int compareTo(Node n) {
// return this.fare - n.fare;
// }
// }
// }