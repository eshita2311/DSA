import java.util.*;

class Solution {
    // ------- Custom Min-Heap for Dijkstra -------- //
    static class MinHeap {
        int size = 0;
        int[] heap;     // stores nodes
        int[] pos;      // stores position of each node
        int[] dist;     // reference to distance array

        MinHeap(int n, int[] dist) {
            heap = new int[n + 1];  // 1-based indexing
            pos  = new int[n];
            Arrays.fill(pos, -1);
            this.dist = dist;
        }

        void push(int node) {
            heap[++size] = node;
            pos[node] = size;
            siftUp(size);
        }

        int pop() {
            int root = heap[1];
            pos[root] = -1;

            heap[1] = heap[size--];
            if (size > 0) {
                pos[heap[1]] = 1;
                siftDown(1);
            }
            return root;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void decreaseKey(int node) {
            siftUp(pos[node]);
        }

        private void siftUp(int i) {
            while (i > 1) {
                int p = i >> 1;
                int a = heap[i], b = heap[p];
                if (dist[a] < dist[b]) {
                    heap[i] = b;  pos[b] = i;
                    heap[p] = a;  pos[a] = p;
                    i = p;
                } else break;
            }
        }

        private void siftDown(int i) {
            while (true) {
                int left = i << 1;
                if (left > size) break;

                int smallest = left;
                int right = left + 1;

                if (right <= size && dist[heap[right]] < dist[heap[left]])
                    smallest = right;

                if (dist[heap[smallest]] < dist[heap[i]]) {
                    int a = heap[i], b = heap[smallest];
                    heap[i] = b;  pos[b] = i;
                    heap[smallest] = a; pos[a] = smallest;
                    i = smallest;
                } else break;
            }
        }
    }


    public int minCost(int n, int[][] edges) {
        int m = edges.length * 2;

        
        int[] head = new int[n];
        int[] to = new int[m];
        int[] wt = new int[m];
        int[] next = new int[m];

        Arrays.fill(head, -1);
        int edgeIdx = 0;

        
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];


            to[edgeIdx] = v;
            wt[edgeIdx] = w;
            next[edgeIdx] = head[u];
            head[u] = edgeIdx++;


            to[edgeIdx] = u;
            wt[edgeIdx] = w + w;   
            next[edgeIdx] = head[v];
            head[v] = edgeIdx++;
        }

        // Dijkstra
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        MinHeap pq = new MinHeap(n, dist);
        pq.push(0);

        while (!pq.isEmpty()) {
            int u = pq.pop();
            if (u == n - 1) return dist[u];

            int base = dist[u];

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int nd = base + wt[e];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    if (pq.pos[v] == -1) pq.push(v);
                    else pq.decreaseKey(v);
                }
            }
        }

        return -1;
    }
}