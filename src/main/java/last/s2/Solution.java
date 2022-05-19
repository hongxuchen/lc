package last.s2;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

class Solution {

    Map<Integer, int[]> priceDiscountMap = new TreeMap<>();

    // k >= 2
    public boolean canBuyWith(int num, int price, int budget) {
        int remainBudget = budget;
        int remainNum = num;
        for (Map.Entry<Integer, int[]> entry : priceDiscountMap.entrySet()) {
            int[] dsc = entry.getValue();
            remainBudget = remainBudget - dsc[1] * dsc[2];
            remainNum = remainNum - dsc[2];
            if (remainBudget >= 0 ) {
                if (remainNum <= 0) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return remainBudget >= 0 && remainBudget >= price * remainNum;

    }

    public int purchaseItems(int num, int price, int[][] discount, int budget) {
        if (num * price <= budget) {
            return 1;
        }

        for (int[] ints : discount) {
            priceDiscountMap.put(ints[1], ints);
            dump();
            if (canBuyWith(num, price, budget)) {
                return ints[0];
            }
        }

        return -1;


    }

    void dump() {
        System.out.println("------------------");
        for (Map.Entry<Integer, int[]> entry : priceDiscountMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + Arrays.toString(entry.getValue()));
        }
        System.out.println("------------------");
    }

    public static void main(String[] args) {
//        {
//            Solution solution = new Solution();
//            int[][] discount = new int[][]{{2, 4, 2}, {3, 1, 4}};
//            int res = solution.purchaseItems(3, 5, discount, 14);
//            System.out.println(res);
//        }

//        {
//            Solution solution = new Solution();
//            int[][] discount = new int[][]{{2, 5, 3}, {3, 6, 2}, {4, 2, 1}, {6, 1, 4}, {100, 3, 1}};
//            int res = solution.purchaseItems(4, 10, discount, 18);
//            System.out.println(res);
//        }


        {
            Solution solution = new Solution();
            int[][] discount = new int[][]{{2, 3, 3}};
            int res = solution.purchaseItems(2, 4, discount, 10);
            System.out.println(res);
        }
    }

}