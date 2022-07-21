package p.s0346;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {

    private int size;
    private double sum;
    private Queue<Integer> queue = new LinkedList<>();

    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0.0;
    }

    public double next(int val) {
        sum += val;
        queue.offer(val);
        if (queue.size() > this.size) {
            sum -= queue.poll();
            return sum / this.size;
        } else {
            return sum / queue.size();
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
