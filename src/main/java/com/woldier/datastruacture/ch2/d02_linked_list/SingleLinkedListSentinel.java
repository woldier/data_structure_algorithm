package com.woldier.datastruacture.ch2.d02_linked_list;

import java.util.Iterator;

/**
 * @author woldier
 * @version 1.0
 * @description 哨兵(sentinel)单链表实现
 * @date 2023/6/14 14:32
 **/
public class SingleLinkedListSentinel implements Iterable<Integer> {

    private Node head = new Node(Integer.MIN_VALUE, null);

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     * description 私有内部类,链表结点,本类加了static修饰
     * <p></p>
     * 没有加static,因此是与类的对象绑定的,依附于类的对象存在  (应用场景,如JUC ReentrantLock 的同步器中使用的Node,Condition,就是非静态的,可以在这些内部类的方法中操作外部类)
     * <p></p>
     * 加了static,内部类可以独立于类的对象存在,与类对象不存在绑定关系
     * <p></p>
     * 链表结构图(带哨兵链表)
     * <pre>
     * index     -1            0             1
     *      +------+  next +-----+       +-----+
     *      | 哨兵  | ----> |     | ----> |     |
     *      +------+       +-----+       +-----+
     *         ⬆
     *        head
     * </pre>
     *
     * @author: woldier
     * @date: 2023/6/14 8:28
     */
    private static class Node {
        private int value;

        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 添加元素到头部
     *
     * @param value
     */
    public void addFirst(int value) {
        head.value = value;
        head = new Node(Integer.MIN_VALUE, head);
    }


    /**
     * description 队尾插入
     *
     * @param value 插入的值
     * @return void
     * @author: woldier
     * @date: 2023/6/14 14:49
     */
    public void addLast(int value) {
        Node p = findLast();
        p.next = new Node(value, null);
    }

    /**
     * description 查找最末尾元素
     * 思想时遍历list到最尾
     *
     * @return com.woldier.datastruacture.ch2.d01_linked_list.SingleLinkedListSentinel.Node
     * @author: woldier
     * @date: 2023/6/14 14:48
     */
    private Node findLast() {
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    private Node findNode(int index) {
        int i = -1;
        Node p = head;
        while (p != null && i != index) {
            p = p.next;
            i++;
        }
        return p;
    }

    /**
     * description 根据索引获取元素,当索引不合法会抛出 {@code IllegalArgumentException.class} 异常
     *
     * @param index 索引
     * @return int 索引对应的值
     * @author: woldier
     * @date: 2023/6/14 14:51
     */
    public int get(int index) {
        Node node;
        if ((node = findNode(index)) == null)
            throw getIllegalArgumentException(index);
        return node.value;
    }

    /**
     * description 索引不合法异常
     *
     * @param index
     * @return java.lang.IllegalArgumentException
     * @author: woldier
     * @date: 2023/6/14 10:50
     */
    private static IllegalArgumentException getIllegalArgumentException(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }


    /**
     * description 插入到指定索引
     *
     * @param index
     * @param value
     * @return void
     * @author: woldier
     * @date: 2023/6/14 14:56
     */
    public void insert(int index, int value) {
        Node pre;
        if ((pre = findNode(index - 1)) == null)
            throw getIllegalArgumentException(index);
        pre.next = new Node(value, pre.next);
    }


    public void removeFirst() {
        Node node = head.next;
        node.value = Integer.MIN_VALUE;
        head.next = null; //help GC
        head = node;

    }

    public void remove(int index) {
        Node pre = findNode(index - 1);
        if (pre == null || pre.next == null)
            throw getIllegalArgumentException(index);
        Node node = pre.next;
        pre.next = node.next;
        node.next = null; //help gc
    }
}
