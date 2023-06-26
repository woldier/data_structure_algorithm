package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description  删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * @return
 * @author: root
 * @date: 2023/6/25 下午9:15
 */
public class E04LeetCode83 {
    /**
     * description 算法思想是用两个指针p1,p2,他们初始指向head节点和p1.next,
     * 然后p2先右移,如果p2的值与p1的值不同 那么p1.next= p2
     * 如果p2的值与p1的值相同,那么继续移动p2,直到不同 然后p1.next= p2
     *
     * @param head 头指针
     * @return
     * @author: root
     * @date: 2023/6/25 下午9:18
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel, p2 = p1;
        while ((p2 = p2.next) != null) {
            if (p1.val != p2.val) {
                p1.next = p2;
                p1 = p2;
            }
        }
        p1.next = null;
        return head;
    }

    /**
     * description 递归实现
     *
     * @param head 头指针
     * @return ListNode
     * @author: root
     * @date: 2023/6/26 上午9:32
     */
    public ListNode deleteDuplicates2(ListNode head) {
        return recursion(head.next);

    }

    /**
     * description 递归去重,算法思想是调用recursion(ListNode p)会返回不含有重复元素的链表
     * <p>
     * 递归的结束条件是p为空时
     * 如果p不为空,那么则进行子调用recursion(ListNode p.next)
     * 返回的子链的头节点与p比较,若值相同,那么则让p指向下一个
     * @param p 当前子链的头节点指针
     * @return ListNode
     * @author: woldier
     * @date: 2023/6/26 上午9:38
     */
    private ListNode recursion(ListNode p) {
        if (p == null) return null;
        ListNode node = recursion(p.next);
        if (node != null && p.val == node.val) { //添加node不等于null,防止出现空指针
            p.next = node.next;
            node.next = null;//help GC
        }
        return p;
    }
}
