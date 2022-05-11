package p.s0020;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    public boolean isValid(String s) {

        Map<Character, Character> hm = new HashMap<>();
        hm.put('(', ')');
        hm.put('[', ']');
        hm.put('{', '}');
        hm.put('0', '0');

        Stack<Character> cs = new Stack<>();

        cs.push('0');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                cs.push(c);
            } else {
                char cc = cs.pop();
                if (!hm.get(cc).equals(c)) {
                    return false;
                }
            }
        }

        return cs.size() == 1;
    }

    public static void main(String[] args) {
//        String s = "()[]{}";
//        String s = "(]";
//        String s = "([)]";
        String s = "{[]}";
        Solution solution = new Solution();
        System.out.println(solution.isValid(s));
    }

}
