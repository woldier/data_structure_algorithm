package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description Leetcode19 删除倒数第n个元素
 *
 * @author: root
 * @date: 2023/6/25 下午8:05
 */
public class E03LeetCode19 {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
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

    /**
     * description 采用递归实现,递归最底层返回0,其他返回下一层+1 ,如果n的值与返回值相等,那么此时head指针指向的下一个元素即是要删除的
     *
     * @param head 头指针
     * @param n    倒数的索引
     * @return
     * @author: root
     * @date: 2023/6/25 下午8:07
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head); //添加哨兵,保证移除的元素为第一个时无法remove
        recursion(sentinel, n);
        return sentinel.next;
    }

    private int recursion(ListNode p, int n) {
        if (p == null) return 0;
        int i = recursion(p.next, n);
        if (i == n) p.next = p.next.next;
        return i + 1;
    }

    /**
     * description 非递归实现,运用两个指针p1,p2他们刚刚好间隔数刚好为需要移除的倒序数,然后当p2指向非空时p1,p2同时右移
     *
     * <pre>
     *     {@code
     *     当移除倒数第一个元素时
     *      s->1->2->3->4->5->6->7
     *      ↑  ↑
     *      p1 p2
     *     p2先移动1次
     *      s->1->2->3->4->5->6->7
     *      ↑     ↑
     *      p1    p2
     *      然后p1,p2同时右移,知道p2指向null,这是p1对应的节点他的next节点即是我们需要删除的元素
     *      s->1->2->3->4->5->6->7->null
     *                        ↑     ↑
     *                        p1    p2
     *     }
     * </pre>
     * @param head 头指针
     * @param n    倒数的第几个节点
     * @return
     * @author: root
     * @date: 2023/6/25 下午8:42
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1, head); //添加哨兵,保证移除的元素为第一个时无法remove
        ListNode p1 = sentinel, p2 = p1.next;
        while (n > 0) {
            p2 = p2.next;
            n--;
        }
        while (p2 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        p1.next = p1.next.next;
        return sentinel.next;
    }
}
