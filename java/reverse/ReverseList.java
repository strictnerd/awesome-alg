package com.xdja.security.reverse;

public class ReverseList {
    public ListNode reverseList(ListNode head) {
        // 1->2->3->4
       /* ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;*/
      if (head == null || head.next == null) {
          return head;
      }
       ListNode p = reverseList(head.next);
       head.next.next = head;
       head.next = null;
       return p;
    }


}
