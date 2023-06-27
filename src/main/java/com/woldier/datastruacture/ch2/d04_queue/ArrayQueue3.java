package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;

/**
*
* description 数组队列实现3,无限自增指针
*
* @author: woldier
* @date: 2023/6/27 下午5:21
*/
public class ArrayQueue3<E> implements Queue<E>,Iterable<E> {
    /**
     * 数组队列 改用诬陷递增的head,与tail指针来指示
     *
     *
     *
     * <pre>
     *
     *
     *     {@code
     *
     *       0   1   2   3   4   5   6
     *      [a] [a] [a] [a] [a] [a]
     *       ↑                       ↑
     *
     *
     *       当需要取元素时 再让head与tail 取余数
     *
     *       不过需要注意的是,当head与tail自增到最大整数后,会变成负数,这时候tail%array.length 为负数,出现错误的数组指针
     *
     *     例如 0b 1 000 0000 1011 0110 对8( 0b 0 000 0000 0000 1000)取模 结果为 -6 (0b 1 000 0000 0000 0110) 出现了负数指针
     *
     *     解决方法是首先需要size大小是2的整数此幂,这样的话可以通过位操作进行优化
     *
     *     例如数组大小是8(0b 0 000 0000 0000 1000) 那么 8-1 (0b 0 000 0000 0000 0111) 此时只需要待取模的数与 (0b 0 000 0000 0000 0111) 做位与运算即可
     *     0b 1 000 0000 1011 0110 & 0b 0 000 0000 0000 011 = 0b 0 000 0000 0000 0110
     *
     *
     *
     *
     *     }
     * </pre>
     */

    private final E[] array;
    private int head=0;
    private int tail=0;

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        array =(E[]) new Object[capacity];
    }


    @Override
    public boolean offer(E e) {
        if(isFull()) return false;
        array[tail & (array.length-1)] = e;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E e = array[head & (array.length - 1)];
        head ++;
        return e;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return array[head & (array.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        return head==tail;
    }
    public boolean isFull(){
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p<tail;
            }

            @Override
            public E next() {
                E e = array[p & (array.length - 1)];
                p++;
                return e;
            }
        };
    }
}
