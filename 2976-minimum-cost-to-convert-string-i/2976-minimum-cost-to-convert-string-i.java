import java.util.*;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        final int A = 26;
        final int INF = Integer.MAX_VALUE;
        int[][] d = new int[A][A];

        // initialize matrix
        for (int i = 0; i < A; i++) {
            int[] row = d[i];
            Arrays.fill(row, INF);
            row[i] = 0;
        }

        // incorporate edges, keeping minimal cost per directed pair
        int m = cost.length;
        for (int i = 0; i < m; i++) {
            int u = original[i] - 'a';
            int v = changed[i]  - 'a';
            int c = cost[i];
            int old = d[u][v];
            if (c < old) d[u][v] = c;
        }

        
        for (int k = 0; k < A; k++) {
            int[] dk = d[k];
            for (int i = 0; i < A; i++) {
                int dik = d[i][k];
                if (dik == INF) continue;
                int[] di = d[i];
                for (int j = 0; j < A; j++) {
                    int dkj = dk[j];
                    if (dkj == INF) continue;
                    int via = dik + dkj;
                    if (via < di[j]) di[j] = via;
                }
            }
        }

        // accumulate answer
        long total = 0L;
        int n = source.length();
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        for (int i = 0; i < n; i++) {
            int u = s[i] - 'a';
            int v = t[i] - 'a';
            int best = d[u][v];
            if (best == INF) return -1L;
            total += best;
        }

        return total;
    }
}