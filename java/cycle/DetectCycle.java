package com.xdja.security.cycle;

public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        //判断是否有环
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //计算第一个交叉点  slow保持不变，重置fast节点前进位置
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
