package p.s0362;

import java.util.ArrayList;
import java.util.List;

public class HitCounter1 {

    List<Integer> hits = new ArrayList<>();

    public HitCounter1() {

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
        int i = getGt(min);
        int j = getLe(max);
        return j - i;
    }

    public int getGt(int min) {
        int l = 0, r = hits.size() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (hits.get(mid) == min) {
                r = mid;
            } else if (hits.get(mid) < min) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int getLe(int max) {
        int l = 0, r = hits.size() - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            int val = hits.get(mid);
            if (val == max) {
                r = mid - 1;
            } else if (val < max) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        HitCounter1 hitCounter1 = new HitCounter1();
        hitCounter1.hit(1);
        hitCounter1.hit(2);
        hitCounter1.hit(3);
        hitCounter1.hit(4);
        hitCounter1.getHits(300);
        hitCounter1.hit(300);
        hitCounter1.getHits(301);
    }

}
