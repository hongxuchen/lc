package p.s0015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        if (nums.length < 3) {
            return lists;
        }

        Arrays.sort(nums);

        int firstPrev = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            if (first == firstPrev) {
                continue;
            }
            int secondPrev = Integer.MAX_VALUE;
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                if (second == secondPrev) {
                    continue;
                }
                int last = -first - second;
                for (int k = nums.length - 1; k >= j + 1; k--) {
                    if (last == nums[k]) {
                        List<Integer> l = List.of(first, second, last);
                        lists.add(l);
                        break;
                    }
                }
                secondPrev = second;
            }
            firstPrev = first;
        }

        return lists;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums));
    }

}
