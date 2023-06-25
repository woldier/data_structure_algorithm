package com.woldier.datastruacture.ch2.d02_linked_list.test;

import com.woldier.datastruacture.ch2.d02_linked_list.E01LeetCode201;
import com.woldier.datastruacture.ch2.d02_linked_list.E01LeetCode201.ListNode;
import org.junit.Before;
import org.junit.Test;

public class Test4E01LeetCode201 {
    private ListNode head;
    E01LeetCode201 leetCode201 = new E01LeetCode201();
    @Before
    public void init(){
        head = new ListNode(5,head);
        head = new ListNode(4,head);
        head = new ListNode(3,head);
        head = new ListNode(2,head);
        head = new ListNode(1,head);
    }


    @Test
    public void test1(){
        System.out.println(head);
        ListNode list1 = leetCode201.reverseList1(head);
        System.out.println(list1);

    }

    @Test
    public void test2(){
        System.out.println(head);
        ListNode list1 = leetCode201.reverseList2(head);
        System.out.println(list1);


    }
    @Test
    public void test3(){
        System.out.println(head);
        ListNode list1 = leetCode201.reverseList3(head);
        System.out.println(list1);
    }

    @Test
    public void test4(){
        System.out.println(head);
        ListNode list1 = leetCode201.reverseList4(head);
        System.out.println(list1);
    }
    @Test
    public void test5(){
        System.out.println(head);
        ListNode list1 = leetCode201.reverseList5(head);
        System.out.println(list1);
    }
}
