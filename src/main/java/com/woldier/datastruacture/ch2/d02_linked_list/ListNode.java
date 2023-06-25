package com.woldier.datastruacture.ch2.d02_linked_list;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(String.valueOf(val));
        ListNode p = next;
        while (p != null) {
            s.append("->" + p.val);
            p = p.next;
        }
        return s.toString();
    }
}