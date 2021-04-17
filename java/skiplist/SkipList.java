package com.github.distribute.skiplist;

public class SkipList {
    private final float skip_listp = 0.5f;
    private final int max_level = 16;

    private int levelCount = 1;
    private Node head = new Node(); //´øÍ·Á´±í

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(21);
        skipList.insert(4);
        skipList.insert(5);
        skipList.insert(6);
        skipList.insert(8);
        skipList.printAll();
    }

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        }else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;

        Node update[] = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        
        Node p = head;
        for (int i = level - 1; i >= 0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
        if (levelCount < level) levelCount = level;
    }

    private void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;

        for (int i = levelCount - 1; i >= 0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0 ; i--) {
                if (update[i].forwards != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }
    }

    private int randomLevel() {
        int level = 1;

        while (Math.random() < skip_listp && level < max_level) {
            level += 1;
        }
        return level;
    }

    private void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0]+" ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node {
        private int data = -1;
        private Node[] forwards = new Node[max_level];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }
}
