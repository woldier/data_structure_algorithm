package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description leetcode206 反转链表
 *
 * @author: root
 * @date: 2023/6/25 下午2:07
 */
public class E01LeetCode201 {

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
     * description 定义的一个list类,用于封装头插与头删方法
     *
     * @author: root
     * @date: 2023/6/25 下午2:41
     */
    public static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void insertFirst(ListNode node) {
            node.next = head;
            head = node;
        }

        public ListNode removeFirst() {
            if (head == null) return null;
            ListNode p = head;
            head = head.next;
            p.next = null;
            return p;

        }
    }

    /**
     * description 反转链表实现1
     * 算法思路是,创建一个新的链表,然后依次将原链表头部的元素remove掉然后插入到新链表中
     *
     * @return
     * @author: root
     * @date: 2023/6/25 下午2:07
     */
    public ListNode reverseList1(ListNode head) {
        ListNode newList = null;
        while (head != null) {
            newList = new ListNode(head.val, newList);
            head = head.next;
        }
        return newList;
    }


    /**
     * description 反转链表实现2
     * <p></p>
     * 思路是将原来的node从链表中删除然后作为新的链表的节点
     * 避免空间的浪费,为了便于操作,扩展了一个list类,定义了头插和头删方法.
     *
     * @return
     * @author: woldier
     * @date: 2023/6/25 下午2:28
     */
    public ListNode reverseList2(ListNode head) {
        List l1 = new List(head);
        List l2 = new List(null);
        ListNode node;
        while ((node = l1.removeFirst()) != null) {
            l2.insertFirst(node);
        }
        return l2.head;
    }

    /**
     * description 反转链表实现3
     *
     * <p></p>
     * 通过递归的方式实现
     *
     * @author: root
     * @date: 2023/6/25 下午2:54
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * description 循环移位法
     * <pre>
     *     {@code
     *      ↓
     *      1->2->3->4->5
     *         ↓
     *      2->1->3->4->5
     *            ↓
     *      3->2->1->4->5
     *               ↓
     *      4->3->2->1->5
     *                  ↓
     *      5->4->3->2->1
     *     }
     * </pre>
     *
     * @author: root
     * @date: 2023/6/25 下午3:11
     */
    public ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode o = head;
        while (head.next != null) {
            ListNode p = head.next;
            head.next = p.next;
            p.next = o;
            o = p;
        }
        return o;
    }

    /**
    *
    * description 思路与方法2类似,不过思路二采用的是面向对象,本解决方式是面对过程
    *
    * @author: root
    * @date: 2023/6/25 下午3:31
    */
    public ListNode reverseList5(ListNode head) {
        ListNode newHead = null;
        while(head!=null){
            ListNode p = head;
            head = head.next;
             p.next = newHead;
             newHead = p;
        }
        return newHead;
    }

}
