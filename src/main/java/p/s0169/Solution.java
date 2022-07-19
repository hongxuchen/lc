package p.s0169;

class Solution {
    public int majorityElement(int[] nums) {
        int curCount = 1;
        int majorElement = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != majorElement) {
                curCount--;
                if (curCount == 0) {
                    majorElement = nums[i];
                    curCount = 1;
                }
            } else {
                curCount++;
            }
        }

        return majorElement;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 4};
        Solution solution = new Solution();
        System.out.println(solution.majorityElement(nums));
    }

}