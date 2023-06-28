package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * description 数组队列
 *
 * @author: woldier
 * @date: 2023/6/27 下午3:31
 */
public class ArrayQueue2<E> implements Queue<E>,Iterable<E> {
    /**
     * 数组队列
        改为用size记录当前个数
     */

    private final E[] array;
    private final int length;
    private int head;
    private int tail;
    private int size;

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];
        length = capacity;
        size=0;
    }

    @Override
    public boolean offer(E e) {
        if(isFull()) return false;
        array[tail] = e;
        tail = (tail + 1) % length;
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E value = array[head];
        head = (head+1)%length;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return size==length;
    }

    public int getLength(){
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int h = head, t = tail,count=0;

            @Override
            public boolean hasNext() {
                return count<size;
            }
            @Override
            public E next() {
                E e = array[h];
                h = (h+1) % length;
                count++;
                return e;
            }
        };
    }
}
