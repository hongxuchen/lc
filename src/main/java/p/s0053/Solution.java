
package p.s0053;

public class Solution {
    public int maxSubArray(int[] nums) {
        int temp = nums[0];
        int res = temp;

        for (int i = 1; i < nums.length; i++) {
            if (temp < 0) {
                temp = nums[i];
            } else {
                temp += nums[i];
            }
            res = Math.max(res, temp);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray(nums));
    }

}
