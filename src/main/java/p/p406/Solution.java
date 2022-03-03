
package p.p406;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public String combineChars(String input, int interval) {

        if (interval == 0) {

            return input;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {

                map.put(ch, 1);
            }
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((c1, c2) -> {

            if (map.get(c2).intValue() != map.get(c1).intValue()) {
                return map.get(c2) - map.get(c1);
            } else {
                return c1.compareTo(c2);
            }
        });
        for (char c : map.keySet()) {
            queue.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        while (!queue.isEmpty()) {

            List<Character> tempChars = new ArrayList<>();
            int n = Math.min(interval, len);
            for (int i = 0; i < n; i++) {

                if (queue.isEmpty()) {
                    return "";
                }
                char ch = queue.poll();
                sb.append(ch);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) > 0) {

                    tempChars.add(ch);
                }
                len--;
            }

            for (Character tempChar : tempChars) {

                queue.offer(tempChar);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        String res = solution.combineChars("aabbcc", 3);
        String res2 = solution.combineChars("aaabc", 3);
        String res3 = solution.combineChars("aaadbbcc", 2);
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }
}
