package org.takeuforward.linkedlist.hard;

public class RotateLLWithGroupRotation {

    public static void main(String[] args) {
        // 1 -> 2 -> 3 -> 4 -> 5
        Node head = buildLL(1, 2);
        printLL(head);

        head = rotateLL(head, 2);
        printLL(head);

    }

    public static Node rotateLL(Node head, int k) {
        if (head == null || head.next == null) return head;
        if (k == 0) return head;

        // find length of the list
        int n = lengthLL(head);

        // reverse the list
        head = reverseLL(head);


        // find mid-point
        int mid = (k % n);
        if (mid == 0) return reverseLL(head);
        Node midNode = findIthNode(head, mid);

        // reverse first half
        Node dummy = new Node(0, head);
        Node firstGroupLast = head;
        dummy.next = reverseGroup(head, midNode);

        firstGroupLast.next = reverseGroup(midNode, null);

        // reverse second half
        return dummy.next;
    }

    public static Node reverseGroup(Node start, Node end) {
        Node curr = start;
        Node prev = end;
        while (curr != end) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node findIthNode(Node head, int i) {
        int count = 0;
        Node curr = head;
        while (curr != null) {
            if (count == i) {
                return curr;
            }
            curr = curr.next;
            count++;
        }
        return null;
    }

    public static int lengthLL(Node head) {
        if (head == null) return 0;
        int count = 0;
        Node curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public static Node reverseLL(Node head) {
        if (head == null || head.next == null) return head;
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
        Node curr = head;
        System.out.print("List : ");
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? "->" : " "));
            curr = curr.next;
        }
        System.out.println();
    }

    public static class Node {
        int val;
        Node next;
        public Node(int val) {this.val = val;}
        public Node(int val, Node next) {this.val = val; this.next = next;}
    }
}
