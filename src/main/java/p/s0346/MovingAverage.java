package p.s0346;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {

    int size;
    int curSum = 0;
    Queue<Integer> queue = new LinkedList<>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        int curSize = queue.size();
        curSum += val;
        if (curSize <= this.size) {
            return 1.0d * curSum / curSize;
        } else {
            curSum -= queue.poll();
            return 1.0d * curSum / this.size;
        }
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // 返回 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // 返回 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // 返回 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // 返回 6.0 = (10 + 3 + 5) / 3
    }

}
