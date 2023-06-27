package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;


/**
*
* description 带哨兵的环型队列
*
* @author: woldier
* @date: 2023/6/26 下午9:15
*/
public class LinkedListQueue<E>
        implements Queue<E>,Iterable<E> {


    /**
     * description 私有内部类,链表结点,本类加了static修饰
     * <p></p>
     * 没有加static,因此是与类的对象绑定的,依附于类的对象存在  (应用场景,如JUC ReentrantLock 的同步器中使用的Node,Condition,就是非静态的,可以在这些内部类的方法中操作外部类)
     * <p></p>
     * 加了static,内部类可以独立于类的对象存在,与类对象不存在绑定关系
     * <p></p>
     * 链表结构图(带哨兵循环链表)
     * <pre>
     *
     *           ---------------------------------------|
     *          ↓                                       |
     * index   -1              0              1         |
     *      +------+  next  +-----+  next  +-----+      |
     *      | 哨兵  |  ----> |     |  ----> |      | ----
     *      +------+  prev  +-----+  prev  +-----+
     *         ⬆                             ⬆
     *        head                          tail
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/14 16:00
     */
    private static class Node<E>{
        E val;
        Node<E> next;

        public Node() {
        }

        public Node(E val) {
            this.val = val;
        }

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    Node<E> head;
    Node<E> tail;

    /**
    *
    * description 构造方法,初始化队列
    *
    * @author: woldier
    * @date: 2023/6/26 下午9:25
    */
    public LinkedListQueue() {
        head = new Node<E>();
        head.next = head;
        tail = head;
    }

    @Override
    public boolean offer(E e) {
        Node<E> p = new Node<>(e,tail.next);
        tail.next = p;
        tail = p;
        return true;
    }
    @Override
    public E poll() {
        if(isEmpty()) return null;//判断是否为null
        Node<E> next = head.next;
        if(next==tail) tail= head; //判断当队列只有一个元素的情况 需要重新设置尾指针
        head.next = next.next;
        return next.val;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return head.next.val;
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    /**
    *
    * description 迭代器
    *

    * @author: woldier
    * @date: 2023/6/26 下午9:29
    */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head


                    .next;
            @Override
            public boolean hasNext() {
                return p!=head;
            }
            @Override
            public E next() {
                E vale = p.val;
                p = p.next;
                return vale;
            }
        };
    }
}
