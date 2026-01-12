class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int srAns = 0;
        for(int i=0;i<points.length-1;i++){
            if(points[i][0]!=points[i+1][0] || points[i][1]!=points[i+1][1]){
                srAns+=Math.max(Math.abs(points[i+1][0]-points[i][0]),Math.abs(points[i+1][1]-points[i][1]));
            }
        }
        return srAns;
    }
}