package e.fibonacci;

public class Solution {

    public long rec1(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return rec1(i - 1) + rec1(i - 2);
    }

    public long rec2Impl(int i, long[] arr) {
        if (i == 0) {
            arr[0] = 0;
            return 0;
        }
        if (i == 1) {
            arr[1] = 1;
            return 1;
        }
        if (arr[i] != 0) {
            return arr[i];
        } else {
            arr[i] = rec2Impl(i - 1, arr) + rec2Impl(i - 2, arr);
            return arr[i];
        }
    }

    public long iterate(int n) {
        if (n <= 1) {
            return n;
        }
        long pre1 = 0;
        long pre2 = 1;
        long res = pre1 + pre2;
        for (int i = 2; i < n; i++) {
            pre1 = pre2;
            pre2 = res;
            res = pre1 + pre2;
        }
        return res;
    }

    public long rec2(int i) {
        long[] arr = new long[i + 1];
        return rec2Impl(i, arr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rec1(20));
        System.out.println(solution.rec2(20));
        System.out.println(solution.iterate(20));
    }


}
