class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long maxSum = 0;
        long minAbsNo = Integer.MAX_VALUE;
        int negativeNoCount = 0;
        boolean isZeroPresent = false;
        for(int row[]: matrix) {
            for(int val: row) {
                if(minAbsNo > Math.abs(val)) {
                    minAbsNo = Math.abs(val);
                }
                if(val < 0) {
                    negativeNoCount++;
                } else if(val == 0) {
                    isZeroPresent = true;
                }
                maxSum += Math.abs(val);
            }
        }
        if(negativeNoCount % 2 == 0 || isZeroPresent) {
            return maxSum;
        }
        return maxSum - (2 * minAbsNo);
    }
}