package com.woldier.datastruacture.ch2.d02_linked_list;
/**
*
* description 合并两有序链表,
 * <pre>
 *     {@code
 *     p1
 *     ↓
 *     1->2->3->4
 *     p2
 *     ↓
 *     2->3->5->7
 *     s
 *     ↓
 *     s->null
 *
 *     }
 * </pre>
*
* @author: woldier
* @date: 2023/6/26 上午11:19
*/
public class E06LeetCode21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, null);
        ListNode p1=list1,p2=list2,s=sentinel;
        while(p1!=null&&p2!=null){
            if(p1.val<p2.val){
                s.next=p1;
                p1=p1.next;
            }
            else {
                s.next=p2;
                p2 = p2.next;
            }
            s = s.next;
            s.next=null;
        }
        if(p1!=null)
            s.next=p1;
        if(p2!=null)
            s.next=p2;
        return sentinel.next;
    }

    /**
    *
    * description 递归解法
    *
    * @param list1  待合并链表一
    * @param list2  待合并链表二
    * @return  合并后的链表
    * @author: woldier
    * @date: 2023/6/26 下午12:05
    */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        return mergeRecursion(list1,list2);

    }
    private ListNode mergeRecursion(ListNode p1, ListNode p2) {
        if(p1==null) return p2;
        if (p2==null) return p1;
        ListNode newList;
        if(p1.val< p2.val){
            newList = mergeRecursion(p1.next,p2);
            p1.next = newList;
            return p1;
        }
        else {
            newList = mergeRecursion(p1,p2.next);
            p2.next = newList;
            return p2;
        }
    }


}
