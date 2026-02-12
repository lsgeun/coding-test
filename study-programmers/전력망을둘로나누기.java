import java.io.*;
import java.util.*;

class 전력망을둘로나누기 {
    static int nCopy;
    static int[][] wiresCopy;
    
    static ArrayList<Integer>[] adjacentList;
    static boolean[] visited;
    static int diff;
    
    public int solution(int n, int[][] wires) {
        setUp(n, wires);
        
        solve();
        
        return diff;
    }
    
    static void setUp(int n, int[][] wires) {
        nCopy = n;
        wiresCopy = wires;
        
        adjacentList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjacentList[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int nodeA = wire[0];
            int nodeB = wire[1];
            
            adjacentList[nodeA].add(nodeB);
            adjacentList[nodeB].add(nodeA);
        }
        
        visited = new boolean[n + 1];
        
        diff = Integer.MAX_VALUE;
    }
    
    static void solve() {
        for (int[] wire : wiresCopy) {
            resetVisited();
            
            int count[] = new int[2];
            int type = 0;
            for (int node = 1; node <= nCopy; node++) {
                if (visited[node]) {
                    continue;
                }
                
                count[type] = getConnectedComponentCount(node, wire);
                
                type += 1;
            }
            
            diff = Math.min(diff, Math.abs(count[0] - count[1]));
        }
    }
    
    static void resetVisited() {
        Arrays.fill(visited, false);
    }
    
    static int getConnectedComponentCount(int node, int[] wire) {
        int count = 1;
        
        visited[node] = true;
        
        for (int nextNode : adjacentList[node]) {
            // 엣지 중에 wire는 제외
            if (wire[0] == node && wire[1] == nextNode) {
                continue;
            }
            if (wire[1] == node && wire[0] == nextNode) {
                continue;
            }
            
            if (visited[nextNode]) {
                continue;
            }
            
            count += getConnectedComponentCount(nextNode, wire);
        }
        
        return count;
    }
}