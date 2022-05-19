package p.s0703;

import java.util.PriorityQueue;

class KthLargest {

    private final int k;

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            if (pq.size() == this.k) {
                pq.offer(num);
                pq.poll();
            } else {
                pq.offer(num);
            }
        }
    }

    public int add(int val) {
        if (pq.size() == this.k) {
            pq.offer(val);
            pq.poll();
        } else {
            pq.offer(val);
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8

    }

}
