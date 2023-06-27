package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * description 数组队列
 *
 * @author: woldier
 * @date: 2023/6/27 下午3:31
 */
public class ArrayQueue<E> implements Queue<E>, Iterable<E> {
    /**
     * 数组队列
     * <pre>
     *     {@code
     *  tail
     *  ↓
     *  0  1  2  3  4
     * [] [] [] [] []
     *  ↑
     *  head
     *
     *  为了让队数组循环使用 那么头指针和尾指针每次自增之后应该取模(模大小为数组的长度)
     *  head = (head+1)%length  tail = (tail+1)%length
     *
     *  判断队空的条件是 tial = head
     *
     *  判断队满的条件是 (tial+1)%length = head
     *
     *                  tail
     *                  ↓
     *  0   1   2   3   4
     * [a] [b] [c] [d] []
     *  ↑
     *  head
     *  因此,队列能够承载的最大元素个数是length-1
     *
     *
     *
     *     }
     * </pre>
     */

    private final E[] array;
    private final int length;
    private int head;
    private int tail;

    @SuppressWarnings("all")
    public ArrayQueue(int capacity) {
        array = (E[]) new Object[capacity+1];
        length = capacity+1;
    }

    public ArrayQueue(int length, Supplier<E[]> supplier) {
        this.length = length;
        array = supplier.get();
    }

    @Override
    public boolean offer(E e) {
        if(isFull()) return false;
        array[tail] = e;
        tail = (tail + 1) % length;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E value = array[head];
        head = (head+1)%length;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % length == head;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int h = head, t = tail;

            @Override
            public boolean hasNext() {
                return h!=tail;
            }
            @Override
            public E next() {
                return array[h++];
            }
        };
    }
}
