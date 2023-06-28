package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;

/**
 * description 双向环形链表实现双端队列
 *
 * @author: woldier
 * @date: 2023/6/28 下午7:50
 */
public class LinkedDeQueue<E> implements DeQueue<E>, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int capacity;
    private int size;

    public static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node() {
        }

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public LinkedDeQueue(int capacity) {
        this.capacity = capacity;
        head = new Node<>(); //创建哨兵节点,并初始化队列
        head.next = head;
        head.prev = head;
        tail = head;
    }

    /**
     * description 放入头部
     *
     * @param e 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    @Override
    public boolean offerFirst(E e) {
        if (isFull()) return false; //如果是满了直接返回
        if (isEmpty()) return offerLast(e); //如果是队空,调用尾插
        Node<E> p = new Node(head, e, head.next);
        p.next.prev = p;
        p.prev.next = p;
        size++;
        return false;
    }

    /**
     * description 尾部
     *
     * @param e 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    @Override
    public boolean offerLast(E e) {
        if (isFull()) return false;
        tail = new Node(tail, e, tail.next);
        tail.prev.next = tail;
        head.prev = tail;
        size++;
        return true;
    }

    /**
     * description 获取队头元素
     *
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        Node<E> p = head.next;
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.prev = null;
        p.next = null;
        if(p==tail) tail=head; // 如果当前节点就是尾节点 那么需要重新赋tail指针
        return null;
    }

    /**
     * description 获取队尾元素
     *
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        Node<E> p = tail;
        tail = tail.prev;

        p.next.prev = tail;
        tail.next = p.next;
        p.prev = null;
        p.next = null;
        return p.value;
    }

    /**
     * description 获取队头元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    @Override
    public E peekFirst() {
        if(!isEmpty()) return head.next.value;
        return null;
    }

    /**
     * description 获取队尾元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    @Override
    public E peekLast() {
        if(!isEmpty()) return tail.value;
        return null;
    }

    /**
     * description 判断是否时队空
     *
     * @return 如果队列尾空返回true, 不为空返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:43
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * description 判断是否时队满
     *
     * @return 如果队列尾空返回 false,不为空返回true
     * @author: woldier
     * @date: 2023/6/28 下午7:43
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p!=head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
