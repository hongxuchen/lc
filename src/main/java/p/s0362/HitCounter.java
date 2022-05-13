package p.s0362;

import java.util.ArrayList;
import java.util.List;

public class HitCounter {

    List<Integer> hits = new ArrayList<>();

    public HitCounter() {

    }

    public void hit(int timestamp) {
        hits.add(timestamp);
    }

    public int getHits(int timestamp) {
        int min = timestamp - 300;
        if (min <= 0) {
            return hits.size();
        }
        return getHitsImpl(min, timestamp);
    }

    public int getHitsImpl(int min, int max) {
        int count = 0;
        for (Integer hit : hits) {
            if (hit > min) {
                count++;
            } else if (hit >= max) {
                break;
            }
        }
        return count;
    }

//    public int getMinGt(int min) {
//        int left = 0;
//        int right = hits.size() - 1;
//        int mid = (right - left) / 2 + left;
//        while (left < right) {
//            int res = hits.get(mid);
//        }
//    }

}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
