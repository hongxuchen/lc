package e.maxdepth;


import u.TreeNode;

import java.util.*;

public class Solution {

    public int[] calculate(TreeNode root, int[] targets) {

        int[] res = new int[targets.length];

        if (root == null) {
            Arrays.fill(res, -1);
            return res;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        depthMax(root, map);

        for (int i=0; i < targets.length; i++) {
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (entry.getValue() > targets[i]) {
                    res[i] = entry.getKey();
                    break;
                }
            }
             res[i] = -1;
        }

        return res;

    }

    void depthMax(TreeNode root, TreeMap<Integer, Integer> map) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        map.put(depth, root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int curMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curMax = Math.max(curMax, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            depth++;
            map.put(depth, curMax);
        }

    }

}
