package e.subway;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    int lastDiection = -1;

    public int[] getTimes(int[] arrTime, int[] direction) {
        int[] res = new int[arrTime.length];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (lastDiection == -1 || lastDiection == 1) {
                    if (o2[2] == o1[2]) {
                        return o1[1] - o2[1];
                    }
                    return o2[2] - o2[1];
                } else {
                    if (o2[2] == o1[2]) {
                        return o1[1] - o2[1];
                    }
                    return o1[2] - o2[2];
                }
            }
        });

        calRes(arrTime, direction, res, queue);
        return res;

    }

    private void calRes(int[] arrTime, int[] direction, int[] res, PriorityQueue<int[]> queue) {
        int time = 0;
        int index = 0;

        while (index < arrTime.length) {
            while (index < arrTime.length && arrTime[index] <= time) {
                queue.offer(new int[] {arrTime[index], index, direction[index]});
                index++;
            }
            if (!queue.isEmpty()) {
                lastDiection = queue.peek()[2];
                int[] tmp = queue.poll();
                res[tmp[1]] = time++;
            } else {
                lastDiection = -1;
                time = arrTime[index];
            }
        }

        while (!queue.isEmpty()) {
            lastDiection = queue.peek()[2];
            int[] tmp = queue.poll();
            res[tmp[1]] = time++;
        }

    }


}
