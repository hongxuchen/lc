package u;

import java.util.*;
import java.util.stream.Collectors;

/**
 * java data structures
 * Queue: LinkedList
 * Stack: Stack, Deque
 * Heap: PriorityQueue
 */

public class Utils {
    public static String[] asStrArray(String arrString) {
        String trimmed = arrString.trim();
        String nobrackets = trimmed.substring(1, trimmed.length() - 1);
        return nobrackets.split(",\\s+");
    }

    public static int[] asIntArray(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).mapToInt(Integer::valueOf).toArray();
    }

    public static List<String> asStringList(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).collect(Collectors.toList());
    }

    public static List<Integer> asIntegerList(String arrString) {
        String[] ss = asStrArray(arrString);
        return Arrays.stream(ss).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static final int MOD = 1000000007;

    public int countLess(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (Map.Entry<Integer, Integer> e : map.headMap(arr[i], true).entrySet()) {
                count = (count + e.getValue()) % MOD;
            }
            // 注意，对于相同数值的节点，不能直接put刷掉原来值，要做累加，不然会漏掉
            map.put(arr[i], (map.getOrDefault(arr[i], 0) + arr[i]) % MOD);

        }
        return count;
    }

    public int minThreadsInPool(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // Java中PriorityQueue默认实现最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!heap.isEmpty() && interval[0] >= heap.peek()) {
                heap.poll();
            }
            heap.offer(interval[1]);
        }
        return heap.size();
    }

    public int transaction(int[] price) {
        if (price.length == 1) {
            return 0;
        }

        int maxProfit = 0;
        int minCost = price[0]; // 以首日买入价格作为初始成本
        for (int i = 1; i < price.length; i++) {
            // 若已累积成本大于今日卖出价格，则以今日价格作为基准成本(此前所赚在今日清零)
            minCost = Math.min(price[i], minCost + 1);
            maxProfit = Math.max(maxProfit, price[i] - minCost);
        }
        return maxProfit;
    }

    public static int[] nextExceed(int[] input) {
        int[] result = new int[input.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>(input.length);
        for (int i = 0; i < input.length; i++) {
            while (!stack.isEmpty() && input[i] > input[stack.peek()]) {
                int top = stack.pop();
                result[top] = i - top;
            }
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        String ss = "[1, 2,  3] ";
        {
            int[] ssInts = asIntArray(ss);
            System.out.println(Arrays.toString(ssInts));
            List<Integer> ssIntList = asIntegerList(ss);
            System.out.println(ssIntList);
        }
        {
            String[] ssStrs = asStrArray(ss);
            System.out.println(Arrays.toString(ssStrs));
            List<String> ssStrList = asStringList(ss);
            System.out.println(ssStrList);
        }

        {
            String[] s = {"good"};
            System.out.println(Arrays.toString(s));
        }

        {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            priorityQueue.offer(3);
            priorityQueue.offer(10);
            priorityQueue.offer(5);
            priorityQueue.offer(8);
            System.out.println(priorityQueue);
            priorityQueue.poll();
            System.out.println(priorityQueue);
            List<Integer> l = priorityQueue.stream().sorted((o1, o2) -> o1 - o2).collect(Collectors.toList());
            System.out.println(l);
            System.out.println(priorityQueue);

            String s = String.join(", ", new String[]{"good", "morning"});
            System.out.println(s);
        }
    }

}
