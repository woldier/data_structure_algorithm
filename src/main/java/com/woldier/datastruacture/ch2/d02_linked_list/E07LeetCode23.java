package com.woldier.datastruacture.ch2.d02_linked_list;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author: woldier
 * @date: 2023/6/26 下午12:19
 */
public class E07LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        return mergeKRecursion(lists);
    }

    private ListNode mergeKRecursion(ListNode[] lists) {
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeTwoLists(lists[0], lists[1]);

        ListNode[] node1 = Arrays.copyOfRange(lists, 0, lists.length >>> 1);
        ListNode[] node2 = Arrays.copyOfRange(lists, lists.length >>> 1, lists.length);
        return mergeTwoLists(mergeKRecursion(node1), mergeKRecursion(node2));
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, null);
        ListNode p1 = list1, p2 = list2, s = sentinel;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                s.next = p1;
                p1 = p1.next;
            } else {
                s.next = p2;
                p2 = p2.next;
            }
            s = s.next;
            s.next = null;
        }
        if (p1 != null)
            s.next = p1;
        if (p2 != null)
            s.next = p2;
        return sentinel.next;
    }

}
