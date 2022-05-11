package p.s0169;

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int majorNum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == majorNum) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    majorNum = nums[i];
                }
            }
        }

        return majorNum;

    }

    public static void main(String[] args) {

    }

}