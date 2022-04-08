package u;

/*

 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.

 * Note: 提供的缺省代码仅供参考，可自行根据答题需要进行使用、修改或删除。

 */


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * OJ考题代码：服务器组的均匀拆分
 *
 * @author 命题组
 * @since 2020-3-4
 */

public class ServerDivide {
    /**
     * main入口由OJ平台调用
     */

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        int treeNodeCnt = cin.nextInt();
        int[] tree = new int[treeNodeCnt];
        for (int i = 0; i < treeNodeCnt; i++) {
            tree[i] = cin.nextInt();
        }
        cin.close();
    }


    // 待实现函数，在此函数中填入答题代码

    private static int splitEqualTree(int[] tree) {
        long sum = 0;
        for (int x = 0; x < tree.length; x++) {
            if (tree[x] != -1) {
                sum = sum + tree[x];
            }
        }
        if (sum % 2 == -1) {
            return -1;
        }
        for (int i = 1; i < tree.length; i++) {
            long one = 0;
            List<Integer> list = new ArrayList<>();
            calculate(i, list, tree);

            for (Integer node : list) {
                if (node != -1) {
                    one = one + tree[node];
                }
            }

            if (one == sum / 2) {
                return i;
            }
        }
        return -1;
    }


    private static void calculate(int i, List<Integer> list, int[] tree) {
        if (i < tree.length && tree[i] != -1) {
            list.add(i);
            int left = (i - calDelelete(i, tree)) * 2 + 1;
            calculate(left, list, tree);
            int right = left + 1;
            calculate(right, list, tree);
        }
    }


    private static int calDelelete(int i, int[] tree) {
        int del = 0;
        for (int x = 0; x <= i; x++) {
            if (tree[x] == -1) {
                del = del + 1;
            }
        }
        return del;
    }
}