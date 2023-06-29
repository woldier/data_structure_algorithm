package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Arrays;
import java.util.Iterator;

/**
 * description 实现思想是入队时通过插入排序判断插入位置
 *
 * @author: woldier
 * @date: 2023/6/29 下午12:37
 */
public class PriorityQueue2<E extends Priority> implements Iterable<E>, Queue<E> {
    private Priority[] array;
    private int size;


    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * description 向队列尾插入值
     *
     * @param e 待插入的值
     * @return 插入成功返回true, 插入失败返回false
     * @author: woldier
     * @date: 2023/6/26 下午9:07
     */
    @Override
    public boolean offer(E e) {
        if (isFull())
            return false;
        int i;
        for (i = size - 1; i >= 0; i--) {
            if (array[i].priority() > e.priority())  //当当前指针的优先级较高,那么移动
                array[i + 1] = array[i];
            else  //当指针指向元素的优先级平级或者小那么
                break;
        }
        array[i + 1] = e;
        size++;
        return true;
    }

    /**
     * description 从队头获取值并移除
     *
     * @return 如果队列非空返回对头元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/26 下午9:10
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;
        Priority elem = array[size - 1];
        array[--size] = null;//help GC
        return (E) elem;

    }


    /**
     * description 从队头获取值不移除
     *
     * @return 如果队列非空返回对头元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/26 下午9:10
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[size - 1];
    }

    /**
     * description 判断是否队空
     *
     * @return 空返回true, 否则返回null
     * @author: woldier
     * @date: 2023/6/27 下午1:50
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                return (E) array[count++];
            }
        };
    }
}
