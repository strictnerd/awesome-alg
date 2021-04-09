package com.github.distribute.lru;

public class LRULinkedList<T> {
    int default_capacity = 5;
    int length = 0;
    private SNode headNode;

    public LRULinkedList(int capacity) {
        this.headNode = new SNode();
        this.default_capacity = capacity;
        this.length = 0;
    }

    public static void main(String[] args) {
        LRULinkedList lruLinkedList = new LRULinkedList(5);
        lruLinkedList.add(12);
        lruLinkedList.add(13);
        lruLinkedList.add(111);
        lruLinkedList.add(435);
        lruLinkedList.add(2);
        lruLinkedList.add(1);
        lruLinkedList.add(0);
        lruLinkedList.add(9);
        lruLinkedList.printAll();
    }

    private void printAll() {
        SNode snode = headNode.getNext();
        while (snode != null) {
            System.out.println(snode.element);
            snode = snode.getNext();
        }
        System.out.println();
    }
    /**
     * 添加节点
     * @param data
     */
    public void add(T data) {
        //判断data是否存在
        SNode preNode = findPreNode(data);
        //如果已经存在，删除原来节点，并添加data到头节点
        if (preNode != null) {
            deleteDataOption(preNode);
            insertHeadNode(data);
        }else {//如果不存在，判断是否已经超过最大长度，如果超过，删除尾节点，并添加新节点到头部
            if (length >= default_capacity) {
                deleteTailNode();
            }
            insertHeadNode(data);
        }

    }
    private void insertHeadNode(T data) {
        SNode sNode = headNode.getNext();
        headNode.setNext(new SNode(data, sNode));
        length++;
    }

    private void deleteDataOption(SNode node) {
        SNode temp = node.getNext();
        node.setNext(temp.getNext());
        temp = null;
        length--;
    }

    private void deleteTailNode() {
        SNode sNode = headNode;
        if(sNode.getNext() == null) {
            sNode.setNext(null);
            length--;
        }
        while (sNode.getNext().getNext() != null) {
            sNode = sNode.next;
        }
        sNode.setNext(null);
        length--;
    }

    private SNode findPreNode(T data) {
        SNode preNode = headNode;
        while (preNode.getNext() != null) {
            if (data.equals(preNode.getNext().getElement())) {
                return preNode;
            }
            preNode = preNode.getNext();
        }
        return null;
    }

    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode() {
            this.next = null;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }
}
