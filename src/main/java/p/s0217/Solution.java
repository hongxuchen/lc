package p.s0217;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num: nums) {
            if (s.contains(num)) {
                return true;
            } else {
                s.add(num);
            }
        }
        return false;
    }
}
