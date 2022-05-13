package p.s0322;

import java.util.HashMap;
import java.util.Map;

/**
 * res(amount) = max {x+ res(amount-x), ,}
 */
class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    public int coinChange(int[] coins, int rem, int[] counts) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (counts[rem - 1] != 0) {
            return counts[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, counts);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        counts[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return counts[rem - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.coinChange(new int[]{419, 186, 83, 408}, 6249));
    }

}
