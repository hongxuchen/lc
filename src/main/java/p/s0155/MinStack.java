package p.s0155;

import java.util.Stack;
import java.util.TreeMap;

class MinStack {

    TreeMap<Integer, Integer> ts = new TreeMap<>();
    private final Stack<Integer> stack = new Stack<>();

    public MinStack() {
    }

    public void push(int val) {
        stack.push(val);
        ts.put(val, ts.getOrDefault(val, 0) + 1);
    }

    public void pop() {
        int val = stack.pop();
        if (!ts.containsKey(val)) {
            throw new IllegalArgumentException("should exist");
        }
        int remaining = ts.get(val) - 1;
        if (remaining == 0) {
            ts.remove(val);
        } else {
            ts.put(val, remaining);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return ts.firstKey();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());  // --> 返回 -3.
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */