package com.woldier.datastruacture.ch2.d02_linked_list;

/**
*
* description leetcode 203 剔除元素
*
* @author: root
* @date: 2023/6/25 下午6:56
*/
public class E02LeetCode203 {
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
    *
    * description 删除元素
     *
     *
     * <pre>
     *     {@code
     *     例如我们要删除值为6的元素
     *
     *     1->2->5->6->8>->6->3->null
     *
     *     为了便于判断没问,添加哨兵
     *  s->1->2->5->6->8>->6->3->null
     *  ↑  ↑
     *  p1 p2
     *     初始时有两个指针分别指向哨兵和第一个节点
     *     当p2指向非空时,进行如下操作,
     *     如果p2的值不等于待删除值那么p1,p2同时右移
     *  s->1->2->5->6->8>->6->3->null
     *     ↑  ↑
     *     p1 p2
     *     如果p2的值等于待删除值那么p1不动,p2右移,p1.next更新为新的p2
     *
     *
     *  s->1->2->5->6->8>->6->3->null
     *           ↑  ↑
     *           p1 p2
     *            _____
     *           |     ↓
     *  s->1->2->5  6->8>->6->3->null
     *           ↑     ↑
     *           p1    p2
     *
     *           随后为了便于垃圾回收,断开6指向8的指针
     *            _____
     *           |     ↓
     *  s->1->2->5  6  8>->6->3->null
     *           ↑     ↑
     *           p1    p2
     *
     *     }
     * </pre>
    *
    * @param head 头指针
    * @param val  期望移除的elem
    * @return
    * @author: root
    * @date: 2023/6/25 下午7:01
    */
    public ListNode removeElem(ListNode head,int val){
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head); // 带哨兵

        ListNode p1 = sentinel,p2=sentinel.next;

        while(p2!=null){
            if(p2.val == val){
                p2 = p2.next;
                p1.next.next = null; //help GC
                p1.next = p2;
            }
            else {
                p2 = p2.next;
                p1 = p1.next;
            }
        }
        return sentinel.next;
    }
    /**
    *
    * description 采用递归实现元素剔除
    *算法思想时遍历各个元素,在此过程中如果出现了元素值为待删除元素那么就让其从list中移除
    * @param head 链表头部
    * @param val 比较的值
    * @return
    * @author: root
    * @date: 2023/6/25 下午7:44
    */
    public ListNode removeElem2(ListNode head,int val){
        while(head!=null&&head.val == val){
            head = head.next;

        }
        if(head == null) return head;  //递归结束条件
        head.next = removeElem2(head.next,val); // 返回删除了期待元素后的list
        return head;
    }
}
