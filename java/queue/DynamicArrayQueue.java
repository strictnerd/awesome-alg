package com.github.distribute.queue;

public class DynamicArrayQueue {
    private String[] array;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public DynamicArrayQueue(int capacity) {
        array = new String[capacity];
        this.capacity = capacity;
    }

    /**
     * 添加元素
     * @param data
     * @return
     */
    private boolean enqueue(String data) {
        //首先判断队列是否已经满
        if (tail == capacity) {
            if (head == 0) {
                return false;
            }
            //发现队列满了进行移动元素
            for (int i = head; i < tail; i++) {
                array[i-head] = array[i];
            }
            //更新tail和head指针元素
            tail -= head;
            head = 0;
        }
        array[tail] = data;
        ++tail;
        return true;
    }

    private String dequeue() {
        //队列为空
        if (head == tail) return null;
        String data = array[head];
        ++head;
        return data;
    }

    private void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        DynamicArrayQueue arrayQueue = new DynamicArrayQueue(5);
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");
        arrayQueue.enqueue("4");
        arrayQueue.enqueue("5");
        arrayQueue.dequeue();
        arrayQueue.enqueue("51");

        arrayQueue.printAll();
    }
}
