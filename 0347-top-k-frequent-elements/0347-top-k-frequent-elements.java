class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> a[1] - b[1]);

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            heap.offer(new int[] {
                    e.getKey(), e.getValue()
            });

            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (!heap.isEmpty()) {
            res[i++] = heap.poll()[0];

        }

        return res;
    }
}