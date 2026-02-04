package org.takeuforward.linkedlist.hard;

import java.util.Stack;

public class ReverseKGroup {

    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        Node head = buildLL(1,2,3,4,5,6,7);
        printLL(head);
        head = reverseGroup(head, 4);
        printLL(head);
    }


    /**
     * Time complexity : O(n + ((n/k)*k)) => O(n + n) => O(2n) => O(n)
     * Space complexity : O(k)
     */
    public static Node reverseGroup(Node head, int k) {
        if (head == null || head.next == null) return head;

        Node dummy = new Node(0, null);
        Node prev = dummy;
        Node curr = head;

        Stack<Node> nodes = new Stack<>();
        while (curr != null) {
            // push to stack
            nodes.push(curr);
            curr = curr.next;

            if (nodes.size() >= k) {
                // do reverse
                while (!nodes.isEmpty()) {
                    Node pop = nodes.pop();
                    prev.next = pop;
                    prev = pop;
                }
                // wire reversed nodes to remaining list
                prev.next = curr;
            }
        }
        return dummy.next == null ? head : dummy.next;
    }

    public static Node buildLL(int... values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node prev = head;

        for (int i = 1; i < values.length; i++) {
            Node node = new Node(values[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }

    public static void printLL(Node head) {
        System.out.print("List : ");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? "->" : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }
}
