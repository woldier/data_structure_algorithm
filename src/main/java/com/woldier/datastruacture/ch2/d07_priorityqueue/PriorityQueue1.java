package com.woldier.datastruacture.ch2.d07_priorityqueue;

import com.woldier.datastruacture.ch2.d04_queue.Queue;

import java.util.Iterator;

/**
 * description 优先级队列的,实现思路是 入队时与传统队列相同 直接尾部插入,
 * <p>
 * 出队时,搜索队列,找到优先级最高的,然后移动数组
 *
 * @author: woldier
 * @date: 2023/6/29 上午10:54
 */
public class PriorityQueue1<E extends Priority> implements Queue<E>, Iterable<E> {
    private Priority[] array;
    private int size;


    public PriorityQueue1(int capacity) {
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
        if (isFull()) return false;
        array[size++] = e;
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
        if (isEmpty()) return null;  //当队空返回null
        //找到当前优先级最高的
        int max = findMaxPriority();
        //判断是否是队尾,是队尾不用移动,不是队尾需要移动
        Priority priority = array[max];
        array[max] = null; //help GC
        if (max < size - 1)
            System.arraycopy(array, max + 1, array, max, size - max - 1);
        size--;
        return (E) priority;
    }

    private int findMaxPriority() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority())
                max = i;
        }
        return max;
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
        int maxPriority = findMaxPriority();

        return (E) array[maxPriority];
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

    private int inc(int value) {
        return (value + 1) % array.length;
    }

    private int dec(int value) {
        return (value - 1 + array.length) % array.length;
    }
}
