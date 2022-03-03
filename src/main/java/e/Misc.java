
package e;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
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
        Map<Integer,String> test = new HashMap<>();
        test.put(1,"hello");
        test.put(2,"unmodifiable");
        Map<Integer,String> umap = Collections.unmodifiableMap(test);
        umap.clear();

        Optional<String> os = null;
        Optional.of(null);

    }

    public static void main(String[] args) {

        // testTS();
//        testNull();

//        testInteger();
        testUnmodifiable();
    }

}
