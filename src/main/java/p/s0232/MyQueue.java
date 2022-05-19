package p.s0232;

import java.util.Stack;

public class MyQueue {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public MyQueue() {
    }

    public void push(int x) {
        if (s1.empty()) {
            s1.push(x);
            return;
        }
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
        s1.push(x);
        while (!s2.empty()) {
            s1.push(s2.pop());
        }
    }

    public int pop() {
        return s1.pop();
    }

    public int peek() {
        return s1.peek();
    }

    public boolean empty() {
        return s1.empty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
//        myQueue.push(2);
//        myQueue.push(3);
//        myQueue.push(4);
//        myQueue.pop();
//        myQueue.push(5);
//        myQueue.pop();
//        myQueue.pop();
//        myQueue.pop();
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());

    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
