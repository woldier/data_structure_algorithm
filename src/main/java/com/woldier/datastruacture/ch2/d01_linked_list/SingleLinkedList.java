package com.woldier.datastruacture.ch2.d01_linked_list;

/**
 * @author woldier
 * @version 1.0
 * @description 单向链表
 * @date 2023/6/13 19:52
 **/
public class SingleLinkedList {

    private String outterInfo = "hello";



    /***
    *
    * description 创建一个链表结点
    *
    * @param value
    * @return com.woldier.datastruacture.ch2.d01_linked_list.SingleLinkedList.Node
    * @author: woldier
    * @date: 2023/6/14 8:29
    */
    private Node newNode(int value){
        return this.new Node(value,null);
    }


    /***
    *
    * description 私有内部类,链表结点,没有加static,因此是与类的对象绑定的,依附于类的对象存在
    *
    * @author: woldier
    * @date: 2023/6/14 8:28
    */
    private class Node{
        private int value;

        private Node next;

        /**
        *
        * description 构造方法
        *
        * @param value node存储的值
         * @param next  指向的下一个Node节点
        * @return
        * @author: woldier
        * @date: 2023/6/13 19:53
        */
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }

}
