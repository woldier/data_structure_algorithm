package com.woldier.datastruacture.ch2.d01_linked_list.test;

import com.woldier.datastruacture.ch2.d01_linked_list.SingleLinkedList;
import org.junit.Test;

/**
 * @author woldier
 * @version 1.0
 * @description 单链表测试类
 * @date 2023/6/14 8:58
 **/
public class Test4SingleLinkedList {


    @Test
    public void test(){
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //头插
        singleLinkedList.addFirst(1);
        singleLinkedList.addFirst(2);
        singleLinkedList.addFirst(3);
        singleLinkedList.addFirst(4);
        singleLinkedList.addFirst(5);
        singleLinkedList.addFirst(6);

        System.out.println("\nwhile遍历");
        /*while 遍历*/
        singleLinkedList.loop1(System.out::print);
        System.out.println("\nIterator遍历");
        for (Integer integer : singleLinkedList) {
            System.out.print(integer);
        }

        //尾部插入
        singleLinkedList.addLast(7);
        System.out.println("\nwhile遍历");
        /*while 遍历*/
        singleLinkedList.loop1(System.out::print);
//        singleLinkedList.insert(0,8);
        singleLinkedList.removeFirst();
        System.out.println("\nwhile遍历");
        /*while 遍历*/
        singleLinkedList.loop1(System.out::print);
        singleLinkedList.remove(6);
        System.out.println("\nwhile遍历");
        /*while 遍历*/
        singleLinkedList.loop1(System.out::print);
    }
}
