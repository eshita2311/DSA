class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;

        for(int i = 1; i < arr.length; i++){
            min = Math.min(min, arr[i] - arr[i-1]);
        }

        for(int i = 1; i < arr.length; i++){
            if((arr[i] - arr[i-1]) == min){
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i-1]);
                temp.add(arr[i]);
                res.add(temp);
            }            
        } 
        return res;
    }
}