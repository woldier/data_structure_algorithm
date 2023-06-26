package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @author: woldier
 * @date: 2023/6/26 下午1:56
 */
public class E09LeetCode234 {


    /**
     * description 算法思想分为想寻找到中间节点,然后反转中间节点之后的元素 ,然后从头进行比较
     * <p>
     *
     * <pre>
     *     {@code
     * 1->2->3->2->1->null
     *       ↑
     * 1->2->3->null
     * 1->2->null
     *
     * 1->2->3->3->2->1->null
     *          ↑
     * 1->2->3->3->2->1->null
     * 1->2->3->null
     *     }
     * </pre>
     *
     * @param head
     * @return 返回 {@code true} 表示是回文链表,否则不是
     * @author: woldier
     * @date: 2023/6/26 下午1:56
     */
    public boolean isPalindrome(ListNode head) {
        //找到中间节点
        ListNode mid = findMid(head);
        //反转
        ListNode s = reverseNode(mid);
        while (s != null) {
            if (s.val != head.val) return false;
            s = s.next;
            head = head.next;
        }
        return true;
    }

    private static ListNode reverseNode(ListNode mid) {
        ListNode s = new ListNode(Integer.MIN_VALUE), p;
        while (mid != null) {
            p = mid;
            mid = mid.next;
            p.next = s.next;
            s.next = p;
        }
        s = s.next;
        return s;
    }

    private ListNode findMid(ListNode p) {
        if (p.next == null) return p;
        ListNode p1 = p, p2 = p;
        while (true) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p2 == null) {
                return p1;
            }
            if (p2.next == null)
                return p1.next;
        }
    }


    /**
     * description 上面的算法性能不是很好,可以若如下优化,在寻找中间节点的同时进行前面节点反转
     *
     * <pre>
     *     {@code
     * 1->2->3->2->1->null
     *       ↑
     * 2->1->null
     * 2->1->null
     *
     * 1->2->3->3->2->1->null
     *          ↑
     * 3->2->1->null
     * 3->2->1->null
     *     }
     * </pre>
     *
     * @param head 头指针
     * @return 返回 {@code true} 表示是回文链表,否则不是
     * @author: woldier
     * @date: 2023/6/26 下午2:43
     */
    public boolean isPalindrome2(ListNode head) {
        if (head.next==null) return true;
        ListNode p1 = head,
                p2 = head,
                s = new ListNode(Integer.MIN_VALUE);

        while (p2!=null&&p2.next!=null) {
            p2 = p2.next.next;
            ListNode node = p1;
            p1 = p1.next;
            node.next =s.next;
            s.next = node;
        }
        if(p2!=null)
            p1 = p1.next;

        s = s.next;
        while (s != null) {
            if (s.val != p1.val) return false;
            s = s.next;
            p1 = p1.next;
        }
        return true;
    }
}
