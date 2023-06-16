package com.woldier.datastruacture.ch2.d01_linked_list;

import java.util.Iterator;

/**
 * @author woldier
 * @version 1.0
 * @description 带哨兵的双向环形链表
 * @date 2023/6/16 10:42
 **/
public class DoublyCircularLinkedListSentinel implements Iterable<Integer>{

    Node head;

    /**
    *
    * description 迭代器对象
    *
    *
    * @return java.util.Iterator<java.lang.Integer>
    * @author: woldier
    * @date: 2023/6/16 10:53
    */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {
                return p!=head;
            }

            @Override
            public Integer next() {
                int value  = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node{
        Node prev;
        int value;
        Node next;

        /**
        *
        * description 构造器
        *
        * @param prev 前驱节点
         * @param value 保存的值
         * @param next  后继节点
        * @return
        * @author: woldier
        * @date: 2023/6/16 10:43
        */
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    /**
    *
    * description 构造方法初始化哨兵节点
    *
    *
    * @return
    * @author: woldier
    * @date: 2023/6/16 10:45
    */
    public DoublyCircularLinkedListSentinel() {
        head = new Node(null,Integer.MIN_VALUE,null);
        head.next = head;
        head.prev = head;
    }

    public void addFirst(int value){
        head.value = value;//将当前哨兵变为普通节点
        head = new Node(head.prev,Integer.MIN_VALUE,head); //创建新的哨兵
        head.next.prev = head;
        head.prev.next = head;
    }

}
