
package e;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Misc {

    public static void testPQ() {
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        pq.offer("good");
        pq.offer("well");
        pq.offer("bad");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

    }

    public static void testTS() {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);
        ts.add(9);
        ts.add(4);
        ts.add(8);
        ts.add(200);
        ts.add(100);

        System.out.println(ts.first());
        System.out.println(ts.last());
        System.out.println(ts.headSet(7));
        System.out.println(ts.lower(7));

    }

    public static void testArray() {
        Integer[] nums = {1, 2, 3, 4, 5};
        Integer[] n = Arrays.copyOfRange(nums, 4, 9);
        System.out.println(Arrays.toString(n));
        Arrays.sort(nums, Collections.reverseOrder());
        System.out.println(Arrays.toString(nums));
    }

    public static void testInteger() {
        Integer srd = 256;
        System.err.println(srd == Integer.valueOf(256));
        float f1[][] = new float[6][6];
        float[] f2[] = new float[6][6];
        System.out.println(Integer.valueOf("+1", 10));
        float f = 1.3f;
    }

    public static void testNull() {
        Set<String> s = new HashSet<>();
        s.add(null);
        s.add(null);
        System.out.println(s);
    }

    public static void testUnmodifiable() {
        Map<Integer, String> test = new HashMap<>();
        test.put(1, "hello");
        test.put(2, "unmodifiable");
        Map<Integer, String> umap = Collections.unmodifiableMap(test);
        umap.clear();

        Optional<String> os = null;
        Optional.of(null);

    }

    public static void testList() {
        List<Integer> l = new ArrayList<>(List.of(1, 2, 3));
        l.add(0, 4);
        l.add(1, 6);
        System.out.println(l);
        l.add(l.size(), 5);
        l.add(l.size() - 1, 7);
        l.add(9);
        System.out.println(l);
    }

    public static void testTreeMap() {
        Map<Integer, Integer> m = new TreeMap<>();
        m.put(1, 10);
        m.put(2, 9);
        m.put(3, 8);
        m.put(4, 100);
        System.out.println(new ArrayList<>(m.values()));
    }

    public static void testDeque() {
//        Deque<String> deque
//                = new LinkedList<>();
        Deque<String> deque = new ArrayDeque<>();
        // We can add elements to the queue
        // in various ways

        // Add at the last
        deque.add("Element 1 (Tail)");

        // Add at the first
        deque.addFirst("Element 2 (Head)");

        // Add at the last
        deque.addLast("Element 3 (Tail)");

        // Add at the first
        deque.push("Element 4 (Head)");

        // Add at the last
        deque.offer("Element 5 (Tail)");

        // Add at the first
        deque.offerFirst("Element 6 (Head)");

        System.out.println(deque + "\n");

        // We can remove the first element
        // or the last element.
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Deque after removing "
                + "first and last: "
                + deque);
    }

    public static void main(String[] args) {

        // testTS();
//        testNull();

//        testInteger();
//        testUnmodifiable();
//        testList();
//        testTreeMap();
        testDeque();
    }

}
