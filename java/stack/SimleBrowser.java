package com.xdja.security.stack;

public class SimleBrowser {
    public static void main(String[] args) {
        SimleBrowser simleBrowser = new SimleBrowser();
        simleBrowser.openBrowser("baidu.com");
        simleBrowser.openBrowser("google.com");
        simleBrowser.openBrowser("mayun.com");
        simleBrowser.backBrowser();
        simleBrowser.forwardBrowser();
        simleBrowser.backBrowser();

    }

    private void forwardBrowser() {
        if (this.forwardStack.size > 0) {
            this.backStack.push(this.currentPage);
            String pop = this.forwardStack.pop();
            this.show(pop, "forward");
            return;
        }
        System.out.println("no forward");
    }

    private void backBrowser() {
        if (this.backStack.size > 0) {
            this.forwardStack.push(this.currentPage);
            String pop = this.backStack.pop();
            this.show(pop, "back");
            return;
        }
        System.out.println("no back");
    }

    private String currentPage;
    private LinkedBaseStack forwardStack;
    private LinkedBaseStack backStack;

    public SimleBrowser() {
        this.forwardStack = new LinkedBaseStack();
        this.backStack = new LinkedBaseStack();
    }

    public void openBrowser(String url) {
        if(this.currentPage != null) {
            this.backStack.push(currentPage);
            this.forwardStack.clear();
        }
        this.show(url, "current");
    }

    private void show(String url, String current) {
        this.currentPage = url;
        System.out.println(current+" -- "+url);
    }



    public static class LinkedBaseStack{

        public static void main(String[] args) {
            LinkedBaseStack stack = new LinkedBaseStack();
            stack.push("a");
            stack.push("b");
            stack.push("c");
            stack.pop();
            stack.clear();
            stack.print();
        }

        private int size;
        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }

        public void clear() {
            this.size = 0;
            this.top = null;
        }

        public void push(String data) {
            Node node = createNode(data, this.top);
            top = node;
            size++;
        }

        public String pop() {
            Node popNode = this.top;
            if (popNode == null) {
                return null;
            }
            this.top = popNode.next;
            if (size > 0) {
                size--;
            }
            return popNode.data;
        }

        public String getTopData() {
            if (this.top == null) return null;

            return this.top.data;
        }

        public int getSize() {
            return this.size;
        }

        public void print() {
            Node currentNode = this.top;
            while (currentNode != null) {
                String data = currentNode.getData();
                System.out.println(data);
                currentNode = currentNode.next;
            }
        }

        public static class Node{
            String data;
            Node next;

            public Node(String data, Node next) {
                this.data = data;
                this.next = next;
            }

            public Node(String data) {
                this.data = data;
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

}
