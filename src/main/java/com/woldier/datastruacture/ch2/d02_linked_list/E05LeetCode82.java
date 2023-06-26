package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description 删除排序链表中的重复元素
 *
 * @author: woldier
 * @date: 2023/6/26 上午10:04
 */
public class E05LeetCode82 {

    /**
     * description 算法思想是 利用三个指针p1,p2,p3
     *
     * <pre>
     *     {@code
     *
     *     s->1->2->3->3->4->4->5-null
     *     ↑  ↑  ↑
     *     p1 p2 p3
     *
     *     当p2.val!=p3.val时
     *     p1,p2,p3 同时右移
     *
     *     s->1->2->3->3->4->4->5-null
     *        ↑  ↑  ↑
     *        p1 p2 p3
     *
     *      当p2.val = p3.val时p3右移,直到p2.val!=p3.val (或者p3为null)
     *     s->1->2->3->3->4->4->5-null
     *           ↑  ↑  ↑
     *           p1 p2 p3
     *      此时让 p1.next = p3  p2=p3 p3 = p3.next
     *     s->1->2->3->3->4->4->5-null
     *           ↑  ↑     ↑
     *           p1 p2    p3
     *            ________
     *           |        ↓
     *     s->1->2  3->3->4->4->5-null
     *           ↑        ↑  ↑
     *           p1       p2 p3
     *
     *
     *        }
     * </pre>
     *
     * @param head 头指针
     * @return 返回删除重复元素后的链表
     * @author: woldier
     * @date: 2023/6/26 上午10:44
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        ListNode p1 = sentinel, p2 = head, p3 = head.next;
        while (p3 != null) {
            if (p2.val != p3.val) {
                p1 = p2;
            } else {
                while (p3 != null && p2.val == p3.val)
                    p3 = p3.next;
                p1.next = p3;
            }
            p2 = p3;
            p3 = p3.next;
        }

        return sentinel.next;
    }

}
