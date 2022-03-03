package p.s001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {


    // brute force
    public int[] twoSum0(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    // map
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int expected = target - nums[i];
            if (map.containsKey(expected)) {
                int expectedIdx = map.get(expected);
                if (expectedIdx != i) {
                    return new int[]{i, map.get(expected)};
                }
            }
        }

        return new int[2];

    }

    // left/right cursor
    public int[] twoSum(int[] nums, int target) {

//        int[] copyNums = nums.clone();

        int[] copyNums = Arrays.copyOf(nums, nums.length);

        Arrays.sort(copyNums);

//        System.out.println(Arrays.toString(nums));

        int left = 0;
        int right = copyNums.length - 1;

        while (left < right) {
            int res = copyNums[left] + copyNums[right];
            if (res == target) {
                break;
            } else if (res < target) {
                left++;
            } else {
                right--;
            }
        }

        int originLeft = 0;
        int originRight = 0;

        for (int i = 0; i < nums.length; i++) {
            if (copyNums[left] == nums[i]) {
                originLeft = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (copyNums[right] == nums[i]) {
                originRight = i;
                break;
            }
        }

        return new int[]{originLeft, originRight};

    }


    public static void main(String[] args) {
//        int[] nums = new int[]{2, 7, 11, 15};
//        int target = 9;
        int[] nums = new int[]{3, 2, 4};
        int target = 6;
//        int[] nums = new int[]{3, 3};
//        int target = 6;
        Solution solution = new Solution();
        int[] ans = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(ans));
    }

}
