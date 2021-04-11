package com.xdja.security.reverse;

public class Test {
    public static ListNode reverseLink(ListNode node) {
        ListNode cur = node; ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static boolean checkCircle(ListNode node) {
        if (node == null) return false;

        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static ListNode mergeTwoLinked(ListNode l1, ListNode l2) {
        ListNode solid = new ListNode(0);
        ListNode p = solid;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                p.next = l2;
                l2 = l2.next;
            }else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return solid.next;
    }

    public static ListNode deleteLinkedK(ListNode listNode, int k) {
        ListNode fast = listNode;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }
        if (fast == null) return listNode;

        ListNode slow = listNode;
        ListNode prev = null;

        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (prev == null) {
            listNode = listNode.next;
        }else {
            prev.next = prev.next.next;
        }
        return listNode;
    }

    public static ListNode middleNode(ListNode node) {
        if (node == null) return null;
        ListNode fast = node;
        ListNode slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode2.next = listNode3;
        listNode3.next = listNode4;
       // listNode4.next = listNode3;

        ListNode listNode5 = new ListNode(1);
        ListNode listNode6 = new ListNode(3);
        ListNode listNode7 = new ListNode(4);
        ListNode listNode8 = new ListNode(5);
        ListNode listNode9 = new ListNode(6);
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        ListNode listNode = middleNode(listNode5);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        System.out.println();

    }
}
