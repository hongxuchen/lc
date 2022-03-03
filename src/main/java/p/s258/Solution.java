package p.s258;

public class Solution {

    public int addDigits(int num) {
        int sum;
        int tmp;
        while (num >= 10) {
            sum = 0;
            while (num > 0) {
                tmp = num / 10;
                sum += (num - tmp * 10);
                num = tmp;
            }
            num = sum;
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addDigits(36));
    }

}
