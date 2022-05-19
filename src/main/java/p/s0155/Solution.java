package p.s0155;

import java.util.Stack;


class MinStack1 {
    Stack<Integer> xStack = new Stack<>();
    Stack<Integer> mStack = new Stack<>();


    public MinStack1() {
    }

    public void push(int val) {
        if (xStack.isEmpty()) {
            xStack.push(val);
            mStack.push(val);
        } else {
            xStack.push(val);
            mStack.push(Math.min(val, mStack.peek()));
        }
    }

    public int pop() {
        mStack.pop();
        return xStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return mStack.peek();
    }

}

