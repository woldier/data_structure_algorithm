package com.woldier.datastruacture.ch2.d01_linked_list;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author woldier
 * @version 1.0
 * @description 带哨兵的双向环形链表
 * @date 2023/6/16 10:42
 **/
public class DoublyCircularLinkedListSentinel implements Iterable<Integer> {

    Node head;

    /**
     * description 迭代器对象
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
                return p != head;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node {
        Node prev;
        int value;
        Node next;

        /**
         * description 构造器
         *
         * @param prev  前驱节点
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
     * description 构造方法初始化哨兵节点
     *
     * @return
     * @author: woldier
     * @date: 2023/6/16 10:45
     */
    public DoublyCircularLinkedListSentinel() {
        head = new Node(null, Integer.MIN_VALUE, null);
        head.next = head;
        head.prev = head;
    }

    /**
     * description 头部添加
     *
     * @param value 值
     * @return void
     * @author: woldier
     * @date: 2023/6/16 11:05
     */

    public void addFirst(int value) {
        head.value = value;//将当前哨兵变为普通节点
        head = new Node(head.prev, Integer.MIN_VALUE, head); //创建新的哨兵
        head.next.prev = head;
        head.prev.next = head;
    }

    /**
     * description 尾部添加
     *
     * @param value 值
     * @return void
     * @author: woldier
     * @date: 2023/6/16 11:05
     */
    public void addLast(int value) {
        Node tail = head.prev;
        tail = new Node(tail, value, tail.next);
        tail.prev.next = tail;
        tail.next.prev = tail;
    }

    /**
     * description 删除链表头部元素,这里需要判断
     *
     * @return void
     * @author: woldier
     * @date: 2023/6/16 11:21
     */
    public void removeFirst() {
        if (head.next == head)
            throw illegal();
        Node remove = head;

        //head 指向新的哨兵
        head = head.next;
        head.value = Integer.MIN_VALUE;
        //成环
        head.prev = remove.prev;
        head.prev.next = head;
        //help GC
        remove.prev = null;
        remove.next = null;
    }

    /**
     * description 删除队尾元素
     *
     * @return void
     * @author: woldier
     * @date: 2023/6/16 11:31
     */
    public void removeLast() {
        if (head.next == head)
            throw illegal();
        Node remove = head.prev;
//        remove.prev.next = head;
//        head.prev = remove.prev;
//        //help GC
//        remove.prev = null;
//        remove.next = null;
        removeNode(remove);
    }

    public void removeByValue(int value) {
        Node remove = findByValue(value);
        if (remove == null)
            throw new IllegalArgumentException("查找失败");
        removeNode(remove);

    }

    private static void removeNode(Node remove) {
        remove.prev.next = remove.next;
        remove.next.prev = remove.prev;
        remove.prev = remove.next = null;
    }

    public Node findByValue(int value) {
        Node p = head.next;
        while (p != head && p.value != value)
            p = p.next;
        return p != head ? p : null;

    }

    private static IllegalArgumentException illegal() {
        return new IllegalArgumentException("当前链表尾空,无法进行remove操作");
    }

    public void loop(Consumer<Integer> consumer){
        recursion(head.next,consumer);
    }

    private void recursion(Node node,Consumer<Integer> consumer){
        if(node!=head){
            consumer.accept(node.value);
            recursion(node.next,consumer);
        }
    }
}
