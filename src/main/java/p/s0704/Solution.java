package p.s0704;

class Solution {
  public int search(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      int val = nums[mid];
      if (val == target) {
        return mid;
      } else if (val < target) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return -1;
  }
}
