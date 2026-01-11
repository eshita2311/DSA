class Solution {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int max_area = 0;

        for(char[] row : matrix){
            for(int i = 0;i < row.length;i++){
                if(row[i] == '1'){
                    heights[i]++;
                }
                else{
                    heights[i] = 0;
                }
            }

            max_area = Math.max(max_area,largestarea(heights));
        }

        return max_area;
    }

    public int largestarea(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int max_area = 0;
        for(int i = 0;i <= heights.length;i++){
            int currheight = 0;
            if(i == heights.length){
                currheight = 0;
            }
            else{
                currheight = heights[i];
            }

            while(!stack.isEmpty() && currheight < heights[stack.peek()]){
                int h = heights[stack.pop()];
                int w = 0;
                if(stack.isEmpty()){
                    w = i;
                }
                else{
                    w = i - stack.peek() - 1;
                }

                int area = h * w;
                max_area = Math.max(max_area,area);
            }
            stack.push(i);
        }

        return max_area;
    }

}