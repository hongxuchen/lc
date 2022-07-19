package p.s0136;

class Solution {
    public int singleNumber(int[] nums) {
        int value = 0;
        for (int num: nums) {
            value ^= num;
        }
        return value;
    }
}
