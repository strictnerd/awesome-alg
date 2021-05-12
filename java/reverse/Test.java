package com.xdja.security.reverse;

public class Test {
    //链表反转得思想非常简单，就是颠倒以下指针指向即可，我首先保存头指针的下一个为临时变量，头指针下一个赋null值，之后的都指向第一个
    public static ListNode reverseLink(ListNode node) {
        ListNode cur = node; ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre; //赋值为前一个节点
            pre = cur; //前一个节点更改为当前节点
            cur = temp; //当前节点更改为下一个节点
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
    //删除链表倒数第k个节点，核心思想就是快慢指针，快指针先走，慢指针随后跟上，快指针走完，慢指针就找到了需要删除的元素。
    public static ListNode deleteLinkedLastK(ListNode listNode, int k) {
        ListNode fast = listNode; //定义一个前进节点，并把整个链表赋给fast
        int i = 1; // 找到正数第k个节点
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }
        if (fast == null) return listNode;

        ListNode slow = listNode; //定义一个慢节点
        ListNode prev = null;

        while (fast.next != null) {
            fast = fast.next; //快节点继续前进
            prev = slow; //把慢节点赋值给prev
            slow = slow.next; //慢节点继续前进
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
