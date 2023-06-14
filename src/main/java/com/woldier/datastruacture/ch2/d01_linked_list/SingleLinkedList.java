package com.woldier.datastruacture.ch2.d01_linked_list;

/**
 * @author woldier
 * @version 1.0
 * @description 单向链表
 * @date 2023/6/13 19:52
 **/
public class SingleLinkedList {

    /**
     * 头指针
     */
    private Node head;


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

    * description 私有内部类,链表结点,没有加static,因此是与类的对象绑定的,依附于类的对象存在
     * <pre>
     *      +------+  next +-----+       +-----+
     *      | node | ----> |     | ----> |     |
     *      +------+       +-----+       +-----+
     *         ⬆
     *        head
     * </pre>
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

    /***
    *
    * description 头部插入的算法步骤如下
     * <p>1.创建新节点</p>
    * <p>2.检查head是否为null</p>
     * 2.1若为null说明链表为空直接让头指针指向新插入的节点
     * 2.2若不为null,说明链表不为空,应当将新节点的next指针指向当前head所指向的节点,再让head指向新插入的节点
     *
     *<pre>
     *     {@code
     *          Node newNode = newNode(value);  //创建一个新节点
     *          if(head==null){  //head为空
     *              head = newNode;
     *          }
     *          else{ // head 不为空
     *              newNode.next = head;
     *              head = newNode;
     *          }
     *     }
     *</pre>
     *
     * 上述代码可以进一步简化,于是就有了下面的实现
    * @param value  新插入的节点的值
    * @return void
    * @author: woldier
    * @date: 2023/6/14 8:39
    */
    public void addFirst(int value){
        Node newNode = newNode(value);  //创建一个新节点

            newNode.next = head;  //将newNode链入链表

        head = newNode;  //重新设置头指针

    }
}
