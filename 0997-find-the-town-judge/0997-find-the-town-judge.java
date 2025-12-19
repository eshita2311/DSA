class Solution {
    public int findJudge(int n, int[][] trust) {
        if (n == 1 && trust.length == 0) return 1;  // Special case: only one person
        
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        
        for (int[] t : trust) {
            int a = t[0], b = t[1];
            outdegree[a]++;   // a trusts someone
            indegree[b]++;    // b is trusted by someone
        }
        
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;  // Found judge
            }
        }
        
        return -1; // No judge found
    }
}