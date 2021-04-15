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
     * ����Ԫ��
     * @param data
     * @return
     */
    private boolean enqueue(String data) {
        //�����ж϶����Ƿ��Ѿ���
        if (tail == capacity) {
            if (head == 0) {
                return false;
            }
            //���ֶ������˽����ƶ�Ԫ��
            for (int i = head; i < tail; i++) {
                array[i-head] = array[i];
            }
            //����tail��headָ��Ԫ��
            tail -= head;
            head = 0;
        }
        array[tail] = data;
        ++tail;
        return true;
    }

    private String dequeue() {
        //����Ϊ��
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