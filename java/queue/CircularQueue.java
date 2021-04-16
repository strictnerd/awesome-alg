package com.github.distribute.queue;

/**
 * 基于数组的环形队列实现
 */
public class CircularQueue {
    private String[] items;
    private int n;
    private int head;
    private int tail;

    public CircularQueue(int n) {
        this.n = n;
        items = new String[n];
    }

    private boolean enqueue(String data) {
        if ((tail+1) % n == head) return false;

        items[tail] = data;
        tail = (tail +1) % n;
        return true;
    }

    private String dequeue() {
        if (tail == head) return null;

        String value = items[head];
        head = (head + 1) % n;
        return value;
    }

    private void printAll() {
        if (0 == n) return;
        for (int i = head; i % n != tail; i = (i + 1) % n) {
            System.out.println(items[i]);
        }
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.enqueue("1");
        circularQueue.enqueue("2");
        circularQueue.enqueue("3");
        circularQueue.enqueue("4");
        circularQueue.dequeue();
        circularQueue.enqueue("5");
        circularQueue.printAll();
    }
}
