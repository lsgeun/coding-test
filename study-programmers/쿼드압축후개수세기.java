import java.io.*;
import java.util.*;

class 쿼드압축후개수세기 {
    static int[][] arrCopy;
    static int[] count;
    static final int ZERO = 0, ONE = 1;
    
    public int[] solution(int[][] arr) {
        setUp(arr);
        calculateCount();
        return count;
    }
    
    static void setUp(int[][] arr) {
        arrCopy = arr;
        
        count = new int[2];
        Arrays.fill(count, 0);
    }
    
    static void calculateCount() {
        _calculateCount(0, arrCopy.length, 0, arrCopy.length);
    }
    
    static void _calculateCount(int startX, int endX, int startY, int endY) {
        int firstNum = arrCopy[startX][startY];
        int length = (endX - startX);
        
        if (length == 1) {
            if (firstNum == 1) {
                count[ONE] += 1;
            } else { // firstNum == 0
                count[ZERO] += 1;
            }
            
            return;
        } // length(2^n) >= 2
        
        boolean canCompress = true;
        
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                if (arrCopy[i][j] != firstNum) {
                    canCompress = false;
                    break;
                }
            }
            
            if (!canCompress) {
                break;
            }
        }
        
        if (canCompress) {
            if (firstNum == 1) {
                count[ONE] += 1;
            } else { // firstNum == 0
                count[ZERO] += 1;
            }
        } else { // canCompress == false
            // 오버플로우 방지
            int middleX = startX + (endX - startX) / 2;
            int middleY = startY + (endY - startY) / 2;
            
            _calculateCount(startX, middleX, startY, middleY);
            _calculateCount(startX, middleX, middleY, endY);
            _calculateCount(middleX, endX, startY, middleY);
            _calculateCount(middleX, endX, middleY, endY);
        }
    }
}