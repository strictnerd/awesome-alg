package com.github.distribute.queue;

public class QueueBasedLinkedList {
    private Node head = null;
    private Node tail = null;
    private void enqueue(String data) {
        if (tail == null) {
            Node newNode = new Node(data, null);
            head = newNode;
            tail = newNode;
        }else {
            tail.next = new Node(data, null);
            tail = tail.next;
        }
    }

    private String dequeue() {
        if (head == null) return null;
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    private void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        QueueBasedLinkedList linkedList = new QueueBasedLinkedList();
        linkedList.enqueue("1");
        linkedList.enqueue("2");
        linkedList.enqueue("3");
        linkedList.dequeue();
        linkedList.printAll();
    }

    public class Node {
        String data;
        Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
