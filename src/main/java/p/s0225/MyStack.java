package p.s0225;

import java.util.ArrayDeque;
import java.util.Queue;

class MyStack {

    private Queue<Integer> q1 = new ArrayDeque<>();
    private Queue<Integer> q2 = new ArrayDeque<>();

    public MyStack() {
    }

    public void push(int x) {
        q1.offer(x);
    }

    public int pop() {
        if (this.empty()) {
            throw new RuntimeException("empty stack");
        }
        if (q1.size() == 1) {
            return q1.poll();
        }
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        int res = q1.poll();
        q1 = q2;
        q2 = new ArrayDeque<>();
        return res;
    }

    public int top() {
        if (this.empty()) {
            throw new RuntimeException("empty stack");
        }
        if (q1.size() == 1) {
            return q1.peek();
        }
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        int res = q1.poll();
        q1 = q2;
        q1.offer(res);
        q2 = new ArrayDeque<>();
        return res;
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // 返回 2
        System.out.println(myStack.pop()); // 返回 2
        System.out.println(myStack.empty()); // 返回 False
    }

}
