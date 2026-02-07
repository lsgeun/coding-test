class 타겟넘버 {
    static int[] numbersCopy;
    static int[] signs;
    static int targetCopy;
    
    static int totalCount;
    
    public int solution(int[] numbers, int target) {
        setUp(numbers, target);
        
        getTotalCount(0);
        
        return totalCount;
    }
    
    static void setUp(int[] numbers, int target) {
        numbersCopy = numbers;
        signs = new int[numbers.length];
        targetCopy = target;
    }
    
    static void getTotalCount(int insertIndex) {
        if (insertIndex == signs.length) {
            // 현재 부호들로 계산한 결과가 타겟과 일치한다면 totalCount 1 증가
            int num = 0;
            for (int i = 0; i < numbersCopy.length; i++) {
                num += signs[i] * numbersCopy[i];
            }
            if (num == targetCopy) {
                totalCount += 1;
            }
            
            return;
        }
        
        signs[insertIndex] = -1;
        getTotalCount(insertIndex + 1);
        signs[insertIndex] = 1;
        getTotalCount(insertIndex + 1);
    }
}