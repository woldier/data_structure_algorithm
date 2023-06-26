package com.woldier.datastruacture.ch2.d02_linked_list.test;

import com.woldier.datastruacture.ch2.d02_linked_list.E09LeetCode234;
import com.woldier.datastruacture.ch2.d02_linked_list.ListNode;
import org.junit.Test;

public class Test4E09LeetCode234 {
    E09LeetCode234 cc =  new E09LeetCode234();

    @Test
    public void test1(){
        ListNode node = new ListNode(1, null);
        node = new ListNode(2, node);
        node = new ListNode(2, node);
        node = new ListNode(1, node);

        cc.isPalindrome2(node);
    }
}
