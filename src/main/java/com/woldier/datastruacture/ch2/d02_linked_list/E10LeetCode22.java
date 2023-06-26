package com.woldier.datastruacture.ch2.d02_linked_list;

/**
 * description 给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/c32eOV
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: woldier
 * @date: 2023/6/26 下午3:41
 */
public class E10LeetCode22 {

    /**
     * description 判断环
     * <p>1.判断是否存在环</p>
     * 算法思想是使用快慢指针,p1一次移动一位,p2一次移动两位
     * 当p2为空说明存在环,否则不存在环
     * <p>2.判断环出现的位置</p>
     * 当p1与p2第一次相遇时,p1指向head,然后p1,p2同时移动一位,当两个指针再次相遇时,则为环的入口
     *
     * <p></p>
     * <p>接下来给出图解与证明</p>
     * <pre>
     *     {@code
     *
     *  p1                 ->e->e
     *  ↓                 |      ↓
     *  e->e->e->e->e->e->e      e
     *  ↑                 ↑ --e<-|
     *  p2
     *
     *  首先,我们需要明确,如果存在环,那么在第一次相遇时p1走过的路程是p2的一半 2*s(p1) = s(p2)
     *         假设入环前需要走l步(上图则为7) ,环内走一圈为n(上图则为5),那么当p1与p2相遇时 (假设一圈中的第k步相遇k小于n)
     *         s(p1) = l + m1 * n + k;  s(p2) = l + m2 * n + k; (m2一定时大于m1的)
     *         那么 这时候 p2在圈内走了  m2 * n + k; 步 如果再走 n-k步那么就一定回到了起点
     *         由于2*s(p1) = s(p2) 则 s(p1) = s(p2) - s(p1)
     *         l + m1 * n + k =  l + m2 * n + k - ( l + m1 * n + k )
     *         l = (m2 - 2*m1)*n -k
     *         由于p2在圈内走了  m2 * n + k; 再走l = (m2 - 2*m1)*n -k步那么 就是2(m2-m1)*n步为整数圈,的证
     *
     *     }
     *
     *
     * </pre>
     *
     * @param head
     * @return 返回环入口
     * @author: woldier
     * @date: 2023/6/26 下午3:42
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {  //阶段1 判断是否有环
            p2 = p2.next.next;
            p1 = p1.next;
            if (p1 == p2) break;
        }
        if (p2 == null || p2.next == null) return null;
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
