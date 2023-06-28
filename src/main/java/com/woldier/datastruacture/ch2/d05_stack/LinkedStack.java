package com.woldier.datastruacture.ch2.d05_stack;

import java.util.Iterator;

/**
 * description 链栈实现
 *
 * @author: woldier
 * @date: 2023/6/28 下午12:41
 */
public class LinkedStack<E> implements Stack<E>, Iterable<E> {

    private Node<E> head = new Node<>();
    private int capacity;
    private int size;

    public LinkedStack() {
        capacity = 8;
    }

    public LinkedStack(int capacity) {
        this.capacity = capacity;
    }

    /**
     * description 由于栈都是从头部弹出元素,因此我们使用带哨兵的单链表即可
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
     * @date: 2023/6/28 下午12:47
     */

    public static class Node<E> {
        E value;
        Node<E> next;

        public Node() {
        }

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new Node<>(value,head.next);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;
        Node<E> p = head.next;
        head.next = p.next;
        return p.value;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head.next==null;
    }

    @Override
    public boolean isFull() {
        return capacity==size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p!=null;
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
