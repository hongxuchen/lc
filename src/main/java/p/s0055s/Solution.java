package p.s0055s;

public class Solution {

    public boolean canJump(int[] nums) {
        int farthest = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i <= farthest) {
                farthest = Math.max(farthest, nums[i] + i);
            }
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums = new int[]{3, 2, 1, 0, 9};
        Solution solution = new Solution();
        System.out.println(solution.canJump(nums));
    }

}
