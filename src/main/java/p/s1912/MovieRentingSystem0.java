package p.s1912;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

class MovieRentingSystem0 {
    Map<Integer, PriorityQueue<int[]>> map;
    Map<Integer, Map<Integer, Integer>> info;
    TreeSet<int[]> set;

    public MovieRentingSystem0(int n, int[][] entries) {
        map = new HashMap<>();
        info = new HashMap<>();
        set = new TreeSet<>((a, b) -> {
            // shop movie price
            if (a[2] != b[2]) {
                return Integer.compare(a[2], b[2]);
            }
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int[] p : entries) {
            var pq = map.get(p[1]);
            var in = info.get(p[1]);
            if (pq == null) {
                // store price
                pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
                map.put(p[1], pq);
            }
            if (in == null) {
                // movie -> [store, price]
                in = new HashMap<>();
                info.put(p[1], in);
            }
            in.put(p[0], p[2]);
            pq.offer(new int[]{ p[0], p[2] });
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        List<int[]> temp = new ArrayList<>();
        var pq = map.get(movie);
        if (pq == null || pq.isEmpty()) {
            return res;
        }

        while (res.size() < 5 && !pq.isEmpty()) {
            var top = pq.poll();
            var arr = new int[]{ top[0], movie, top[1] };
            // System.out.println(top[0] + " " + top[1]);
            if (!set.contains(arr)) {
                res.add(top[0]);
            }
            temp.add(top);
        }

        for (var x : temp) {
            pq.offer(x);
        }

        return res;
    }

    public void rent(int shop, int movie) {
        set.add(new int[]{ shop, movie, info.get(movie).get(shop) });
    }

    public void drop(int shop, int movie) {
        set.remove(new int[]{ shop, movie, info.get(movie).get(shop) });
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> temp = new ArrayList<>();

        while (res.size() < 5 && !set.isEmpty()) {
            var top = set.pollFirst();
            res.add(List.of(top[0], top[1]));
            temp.add(top);
        }

        set.addAll(temp);
        return res;
    }
}
