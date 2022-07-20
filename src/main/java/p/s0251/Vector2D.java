package p.s0251;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Vector2D {

    Queue<Integer> queue = new LinkedList<>();

    public Vector2D(int[][] vec) {
        for (int[] nums : vec) {
            List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
            queue.addAll(numList);
        }
    }

    public int next() {
        Integer res = queue.poll();
        return res;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }


    public static void main(String[] args) {
        int[][] vec = new int[][]{{1, 2}, {3}, {4}};
        Vector2D iterator = new Vector2D(vec);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());

    }

}
