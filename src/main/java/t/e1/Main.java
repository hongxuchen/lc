package t.e1;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 * Description: 上机编程认证
 * Note: 缺省代码仅供参考，可自行决定使用、修改或删除
 */

/**
 * OJ考题代码： 紧急生产口罩问题
 *
 * @author 命题组
 * @since 2021-09-10
 */
public class Main {
    private static int getMaxQuantity(int[] productions, int[] plans, int window) {
        int totalDays = productions.length;
        int preSum = 0;
        for (int i = 0; i < totalDays; i++) {
            if (i < window) {
                preSum += productions[i];
            } else {
                preSum += productions[i] * plans[i];
            }
        }
        if (window == totalDays) {
            return preSum;
        }
        int max = 0;
        int curSum;
        for (int i = 1; i < totalDays - window + 1; i++) {
            curSum = preSum + productions[i - 1] * (plans[i - 1] - 1)
                    + productions[i + window - 1] * (1 - plans[i + window - 1]);
            max = Math.max(preSum, curSum);
            preSum = curSum;
        }
        return max;
    }

    // main入口由OJ平台调用
    public static void main(String[] args) {

//        int[] productions = new int[]{803, 804, 805, 802, 803, 804, 805, 804};
//        int[] plans = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
//        int window = 1;

//        int[] productions = new int[]{980, 910, 1000, 940, 973};
//        int[] plans = new int[]{0, 1, 1, 0, 0};
//        int window = 2;

        int[] productions = new int[]{980, 910, 1000, 940, 973};
        int[] plans = new int[]{0, 1, 1, 1, 1};
        int window = 5;

        int result = getMaxQuantity(productions, plans, window);
        System.out.print(result);
    }
}

