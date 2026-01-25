class Solution {
    public int minimumDifference(int[] nums, int k) {
        int minDiff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i = k - 1; i < nums.length; i++){
            minDiff = Math.min(minDiff, nums[i] - nums[i - k + 1]);
        }
        return minDiff;
    }
}