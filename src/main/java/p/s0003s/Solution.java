package p.s0003s;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int lengthOfLongestSubstring0(String s) {

        if (s.length() <= 1) {
            return s.length();
        }

        int ans = 0;
        int i = 0;
        int j = 0;
        Set<Character> cs = new HashSet<>();

        int n = s.length();

        while (i < n && j < n) {
            char curChar = s.charAt(j);
            if (cs.contains(curChar)) {
                cs.remove(s.charAt(i));
                i++;
            } else {
                cs.add(curChar);
                j++;
                ans = Math.max(ans, j - i);
            }
        }

        return ans;

    }

    public int lengthOfLongestSubstring(String s) {

        int n = s.length();
        int i = 0;
        int j = 0;
        int res = 0;
        int[] charIndex = new int[256];

        for (i = 0, j = 0; j < n; j++) {
            char c = s.charAt(j);
            i = Math.max(charIndex[c], i);
            res = Math.max(res, j - i + 1);
            charIndex[c] = j + 1;
        }

        return res;

    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "au";
//        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }


}
