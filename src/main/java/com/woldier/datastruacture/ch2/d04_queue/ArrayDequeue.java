package com.woldier.datastruacture.ch2.d04_queue;

import java.util.Iterator;

/**
 * description 双端队列的链表实现
 *
 * @author: woldier
 * @date: 2023/6/28 下午9:03
 */
public class ArrayDequeue<T> implements DeQueue<T>, Iterable<T> {

    /**
     *
     * description 数组实现双端队列
     *
     *
     * <pre>
     *
     * {@code
     *      ↓tail
     *      ↓head
     *      0 1 2 3 4
     *      队空时head与tail指针指向相同
     *      offerLast(a)
     *      offerLast(b)
     *          ↓tail
     *      ↓head
     *      0 1 2 3 4
     *      a b
     *
     *      offerFirst(c)
     *          ↓tail
     *              ↓head
     *      0 1 2 3 4
     *      a b     c
     *
     *      offerLast(d)
     *            ↓tail
     *              ↓head
     *      0 1 2 3 4
     *      a b d   c
     *      队满(tail+1)%length==head
     *  }
     * </pre>
     * @author: woldier
     * @date: 2023/6/28 下午9:04
     */
    private final T[] array;
    private int head;
    private int tail;


    public ArrayDequeue(int capacity) {
        array = (T[]) new Object[capacity+1];
    }

    /**
     * description 放入头部
     *
     * @param t 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    @Override
    public boolean offerFirst(T t) {
        if (isFull()) return false;
        head = (head-1 + array.length) % array.length ;
        array[head] = t;
        return true;
    }

    /**
     * description 尾部
     *
     * @param t 待放入元素
     * @return 如果放入成功放回true, 放入失败则返回false
     * @author: woldier
     * @date: 2023/6/28 下午7:40
     */
    @Override
    public boolean offerLast(T t) {
        if (isFull()) return false;
        array[tail] = t;
        tail = (tail+1) % array.length;
        return true;
    }

    /**
     * description 获取队头元素
     * 需要额外注意的时,这里的数组存储的时引用类型 出栈后应该要把对应位置的指针引用指向null  如果是基本类型则不用考虑
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    @Override
    public T pollFirst() {
        if(isEmpty()) return null;
        T t = array[head];
        array[head] = null;  //help  GC
        head = (head+1) % array.length;
        return t;
    }

    /**
     * description 获取队尾元素
     * 需要额外注意的时,这里的数组存储的时引用类型 出栈后应该要把对应位置的指针引用指向null  如果是基本类型则不用考虑
     * @return 如果成功返回元素, 不成功返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:41
     */
    @Override
    public T pollLast() {
        if(isEmpty()) return null;
        tail = (tail-1 +array.length) % array.length;
        T t = array[tail];
        array[tail] = null; //help  GC
        return t;
    }

    /**
     * description 获取队头元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    @Override
    public T peekFirst() {
        if(isEmpty()) return null;

        return array[head];
    }

    /**
     * description 获取队尾元素,不删除
     *
     * @return 如果有返回该元素, 否则返回null
     * @author: woldier
     * @date: 2023/6/28 下午7:42
     */
    @Override
    public T peekLast() {
        if(isEmpty()) return null;

        return array[(tail-1+array.length)%array.length];
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
        return head==tail;
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
        return (tail+1)%array.length==head;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p!=tail;
            }

            @Override
            public T next() {
                T t = array[p];
                p = (p+1) % array.length;
                return t;
            }
        };
    }
}
