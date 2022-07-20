package p.s0075;

import java.util.Arrays;

public class Solution {

    public void sortColors(int[] nums) {
        int[] counts = new int[3];

        for (int num : nums) {
            counts[num]++;
        }

        int p = 0;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            for (int j = 0; j < count; j++) {
                nums[p++] = i;
            }
        }

    }


    public static void main(String[] args) {
        int[] arrs = new int[]{2,0,1};
        Solution solution = new Solution();
        solution.sortColors(arrs);
        System.out.println(Arrays.toString(arrs));
    }

}
