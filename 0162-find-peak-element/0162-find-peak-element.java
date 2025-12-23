class Solution {
    public int findPeakElement(int[] nums) {
        int low = 1, high = nums.length - 2;
        if(nums.length == 1) return 0;
        if(nums.length == 2) return (nums[0] > nums[1])? 0 : 1;
        // we also know that edge can also be peak as the condition is nums[-1] = nums[n] = -inf
        // so we can check for 0 and n - 1 index values also
        if(nums[0] > nums[1]) return 0;
        if(nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]){
                return mid;
            }
            else if(nums[mid - 1] < nums[mid]){
                // we know that answer is located in right side
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }
}