package org.takeuforward.stackAndQueue.learning;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    public Queue<Integer> queue = new LinkedList<Integer>();

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.print();
        myStack.push(2);
        myStack.print();
        myStack.push(3);
        myStack.print();

        myStack.pop();
        myStack.print();
        myStack.pop();
        myStack.print();

        System.out.println("Is stack empty? " + myStack.isEmpty());
        myStack.push(4);
        myStack.print();

    }

    public void push(int x) {
        int s = queue.size();
        queue.add(x);
        for(int i = 0; i < s; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        int n = queue.peek() == null ? -1 : queue.peek();
        queue.poll();
        return n;
    }

    public int peek() {
        return queue.peek() == null ? -1 : queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void print() {
        System.out.println(queue);
    }
}
