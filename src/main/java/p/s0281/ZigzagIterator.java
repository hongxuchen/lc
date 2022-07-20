package p.s0281;

import java.util.ArrayList;
import java.util.List;

public class ZigzagIterator {

    private final List<Integer> list = new ArrayList<>();
    private int cur;
    private final int total;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {

        int len1 = v1.size();
        int len2 = v2.size();
        int len = Math.min(len1, len2);

        for (int i = 0; i < len; i++) {
            list.add(v1.get(i));
            list.add(v2.get(i));
        }

        if (len1 != len2) {
            if (len1 < len2) {
                // len1 == len
                for (int i = len; i < len2; i++) {
                    list.add(v2.get(i));
                }
            }
            if (len2 < len1) {
                // len2 == len
                for (int i = len; i < len1; i++) {
                    list.add(v1.get(i));
                }
            }
        }

        this.cur = 0;
        this.total = len1 + len2;
    }

    public int next() {
        if (cur <= total - 1) {
            return this.list.get(cur++);
        } else {
            throw new NullPointerException();
        }
    }

    public boolean hasNext() {
        return cur != total;
    }

    public static void main(String[] args) {
        List<Integer> v1 = List.of(1, 2);
        List<Integer> v2 = List.of(3, 4, 5, 6);
        ZigzagIterator iterator = new ZigzagIterator(v1, v2);
        System.out.print("[");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            System.out.print(",");
        }
        System.out.println("]");
    }

}
