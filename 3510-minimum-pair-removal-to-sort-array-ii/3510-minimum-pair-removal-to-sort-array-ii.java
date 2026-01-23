import java.util.*;

class Solution {

    static class Pair {
        long sum;
        int left;  
        Pair(long sum, int left) {
            this.sum = sum;
            this.left = left;
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] val = new long[n];
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] alive = new boolean[n];

        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            prev[i] = i - 1;
            next[i] = i + 1;
            alive[i] = true;
        }
        next[n - 1] = -1;

        int disorder = 0;
        for (int i = 1; i < n; i++) {
            if (val[i] < val[i - 1]) disorder++;
        }
        if (disorder == 0) return 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.sum != b.sum) {
                    return Long.compare(a.sum, b.sum);
                }
                return Integer.compare(a.left, b.left);
            }
        );


        for (int i = 0; i < n - 1; i++) {
            pq.offer(new Pair(val[i] + val[i + 1], i));
        }

        int operations = 0;

        while (disorder > 0) {
            Pair p = pq.poll();
            int i = p.left;
            int j = (i >= 0) ? next[i] : -1;

            if (i == -1 || j == -1 || !alive[i] || !alive[j]) continue;
            if (val[i] + val[j] != p.sum) continue;

            int pi = prev[i];
            int nj = next[j];

            if (pi != -1 && val[i] < val[pi]) disorder--;
            if (nj != -1 && val[nj] < val[j]) disorder--;
            if (val[j] < val[i]) disorder--;

            val[i] = val[i] + val[j];
            alive[j] = false;
            next[i] = nj;
            if (nj != -1) prev[nj] = i;

            if (pi != -1 && val[i] < val[pi]) disorder++;
            if (nj != -1 && val[nj] < val[i]) disorder++;

            if (pi != -1) {
                pq.offer(new Pair(val[pi] + val[i], pi));
            }
            if (nj != -1) {
                pq.offer(new Pair(val[i] + val[nj], i));
            }

            operations++;
        }

        return operations;
    }
}