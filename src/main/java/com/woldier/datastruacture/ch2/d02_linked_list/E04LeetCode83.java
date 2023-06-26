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
        ListNode sentinel = new ListNode(-1,head);
        ListNode p1=sentinel,p2=p1;
        while((p2=p2.next)!=null){
            if(p1.val!=p2.val){
                p1.next=p2;
                p1=p2;
            }
        }
        p1.next=null;
        return head;
    }
}
