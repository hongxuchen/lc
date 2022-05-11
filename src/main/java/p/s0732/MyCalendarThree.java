package p.s0732;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyCalendarThree {

    private Map<Integer, Integer> m = new TreeMap<>();

    public MyCalendarThree() {

    }

    public int book(int start, int end) {
        m.put(start, m.getOrDefault(start, 0) + 1);
        m.put(end, m.getOrDefault(end, 0) - 1);

        int active = 0;
        int max = 0;

        for (int v: m.values()) {
            active += v;
            max = Integer.max(active, max);
        }

        return max;

    }

}
