package com.woldier.datastruacture.ch2.d02_linked_list.test;

import com.woldier.datastruacture.ch2.d02_linked_list.DoublyCircularLinkedListSentinel;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * @author woldier
 * @version 1.0
 * @description 带哨兵的双向环形链表测试类
 * @date 2023/6/16 11:00
 **/
public class Test4DoublyCircularLinkedList {
    /**
    *
    * description 测试
    *
    *
    * @return void
    * @author: woldier
    * @date: 2023/6/16 11:01
    */
    @Test
    public void test(){
        DoublyCircularLinkedListSentinel list = new DoublyCircularLinkedListSentinel();
        Assertions.assertThrows(IllegalArgumentException.class, list::removeFirst);
        Assertions.assertThrows(IllegalArgumentException.class, list::removeLast);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        Assertions.assertIterableEquals(Arrays.asList(4,3,2,1),list);
        list.addLast(0);
        Assertions.assertIterableEquals(Arrays.asList(4,3,2,1,0),list);
        list.removeFirst();
        Assertions.assertIterableEquals(Arrays.asList(3,2,1,0),list);
        list.removeLast();
        Assertions.assertIterableEquals(Arrays.asList(3,2,1),list);
        list.removeByValue(2);
        Assertions.assertIterableEquals(Arrays.asList(3,1),list);
        list.loop(System.out::print);
    }
}
