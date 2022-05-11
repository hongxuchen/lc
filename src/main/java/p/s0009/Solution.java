package p.s0009;

public class Solution {

    public boolean isPalindrome0(int x) {
        if (x < 0) {
            return false;
        }
        String s = Integer.toString(x);

        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;

    }

    public int getReversed(int x) {
        int res = 0;
        while (x != 0) {
            int tmp = x - x / 10 * 10;
            res *= 10;
            res += tmp;
            x /= 10;
        }
        return res;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reversed = 0;
        while (x > reversed) {
            reversed *= 10;
            int tmp = x - x / 10 * 10;
            reversed += tmp;
            x /= 10;
        }
        return x == reversed || x == (reversed / 10);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = 1;

        System.out.println(solution.isPalindrome(x));
    }

}
