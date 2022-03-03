package p.s002;

import u.ListNode;

public class Solution {

    int curAdd = 0;

    private void makeSameLength(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;

        while (n1.next != null && n2.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }

        while (n1.next != null && n2.next == null) {
            n1 = n1.next;
            n2.next = new ListNode(0);
            n2 = n2.next;
        }

        while (n1.next == null && n2.next != null) {
            n1.next = new ListNode(0);
            n1 = n1.next;
            n2 = n2.next;
        }

        if (n1.next != null) {
            throw new IllegalArgumentException("???");
        }

        if (n2.next != null) {
            throw new IllegalArgumentException("???");
        }

    }

    public ListNode computeNewNode(int v1, int v2) {
        int curVal = v1 + v2 + curAdd;
        if (curVal >= 10) {
            curVal -= 10;
            curAdd = 1;
        } else {
            curAdd = 0;
        }
        return new ListNode(curVal);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        makeSameLength(l1, l2);
        ListNode res = new ListNode(0);
        ListNode pre = res;
        while (l1 != null && l2 != null) {
            pre.next = computeNewNode(l1.val, l2.val);
            pre = pre.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (curAdd == 1) {
            pre.next = new ListNode(1);
        }
        return res.next;
    }

}
