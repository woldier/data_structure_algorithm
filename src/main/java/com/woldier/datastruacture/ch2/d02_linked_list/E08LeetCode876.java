package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * @author: woldier
 * @date: 2023/6/26 下午1:31
 */
public class E08LeetCode876 {
    /**
     * description 查找中间节点,采用快慢指针
     * 指针p1一次移动一步,p2一次移动两步
     *
     * <pre>
     *     {@code
     *     当个数为奇数时结束条件为p2.next==null
     *           ↓
     *     1->2->3->4->5->null
     *                 ↑
     * d    当个数为偶数时,结束条件为p2==null
     *              ↓
     *     1->2->3->4->5->6->null
     *                       ↑
     *     }
     * </pre>
     *
     * @param head
     * @return
     * @author: woldier
     * @date: 2023/6/26 下午1:31
     */
    public ListNode middleNode(ListNode head) {
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }
}
