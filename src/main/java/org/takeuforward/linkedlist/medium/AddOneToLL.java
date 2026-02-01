package org.takeuforward.linkedlist.medium;

import java.util.Stack;

public class AddOneToLL {

    public static void main(String[] args) {
        // 4 -> 5 -> 6
        Node head = buildLL(4,5,6);
        printLL(head);

        head = addOne1(head);
        printLL(head);

        head = buildLL(9,9,9);
        printLL(head);

        head = addOne1(head);
        printLL(head);

    }

    /**
     * Time complexity : O(n) 3-pass
     * Space complexity : O(n)
     */
    public static Node addOne(Node head) {
        if (head == null) return null;
        Stack<Integer> integers = new Stack<>();

        Node curr = head;
        while (curr != null) {
            integers.push(curr.data);
            curr = curr.next;
        }

        curr = head;
        int carryOver = 1;
        while (carryOver > 0 || curr != null) {
            int currVal = integers.isEmpty() ? 0 : integers.pop();
            int value = currVal + carryOver;
            if (value > 9) {
                curr.data = value - 10;
                carryOver  = 1;
            } else {
                curr.data = value;
                carryOver = 0;
            }
            if (carryOver > 0 && curr.next == null) {
                curr.next = new Node(0, null);
            }
            curr = curr.next;
        }

        return reverse(head);
    }

    /**
     * Time complexity : O(n) 3 pass
     * Space complexity : O(1)
     */
    public static Node addOne1(Node head) {
        if (head == null) return null;

        head = reverse(head);
        int carryForward = 1;

        Node curr = head;
        while (curr != null && carryForward > 0) {
            int val = curr.data + carryForward;

            if (val > 9) {
                curr.data = val - 10;
                carryForward = val / 10;
            } else {
                curr.data = val;
                carryForward = 0;
            }

            if (carryForward > 0 && curr.next == null) {
                Node node = new Node(0, null);
                curr.next = node;
            }

            curr = curr.next;
        }
        return reverse(head);
    }

    public static Node reverse(Node head) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
    }
}
